package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Optional<Payment> savePayment(Payment payment, PaymentMethod method) {
        payment.setMethod(method);
        return Optional.of(paymentRepository.save(payment));
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public void deletePayment(Long id) {
        paymentRepository.deleteById(id);
    }

    @Override
    public Optional<Payment> updatePaymentStatus(Long id, PaymentStatus status) {
        Optional<Payment> payment = paymentRepository.findById(id);
        if (payment.isPresent()) {
            payment.get().setStatus(status);
            paymentRepository.save(payment.get());
        }
        return payment;
    }

    @Override
    public Optional<Payment> getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrder_Id(orderId);
    }

    @Override
    public Optional<Payment> applyPaymentStrategy(Long paymentId, PaymentStrategy strategy) {
        Optional<Payment> payment = paymentRepository.findById(paymentId);
        if (payment.isPresent()) {
            strategy.apply(payment.get());
            paymentRepository.save(payment.get());
        }
        return payment;
    }
}