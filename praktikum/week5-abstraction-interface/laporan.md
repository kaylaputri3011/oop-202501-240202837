# Laporan Praktikum Minggu 5
Topik: Abstraksi dan Interface (Penerapan Polimorfisme)
## Identitas
- Nama  : Kayla Putri Arsonisr
- NIM   : 240202837
- Kelas : 3IKRA

---

## Tujuan
1. Memahami dan dapat mengimplementasikan konsep Abstract Class untuk menciptakan kerangka kerja (blueprint) yang tidak lengkap.
2. Memahami dan dapat mengimplementasikan Interface untuk mendefinisikan kontrak perilaku (metode) yang wajib diimplementasikan.
3. Dapat mendemonstrasikan Polimorfisme melalui upcasting dari kelas konkret ke tipe data Abstract Class dan casting ke Interface.
4. Dapat menerapkan Multiple Inheritance (perilaku) melalui penggunaan interface.
---

## Dasar Teori
1. Abstraksi (Abstract Class): Adalah kelas yang tidak dapat diinstansiasi menjadi objek secara langsung. Bertujuan untuk mendefinisikan kerangka kerja umum dan kontrak melalui abstract method. Abstract class dapat memiliki method konkret dan field (state).
2. Interface: Mendefinisikan kontrak perilaku murni (hanya memiliki abstract method secara implisit). Digunakan untuk mencapai multiple inheritance pada perilaku, memungkinkan satu kelas memiliki banyak tipe.
3. Polimorfisme: Kemampuan objek untuk mengambil banyak bentuk. Dalam konteks ini, Pembayaran cash = new Cash(...) menunjukkan polimorfisme di mana objek Cash diperlakukan sebagai tipe data Pembayaran.
4. Kontrak Wajib: Setiap abstract method (di abstract class) atau method di interface wajib diimplementasikan oleh kelas turunannya atau kelas yang mengimplementasikannya.

---

## Langkah Praktikum
1. Setup Proyek: Membuat struktur package com.upb.agripos.model.pembayaran, com.upb.agripos.model.kontrak, com.upb.agripos.util, dan com.upb.agripos.
2. Pembuatan Abstraksi: Mendefinisikan Pembayaran.java sebagai abstract class dengan abstract method biaya() dan prosesPembayaran().
3. Pembuatan Interface: Mendefinisikan Validatable.java dan Receiptable.java sebagai interface di package kontrak.
4. Implementasi Subclass: Membuat Cash.java, EWallet.java, dan TransferBank.java (Latihan Mandiri).
   a. Cash meng-extend Pembayaran dan mengimplementasikan Receiptable.
   b. EWallet dan TransferBank meng-extend Pembayaran dan mengimplementasikan Validatable serta Receiptable.
5. Main Program: Membuat MainAbstraction.java untuk mendemonstrasikan upcasting (Pembayaran namaObj = new Subclass(...)) dan casting ke interface ((Receiptable) namaObj).
6. Eksekusi: Mengompilasi dan menjalankan program untuk menampilkan struk pembayaran dari ketiga metode.

---

## Kode Program
1. Pembayaran.java
package main.java.com.upb.agripos.model.pembayaran;

public abstract class Pembayaran {
    protected String invoiceNo;
    protected double total;

    public Pembayaran(String invoiceNo, double total) {
        this.invoiceNo = invoiceNo;
        this.total = total;
    }

    public abstract double biaya();               // fee/biaya tambahan
    public abstract boolean prosesPembayaran();   // proses spesifik tiap metode

    public double totalBayar() {
        return total + biaya();
    }

    public String getInvoiceNo() { return invoiceNo; }
    public double getTotal() { return total; }
}
2. Cash.java
package main.java.com.upb.agripos.model.pembayaran;

import main.java.com.upb.agripos.model.kontrak.Receiptable;

public class Cash extends Pembayaran implements Receiptable {
    private double tunai;

    public Cash(String invoiceNo, double total, double tunai) {
        super(invoiceNo, total);
        this.tunai = tunai;
    }

    @Override
    public double biaya() {
        return 0.0;
    }

    @Override
    public boolean prosesPembayaran() {
        return tunai >= totalBayar(); // Berhasil jika cukup uang tunai
    }

