package main.java.com.upb.agripos.view;

import main.java.com.upb.agripos.model.Produk;

public class ConsoleView {
    public void showProductDetails(Produk produk) {
        System.out.println("=== Detail Produk ===");
        System.out.println("Kode  : " + produk.getKode());
        System.out.println("Nama  : " + produk.getNama());
        System.out.println("Harga : Rp " + produk.getHarga());
        System.out.println("Stok  : " + produk.getStok());
        System.out.println("=====================");
    }

    public void showMessage(String message) {
        System.out.println("[INFO] " + message);
    }
}