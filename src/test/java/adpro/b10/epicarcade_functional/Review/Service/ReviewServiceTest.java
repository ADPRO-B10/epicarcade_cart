package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Repository.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;

    @InjectMocks
    private ReviewService reviewService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testAddReview() throws Exception {
        Review review = new Review();
        review.setId(UUID.randomUUID().toString());
        review.setId_game("game1");
        review.setRating(5);
        review.setComment("Great game!");

        when(reviewRepository.save(any(Review.class))).thenReturn(review);

        CompletableFuture<Review> future = reviewService.addReview("game1", 5, "Great game!");
        Review result = future.get();

        assertNotNull(result);
        assertEquals("Great game!", result.getComment());

        verify(reviewRepository, times(1)).save(any(Review.class));
    }

    @Test
    public void testFindAllReviews() throws Exception {
        Review review1 = new Review("1", "game1", 5, "Great game!");
        Review review2 = new Review("2", "game2", 4, "Good game!");

        List<Review> reviews = Arrays.asList(review1, review2);

        when(reviewRepository.findAll()).thenReturn(reviews);

        CompletableFuture<List<Review>> future = reviewService.findAllReviews();
        List<Review> result = future.get();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Great game!", result.get(0).getComment());

        verify(reviewRepository, times(1)).findAll();
    }

    @Test
    public void testDeleteReview() throws Exception {
        String reviewId = "1";

        doNothing().when(reviewRepository).deleteById(reviewId);

        CompletableFuture<Void> future = reviewService.deleteReview(reviewId);
        future.get();

        verify(reviewRepository, times(1)).deleteById(reviewId);
    }
}
