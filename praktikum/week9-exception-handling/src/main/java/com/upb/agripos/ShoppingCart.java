package main.java.com.upb.agripos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import main.java.com.upb.agripos.Produk; // Pastikan import ini benar

public class ShoppingCart {
    private final Map<Produk, Integer> items = new HashMap<>();

    // Menambahkan 'throws' untuk memberi tahu bahwa method ini bisa error
    public void addProduct(Produk p, int qty) throws InvalidQuantityException {
        // Validasi 1: Jumlah tidak boleh 0 atau minus
        if (qty <= 0) {
            throw new InvalidQuantityException("Gagal tambah: Jumlah (qty) harus lebih dari 0.");
        }
        items.put(p, items.getOrDefault(p, 0) + qty);
    }

    public void removeProduct(Produk p) throws ProductNotFoundException {
        // Validasi 2: Cek apakah produk ada di keranjang
        if (!items.containsKey(p)) {
            throw new ProductNotFoundException("Gagal hapus: Produk " + p.getNama() + " tidak ada di keranjang.");
        }
        items.remove(p);
    }

    public void checkout() throws InsufficientStockException {
        // Validasi 3: Cek stok sebelum mengurangi
        for (Map.Entry<Produk, Integer> entry : items.entrySet()) {
            Produk product = entry.getKey();
            int qtyDiKeranjang = entry.getValue();

            // Cek Stok (menggunakan method getStok() dari Produk.java Anda)
            if (product.getStok() < qtyDiKeranjang) {
                throw new InsufficientStockException(
                    "Checkout Gagal: Stok " + product.getNama() + " tidak cukup! (Sisa: " + product.getStok() + ", Diminta: " + qtyDiKeranjang + ")"
                );
            }
        }

        // Jika semua stok aman, baru kurangi stok beneneran
        System.out.println("Checkout Berhasil! Mengurangi stok...");
        for (Map.Entry<Produk, Integer> entry : items.entrySet()) {
            // Menggunakan method kurangiStok() dari Produk.java Anda
            entry.getKey().kurangiStok(entry.getValue());
        }
        items.clear(); // Kosongkan keranjang setelah checkout
    }

    public void printCart() {
        // ... (kode printCart minggu lalu tetap sama)
        System.out.println("Isi Keranjang:");
        for (Map.Entry<Produk, Integer> e : items.entrySet()) {
            System.out.println("- " + e.getKey().getNama() + " x" + e.getValue());
        }
    }

    public void addProduct(Produk p) {
        try {
            addProduct(p, 1); // Panggil method utama dengan default qty 1
        } catch (InvalidQuantityException e) {
            e.printStackTrace(); // Abaikan error untuk kompatibilitas lama
        }
    }
}