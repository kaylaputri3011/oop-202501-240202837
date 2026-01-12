package test.java.com.upb.agripos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import main.java.com.upb.agripos.model.Produk;

public class ProdukTest {

    @Test
    public void testInisialisasiProduk() {
        Produk p = new Produk("P01", "Pupuk", 50000, 10);
        assertEquals("P01", p.getKode());
        assertEquals(50000, p.getHarga());
        assertEquals(10, p.getStok());
    }

    @Test
    public void testTambahStok() {
        Produk p = new Produk("P01", "Pupuk", 50000, 10);
        
        // Test tambah stok positif
        p.tambahStok(5);
        assertEquals(15, p.getStok(), "Stok harus bertambah menjadi 15");

        // Test tambah stok negatif (tidak boleh berubah)
        p.tambahStok(-5);
        assertEquals(15, p.getStok(), "Stok tidak boleh berubah jika input negatif");
    }

    @Test
    public void testKurangiStokBerhasil() {
        Produk p = new Produk("P01", "Pupuk", 50000, 10);
        
        // Kurangi stok wajar
        p.kurangiStok(3);
        assertEquals(7, p.getStok(), "Stok harus berkurang menjadi 7");
    }

    @Test
    public void testKurangiStokGagal() {
        Produk p = new Produk("P01", "Pupuk", 50000, 5);
        
        // Coba kurangi lebih dari stok yang ada
        p.kurangiStok(10);
        
        // Stok harus tetap 5 karena transaksi gagal
        assertEquals(5, p.getStok(), "Stok tidak boleh minus/berkurang jika stok tidak cukup");
    }
}