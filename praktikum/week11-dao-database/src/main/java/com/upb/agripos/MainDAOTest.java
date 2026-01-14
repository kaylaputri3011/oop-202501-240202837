package main.java.com.upb.agripos;

import java.sql.Connection;
import java.sql.DriverManager;
import main.java.com.upb.agripos.dao.ProductRepository;
import main.java.com.upb.agripos.dao.SqlProductRepository;
import main.java.com.upb.agripos.model.Produk;

public class MainDAOTest {
    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", 
                "postgres", 
                "111123")) {

            ProductRepository dao = new SqlProductRepository(conn); 
            
            System.out.println("=== 1. Insert Data ===");
            Produk p1 = new Produk("P01", "Pupuk Organik", 25000, 10);
            
            dao.save(p1); 
            p1.tampilkanData(); 

            System.out.println("\n=== 2. Update Data (Tambah Stok) ===");
            
            Produk found = dao.findByCode("P01"); 
            
            if (found != null) {
             
                found.tambahStok(5); 
                found.setNama("Pupuk Organik Super");
                
                System.out.println("Data diupdate (Simulasi).");
            }

            System.out.println("\n=== 3. Cek Hasil Update ===");
            
            Produk updated = dao.findByCode("P01"); 
            
            if (updated != null) {
                updated.tampilkanData();
            }


            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}