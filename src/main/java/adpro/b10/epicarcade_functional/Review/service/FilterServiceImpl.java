package adpro.b10.epicarcade_functional.Review.service;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.Review.Repository.FilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilterServiceImpl implements FilterService {

    private final FilterRepository filterRepository;

    @Autowired
    public FilterServiceImpl(FilterRepository filterRepository) {
        this.filterRepository = filterRepository;
    }

    @Override
    public List<Game> findByTitleIgnoreCaseOrderByTitle(String keyword) {
        return filterRepository.findByTitleIgnoreCaseOrderByTitle(keyword);
    }

    @Override
    public List<Game> findByPriceBetweenOrderByTitle(int minPrice, int maxPrice) {
        return filterRepository.findByPriceBetweenOrderByTitle(minPrice, maxPrice);
    }

    @Override
    public List<Game> findByPriceEqualsOrderByTitle(int price) {
        return filterRepository.findByPriceEqualsOrderByTitle(price);
    }

    @Override
    public List<Game> findAllOrderByTitle() {
        return filterRepository.findByOrderByTitle();
    }
}
