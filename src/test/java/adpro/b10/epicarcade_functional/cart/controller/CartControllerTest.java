package adpro.b10.epicarcade_functional.cart.controller;

import adpro.b10.epicarcade_functional.cart.dto.CartDTO;
import adpro.b10.epicarcade_functional.cart.dto.CartItemDTO;
import adpro.b10.epicarcade_functional.cart.service.CartService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

class CartControllerTest {

    @Mock
    private CartService cartService;

    @InjectMocks
    private CartController cartController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddItemToCart_Success() {
        CartItemDTO cartItemDTO = new CartItemDTO("gameId", 2);
        ResponseEntity<String> expectedResponse = ResponseEntity.ok("Item added to cart");

        when(cartService.addToCart(anyString(), anyString(), anyInt())).thenReturn(null);

        ResponseEntity<String> response = cartController.addItemToCart("test@example.com", cartItemDTO);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void testAddItemToCart_Error() {
        CartItemDTO cartItemDTO = new CartItemDTO("gameId", 2);
        String errorMessage = "The game is out of stock or does not exist.";
        ResponseEntity<String> expectedResponse = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);

        when(cartService.addToCart(anyString(), anyString(), anyInt())).thenThrow(new IllegalArgumentException(errorMessage));

        ResponseEntity<String> response = cartController.addItemToCart("test@example.com", cartItemDTO);

        assertEquals(expectedResponse.getStatusCode(), response.getStatusCode());
        assertEquals(expectedResponse.getBody(), response.getBody());
    }

    @Test
    void testGetCartDetails() {
        String userEmail = "test@example.com";
        Map<String, Integer> itemMap = new HashMap<>();
        itemMap.put("gameId", 2);
        double totalPrice = 50.0;
        CartDTO expectedCartDTO = new CartDTO(userEmail, itemMap, totalPrice);

        when(cartService.getCartDetails(anyString())).thenReturn(itemMap);
        when(cartService.getTotalPrice(any())).thenReturn(totalPrice);

        ResponseEntity<CartDTO> response = cartController.getCartDetails(userEmail);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(expectedCartDTO, response.getBody());
    }
}
