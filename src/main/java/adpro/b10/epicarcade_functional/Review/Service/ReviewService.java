package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(Review review) {
        return reviewRepository.save(review);
    }

    public List<Review> findReviewsByGame(String gameId) {
        return reviewRepository.findAllByGameId(gameId);
    }
}
