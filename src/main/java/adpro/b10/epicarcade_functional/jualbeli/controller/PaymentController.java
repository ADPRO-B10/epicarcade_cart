package adpro.b10.epicarcade_functional.jualbeli.controller;

import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import adpro.b10.epicarcade_functional.jualbeli.service.PaymentServiceImpl;
import adpro.b10.epicarcade_functional.jualbeli.strategy.DefaultPaymentStrategy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentServiceImpl paymentService;

    @PostMapping
    public ResponseEntity<PaymentDto> createPayment(@RequestBody PaymentDto paymentDto) {
        PaymentDto savedPaymentDto = paymentService.createPaymentWithOrder(paymentDto, null);
        return ResponseEntity.ok(savedPaymentDto);
    }

    @PutMapping("/{id}/method")
    public ResponseEntity<PaymentDto> setPaymentMethod(@PathVariable String id, @RequestBody PaymentMethod method) {
        Optional<PaymentDto> paymentDto = paymentService.setPaymentMethod(paymentService.getPaymentById(id).get(), method);
        return paymentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentDto> getPaymentById(@PathVariable String id) {
        Optional<PaymentDto> paymentDto = paymentService.getPaymentById(id);
        return paymentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePayment(@PathVariable String id) {
        paymentService.deletePayment(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<PaymentDto> updatePaymentStatus(@PathVariable String id, @RequestBody PaymentStatus status) {
        Optional<PaymentDto> paymentDto = paymentService.updatePaymentStatus(id, status);
        return paymentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentDto> getPaymentByOrderId(@PathVariable String orderId) {
        Optional<PaymentDto> paymentDto = paymentService.getPaymentByOrderId(orderId);
        return paymentDto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<PaymentStatus> processPayment(@PathVariable String id) {
        Optional<PaymentStatus> paymentStatus = paymentService.processPayment(paymentService.getPaymentById(id).get());
        return paymentStatus.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}