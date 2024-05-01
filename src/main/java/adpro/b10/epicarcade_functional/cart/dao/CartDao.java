package adpro.b10.epicarcade_functional.cart.dao;

import adpro.b10.epicarcade_functional.cart.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartDao extends CrudRepository<Cart, Integer> {

}
