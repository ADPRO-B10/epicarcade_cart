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

    @GetMapping("/list-reviews")
    public List<Review> listReviews() {
        return reviewService.findAllReviews();
    }

    @PostMapping("/add-review")
    public Review addReview(@RequestBody String id_game, int rating, String comment) {
        return reviewService.addReview(id_game,rating,comment);
    }
//    @GetMapping("/game/{Id_game}")
//    public List<Review> listReviewsByGame(@PathVariable String Id_game) {
//        return reviewService.findReviewsByGame(Id_game);
//    }
}
