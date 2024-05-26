package adpro.b10.epicarcade_functional.history.repository;

import adpro.b10.epicarcade_functional.history.model.TransactionItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionItemRepository extends JpaRepository<TransactionItem, Long> {
}
