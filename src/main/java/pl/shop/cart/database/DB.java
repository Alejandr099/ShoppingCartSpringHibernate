package pl.shop.cart.database;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Component;
import pl.shop.cart.model.Item;
import pl.shop.cart.model.Order;
import pl.shop.cart.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DB {
    private List<Item> items = new ArrayList<>();
    private List<User> users = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();

    public DB() {
        this.items.add(new Item(1, "Acer Predator 21X",12000.00,12));
        this.items.add(new Item(2,"IPhone 8",1337.00,5));
        this.items.add(new Item(3,"Rolex Submarine",79000.00,2));

        this.users.add(new User(1, "Aleksander", "Wozniczka", "admin", DigestUtils.md5Hex("admin")));
        this.users.add(new User(2, "Uzytkownik", "Testowy", "user", DigestUtils.md5Hex("user")));
    }

    public List<Item> getItems() {
        return items;
    }

    public Optional<User> getUserByLogin(String login) {
        for(User user : this.users) {
            if(user.getLogin().equals(login)) {
                return Optional.of(user);
            }
        }

        return Optional.empty();
    }

    public Optional<Item> getItemById(int itemId) {
        for(Item item : this.items) {
            if(item.getId() == itemId) {
                return Optional.of(item);
            }
        }

        return Optional.empty();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public List<Order> getOrdersByUserId(int userId) {
        List<Order> result = new ArrayList<>();
        for(Order order : this.orders) {
            if(order.getUser().getId() == userId) {
                result.add(order);
            }
        }

        return result;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}
