package adpro.b10.epicarcade_functional.Review.Controller;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Model.Review;
import adpro.b10.epicarcade_functional.Review.Service.GameService;
import adpro.b10.epicarcade_functional.Review.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

//    @Autowired
    private GameService gameService;

//    @Autowired
    private ReviewService reviewService;

    @GetMapping("/games")
    public String listGames(Model model) {
        List<Game> games = gameService.getAllGames();
        model.addAttribute("games", games);
        return "list-games";
    }

    @PostMapping("/submit")
    public Review submitReview(@RequestBody Review review) {
        return reviewService.saveReview(review);
    }
}