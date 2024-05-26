package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.mapper.PaymentMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;
import adpro.b10.epicarcade_functional.jualbeli.service.PaymentServiceImpl;
import adpro.b10.epicarcade_functional.jualbeli.strategy.DefaultPaymentStrategy;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentMapper paymentMapper;

    @Mock
    private DefaultPaymentStrategy defaultPaymentStrategy;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePaymentWithOrder() {
        PaymentDto paymentDto = new PaymentDto();
        Order order = new Order();
        Payment payment = new Payment();
        when(paymentMapper.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(payment);
        when(paymentMapper.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        PaymentDto result = paymentService.createPaymentWithOrder(paymentDto, order);

        assertEquals(paymentDto, result);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testSetPaymentMethod() {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = new Payment();
        when(paymentMapper.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(payment);
        when(paymentMapper.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        Optional<PaymentDto> result = paymentService.setPaymentMethod(paymentDto, PaymentMethod.DEFAULT);

        assertEquals(Optional.of(paymentDto), result);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testSavePayment() {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = new Payment();
        when(paymentMapper.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(paymentRepository.save(payment)).thenReturn(payment);
        when(paymentMapper.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        Optional<PaymentDto> result = paymentService.savePayment(paymentDto);

        assertEquals(Optional.of(paymentDto), result);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testGetPaymentById() {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = new Payment();
        when(paymentRepository.findById("1")).thenReturn(Optional.of(payment));
        when(paymentMapper.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        Optional<PaymentDto> result = paymentService.getPaymentById("1");

        assertEquals(Optional.of(paymentDto), result);
    }

    @Test
    public void testDeletePayment() {
        paymentService.deletePayment("1");
        verify(paymentRepository, times(1)).deleteById("1");
    }

    @Test
    public void testUpdatePaymentStatus() {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = new Payment();
        when(paymentRepository.findById("1")).thenReturn(Optional.of(payment));
        when(paymentMapper.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        Optional<PaymentDto> result = paymentService.updatePaymentStatus("1", PaymentStatus.SUCCESS);

        assertEquals(Optional.of(paymentDto), result);
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    public void testGetPaymentByOrderId() {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = new Payment();
        when(paymentRepository.findByOrderId("1")).thenReturn(Optional.of(payment));
        when(paymentMapper.paymentToPaymentDto(payment)).thenReturn(paymentDto);

        Optional<PaymentDto> result = paymentService.getPaymentByOrderId("1");

        assertEquals(Optional.of(paymentDto), result);
    }

    @Test
    public void testProcessPayment() {
        PaymentDto paymentDto = new PaymentDto();
        Payment payment = new Payment();
        payment.setMethod(PaymentMethod.DEFAULT);
        when(paymentMapper.paymentDtoToPayment(paymentDto)).thenReturn(payment);
        when(defaultPaymentStrategy.processPayment(payment)).thenReturn(Optional.of(PaymentStatus.SUCCESS));

        Optional<PaymentStatus> result = paymentService.processPayment(paymentDto);

        assertEquals(Optional.of(PaymentStatus.SUCCESS), result);
    }
}