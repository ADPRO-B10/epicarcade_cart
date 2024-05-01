package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;



    @Override
    public Order createOrder(Order order) {
        if (orderRepository.findById(order.getId()).orElse(null) == null) {
            orderRepository.save(order);

            Payment payment = new Payment();
            payment.setOrder(order);
            payment.setMethod(PaymentMethod.DEFAULT.getValue());
            payment.setStatus(PaymentMethod.PENDING.getValue()); 
            paymentService.createPayment(payment);

            return order;
        }
        return null;
    }

    @Override
    public Order updateStatus(String orderId, String status) {
        Order order = findById(orderId);
        if (order != null) {
            order.setStatus(status);
            orderRepository.save(order);
            return order;
        } else {
            throw new NoSuchElementException();
        }
    }

    @Override
    public List<Order> getOrdersByBuyerId(String buyerId) {
        return orderRepository.findAllByBuyerId(buyerId);
    }

    @Override
    public Order findById(String orderId) {
        return orderRepository.findById(orderId).orElse(null);
    }
}