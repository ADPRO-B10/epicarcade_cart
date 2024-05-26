package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.cart.dto.CartDTO;
import adpro.b10.epicarcade_functional.cart.repository.CartDao;
import adpro.b10.epicarcade_functional.cart.repository.CartRepository;
import adpro.b10.epicarcade_functional.cart.model.Cart;
import adpro.b10.epicarcade_functional.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService{

    @Autowired
    private CartRepository shoppingCartRepository;

    public Cart addToCart(String email, String itemId, Integer quantity) {
        Map<String, String> response = new HashMap<>();
        Cart cart = CartRepository.findByUserEmail(email);

        if (cart == null) {
            cart = new Cart();
            shoppingCartRepository.save(cart);
        }

        cart.getItems().merge(itemId, quantity, Integer::sum);

        return new CartDTO(
                cart.getEmail(),
                cart.getItems(),
                cart.getTotalPrice());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    public void removeFromCart(String username, CartItemDTO cartItemDTO) {
        Optional<Cart> cartOpt = cartRepository.findByUsername(username);
        cartOpt.ifPresent(cart -> {
            cart.removeItem(cartItemDTO.toEntity());
            cartRepository.save(cart);
        });
    }

    public Cart incrementProductQuantity(String username, Integer productId) {
        // Increment product quantity in the cart
    }

    public Cart decrementProductQuantity(String username, Integer productId) {
        // Decrement product quantity in the cart
    }

    public void checkout(String username) {
        // Perform checkout operation
    }

    public List<Cart> getCartDetails() {
        String username = JwtRequestFilter.CURRENT_USER;
        UserEntity user = userDao.findById(username).get();
        return cartDao.findByUser(user);
    }
}
