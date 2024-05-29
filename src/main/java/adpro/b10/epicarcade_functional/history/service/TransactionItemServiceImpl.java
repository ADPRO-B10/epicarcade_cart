package adpro.b10.epicarcade_functional.history.service;

import adpro.b10.epicarcade_functional.history.model.TransactionItem;
import adpro.b10.epicarcade_functional.history.repository.TransactionItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionItemServiceImpl implements TransactionItemService {

    @Autowired
    private TransactionItemRepository repository;

    @Override
    public List<TransactionItem> getAllTransactions() {
        return repository.findAll();
    }

    @Override
    public TransactionItem getTransactionById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TransactionItem saveTransaction(TransactionItem transactionItem) {
        return repository.save(transactionItem);
    }

    @Override
    public void deleteTransaction(Long id) {
        repository.deleteById(id);
    }
}
