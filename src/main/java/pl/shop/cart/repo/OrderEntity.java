package pl.shop.cart.repo;

import pl.shop.cart.model.Order;
import java.util.List;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.shop.cart.model.User;

@RepositoryRestResource(collectionResourceRel = "order", path = "order")

public interface OrderEntity  extends PagingAndSortingRepository<Order, Integer> {

    List<Order> findByOrderPositions(@Param("ID") int id);

}