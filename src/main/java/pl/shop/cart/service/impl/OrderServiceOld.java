package pl.shop.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.database.IOrderDAO;
import pl.shop.cart.model.Item;
import pl.shop.cart.model.Order;
import pl.shop.cart.model.OrderPosition;
import pl.shop.cart.service.IOrderService;
import pl.shop.cart.session.SessionObject;

import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import java.util.Optional;

public class OrderServiceOld implements IOrderService {

    @Resource
    SessionObject sessionObject;

    @Autowired
    IOrderDAO orderDAO;

    @Autowired
    IItemDAO itemDAO;

    @Override
    public void confirmOrder() {
        Order order = new Order(this.sessionObject.getUser(), new HashSet<>(this.sessionObject.getCart().getOrderPositions()));
        this.orderDAO.addOrder(order);
        for (OrderPosition orderPosition : order.getOrderPositions()) {
            Optional<Item> itemBox = itemDAO.getItemById(orderPosition.getItem().getId());
            if(itemBox.isPresent()) {
                itemBox.get().setQuantity(itemBox.get().getQuantity() - orderPosition.getQuantity());
            }
        }
        this.sessionObject.getCart().clearOrderPositions();
    }

    @Override
    public List<Order> getOrdersForCurrentUser() {
        return this.orderDAO.getOrdersByUserId(this.sessionObject.getUser().getId());
    }
}
