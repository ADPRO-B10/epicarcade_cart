package adpro.b10.epicarcade_functional.Review.Controller;


import adpro.b10.epicarcade_functional.Review.Dto.AddReviewDTO;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Service.GameService;
import adpro.b10.epicarcade_functional.Review.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/games")
    public CompletableFuture<List<Game>> listGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/list-reviews")
    public CompletableFuture<List<Review>> listReviews() {
        return reviewService.findAllReviews();
    }

    @PostMapping("/add-review")
    public CompletableFuture<Review> addReview(@RequestBody AddReviewDTO reviewDTO) {
        System.out.println("Received reviewDTO: ");
        System.out.println("id_game: " + reviewDTO.getId_game());
        System.out.println("rating: " + reviewDTO.getRating());
        System.out.println("comment: " + reviewDTO.getComment());
        return reviewService.addReview(reviewDTO.getId_game(), reviewDTO.getRating(), reviewDTO.getComment());
    }

    @DeleteMapping("/delete-review/{reviewId}")
    public CompletableFuture<Void> deleteReview(@PathVariable String reviewId) {
        return reviewService.deleteReview(reviewId);
    }

}