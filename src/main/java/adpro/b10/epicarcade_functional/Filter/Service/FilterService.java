package adpro.b10.epicarcade_functional.Filter.service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import java.util.List;

public interface FilterService {

    List<Game> findByTitleIgnoreCaseOrderByTitle(String keyword);

    List<Game> findByPriceBetweenOrderByTitle(int minPrice, int maxPrice);

    List<Game> findByPriceEqualsOrderByTitle(int price);

    List<Game> findAllOrderByTitle();
}