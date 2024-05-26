package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderDto;
import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.mapper.OrderMapper;
import adpro.b10.epicarcade_functional.jualbeli.mapper.PaymentMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import adpro.b10.epicarcade_functional.jualbeli.service.OrderServiceImpl;
import adpro.b10.epicarcade_functional.jualbeli.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceImplTest {

    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private OrderMapper orderMapper;

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private PaymentService paymentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrderFromCart() throws ExecutionException, InterruptedException {
        // Arrange
        String cartId = "cartId";
        OrderDto orderDto = new OrderDto();
        orderDto.setBuyerId("buyerId");
        orderDto.setTotalPrice(BigDecimal.TEN);
        orderDto.setOrderGames(Collections.emptyList());

        ResponseEntity<Map> mockResponse = new ResponseEntity<>(
                Map.of(
                        "buyerId", "buyerId",
                        "items", List.of(
                                Map.of(
                                        "gameId", "game1",
                                        "quantity", 1,
                                        "price", "10.00"
                                )
                        )
                ),
                HttpStatus.OK
        );


        PaymentDto mockPaymentDto = new PaymentDto();
        mockPaymentDto.setMethod(PaymentMethod.DEFAULT);
        mockPaymentDto.setStatus(PaymentStatus.PENDING);
        mockPaymentDto.setAmount(BigDecimal.TEN);

        OrderDto mockOrderDto = new OrderDto();

        when(restTemplate.getForEntity(anyString(), eq(Map.class))).thenReturn(mockResponse);
        when(paymentService.createPaymentWithOrder(any(PaymentDto.class), any(Order.class))).thenReturn(mockPaymentDto);
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(orderDto);
        CompletableFuture<Optional<OrderDto>> completedFuture = CompletableFuture.completedFuture(Optional.of(mockOrderDto));
        when(orderService.createOrderFromCart()).thenReturn(completedFuture);

        // Act
        CompletableFuture<Optional<OrderDto>> futureResult = orderService.createOrderFromCart();
        Optional<OrderDto> result = futureResult.get();

        // Assert
        assertTrue(result.isPresent());
        assertEquals(orderDto, result.get());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testSaveOrder() {
        // Arrange
        OrderDto orderDto = new OrderDto();
        when(orderMapper.orderDtoToOrder(any(OrderDto.class))).thenReturn(new Order());
        when(orderRepository.save(any(Order.class))).thenReturn(new Order());
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(orderDto);

        // Act
        OrderDto result = orderService.saveOrder(orderDto);

        // Assert
        assertEquals(orderDto, result);
    }

    @Test
    public void testGetOrderByBuyerId() {
        // Arrange
        String buyerId = "buyerId";
        when(orderRepository.findByBuyerId(anyString())).thenReturn(Optional.of(new Order()));
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(new OrderDto());

        // Act
        Optional<OrderDto> result = orderService.getOrderByBuyerId(buyerId);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void testGetOrderById() {
        // Arrange
        String id = "id";
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(new Order()));
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(new OrderDto());

        // Act
        Optional<OrderDto> result = orderService.getOrderById(id);

        // Assert
        assertTrue(result.isPresent());
    }

    @Test
    public void testDeleteOrder() {
        // Arrange
        String id = "id";

        // Act
        orderService.deleteOrder(id);

        // Assert
        verify(orderRepository, times(1)).deleteById(id);
    }

    @Test
    public void testUpdateOrderStatus() {
        // Arrange
        String id = "id";
        OrderStatus status = OrderStatus.WAITING_PAYMENT;
        when(orderRepository.findById(anyString())).thenReturn(Optional.of(new Order()));
        when(orderMapper.orderToOrderDto(any(Order.class))).thenReturn(new OrderDto());

        // Act
        Optional<OrderDto> result = orderService.updateOrderStatus(id, status);

        // Assert
        assertTrue(result.isPresent());
        verify(orderRepository, times(1)).save(any(Order.class));
    }
}