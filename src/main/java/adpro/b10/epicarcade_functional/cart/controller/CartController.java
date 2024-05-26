package adpro.b10.epicarcade_functional.cart.controller;

import adpro.b10.epicarcade_functional.cart.dto.CartDTO;
import adpro.b10.epicarcade_functional.cart.dto.CartItemDTO;
import adpro.b10.epicarcade_functional.cart.model.Cart;
import adpro.b10.epicarcade_functional.cart.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("add")
    public ResponseEntity<CartDTO> addItemToCart(@RequestBody CartItemDTO cartItemDTO, Authentication authentication) {
        String user = authentication.getName();
        cartService.addToCart(user, cartItemDTO);
        return ResponseEntity.ok("Item added to cart");
    }

    @PostMapping("increment")
    public ResponseEntity<CartDTO> incrementItem(@RequestParam String email, @RequestParam String itemId) {
        CartDTO response = cartService.incrementItem(email, itemId);
        return ResponseEntity.ok(response);
    }

    @PostMapping("decrement")
    public ResponseEntity<CartDTO> decrementItem(@RequestParam String email, @RequestParam String itemId) {
        CartDTO response = cartService.decrementItem(email, itemId);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("remove")
    public ResponseEntity<CartDTO> removeItem(@RequestParam String email, @RequestParam String itemId) {
        CartDTO response = cartService.deleteItem(email, itemId);
        return ResponseEntity.ok(response);
    }

    @PutMapping("update")
    public ResponseEntity<CartDTO> updateItem(@RequestParam String email, @RequestParam String itemId, @RequestParam Integer quantity) {
        CartDTO response = cartService.updateItem(email, itemId, quantity);
        return ResponseEntity.ok(response);
    }

    @GetMapping({"getCartDetails"})
    public List<Cart> getCartDetails(Authentication authentication) {
        String userEmail = authentication.getName();
        List<CartItemDTO> cartDetails = cartService.getCartDetails(username);
        Map<String, Integer> itemMap =
        return new ResponseEntity<>(
                new CartDTO(
                        userEmail,
                        cartDetails,
                        cartService.getTotalPrice(user)
                )
        )
    }
}