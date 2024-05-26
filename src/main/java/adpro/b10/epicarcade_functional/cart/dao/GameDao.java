package adpro.b10.epicarcade_functional.cart.dao;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;

@Repository
public interface GameDao extends CrudRepository<Game, Integer> {
    public List<Game> findAll(Pageable pageable);

    public List<Game> findByProductName(String productName);
}
