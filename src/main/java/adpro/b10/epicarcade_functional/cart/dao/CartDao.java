package adpro.b10.epicarcade_functional.cart.dao;

import adpro.b10.epicarcade_functional.cart.model.Cart;
import adpro.b10.epicarcade_functional.model.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {
    public List<Cart> findByUser(UserEntity user);
}
