package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order createOrder(Order order) {
        if (orderRepository.findById(order.getId()) == null) {
            orderRepository.save(order);
            return order;
        }
        return null;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = orderRepository.findById(orderId);
        if (order != null) {
            Order newOrder = new Order(order.getId(), order.getGamesQuantity(), order.getBuyerId(),
                    status);
            orderRepository.save(newOrder);
            return newOrder;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Order> findAllByBuyer(String buyer) {
        return orderRepository.findAllByAuthor(buyer);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findById(orderId);
    }
}