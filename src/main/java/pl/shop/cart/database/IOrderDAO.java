package pl.shop.cart.database;

import pl.shop.cart.model.Order;

import java.util.List;

public interface IOrderDAO {
    void addOrder(Order order);
    List<Order> getOrdersByUserId(int userId);
}
