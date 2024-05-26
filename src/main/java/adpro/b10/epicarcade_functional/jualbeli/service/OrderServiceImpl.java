package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderDto;
import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.mapper.OrderMapper;
import adpro.b10.epicarcade_functional.jualbeli.mapper.PaymentMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private PaymentMapper paymentMapper;
    
    @Autowired
    private PaymentService paymentService;

    @Async
    @Override
    public CompletableFuture<Optional<OrderDto>> createOrderFromCart() {
        // Change URL based on cart service
        ResponseEntity<Map> response = restTemplate.getForEntity("http://34.34.219.228/api/cart/getCartDetails", Map.class);
        Map<String, Object> cartDTO = response.getBody();

        if (cartDTO == null) {
            return CompletableFuture.completedFuture(Optional.empty());
        }

        Order order = new Order();
        order.setOrderGames(new ArrayList<>());

        List<Map<String, Object>> items = (List<Map<String, Object>>) cartDTO.get("items");
        BigDecimal totalPrice = BigDecimal.ZERO;
        for (Map<String, Object> cartItemDTO : items) {
            OrderGame orderGame = new OrderGame();
            orderGame.setOrder(order);
            orderGame.setGameId((String) cartItemDTO.get("gameId"));
            orderGame.setQuantity((Integer) cartItemDTO.get("quantity"));
            order.getOrderGames().add(orderGame);

            BigDecimal price = new BigDecimal((String) cartItemDTO.get("price"));
            totalPrice = totalPrice.add(price.multiply(BigDecimal.valueOf(orderGame.getQuantity())));
        }

        order.setBuyerId((String) cartDTO.get("buyerId"));
        order.setTotalPrice(totalPrice);
        order.setOrderDate(LocalDateTime.now());

        PaymentDto paymentDto = new PaymentDto();
        paymentDto.setMethod(PaymentMethod.DEFAULT); 
        paymentDto.setStatus(PaymentStatus.PENDING);
        paymentDto.setAmount(totalPrice);
        PaymentDto savedPaymentDto = paymentService.createPaymentWithOrder(paymentDto, order);

        order.setPayment(paymentMapper.paymentDtoToPayment(savedPaymentDto));
        orderRepository.save(order);

        OrderDto orderDto = orderMapper.orderToOrderDto(order);
        return CompletableFuture.completedFuture(Optional.ofNullable(orderDto));
    }

    @Override
    public OrderDto saveOrder(OrderDto orderDTO) {
        System.out.println(orderDTO);
        if (orderDTO == null) {
            throw new IllegalArgumentException("OrderDto cannot be null");
        }
        Order order = orderMapper.orderDtoToOrder(orderDTO);
        if (order == null) {
            throw new IllegalArgumentException("OrderDto cannot be null");
        }
        Order savedOrder = orderRepository.save(order);
        return orderMapper.orderToOrderDto(savedOrder);
    }

    @Override
    public Optional<OrderDto> getOrderByBuyerId(String buyerId) {
        return Optional.of(orderRepository.findByBuyerId(buyerId)
                              .map(orderMapper::orderToOrderDto).orElse(null));
    }

    @Override
    public Optional<OrderDto> getOrderById(String id) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (!orderOptional.isPresent()) {
            return Optional.empty();
        }
        OrderDto orderDto = orderMapper.orderToOrderDto(orderOptional.get());
        if (orderDto == null) {
            throw new IllegalStateException("OrderMapper failed to map Order to OrderDto");
        }
        return Optional.of(orderDto);
    }

    @Override
    public void deleteOrder(String id) {
        orderRepository.deleteById(id);
    }

    @Override
    public Optional<OrderDto> updateOrderStatus(String id, OrderStatus status) {
        Optional<Order> orderOptional = orderRepository.findById(id);
        if (orderOptional.isPresent()) {
            Order order = orderOptional.get();
            order.setStatus(status);
            orderRepository.save(order);
            return Optional.of(orderMapper.orderToOrderDto(order));
        }
        return Optional.empty();
    }
}