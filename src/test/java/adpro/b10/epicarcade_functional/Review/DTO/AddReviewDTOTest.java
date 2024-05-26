package adpro.b10.epicarcade_functional.Review.DTO;

import adpro.b10.epicarcade_functional.Review.Dto.AddReviewDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AddReviewDTOTest {

    @Test
    public void testGettersAndSetters() {
        AddReviewDTO dto = new AddReviewDTO();

        dto.setId_game("game1");
        dto.setRating(5);
        dto.setComment("Great game!");

        assertEquals("game1", dto.getId_game());
        assertEquals(5, dto.getRating());
        assertEquals("Great game!", dto.getComment());
    }

    @Test
    public void testConstructor() {
        AddReviewDTO dto = new AddReviewDTO();
        assertNull(dto.getId_game());
        assertEquals(0, dto.getRating());
        assertNull(dto.getComment());
    }

    @Test
    public void testSetNullValues() {
        AddReviewDTO dto = new AddReviewDTO();

        dto.setId_game(null);
        dto.setRating(0);
        dto.setComment(null);

        assertNull(dto.getId_game());
        assertEquals(0, dto.getRating());
        assertNull(dto.getComment());
    }

    @Test
    public void testToString() {
        AddReviewDTO dto = new AddReviewDTO();

        dto.setId_game("game1");
        dto.setRating(5);
        dto.setComment("Great game!");

        String expected = "AddReviewDTO(id_game=game1, rating=5, comment=Great game!)";
        assertEquals(expected, dto.toString());
    }
}