package main.java.com.upb.agripos;

import main.java.com.upb.agripos.Produk; 
public class ProductService {
    
    // 1. Variabel static untuk menyimpan satu-satunya instance
    private static ProductService instance;

    // 2. Constructor private agar tidak bisa di-new sembarangan dari luar
    private ProductService() {
        System.out.println("ProductService berhasil dibuat (Singleton).");
    }

    // 3. Method static untuk mengambil instance
    public static ProductService getInstance() {
        if (instance == null) {
            instance = new ProductService();
        }
        return instance;
    }

    // --- CONTOH METHOD LAYANAN (Opsional) ---
    // Ini contoh logika bisnis: Mengecek ketersediaan stok
    public boolean isProductAvailable(Produk p) {
        return p.getStok() > 0;
    }
}