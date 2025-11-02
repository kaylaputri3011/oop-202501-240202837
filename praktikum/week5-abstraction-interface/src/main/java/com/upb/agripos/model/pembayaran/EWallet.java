package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Validatable;
import main.java.com.upb.agripos.model.kontrak.Receiptable;

public class EWallet extends Pembayaran implements Validatable, Receiptable {
    private String akun;
    private String otp; 
    private boolean isProcessed = false; // <-- FIELD BARU untuk Status
    private boolean statusPembayaran = false; // <-- FIELD BARU untuk Hasil Proses

    public EWallet(String invoiceNo, double total, String akun, String otp) {
        super(invoiceNo, total);
        this.akun = akun;
        this.otp = otp;
    }

    @Override
    public double biaya() {
        return total * 0.015;
    }

    @Override
    public boolean validasi() {
        System.out.println("-> Mencoba Validasi OTP/PIN untuk akun " + akun + "...");
        return otp != null && otp.length() == 6; 
    }

    @Override
    public boolean prosesPembayaran() {
        // Hanya proses jika belum pernah diproses
        if (!isProcessed) {
            this.statusPembayaran = validasi(); // Jalankan validasi dan simpan hasilnya
            this.isProcessed = true;
        }
        return this.statusPembayaran; // Kembalikan status yang sudah disimpan
    }

    @Override
    public String cetakStruk() {
        // mengambil status yang sudah tersimpan, sehingga tidak ada panggilan prosesPembayaran() yang menjalankan validasi lagi.
        String status = statusPembayaran ? "BERHASIL" : "GAGAL (Validasi Gagal)";

        return "\n--- STRUK PEMBAYARAN E-WALLET ---\n" +
               "Invoice No: " + invoiceNo + "\n" +
               "Akun E-Wallet: " + akun + "\n" +
               "Total Belanja: " + String.format("%,.2f", total) + "\n" +
               "Biaya (Fee 1.5%): " + String.format("%,.2f", biaya()) + "\n" +
               "**TOTAL BAYAR**: " + String.format("%,.2f", totalBayar()) + "\n" +
               "STATUS: " + status +
               "\n---------------------------------";
    }
}