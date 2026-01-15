package main.java.com.upb.agripos.service;

import main.java.com.upb.agripos.dao.ProductRepository;
import main.java.com.upb.agripos.model.Produk;
import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public void addProduct(String kode, String nama, double harga, int stok) throws Exception {
        // ... (Validasi lama tetap ada) ...
        if (kode.isEmpty() || nama.isEmpty()) throw new Exception("Data tidak lengkap!");
        if (harga <= 0) throw new Exception("Harga invalid!");
        if (stok < 0) throw new Exception("Stok invalid!");
        
        repository.save(new Produk(kode, nama, harga, stok));
    }

    // --- TAMBAHAN BARU: DELETE ---
    public void deleteProduct(String code) throws Exception {
        if (code == null || code.isEmpty()) {
            throw new Exception("Pilih produk yang akan dihapus!");
        }
        repository.delete(code);
    }

    public List<Produk> getAllProducts() throws Exception {
        return repository.findAll();
    }
}