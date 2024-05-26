package adpro.b10.epicarcade_functional.cart.repository;

import adpro.b10.epicarcade_functional.cart.model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{
    static Cart findByUserEmail(String email);
}
