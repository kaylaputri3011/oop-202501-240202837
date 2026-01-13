package main.java.com.upb.agripos.dao;

import java.util.List;
import main.java.com.upb.agripos.model.Produk; // Import class Produk baru

public interface ProdukDAO {
    void insert(Produk produk) throws Exception;
    Produk findByKode(String kode) throws Exception; // findByCode -> findByKode
    List<Produk> findAll() throws Exception;
    void update(Produk produk) throws Exception;
    void delete(String kode) throws Exception;
}