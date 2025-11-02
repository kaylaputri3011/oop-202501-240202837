package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Receiptable;
import main.java.com.upb.agripos.model.kontrak.Validatable;

public class TransferBank extends Pembayaran implements Validatable, Receiptable {
    private final double BIAYA_TETAP = 3500.00;
    private String kodeBank;
    private boolean isProcessed = false; // Status apakah proses pembayaran sudah dijalankan
    private boolean statusPembayaran = false; // Hasil dari proses/validasi yang tersimpan


    public TransferBank(String invoiceNo, double total, String kodeBank) {
        super(invoiceNo, total);
        this.kodeBank = kodeBank;
    }

    @Override
    public double biaya() {
        return BIAYA_TETAP; // Biaya tetap Rp3.500,00
    }

    @Override
    public boolean validasi() {
        // Contoh validasi: kode bank harus 3 digit angka
        boolean valid = kodeBank != null && kodeBank.matches("\\d{3}");
        System.out.println("-> Mencoba Validasi Kode Bank (" + kodeBank + ")... " + (valid ? "BERHASIL" : "GAGAL (Kode Invalid)"));
        return valid; 
    }

    @Override
    public boolean prosesPembayaran() {
        if (!isProcessed) {
            this.statusPembayaran = validasi(); // Jalankan validasi dan simpan hasilnya
            this.isProcessed = true;
        }
        return this.statusPembayaran;
    }

    @Override
    public String cetakStruk() {
        // Panggilan prosesPembayaran() di sini akan menjalankan validasi
        boolean berhasil = prosesPembayaran();
        String status = berhasil ? "BERHASIL" : "GAGAL (Validasi Gagal)";

        return "\n--- STRUK PEMBAYARAN TRANSFER BANK ---\n" +
               "Invoice No: " + invoiceNo + "\n" +
               "Kode Bank: " + kodeBank + "\n" +
               "Total Belanja: " + String.format("%,.2f", total) + "\n" +
               "Biaya (Fee Tetap): " + String.format("%,.2f", biaya()) + "\n" +
               "**TOTAL BAYAR**: " + String.format("%,.2f", totalBayar()) + "\n" +
               "STATUS: " + status +
               "\n----------------------------------------";
    }
}