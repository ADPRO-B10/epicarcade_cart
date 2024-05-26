package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.mapper.PaymentMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;
import adpro.b10.epicarcade_functional.jualbeli.strategy.DefaultPaymentStrategy;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private Map<PaymentMethod, PaymentStrategy> paymentStrategies;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
        paymentStrategies = new HashMap<>();
        paymentStrategies.put(PaymentMethod.DEFAULT, new DefaultPaymentStrategy());
    }

    @Override
    public PaymentDto createPaymentWithOrder(PaymentDto paymentDto, Order order) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDto);
        payment.setOrder(order);
        Payment savedPayment = paymentRepository.save(payment);
        return paymentMapper.paymentToPaymentDto(savedPayment);
    }

    @Override
    public Optional<PaymentDto> setPaymentMethod(PaymentDto paymentDto, PaymentMethod method) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDto);
        payment.setMethod(method);
        Payment savedPayment = paymentRepository.save(payment);
        return Optional.of(paymentMapper.paymentToPaymentDto(savedPayment));
    }

    @Override
    public Optional<PaymentDto> savePayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDto);
        Payment savedPayment = paymentRepository.save(payment);
        return Optional.of(paymentMapper.paymentToPaymentDto(savedPayment));
    }

    @Override
    public Optional<PaymentDto> getPaymentById(String id) {
        Optional<Payment> payment = paymentRepository.findById(id);
        return Optional.of(payment.map(paymentMapper::paymentToPaymentDto).orElse(null));
    }

    @Override
    public void deletePayment(String id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Optional<PaymentDto> updatePaymentStatus(String id, PaymentStatus status) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            payment.get().setStatus(status);
            paymentRepository.save(payment.get());
        }
        return Optional.of(payment.map(paymentMapper::paymentToPaymentDto).orElse(null));
    }

    @Override
    public Optional<PaymentDto> getPaymentByOrderId(String orderId) {
        Optional<Payment> payment = paymentRepository.findByOrderId(orderId);
        return Optional.of(payment.map(paymentMapper::paymentToPaymentDto).orElse(null));
    }

    @Override
    public Optional<PaymentStatus> processPayment(PaymentDto paymentDto) {
        Payment payment = paymentMapper.paymentDtoToPayment(paymentDto);
        PaymentMethod method = payment.getMethod();
        PaymentStrategy strategy = paymentStrategies.get(method);
        return Optional.of(strategy.processPayment(payment).orElse(null));
    }
}