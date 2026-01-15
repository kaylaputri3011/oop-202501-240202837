package main.java.com.upb.agripos.model;

public class CartItem {
    private Produk produk;
    private int qty;

    public CartItem(Produk produk, int qty) {
        this.produk = produk;
        this.qty = qty;
    }

    public double getSubtotal() {
        return produk.getHarga() * qty;
    }

    public Produk getProduk() { return produk; }
    public int getQty() { return qty; }
    public void addQty(int n) { this.qty += n; }

    @Override
    public String toString() {
        return produk.getNama() + " x" + qty + " = Rp" + String.format("%,.0f", getSubtotal());
    }
}