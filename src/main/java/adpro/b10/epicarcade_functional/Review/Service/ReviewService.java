package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import adpro.b10.epicarcade_functional.Review.Repository.ReviewRepository;

@Service
public class ReviewService {

    private static ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findReviewsByGame(String gameId) {
        return reviewRepository.findAllByGameId(gameId);
    }
}
