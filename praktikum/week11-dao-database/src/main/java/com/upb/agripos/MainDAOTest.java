package main.java.com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import main.java.com.upb.agripos.dao.ProdukDAO;
import main.java.com.upb.agripos.dao.ProdukDAOImpl;
import main.java.com.upb.agripos.model.Produk;

public class MainDAOTest {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", 
                "postgres", 
                "111123")) {

            ProdukDAO dao = new ProdukDAOImpl(conn);
            
            System.out.println("=== 1. Insert Data ===");
            Produk p1 = new Produk("P01", "Pupuk Organik", 25000, 10);
            dao.insert(p1);
            p1.tampilkanData(); // Menggunakan method baru di Model

            System.out.println("\n=== 2. Update Data (Tambah Stok) ===");
            Produk found = dao.findByKode("P01");
            if (found != null) {
                // Menggunakan logika bisnis di Model
                found.tambahStok(5); 
                found.setNama("Pupuk Organik Super");
                
                // Simpan perubahan ke database
                dao.update(found);
                System.out.println("Data berhasil diupdate ke Database.");
            }

            System.out.println("\n=== 3. Cek Hasil Update ===");
            Produk updated = dao.findByKode("P01");
            if (updated != null) {
                updated.tampilkanData();
            }

            // Uncomment baris di bawah ini jika ingin menghapus data setelah test
            // dao.delete("P01"); 

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}