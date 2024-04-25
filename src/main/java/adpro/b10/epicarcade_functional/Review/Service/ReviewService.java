package adpro.b10.epicarcade_functional.Review.Service;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }
}