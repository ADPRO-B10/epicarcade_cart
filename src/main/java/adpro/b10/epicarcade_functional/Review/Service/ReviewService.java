package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import org.springframework.stereotype.Service;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReviewService {
    private static final List<Review> reviews = Arrays.asList(
            new Review("1",new Game("1", "Game One", "First game description", 20, 5), 4, "Pretty good!"),
            new Review("2",new Game("2", "Game Two", "Second game description", 30, 3), 5, "Excellent!")
    );

    public Review addReview(Review review) {
        try {
            reviews.add(review);
            return review;
        } catch (Exception e) {
            throw new RuntimeException("Failed to add review: " + e.getMessage(), e);
        }
    }

    public List<Review> findReviewsByGame(String gameId) {
        return reviews.stream()
                .filter(review -> review.getGame().getId().equals(gameId))
                .collect(Collectors.toList());
    }
}
