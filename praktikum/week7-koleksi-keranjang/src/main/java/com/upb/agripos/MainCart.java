package main.java.com.upb.agripos;

import main.java.com.upb.agripos.Produk; 

public class MainCart {
    // Tambahkan tulisan 'throws Exception' di sini
public static void main(String[] args) throws Exception {

        System.out.println("Hello, I am Kayla-240202837 (Week7)");

        // Perubahan: Constructor Produk butuh 4 parameter (Kode, Nama, Harga, STOK)
        // Saya isi stok sembarang (misal: 100 dan 50) agar tidak error
        Produk p1 = new Produk("P01", "Beras", 50000, 100); 
        Produk p2 = new Produk("P02", "Pupuk", 30000, 50);

        // --- SKENARIO 1: Menggunakan ArrayList (ShoppingCart) ---
        System.out.println("\n--- Test ArrayList ---");
        ShoppingCart cart = new ShoppingCart();
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.printCart();

        cart.removeProduct(p1);
        System.out.println("Setelah hapus P1:");
        cart.printCart();

        // --- SKENARIO 2: Menggunakan HashMap (ShoppingCartMap) ---
        // Ini penting untuk menguji logika quantity yang Anda buat sebelumnya
        System.out.println("\n--- Test HashMap (Quantity) ---");
        ShoppingCartMap mapCart = new ShoppingCartMap();
        
        mapCart.addProduct(p1); // Tambah Beras (qty jadi 1)
        mapCart.addProduct(p1); // Tambah Beras lagi (qty harusnya jadi 2)
        mapCart.addProduct(p2); // Tambah Pupuk (qty jadi 1)
        
        mapCart.printCart(); // Cek apakah outputnya: Beras x2
    }
}