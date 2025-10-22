package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.ObatHama;
import main.java.com.upb.agripos.util.CreditBy; 

public class MainPolymorphism {
    public static void main(String[] args) {
        
        System.out.println("=== WEEK4 POLYMORPHISM ===");
        
        System.out.println("\n--- Overloading (tambahStok) ---");
        Produk produkUmum = new Produk("PRD001", "Goni Karung", 15000, 100);

        System.out.print("Panggilan INT: ");
        produkUmum.tambahStok(50); 
        
        System.out.print("Panggilan DOUBLE: ");
        produkUmum.tambahStok(15.9); 

        
        System.out.println("\n--- Overriding (getInfo) & Dynamic Binding ---");
        
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000.0, 100, "IR64");
        

        Pupuk p_urea = new Pupuk("PPK-101", "Pupuk Urea", 350000.0, 40, "Urea");
        Pupuk p_npk = new Pupuk("PPK-102", "Pupuk NPK Phonska", 450000.0, 60, "NPK"); 

    
        AlatPertanian a_baja = new AlatPertanian("ALT-501", "Cangkul Baja", 90000.0, 15, "Baja");
        AlatPertanian a_kayu = new AlatPertanian("ALT-502", "Gagang Pacul", 25000.0, 50, "Kayu");
        AlatPertanian a_plastik = new AlatPertanian("ALT-503", "Sprayer", 150000.0, 25, "Plastik");

        ObatHama obat1 = new ObatHama("OBT033", "Pestisida Cair", 45000, 25, "Cair"); 
        
        Produk[] daftarProduk = {
            b, 
            p_urea, 
            p_npk,
            a_baja,
            a_kayu,
            a_plastik, 
            obat1, 
            produkUmum 
        };
        
        System.out.println("\nHasil getInfo() melalui array Produk[]:");
        for (Produk p : daftarProduk) {
            
            p.getInfo(); 
        }

        
        CreditBy.print("240202837", "Kayla Putri Arsonisr"); 
    }
}