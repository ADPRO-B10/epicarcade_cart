package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class OrderServiceImplTest {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateOrder() {
        Order order = new Order("1", null, "buyer1");
        when(orderRepository.findById("1")).thenReturn(null);
        when(orderRepository.save(order)).thenReturn(order);

        Order result = orderService.createOrder(order);

        assertEquals(order, result);
        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateStatus() {
        Order order = new Order("1", null, "buyer1");
        when(orderRepository.findById("1")).thenReturn(order);

        Order updatedOrder = orderService.updateStatus("1", "SUCCESS");

        assertEquals("SUCCESS", updatedOrder.getStatus());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    public void testUpdateStatusOrderNotFound() {
        when(orderRepository.findById("1")).thenReturn(null);

        assertThrows(NoSuchElementException.class, () -> {
            orderService.updateStatus("1", "SUCCESS");
        });
    }

    @Test
    public void testFindAllByBuyer() {
        List<Order> orders = Arrays.asList(new Order("1", null, "buyer1"));
        when(orderRepository.findAllByAuthor("buyer1")).thenReturn(orders);

        List<Order> result = orderService.findAllByBuyer("buyer1");

        assertEquals(orders, result);
    }

    @Test
    public void testFindById() {
        Order order = new Order("1", null, "buyer1");
        when(orderRepository.findById("1")).thenReturn(order);

        Order result = orderService.findById("1");

        assertEquals(order, result);
    }
}