package adpro.b10.epicarcade_functional.jualbeli.service;

import adpro.b10.epicarcade_functional.jualbeli.dto.OrderGameDto;
import adpro.b10.epicarcade_functional.jualbeli.mapper.OrderGameMapper;
import adpro.b10.epicarcade_functional.jualbeli.model.OrderGame;
import adpro.b10.epicarcade_functional.jualbeli.repository.OrderGameRepository;
import adpro.b10.epicarcade_functional.jualbeli.service.OrderGameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class OrderGameServiceImplTest {

    @InjectMocks
    private OrderGameServiceImpl orderGameService;

    @Mock
    private OrderGameRepository orderGameRepository;

    @Mock
    private OrderGameMapper orderGameMapper;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveOrderGame() {
        OrderGameDto orderGameDto = new OrderGameDto();
        OrderGame orderGame = new OrderGame();
        when(orderGameMapper.orderGameDTOToOrderGame(orderGameDto)).thenReturn(orderGame);
        when(orderGameRepository.save(orderGame)).thenReturn(orderGame);
        when(orderGameMapper.orderGameToOrderGameDTO(orderGame)).thenReturn(orderGameDto);

        OrderGameDto result = orderGameService.saveOrderGame(orderGameDto);

        assertEquals(orderGameDto, result);
        verify(orderGameRepository, times(1)).save(orderGame);
    }

    @Test
    public void testGetOrderGameById() {
        OrderGameDto orderGameDto = new OrderGameDto();
        OrderGame orderGame = new OrderGame();
        when(orderGameRepository.findById("1")).thenReturn(Optional.of(orderGame));
        when(orderGameMapper.orderGameToOrderGameDTO(orderGame)).thenReturn(orderGameDto);

        Optional<OrderGameDto> result = orderGameService.getOrderGameById("1");

        assertEquals(Optional.of(orderGameDto), result);
    }

    @Test
    public void testDeleteOrderGame() {
        orderGameService.deleteOrderGame("1");
        verify(orderGameRepository, times(1)).deleteById("1");
    }

    @Test
    public void testGetOrderGamesByOrderId() {
        OrderGameDto orderGameDto = new OrderGameDto();
        OrderGame orderGame = new OrderGame();
        when(orderGameRepository.findByOrderId("1")).thenReturn(Optional.of(Arrays.asList(orderGame)));
        when(orderGameMapper.orderGameToOrderGameDTO(orderGame)).thenReturn(orderGameDto);

        List<OrderGameDto> result = orderGameService.getOrderGamesByOrderId("1");

        assertEquals(List.of(orderGameDto), result);
    }
}