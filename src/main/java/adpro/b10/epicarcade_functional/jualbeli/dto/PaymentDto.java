package adpro.b10.epicarcade_functional.jualbeli.dto;

import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentMethod;
import adpro.b10.epicarcade_functional.jualbeli.enums.PaymentStatus;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentDto {
    private String id;
    private PaymentMethod method;
    private String orderId;
    private PaymentStatus status;
    private BigDecimal amount;
}