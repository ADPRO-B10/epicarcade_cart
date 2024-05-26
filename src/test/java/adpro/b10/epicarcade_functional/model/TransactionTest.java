package adpro.b10.epicarcade_functional.model;

import static org.junit.jupiter.api.Assertions.*;

import adpro.b10.epicarcade_functional.Review.Model.Game;
import adpro.b10.epicarcade_functional.history.enums.TransactionStatus;
import adpro.b10.epicarcade_functional.history.model.Transaction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

    private Transaction transaction;
    private List<Game> games;

    @BeforeEach
    public void setUp() {
        games = new ArrayList<>();
        games.add(new Game("Game1", "a", "This game is about superheroes", 10, 2));
        games.add(new Game("Game2", "b", "This game is about supervillains", 100, 4));

        transaction = Transaction.builder()
                .id("TX123")
                .pembeli("John Doe")
                .penjual("Jane Smith")
                .listGame(games)
                .waktuTransaksi(LocalDate.now())
                .totalHarga(25000L)
                .build();
    }

    @Test
    public void testTransactionCreation_ValidData() {
        assertNotNull(transaction);
        assertEquals("TX123", transaction.getId());
        assertEquals("John Doe", transaction.getPembeli());
        assertEquals("Jane Smith", transaction.getPenjual());
        assertEquals(2, transaction.getListGame().size());
        assertEquals(TransactionStatus.WAITING_PAYMENT.getValue(), transaction.getStatusPembayaran());
    }

    @Test
    public void testTransactionCreation_EmptyGameList() {
        assertThrows(IllegalArgumentException.class, () -> {
            Transaction.builder()
                    .id("TX124")
                    .pembeli("Alice")
                    .penjual("Bob")
                    .listGame(new ArrayList<>())
                    .waktuTransaksi(LocalDate.now())
                    .totalHarga(5000L)
                    .build();
        });
    }

    @Test
    public void testSetStatus_ValidStatus() {
        transaction.setStatus(TransactionStatus.SUCCESS.getValue());
        assertEquals(TransactionStatus.SUCCESS.getValue(), transaction.getStatusPembayaran());
    }

    @Test
    public void testSetStatus_InvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            transaction.setStatus("INVALID_STATUS");
        });
    }

    @Test
    public void testTambahProduk_AddProduct() {
        int initialSize = transaction.getListGame().size();
        transaction.tambahProduk(new Game("Game3", "gf", "fsgfgds", 500, 55));
        assertEquals(initialSize + 1, transaction.getListGame().size());
    }}