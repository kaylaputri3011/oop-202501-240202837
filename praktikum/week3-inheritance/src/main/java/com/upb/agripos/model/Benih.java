package main.java.com.upb.agripos.model;

public class Benih extends Produk {
    private String varietas;

    public Benih(String kode, String nama, double harga, int stok, String varietas) {
        super(kode, nama, harga, stok);
        this.varietas = varietas;
    }

    public String getVarietas() { return varietas; }
    public void setVarietas(String varietas) { this.varietas = varietas; }

    @Override
    public void tampilkanData() {
        System.out.println("\n--- Detail Produk Benih ---");
        super.tampilkanData(); // Memanggil method tampilkanData() dari class Produk
        System.out.println("  Varietas Benih : " + varietas);
    }

    public void deskripsi() {
        tampilkanData(); // Memanggil tampilkanData() yang sudah di-override
        System.out.println("  Keterangan Tambahan: Benih " + varietas + " ini merupakan varietas unggul dengan ketahanan penyakit yang baik.");
    }
    
}
