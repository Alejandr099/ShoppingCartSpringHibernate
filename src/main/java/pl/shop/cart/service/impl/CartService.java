package pl.shop.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.model.Item;
import pl.shop.cart.model.OrderPosition;
import pl.shop.cart.service.ICartService;
import pl.shop.cart.session.SessionObject;

import javax.annotation.Resource;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    IItemDAO itemDAO;

    @Resource
    SessionObject sessionObject;
    private int ItemId;

    public void addItemToCart(int itemId) {
        Optional<Item> itemBox = this.itemDAO.getItemById(ItemId);

        if(itemBox.isEmpty()) {
            return;
        }

        Item item = itemBox.get();
        if(item.getQuantity() <= 0) {
            return;
        }

        for(OrderPosition orderPosition : this.sessionObject
                .getCart().getOrderPositions()) {
            if(orderPosition.getItem().getId() == itemId) {
                orderPosition.incrementQuantity();
                return;
            }
        }

        OrderPosition orderPosition = new OrderPosition(0, item, 1);
        this.sessionObject.getCart().getOrderPositions().add(orderPosition);
    }
}