    @Override
    public String cetakStruk() {
        boolean berhasil = prosesPembayaran();
        String status = berhasil ? "BERHASIL" : "GAGAL (Tunai Kurang)";
        double kembalian = berhasil ? (tunai - totalBayar()) : 0.0;
        
        return "\n--- STRUK PEMBAYARAN TUNAI ---\n" +
               "Invoice No: " + invoiceNo + "\n" +
               "Total Belanja: " + String.format("%,.2f", total) + "\n" +
               "Biaya (Fee): " + String.format("%,.2f", biaya()) + "\n" +
               "**TOTAL BAYAR**: " + String.format("%,.2f", totalBayar()) + "\n" +
               "Tunai Diberikan: " + String.format("%,.2f", tunai) + "\n" +
               "Kembalian: " + String.format("%,.2f", kembalian) + "\n" +
               "STATUS: " + status +
               "\n------------------------------";
    }
}
3. EWallet.java
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
4. TransferBank.java
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
5. Receiptable.java
package main.java.com.upb.agripos.model.kontrak;

public interface Receiptable {
    String cetakStruk();
}
6. Validatable.java
package main.java.com.upb.agripos.model.kontrak;

public interface Validatable {
    boolean validasi(); // misal validasi OTP/ PIN
}
7. CreditBy.java
package main.java.com.upb.agripos.util; 

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
8. MainAbstraction.java
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.pembayaran.Pembayaran;
import main.java.com.upb.agripos.model.pembayaran.Cash;
import main.java.com.upb.agripos.model.pembayaran.EWallet;
import main.java.com.upb.agripos.model.pembayaran.TransferBank; 
import main.java.com.upb.agripos.model.kontrak.Receiptable;
import main.java.com.upb.agripos.util.CreditBy;

public class MainAbstraction {
    public static void main(String[] args) {

        System.out.println("=== WEEK 5: ABSTRACTION INTERFACE ===");
        System.out.println("\n--------------------------------\n");
        
        // --- 1. CASH (Tunai Cukup) ---
        Pembayaran cash = new Cash("INV-C01", 100000, 120000);
        System.out.println("=== PEMBAYARAN CASH ===");
        cash.prosesPembayaran(); // Memproses
        System.out.println(((Receiptable) cash).cetakStruk()); // Mencetak Struk
        
        System.out.println("\n--------------------------------\n");

        // --- 2. E-WALLET (Validasi Berhasil) ---
        Pembayaran ew = new EWallet("INV-E01", 150000, "ella@ewallet", "257845");
        System.out.println("=== PEMBAYARAN E-WALLET ===");
        ew.prosesPembayaran(); // Memanggil Validasi
        System.out.println(((Receiptable) ew).cetakStruk()); // Mencetak Struk

        System.out.println("\n--------------------------------\n");

        // --- 3. TRANSFER BANK (Validasi Berhasil) ---
        // Biaya: Rp3.500,00
        Pembayaran transfer = new TransferBank("INV-T01", 75000, "014"); 
        System.out.println("=== PEMBAYARAN TRANSFER BANK ===");
        transfer.prosesPembayaran(); // Memanggil Validasi
        System.out.println(((Receiptable) transfer).cetakStruk());

        System.out.println("\n--------------------------------\n");
        
        // --- 4. TRANSFER BANK (Validasi GAGAL) ---
        Pembayaran transferGagal = new TransferBank("INV-T02", 5000, "ABC"); 
        System.out.println("=== TRANSFER BANK (GAGAL) ===");
        transferGagal.prosesPembayaran(); 
        System.out.println(((Receiptable) transferGagal).cetakStruk());

        
        // --- 3 - Panggilan CreditBy
        CreditBy.print("240202837", "Kayla Putri Arsonisr");
    }
}

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![alt text](<Screenshot (163).png>) ![alt text](<Screenshot (164).png>)
)
---

