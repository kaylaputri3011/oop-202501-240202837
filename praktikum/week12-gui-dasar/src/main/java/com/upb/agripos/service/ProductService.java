package main.java.com.upb.agripos.service;

import main.java.com.upb.agripos.dao.ProductRepository;
import main.java.com.upb.agripos.model.Produk;
import java.util.List;

public class ProductService {
    // DIP: Bergantung pada Interface, bukan Class Konkret
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String kode, String nama, double harga, int stok) throws Exception {
        // 1. Validasi Input (Sesuai Activity Diagram)
        if (kode.isEmpty() || nama.isEmpty()) {
            throw new Exception("Kode dan Nama produk wajib diisi!");
        }
        if (harga <= 0) {
            throw new Exception("Harga harus lebih dari 0!");
        }
        if (stok < 0) {
            throw new Exception("Stok tidak boleh negatif!");
        }

        // 2. Bungkus ke Model
        Produk p = new Produk(kode, nama, harga, stok);

        // 3. Simpan ke Database
        repository.save(p);
    }

    public List<Produk> getAllProducts() throws Exception {
        return repository.findAll();
    }
}