package adpro.b10.epicarcade_functional.Review.Controller;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Service.GameService;
import adpro.b10.epicarcade_functional.Review.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/add-review")
    public Review addReview(@RequestBody Review review) {
        return reviewService.addReview(review);
    }
    @GetMapping("/game/{gameId}")
    public List<Review> listReviewsByGame(@PathVariable String gameId) {
        return reviewService.findReviewsByGame(gameId);
    }
}
