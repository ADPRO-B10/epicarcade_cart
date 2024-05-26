package adpro.b10.epicarcade_functional.jualbeli.mapper;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderDto;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {OrderGameMapper.class, PaymentMapper.class})
public interface OrderMapper {

    @Mapping(source = "orderGames", target = "orderGames")
    @Mapping(source = "payment", target = "payment")
    OrderDto orderToOrderDto(Order order);

    @Mapping(source = "orderGames", target = "orderGames")
    @Mapping(source = "payment", target = "payment")
    Order orderDtoToOrder(OrderDto orderDto);
}