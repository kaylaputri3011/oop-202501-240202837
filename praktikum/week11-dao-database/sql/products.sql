-- File: sql/products.sql
-- Database: PostgreSQL (agripos)

-- 1. Hapus Tabel jika sudah ada
DROP TABLE IF EXISTS products;

-- 2. Buat Tabel sesuai spesifikasi minimal
CREATE TABLE products (
    code VARCHAR(10) PRIMARY KEY,
    name VARCHAR(100),
    price DOUBLE PRECISION,
    stock INT
);

-- 3. Cek tabel (Opsional)
SELECT * FROM products;