## Analisis
(
- Jelaskan bagaimana kode berjalan. 
Kode program berjalan dengan mendefinisikan tiga objek pembayaran (cash, ew, transfer) menggunakan tipe referensi Pembayaran (polimorfisme). Ketika prosesPembayaran() dipanggil, mesin Java secara dinamis (run-time) memanggil implementasi yang sesuai dari kelas konkret (Cash, EWallet, atau TransferBank). Kemudian, objek di-casting ke Receiptable agar metode cetakStruk() dapat dipanggil, menunjukkan bahwa ketiga kelas tersebut memiliki kemampuan untuk mencetak struk, meskipun struk tersebut dihasilkan oleh tiga kelas berbeda. Panggilan ke prosesPembayaran() pada EWallet dan TransferBank memicu eksekusi validasi() dari interface Validatable. 
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
Pada minggu sebelumnya hanya untuk menampilkan daftar produk yaitu kode produk, nama produk, harga, stok, jenis produk, deskripsi produk dan penambah pengurangan produk dengan metode int dan double. Pada minggu ini atau minggu ke 5 sudah memasuki tentang pembayaran, seperti bayar pakai cash, ewallet, dan transfer bank serta mencetak struk untuk pembayaran cash dan ewallet. Serta juga validasi untuk pembayaran ewallet dan transferbank.
- Kendala yang dihadapi dan cara mengatasinya.  
Kendala utama adalah memastikan semua abstract method di abstract class dan method di interface diimplementasikan dengan benar di setiap subclass.
Awalnya, terjadi duplikasi pesan validasi pada EWallet dan TransferBank karena cetakStruk() memanggil prosesPembayaran() (yang menjalankan validasi) lagi. Solusinya adalah memastikan prosesPembayaran() dipanggil secara eksplisit sebelum mencetak struk, meskipun cetakStruk() tetap perlu memanggilnya untuk mendapatkan status terakhir.
)

---

## Kesimpulan
Praktikum ini berhasil membuktikan bahwa Abstract Class (Pembayaran) sangat efektif sebagai kerangka kerja wajib, memaksa subclass untuk mengimplementasikan fungsionalitas inti (biaya dan proses). Sementara itu, Interface (Validatable, Receiptable) memberikan fleksibilitas untuk menambahkan kemampuan plug-and-play ke kelas konkret, mencapai Multiple Inheritance of Behavior yang merupakan ciri khas PBO modern. Penggunaan kedua konsep ini memfasilitasi Polimorfisme yang kuat, memungkinkan sistem Agri-POS memperlakukan semua metode pembayaran secara seragam.

---

## Quiz
1. Jelaskan perbedaan konsep dan penggunaan abstract class dan interface.
Jawaban:
a. Abstract Class (AC): Digunakan untuk membangun kerangka kerja dengan fokus pada hubungan is-a yang kuat (inheritance). AC dapat memiliki field (state), method konkret (berisi implementasi), constructor, dan abstract method. Tujuannya adalah menyediakan dasar yang harus diwarisi dan diselesaikan.
b. Interface (I): Digunakan untuk mendefinisikan kontrak perilaku dengan fokus pada hubungan can-do. Interface secara implisit memiliki abstract method saja (sejak Java 8 ke atas, dapat memiliki default method). Interface tidak dapat memiliki state dan digunakan untuk mencapai multiple inheritance pada perilaku.
2. Mengapa multiple inheritance lebih aman dilakukan dengan interface pada Java?
Jawaban:
a. Multiple inheritance pada kelas di Java dilarang karena dapat menyebabkan Diamond Problem (masalah ambiguitas pewarisan state dan method konkret).
b. Interface aman karena hanya mendefinisikan method tanpa state (data) atau implementasi konkret. Oleh karena itu, ketika sebuah kelas mengimplementasikan dua interface yang memiliki method dengan tanda tangan yang sama, tidak ada ambiguitas; kelas tersebut hanya perlu menyediakan satu implementasi untuk method tersebut.
3. Pada contoh Agri-POS, bagian mana yang paling tepat menjadi abstract class dan mana yang menjadi interface? Jelaskan alasannya.
Jawaban:
a. Abstract Class: Pembayaran. Alasannya, semua metode pembayaran pasti memiliki properti yang sama (invoiceNo, total) dan memerlukan dua proses inti yang berbeda-beda (biaya() dan prosesPembayaran()). Ini adalah hubungan is-a: Cash adalah Pembayaran, EWallet adalah Pembayaran.
b. Interface: Validatable dan Receiptable. Alasannya:
- Validatable adalah perilaku opsional; tidak semua pembayaran (Cash) membutuhkannya. Ini adalah hubungan can-do: EWallet bisa divalidasi.
- Receiptable adalah kemampuan; semua pembayaran bisa mencetak struk, menjadikannya kontrak yang dapat diterapkan secara universal tanpa memengaruhi hierarki inheritance utama.