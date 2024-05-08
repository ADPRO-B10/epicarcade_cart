package adpro.b10.epicarcade_functional.Review.Model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReviewTest {
    private Game game;
    private Review review;
    private final String gameName = "Chess";
    private final String gameDescription = "Strategic board game";
    private final int rating = 4;
    private final String comment = "Challenging and fun.";

    @BeforeEach
    public void setUp() {
        game = Mockito.mock(Game.class);
        Mockito.when(game.getName()).thenReturn(gameName);
        Mockito.when(game.getDescription()).thenReturn(gameDescription);

        review = new Review("1",game, rating, comment);
    }

    @Test
    public void testReviewConstructor() {
        assertNotNull(review.getGame());
        assertEquals(rating, review.getRating());
        assertEquals(comment, review.getComment());
    }

    @Test
    public void testSetRating() {
        int newRating = 5;
        review.setRating(newRating);
        assertEquals(newRating, review.getRating());
    }

    @Test
    public void testSetRatingWithInvalidValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> review.setRating(0));
        assertEquals("Rating has to be 1-5", exception.getMessage());
    }

    @Test
    public void testSetComment() {
        String newComment = "WOW!!!";
        review.setComment(newComment);
        assertEquals(newComment, review.getComment());
    }

    @Test
    public void testSetCommentWithNullValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> review.setComment(null));
        assertEquals("Game comment cannot be empty", exception.getMessage());
    }

    @Test
    public void testSetNameOnGame() {
        String newName = "GTA 6";
        review.setName(game, newName);
        Mockito.verify(game).setName(newName);
    }

    @Test
    public void testSetNameWithNullValue() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> review.setName(game, null));
        assertEquals("Game name cannot be empty", exception.getMessage());
    }
}
