package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.repository.PaymentRepository;
import adpro.b10.epicarcade_functional.jualbeli.strategy.PaymentStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Payment createPayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Payment updatePaymentMethod(String paymentId, String newMethod) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            payment.setMethod(newMethod);
            paymentRepository.save(payment);
        } else {
            throw new NoSuchElementException("No payment found with id: " + paymentId);
        }
        return payment;
    }

    @Override
    public Payment getPaymentByOrderId(String orderId) {
        return paymentRepository.findByOrder_Id(orderId);
    }

    @Override
    public void applyPaymentStrategy(String paymentId, PaymentStrategy strategy) {
        Payment payment = paymentRepository.findById(paymentId).orElse(null);
        if (payment != null) {
            strategy.pay(payment.getAmount());
            payment.setStatus(PaymentStatus.SUCCESS.getValue());
            paymentRepository.save(payment);
        } else {
            throw new NoSuchElementException("No payment found with id: " + paymentId);
        }
    }
}