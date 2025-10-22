package main.java.com.upb.agripos.model;

public class ObatHama extends Produk {
    private String bentuk; 

    
    public ObatHama(String kode, String nama, double harga, int stok, String bentuk) {
        super(kode, nama, harga, stok);
        this.bentuk = bentuk;
    }

    public String getBentuk() { return bentuk; }
    public void setBentuk(String bentuk) { this.bentuk = bentuk; }

    
    @Override
    public void getInfo() {
        System.out.println("--- Detail OBAT HAMA ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Bentuk Fisik: " + bentuk);
        System.out.println(" Harga (Rp): " + getHarga()); 
        System.out.println(" Stok: " + getStok() + " botol/kemasan.");
    }
}