package main.java.com.upb.agripos.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import main.java.com.upb.agripos.model.Produk;

public class SqlProductRepository implements ProductRepository {

    private final Connection connection;

    public SqlProductRepository(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void save(Produk p) throws Exception {
        // Nama kolom di database TETAP bahasa Inggris (sesuai CREATE TABLE)
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            // Mapping: Java (Indo) -> Database (Inggris)
            ps.setString(1, p.getKode());
            ps.setString(2, p.getNama());
            ps.setDouble(3, p.getHarga());
            ps.setInt(4, p.getStok());
            ps.executeUpdate();
        }
    }

    @Override
    public Produk findByCode(String code) throws Exception {
        String sql = "SELECT * FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    // Mapping: Database (Inggris) -> Java (Indo)
                    return new Produk(
                        rs.getString("code"),  // Ambil kolom 'code'
                        rs.getString("name"),  // Ambil kolom 'name'
                        rs.getDouble("price"), // Ambil kolom 'price'
                        rs.getInt("stock")     // Ambil kolom 'stock'
                    );
                }
            }
        }
        return null;
    }

    @Override
    public List<Produk> findAll() throws Exception {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (PreparedStatement ps = connection.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                list.add(new Produk(
                    rs.getString("code"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock")
                ));
            }
        }
        return list;
    }

    @Override
    public void update(Produk p) throws Exception {
        String sql = "UPDATE products SET name=?, price=?, stock=? WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setDouble(2, p.getHarga());
            ps.setInt(3, p.getStok());
            ps.setString(4, p.getKode());
            ps.executeUpdate();
        }
    }

    @Override
    public void delete(String kode) throws Exception {
        String sql = "DELETE FROM products WHERE code=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, kode);
            ps.executeUpdate();
        }
    }
}