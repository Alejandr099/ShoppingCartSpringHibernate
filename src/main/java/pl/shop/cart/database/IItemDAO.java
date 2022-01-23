package pl.shop.cart.database;


import pl.shop.cart.model.Item;

import java.util.List;
import java.util.Optional;

public interface IItemDAO {
    List<Item> getItems();
    Optional<Item> getItemById(int itemId);
    void updateItem(Item item);
}
