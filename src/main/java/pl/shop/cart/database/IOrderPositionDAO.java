package pl.shop.cart.database;

import pl.shop.cart.model.OrderPosition;
import java.util.List;

public interface IOrderPositionDAO {
    void addOrderPosition(OrderPosition orderPosition, int orderId);
    List<OrderPosition> getOrderPositionsByOrderId(int orderId);
}
