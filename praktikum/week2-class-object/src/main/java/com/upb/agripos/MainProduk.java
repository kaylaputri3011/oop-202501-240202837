package main.java.com.upb.agripos; 

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        System.out.println(); 
        System.out.println("Benih Padi IR64: " + p1.getStok());
        p1.kurangiStok(23); // Memicu pesan error
        System.out.println("sisa stok " + p1.getNama() + " setelah berkurang 23 produk: " + p1.getStok());

        System.out.println(); 
        System.out.println("Pupuk Urea 50kg: " + p2.getStok());
        p2.tambahStok(250); // Memicu pesan error
        System.out.println("sisa stok " + p2.getNama() + " setelah bertambah 250 produk: " + p2.getStok());

        System.out.println(); 
        System.out.println("Stok awal Cangkul Baja: " + p3.getStok());
        p3.kurangiStok(2005); // Memicu pesan error
        System.out.println("sisa stok " + p3.getNama() + " setelah berkurang 2005 produk: " + p3.getStok());
        // Tampilkan identitas mahasiswa
        CreditBy.print("240202837", "Kayla Putri Arsonisr");
    }
    
}