package adpro.b10.epicarcade_functional.Review.Repository;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface FilterRepository extends JpaRepository<Game, Long> {

    @Query("SELECT g FROM Game g WHERE LOWER(g.title) LIKE LOWER(concat('%', ?1, '%'))")
    List<Game> findByTitleIgnoreCaseOrderByTitle(String keyword);

    List<Game> findByPriceBetweenOrderByTitle(int minPrice, int maxPrice);

    List<Game> findByPriceEqualsOrderByTitle(int price);

    List<Game> findByOrderByTitle();

}