package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;
import adpro.b10.epicarcade_functional.Review.Repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public Review addReview(String id_game, int rating, String comment) {
        Review review = new Review();
        review.setId(UUID.randomUUID().toString());
        review.setId_game(id_game);
        review.setRating(rating);
        review.setComment(comment);

        return reviewRepository.save(review);
    }

//    public List<Review> findReviewsByGame(String Id_game) {
//        return reviewRepository.findAllById_game(Id_game);
//    }

    public List<Review> findAllReviews() {
        return reviewRepository.findAll();
    }
}
