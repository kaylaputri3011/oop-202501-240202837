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

    @Override
    public void save(Produk p) throws Exception {
        // ... (Kode save minggu lalu tetap sama) ...
        String sql = "INSERT INTO products(code, name, price, stock) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, p.getKode());
            ps.setString(2, p.getNama());
            ps.setDouble(3, p.getHarga());
            ps.setInt(4, p.getStok());
            ps.executeUpdate();
        }
    }

    // --- TAMBAHAN BARU: DELETE ---
    @Override
    public void delete(String code) throws Exception {
        String sql = "DELETE FROM products WHERE code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, code);
            ps.executeUpdate();
        }
    }

    @Override
    public List<Produk> findAll() throws Exception {
        // ... (Kode findAll minggu lalu tetap sama) ...
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

    @Override
    public Produk findByCode(String code) throws Exception {
        // ... (Implementasi findByCode jika diperlukan) ...
        return null;
    }

    @Override
    public void update(Produk p) throws Exception {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }
}