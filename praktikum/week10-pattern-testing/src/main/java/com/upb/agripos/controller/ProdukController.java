package main.java.com.upb.agripos.controller;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.view.ConsoleView;

public class ProdukController {
    private Produk model;
    private ConsoleView view;

    public ProdukController(Produk model, ConsoleView view) {
        this.model = model;
        this.view = view;
    }

    public void tampilkanProduk() {
        view.showProductDetails(model);
    }

    public void prosesRestock(int jumlah) {
        model.tambahStok(jumlah);
        view.showMessage("Stok berhasil ditambahkan: " + jumlah);
        tampilkanProduk(); // Tampilkan data terbaru
    }
    
    public void prosesPenjualan(int jumlah) {
        model.kurangiStok(jumlah);
        view.showMessage("Stok dikurangi: " + jumlah);
        tampilkanProduk();
    }
}