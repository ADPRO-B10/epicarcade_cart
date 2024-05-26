package adpro.b10.epicarcade_functional.jualbeli.mapper;

import adpro.b10.epicarcade_functional.jualbeli.dto.PaymentDto;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface PaymentMapper {

    @Mapping(source = "order.id", target = "orderId")
    PaymentDto paymentToPaymentDto(Payment payment);

    @Mapping(source = "orderId", target = "order")
    Payment paymentDtoToPayment(PaymentDto paymentDto);

    default Order map(String id) {
        if (id == null) {
            return null;
        }

        Order order = new Order();
        order.setId(id);
        return order;
    }
}