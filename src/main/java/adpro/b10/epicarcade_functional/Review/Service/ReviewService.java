package adpro.b10.epicarcade_functional.Review.Service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Async("taskExecutorForHeavyTasks")
    public CompletableFuture<Review> addReview(String id_game, int rating, String comment) {
        return CompletableFuture.supplyAsync(   () -> {
            Review review = new Review();
            review.setId(UUID.randomUUID().toString());
            review.setId_game(id_game);
            review.setRating(rating);
            review.setComment(comment);

            return reviewRepository.save(review);
        });
    }

    @Async("taskExecutorForHeavyTasks")
    public CompletableFuture<List<Review>> findAllReviews() {
        return CompletableFuture.supplyAsync(() -> reviewRepository.findAll());
    }

    @Async("taskExecutorForHeavyTasks")
    public CompletableFuture<Void> deleteReview(String reviewId) {
        return CompletableFuture.runAsync(() -> {
            reviewRepository.deleteById(reviewId);
        });
    }

//    @Async("taskExecutorForHeavyTasks")
//    public CompletableFuture<Review> editReview(String reviewId, String id_game, int rating, String comment) {
//        return CompletableFuture.supplyAsync(() -> {
//            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new RuntimeException("Review not found"));
//            review.setId_game(id_game);
//            review.setRating(rating);
//            review.setComment(comment);
//            return reviewRepository.save(review);
//        });
//    }

}
