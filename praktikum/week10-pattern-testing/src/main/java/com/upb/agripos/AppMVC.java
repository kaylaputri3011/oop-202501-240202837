package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.view.ConsoleView;
import main.java.com.upb.agripos.controller.ProdukController;
import main.java.com.upb.agripos.config.DatabaseConnection; // Jangan lupa Singleton

public class AppMVC {
    public static void main(String[] args) {
        System.out.println("=== Aplikasi Agri-POS (Week 10) ===");
        System.out.println("Created by: Kayla-240202837");

        // -------------------------------------------------------
        // 1. TEST SINGLETON (Database)
        // -------------------------------------------------------
        // Memastikan hanya ada 1 koneksi database
        DatabaseConnection db = DatabaseConnection.getInstance();
        System.out.println("Status DB: " + db.getStatus());

        // -------------------------------------------------------
        // 2. TEST MVC (Model-View-Controller)
        // -------------------------------------------------------
        
        // MODEL: Gunakan 4 parameter 
        // (Kode, Nama, Harga, Stok)
        Produk produk = new Produk("P01", "Pupuk Organik", 50000.0, 100);

        // VIEW: Menyiapkan tampilan
        ConsoleView view = new ConsoleView();

        // CONTROLLER: Menghubungkan Model dan View
        ProdukController controller = new ProdukController(produk, view);

        // Jalankan fungsi controller
        controller.tampilkanProduk();
        
        System.out.println("\n--- Simulasi Transaksi ---");
        // Contoh restock (tambah stok)
        controller.prosesRestock(20); 
    }
}