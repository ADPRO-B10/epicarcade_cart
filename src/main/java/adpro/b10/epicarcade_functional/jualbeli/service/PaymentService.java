package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;

import java.util.Optional;

public interface PaymentService {
    PaymentDto createPaymentWithOrder(PaymentDto paymentDto, Order order);
    Optional<PaymentDto> savePayment(PaymentDto payment);
    Optional<PaymentDto> setPaymentMethod(PaymentDto payment, PaymentMethod method);
    Optional<PaymentDto> getPaymentById(String id);
    void deletePayment(String id);
    Optional<PaymentDto> updatePaymentStatus(String id, PaymentStatus status);
    Optional<PaymentDto> getPaymentByOrderId(String orderId);
    Optional<PaymentStatus> processPayment(PaymentDto paymentDto);
}