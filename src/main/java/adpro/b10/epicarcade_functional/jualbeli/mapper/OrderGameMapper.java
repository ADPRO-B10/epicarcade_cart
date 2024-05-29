package adpro.b10.epicarcade_functional.jualbeli.mapper;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderGameDto;
import adpro.b10.epicarcade_functional.jualbeli.model.Order;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = OrderMapper.class)
public interface OrderGameMapper {

    @Mapping(source = "order.id", target = "orderId")
    OrderGameDto orderGameToOrderGameDTO(OrderGame orderGame);

    @Mapping(source = "orderId", target = "order")
    OrderGame orderGameDTOToOrderGame(OrderGameDto orderGameDTO);

    default Order map(String id) {
        if (id == null) {
            return null;
        }

        Order order = new Order();
        order.setId(id);
        return order;
    }
}