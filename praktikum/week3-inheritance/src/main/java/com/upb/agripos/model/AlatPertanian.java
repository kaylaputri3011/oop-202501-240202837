package main.java.com.upb.agripos.model;

public class AlatPertanian extends Produk {
    private String material;

    public AlatPertanian(String kode, String nama, double harga, int stok, String material) {
        super(kode, nama, harga, stok);
        this.material = material;
    }

    public String getMaterial() { return material; }
    public void setMaterial(String material) { this.material = material; }

    @Override
    public void tampilkanData() {
        System.out.println("\n--- Detail Produk Alat Pertanian ---");
        super.tampilkanData(); // Call the tampilkanData() method from the Produk class
        System.out.println("  Material       : " + material); // Display the specific attribute
    }

    public void deskripsi() {
        tampilkanData(); 
        String ket;
        if (material.equalsIgnoreCase("Kayu")) {
            ket = "Alat berbahan Kayu ini ringan dan umumnya digunakan untuk pekerjaan meratakan atau membersihkan lahan kering.";
        } else if (material.equalsIgnoreCase("Plastik")) {
            ket = "Alat berbahan Plastik seperti sprayer sangat efektif untuk penyemprotan cairan karena sifatnya yang tahan karat.";
        } else { // Termasuk Baja
            ket = "Alat ini dibuat dari bahan " + material + " yang dirancang ergonomis untuk pekerjaan berat.";
        }
        System.out.println("  Keterangan Tambahan: " + ket);
    }

    public String getJenis() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getJenis'");
    }
    
}
