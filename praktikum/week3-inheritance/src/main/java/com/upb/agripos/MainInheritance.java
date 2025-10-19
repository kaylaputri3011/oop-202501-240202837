package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        
        System.out.println("=============== PRAKTIKUM INHERITANCE (WEEK 3) ===============");
        
        
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000.0, 100, "IR64");
        

        Pupuk p_urea = new Pupuk("PPK-101", "Pupuk Urea", 350000.0, 40, "Urea");
        Pupuk p_npk = new Pupuk("PPK-102", "Pupuk NPK Phonska", 450000.0, 60, "NPK"); 

    
        AlatPertanian a_baja = new AlatPertanian("ALT-501", "Cangkul Baja", 90000.0, 15, "Baja");
        AlatPertanian a_kayu = new AlatPertanian("ALT-502", "Gagang Pacul", 25000.0, 50, "Kayu");
        AlatPertanian a_plastik = new AlatPertanian("ALT-503", "Sprayer", 150000.0, 25, "Plastik");

        // =============================================================
        // Uji method deskripsi()
        // =============================================================

        System.out.println("\n=============== DATA PRODUK DENGAN DESKRIPSI =================");
        b.deskripsi();
        System.out.println("---------------------------------------------------------------");
        p_urea.deskripsi();
        System.out.println();
        p_npk.deskripsi(); 
        System.out.println("---------------------------------------------------------------");
        a_baja.deskripsi();
        System.out.println();
        a_kayu.deskripsi();
        System.out.println();
        a_plastik.deskripsi();

        System.out.println("\n-------------------------------------------------------------");
        
        System.out.println("INFO QUICK CHECK: Produk " + b.getNama() + ", " + p_urea.getNama() + ", " + p_npk.getNama() + ", " + a_baja.getNama()
         + ", " + a_kayu.getNama() + ", dan " + a_plastik.getNama() + " telah berhasil di-load.");
        
        System.out.println("\n==============================================================");
        
        CreditBy.print("240202837", "Kayla Putri Arsonisr");
    }
    
}