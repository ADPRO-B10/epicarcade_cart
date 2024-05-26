package adpro.b10.epicarcade_functional.cart.service;

import adpro.b10.epicarcade_functional.cart.dto.CartDTO;
import adpro.b10.epicarcade_functional.cart.dto.CartItemDTO;
import adpro.b10.epicarcade_functional.cart.model.CartItem;
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

    @Override
    public Cart addToCart(String email, String itemId, Integer quantity) {
        Cart cart = shoppingCartRepository.findByUserEmail(email);

        if (cart == null) {
            cart = new Cart();
            cart.setUserEmail(email);
        }

        List<CartItem> items = cart.getItems();
        boolean itemFound = false;

        //Check if already added to cart
        for (CartItem item : items) {
            if (item.getProductId().equals(itemId)) {
                item.setQuantity(item.getQuantity() + quantity);
                itemFound = true;
                break;
            }
        }

        //Item not found
        if (!itemFound) {
            CartItem newItem = new CartItem();
            newItem.setProductId(itemId);
            newItem.setQuantity(quantity);
            newItem.setCart(cart);
            items.add(newItem);
        }

        cart.setItems(items);
        return shoppingCartRepository.save(cart);
    }

    @Override
    public void removeFromCart(String email, CartItemDTO cartItemDTO) {
        Cart cart = shoppingCartRepository.findByUserEmail(email);

        if (cart != null) {
            List<CartItem> items = cart.getItems();
            items.removeIf(item -> item.getProductId().equals(cartItemDTO.getGameId()));
            cart.setItems(items);
            shoppingCartRepository.save(cart);
        }
    }

    @Override
    public Cart incrementProductQuantity(String username, Integer productId) {
        // Increment product quantity in the cart
    }

    @Override
    public Cart decrementProductQuantity(String username, Integer productId) {
        // Decrement product quantity in the cart
    }

    @Override
    public void checkout(String username) {
        // Perform checkout operation
    }

    @Override
    public Map<String, Integer> getCartDetails(String userEmail) {
        Cart cart = shoppingCartRepository.findByUserEmail(userEmail);
        Map<String, Integer> cartDetails = new HashMap<>();

        if (cart != null) {
            List<CartItem> cartItems = cart.getItems();
            for (CartItem item : cartItems) {
                cartDetails.put(item.getProductId(), item.getQuantity());
            }
        }

        return cartDetails;
    }
}
