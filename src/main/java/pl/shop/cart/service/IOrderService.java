package pl.shop.cart.service;

import pl.shop.cart.model.Order;

import java.util.List;

public interface IOrderService {
    void confirmOrder();
    List<Order> getOrdersForCurrentUser();
}
