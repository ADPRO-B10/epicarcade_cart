package adpro.b10.epicarcade_functional.history.service;

import adpro.b10.epicarcade_functional.history.model.TransactionItem;
import java.util.List;

public interface TransactionItemService {
    List<TransactionItem> getAllTransactions();
    TransactionItem getTransactionById(Long id);
    TransactionItem saveTransaction(TransactionItem transactionItem);
    void deleteTransaction(Long id);
}
