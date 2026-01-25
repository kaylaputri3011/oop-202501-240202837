package main.java.com.upb.agripos.dao;

import main.java.com.upb.agripos.model.Produk;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlProductRepository implements ProductRepository {
    private final Connection connection;

    public SqlProductRepository(Connection connection) {
        this.connection = connection;
    }

    // 1. SIMPAN (INSERT)
    @Override
    public void save(Produk p) throws Exception {
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getKode());
            ps.setString(2, p.getNama());
            ps.setDouble(3, p.getHarga());
            ps.setInt(4, p.getStok());
            ps.executeUpdate();
        }
    }

    // 2. HAPUS (DELETE)
    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }

    // 3. AMBIL SEMUA (SELECT ALL)
    @Override
    public List<Produk> findAll() throws Exception {
        List<Produk> list = new ArrayList<>();
        String sql = "SELECT * FROM products";
        try (Statement st = connection.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
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

    // 4. CARI BY KODE (SELECT BY ID)
    @Override
    public Produk findByCode(String code) throws Exception {
        String sql = "SELECT * FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Produk(
                        rs.getString("code"),
                        rs.getString("name"),
                        rs.getDouble("price"),
                        rs.getInt("stock")
                    );
                }
            }
        }
        return null; // Kembalikan null jika tidak ketemu
    }

    // 5. UPDATE (EDIT DATA) - SUDAH DIPERBAIKI
    @Override
    public void update(Produk p) throws Exception {
        String sql = "UPDATE products SET name = ?, price = ?, stock = ? WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setDouble(2, p.getHarga());
            ps.setInt(3, p.getStok());
            ps.setString(4, p.getKode()); // Where clause (kunci pencarian)
            ps.executeUpdate();
        }
    }
}