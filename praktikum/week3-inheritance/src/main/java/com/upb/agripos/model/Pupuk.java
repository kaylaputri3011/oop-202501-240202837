package main.java.com.upb.agripos.model;

public class Pupuk extends Produk {
    private String jenis;

    public Pupuk(String kode, String nama, double harga, int stok, String jenis) {
        super(kode, nama, harga, stok);
        this.jenis = jenis;
    }

    public String getJenis() { return jenis; }
    public void setJenis(String jenis) { this.jenis = jenis; }

    @Override
    public void tampilkanData() {
        System.out.println("\n--- Detail Produk Pupuk ---");
        super.tampilkanData(); // Memanggil tampilkanData() dari class Produk
        System.out.println("  Jenis Pupuk    : " + jenis); // Menampilkan atribut khusus
    }
    
    public void deskripsi() {
        tampilkanData(); 
        String ket;
        if (jenis.equalsIgnoreCase("NPK")) {
            ket = "Pupuk NPK adalah pupuk majemuk yang mengandung Nitrogen, Fosfor, dan Kalium untuk nutrisi lengkap.";
        } else if (jenis.equalsIgnoreCase("Urea")) {
            ket = "Pupuk Urea disarankan untuk meningkatkan unsur hara N (Nitrogen) pada fase vegetatif.";
        } else {
            ket = "Pupuk ini adalah jenis " + jenis + " dan digunakan sesuai kebutuhan spesifik lahan.";
        }
        System.out.println("  Keterangan Tambahan: " + ket);
    }

}
