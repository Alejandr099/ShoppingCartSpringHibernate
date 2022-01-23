package pl.shop.cart.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.database.IOrderPositionDAO;
import pl.shop.cart.model.OrderPosition;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderPositionDAOImpl implements IOrderPositionDAO {

    @Autowired
    Connection connection;

    @Autowired
    IItemDAO itemDAO;

    @Override
    public void addOrderPosition(OrderPosition orderPosition, int orderId) {
        try {
            String sql = "INSERT INTO torderposition VALUES (?, ?, ?, ?)";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setNull(1, Types.INTEGER);
            preparedStatement.setInt(2, orderId);
            preparedStatement.setInt(3, orderPosition.getItem().getId());
            preparedStatement.setInt(4, orderPosition.getQuantity());

            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if(rs.next()) {
                orderPosition.setId(rs.getInt(1));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public List<OrderPosition> getOrderPositionsByOrderId(int orderId) {
        List<OrderPosition> result = new ArrayList<>();
        try {
            String sql = "SELECT * FROM torderposition WHERE order_id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, orderId);

            ResultSet rs = preparedStatement.executeQuery();

            while(rs.next()) {
                OrderPosition orderPosition = new OrderPosition();
                orderPosition.setId(rs.getInt("id"));
                orderPosition.setQuantity(rs.getInt("quantity"));
                orderPosition.setItem(this.itemDAO.getItemById(rs.getInt("item_id")).get());

                result.add(orderPosition);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return result;
    }
}
