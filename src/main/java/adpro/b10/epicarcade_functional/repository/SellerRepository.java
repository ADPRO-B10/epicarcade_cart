package adpro.b10.epicarcade_functional.repository;

import adpro.b10.epicarcade_functional.model.SellerEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SellerRepository extends JpaRepository<SellerEntity, Integer> {
    Optional<SellerEntity> findByUsername(String username);
    Optional<SellerEntity> findByEmail(String email);
    Optional<SellerEntity> findBySellerId(Integer userId);
}
