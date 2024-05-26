package adpro.b10.epicarcade_functional.jualbeli.dto;

import adpro.b10.epicarcade_functional.jualbeli.enums.OrderStatus;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private String id;
    private String buyerId;
    private OrderStatus status;
    private LocalDateTime orderDate;
    private BigDecimal totalPrice;
    private List<OrderGameDto> orderGames;
    private PaymentDto payment;
}