package adpro.b10.epicarcade_functional.cart.dao;

import adpro.b10.epicarcade_functional.cart.model.Cart;
import org.springframework.data.repository.CrudRepository;

public interface CartDao extends CrudRepository<Integer, Cart> {

}
