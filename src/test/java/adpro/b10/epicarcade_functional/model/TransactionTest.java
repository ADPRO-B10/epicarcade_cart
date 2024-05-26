package adpro.b10.epicarcade_functional.model;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import org.mockito.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TransactionTest {

    private Transaction transaction;
    private List<Game> games;

    @Before
    public void setUp() {
        games = new ArrayList<>();
        games.add(new Game("Game1", "This game is about superheroes"));
        games.add(new Game("Game2", "This game is about supervillains"));

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

    @Test(expected = IllegalArgumentException.class)
    public void testTransactionCreation_EmptyGameList() {
        Transaction.builder()
                .id("TX124")
                .pembeli("Alice")
                .penjual("Bob")
                .listGame(new ArrayList<>())
                .waktuTransaksi(LocalDate.now())
                .totalHarga(5000L)
                .build();
    }

    @Test
    public void testSetStatus_ValidStatus() {
        transaction.setStatus(TransactionStatus.SUCCESS.getValue());
        assertEquals(TransactionStatus.SUCCESS.getValue(), transaction.getStatusPembayaran());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetStatus_InvalidStatus() {
        transaction.setStatus("INVALID_STATUS");
    }

    @Test
    public void testTambahProduk_AddProduct() {
        int initialSize = transaction.getListGame().size();
        transaction.tambahProduk(new Game("Game3", 20000L));
        assertEquals(initialSize + 1, transaction.getListGame().size());
    }
}
