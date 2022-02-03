package pl.shop.cart.repo;

import java.util.List;
import org.springframework.data.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import pl.shop.cart.model.User;

@RepositoryRestResource(collectionResourceRel = "user", path = "user")
public interface UserEntity extends PagingAndSortingRepository<User, Long> {

    List<User> findByfirstName(@Param("name") String name);

}
