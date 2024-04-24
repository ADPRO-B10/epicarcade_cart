package adpro.b10.epicarcade_functional.model;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;

import java.util.List;
import java.time.LocalDate;

@Builder
@Getter

public class Transaction {
    private String id;
    private String pembeli;
    private String penjual;
    private String statusPembayaran;
    private List<Game> listGame;
    private LocalDate waktuTransaksi;
    private Long totalHarga;

    public Transaction(String id, String pembeli, String penjual, List<Game> listGame, LocalDate waktuTransaksi, Long totalHarga) {
        this.id = id;
        this.pembeli = pembeli;
        this.penjual = penjual;
        this.statusPembayaran = "WAITING_PAYMENT";
        this.waktuTransaksi = waktuTransaksi;
        this.totalHarga = totalHarga;

        if (listGame.isEmpty()) {
            throw new IllegalArgumentException();
        } else {
            this.listGame = listGame;
        }
    }

    public Transaction(String id, String pembeli, String penjual, List<Game> listGame, LocalDate waktuTransaksi, Long totalHarga) {
        this(id, pembeli, penjual,  listGame, waktuTransaksi, totalHarga);

        String[] statusList = {"WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELED"};
        if (Arrays.stream(statusList).noneMatch(item -> item.equals(statusPembayaran))) {
            throw new IllegalArgumentException();
        } else {
            this.statusPembayaran = statusPembayaran;
        }
    }


    public void setStatus(String statusPembayaran) {
        String[] statusList = {"WAITING_PAYMENT", "FAILED", "SUCCESS", "CANCELLED"};
        if (Arrays.stream(statusList).noneMatch(item -> item.equals(statusPembayaran))) {
            throw new IllegalArgumentException();
        } else {
            this.statusPembayaran = statusPembayaran;
        }
    }
    public void tambahProduk(Game game) {
        this.listGame.add(game);
    }
}