package adpro.b10.epicarcade_functional.history.controller;

import adpro.b10.epicarcade_functional.history.model.TransactionItem;
import adpro.b10.epicarcade_functional.history.service.TransactionItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionItemController {

    @Autowired
    private TransactionItemService service;

    @GetMapping
    public List<TransactionItem> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TransactionItem> getTransactionById(@PathVariable Long id) {
        TransactionItem transactionItem = service.getTransactionById(id);
        if (transactionItem == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(transactionItem);
    }

    @PostMapping
    public TransactionItem createTransaction(@RequestBody TransactionItem transactionItem) {
        return service.saveTransaction(transactionItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTransaction(@PathVariable Long id) {
        if (service.getTransactionById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        service.deleteTransaction(id);
        return ResponseEntity.ok().build();
    }
}
