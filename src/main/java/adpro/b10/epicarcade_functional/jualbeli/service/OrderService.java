package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;

import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

public interface OrderService {
    CompletableFuture<Optional<OrderDto>> createOrderFromCart();
    OrderDto saveOrder(OrderDto orderDto);
    Optional<OrderDto> getOrderByBuyerId(String buyerId);
    Optional<OrderDto> getOrderById(String buyerId);
    void deleteOrder(String id);
    Optional<OrderDto> updateOrderStatus(String id, OrderStatus status);
}