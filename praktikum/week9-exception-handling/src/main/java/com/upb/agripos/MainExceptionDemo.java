package main.java.com.upb.agripos;

import main.java.com.upb.agripos.Produk;

public class MainExceptionDemo {
    public static void main(String[] args) {
        System.out.println("Hello, I am Kayla-240202837 (Week9 - Exception Handling)");

        ShoppingCart cart = new ShoppingCart();
        // Buat produk dengan stok sedikit (cuma 3)
        Produk p1 = new Produk("P01", "Bibit Durian", 75000, 3);
        Produk p2 = new Produk("P02", "Pupuk NPK", 25000, 10);

        // --- TEST CASE 1: Input Quantity Minus (InvalidQuantityException) ---
        System.out.println("\n--- Test 1: Input Qty Minus ---");
        try {
            cart.addProduct(p1, -5); // Ini akan error
        } catch (InvalidQuantityException e) {
            System.err.println("TERTANGKAP: " + e.getMessage());
        }

        // --- TEST CASE 2: Hapus Produk yg Tidak Ada (ProductNotFoundException) ---
        System.out.println("\n--- Test 2: Hapus Produk Gaib ---");
        try {
            cart.removeProduct(p2); // p2 belum pernah dimasukkan
        } catch (ProductNotFoundException e) {
            System.err.println("TERTANGKAP: " + e.getMessage());
        }

        // --- TEST CASE 3: Beli Melebihi Stok (InsufficientStockException) ---
        System.out.println("\n--- Test 3: Checkout Melebihi Stok ---");
        try {
            cart.addProduct(p1, 5); // Stok cuma 3, minta 5
            cart.printCart();
            cart.checkout(); // Ini harusnya error
        } catch (InvalidQuantityException | InsufficientStockException e) {
            // Multi-catch block
            System.err.println("TERTANGKAP: " + e.getMessage());
        } finally {
            System.out.println("INFO: Blok finally selalu dijalankan (bersih-bersih resource).");
        }
    }
}