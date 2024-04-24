package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Order;

import java.util.List;

public interface OrderService {
    public Order createOrder(Order order);

    public Order updateStatus(String orderId, String status);

    public Order findById(String id);

    public List<Order> findAllByBuyer(String buyerId);
}