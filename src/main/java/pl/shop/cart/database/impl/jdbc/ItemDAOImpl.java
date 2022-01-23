package pl.shop.cart.database.impl.jdbc;

import org.springframework.beans.factory.annotation.Autowired;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.model.Item;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ItemDAOImpl implements IItemDAO {

    @Autowired
    Connection connection;

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try {
            String sql = "SELECT * FROM titem";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Item item = new Item();
                item.setId(rs.getInt("id"));
                item.setItemName(rs.getString("name"));
                item.setPrice(rs.getDouble("price"));
                item.setQuantity(rs.getInt("quantity"));

                items.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return items;
    }

    @Override
    public Optional<Item> getItemById(int itemId) {
        try {
            String sql = "SELECT * FROM titem WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setInt(1, itemId);

            ResultSet resultSet = preparedStatement.executeQuery();

            if(resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setItemName(resultSet.getString("name"));
                item.setPrice(resultSet.getDouble("price"));
                item.setQuantity(resultSet.getInt("quantity"));

                return Optional.of(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return Optional.empty();
    }

    @Override
    public void updateItem(Item item) {
        try {
            String sql = "UPDATE titem SET itemName = ?, price = ?, quantity = ? WHERE id = ?";

            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, item.getItemName());
            preparedStatement.setDouble(2, item.getPrice());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.setInt(4, item.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
