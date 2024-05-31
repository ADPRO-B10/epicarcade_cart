package adpro.b10.epicarcade_functional.Filter.Controller;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.service.FilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class FilterController {

    private final FilterService filterService;

    @Autowired
    public FilterController(FilterService filterService) {
        this.filterService = filterService;
    }

    @GetMapping("/filter/title")
    public List<Game> getGamesByTitle(@RequestParam String keyword) {
        return filterService.findByTitleIgnoreCaseOrderByTitle(keyword);
    }

    @GetMapping("/filter/price-range")
    public List<Game> getGamesByPriceRange(@RequestParam int minPrice, @RequestParam int maxPrice) {
        return filterService.findByPriceBetweenOrderByTitle(minPrice, maxPrice);
    }

    @GetMapping("/filter/price")
    public List<Game> getGamesByPrice(@RequestParam int price) {
        return filterService.findByPriceEqualsOrderByTitle(price);
    }

    @GetMapping("/filter/all")
    public List<Game> getAllGames() {
        return filterService.findAllOrderByTitle();
    }
}
