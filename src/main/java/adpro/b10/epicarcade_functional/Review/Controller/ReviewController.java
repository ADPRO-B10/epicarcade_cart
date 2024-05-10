package adpro.b10.epicarcade_functional.Review.Controller;

import adpro.b10.epicarcade_functional.Review.Dto.AddReviewDTO;
import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Service.GameService;
import adpro.b10.epicarcade_functional.Review.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private GameService gameService;

    @Autowired
    private ReviewService reviewService;

    @GetMapping("/games")
    public List<Game> listGames() {
        return gameService.findAllGames();
    }

    @GetMapping("/list-reviews")
    public List<Review> listReviews() {
        return reviewService.findAllReviews();
    }

@PostMapping("/add-review")
public Review addReview(@RequestBody AddReviewDTO reviewDTO) {
    return reviewService.addReview(reviewDTO.getId_game(), reviewDTO.getRating(), reviewDTO.getComment());
    }
}
