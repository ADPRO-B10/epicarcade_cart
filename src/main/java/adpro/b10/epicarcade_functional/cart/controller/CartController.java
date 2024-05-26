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

@RestController
@RequestMapping("/api/cart")
class CartController {

    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<CartDTO> addItemToCart(@RequestBody CartItemDTO cartItemDTO, Authentication authentication) {
        String user = authentication.getName();
        cartService.addToCart(user, cartItemDTO);
        return ResponseEntity.ok("Item added to cart");
    }

    @PreAuthorize("hasRole('BUYER')")
    @GetMapping("/addToCart/{productId}")
    public Cart addProductToCart(@PathVariable(name = "productId") Integer productId) {
        String username = userDetails.getUsername();
        Cart updatedCart = cartService.addToCart(username, productId);
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasRole('BUYER')")
    @GetMapping({"/getCartDetails"})
    public List<Cart> getCartDetails(Authentication authentication) {
        String username = userDetails.getUsername();
        List<Cart> cartDetails = cartService.getCartDetails(username);
        return cartService.getCartDetails();
    }
}