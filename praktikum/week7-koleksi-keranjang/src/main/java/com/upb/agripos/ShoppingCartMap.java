package main.java.com.upb.agripos; 

import java.util.HashMap;
import java.util.Map;


import main.java.com.upb.agripos.Produk; 

public class ShoppingCartMap {
    
    private final Map<Produk, Integer> items = new HashMap<>();

    public void addProduct(Produk p) { 
        
        items.put(p, items.getOrDefault(p, 0) + 1); 
    }

    public void removeProduct(Produk p) {
        if (!items.containsKey(p)) return;
        
        int qty = items.get(p);
        if (qty > 1) {
            items.put(p, qty - 1); // Kurangi jumlah jika lebih dari 1
        } else {
            items.remove(p); // Hapus item jika sisa 1
        }
    }

    public double getTotal() {
        double total = 0;
        for (Map.Entry<Produk, Integer> entry : items.entrySet()) {
            
            total += entry.getKey().getHarga() * entry.getValue();
        }
        return total;
    }

    public void printCart() {
        System.out.println("Isi Keranjang:");
        for (Map.Entry<Produk, Integer> e : items.entrySet()) {
            Produk p = e.getKey();
            int qty = e.getValue();
            // Hitung subtotal per baris
            double subtotal = p.getHarga() * qty;
            
            // Tampilkan: Nama xQty = Rp Subtotal
            System.out.println("- " + p.getNama() + " x" + qty + " = Rp " + subtotal);
        }
        
        System.out.println("Total: " + getTotal());
    }
}