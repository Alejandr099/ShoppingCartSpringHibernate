package pl.shop.cart.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.shop.cart.database.IItemDAO;
import pl.shop.cart.model.Item;
import pl.shop.cart.service.IItemService;

import java.util.List;

@Service
public class ItemService implements IItemService {

    @Autowired
    IItemDAO itemDAO;

    public List<Item> getAllItems() {
        return this.itemDAO.getItems();
    }
}
