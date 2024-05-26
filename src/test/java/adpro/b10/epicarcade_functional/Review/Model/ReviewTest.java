package adpro.b10.epicarcade_functional.Review.Model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ReviewTest {

    @Test
    public void testCreateReview() {
        Review review = new Review("1", "game1", 5, "Great game!");

        assertNotNull(review);
        assertEquals("1", review.getId());
        assertEquals("game1", review.getId_game());
        assertEquals(5, review.getRating());
        assertEquals("Great game!", review.getComment());
    }

    @Test
    public void testSetValidRating() {
        Review review = new Review();
        review.setRating(4);

        assertEquals(4, review.getRating());
    }

    @Test
    public void testSetInvalidRating() {
        Review review = new Review();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setRating(0);
        });

        String expectedMessage = "Rating has to be between 1 and 5";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setRating(6);
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testSetValidComment() {
        Review review = new Review();
        review.setComment("Good game");

        assertEquals("Good game", review.getComment());
    }

    @Test
    public void testSetInvalidComment() {
        Review review = new Review();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setComment("");
        });

        String expectedMessage = "Comment cannot be empty";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));

        exception = assertThrows(IllegalArgumentException.class, () -> {
            review.setComment(null);
        });

        actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}