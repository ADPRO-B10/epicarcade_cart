package adpro.b10.epicarcade_functional.jualbeli.controller;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/cart/{cartId}")
    public CompletableFuture<ResponseEntity<OrderDto>> createOrderFromCart(@PathVariable String cartId) {
        return orderService.createOrderFromCart(cartId)
                           .thenApply(orderDto -> orderDto.map(ResponseEntity::ok)
                                                          .orElseGet(() -> ResponseEntity.notFound().build()));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.saveOrder(orderDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable String id) {
        return orderService.getOrderById(id)
                           .map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{buyerId}")
    public ResponseEntity<OrderDto> getOrderByBuyerId(@PathVariable String buyerId) {
        return orderService.getOrderByBuyerId(buyerId)
                           .map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable String id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<OrderDto> updateOrderStatus(@PathVariable String id, @PathVariable OrderStatus status) {
        return orderService.updateOrderStatus(id, status)
                           .map(ResponseEntity::ok)
                           .orElseGet(() -> ResponseEntity.notFound().build());
    }
}