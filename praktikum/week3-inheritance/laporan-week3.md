# Laporan Praktikum Minggu 3
Topik: Inheritance (Kategori Produk)

## Identitas
- Nama  : kayla putri arsonisr
- NIM   : 240202837
- Kelas : 3IKRA

---

## Tujuan
1. memahami konsep Pewarisan (Inheritance) dalam Pemrograman Berorientasi Objek.
2. Dapat mengimplementasikan Superclass (Produk) dan Subclass (Benih, Pupuk, AlatPertanian).
3. Mampu menerapkan Method Overriding untuk menyesuaikan perilaku pada Subclass.
4. Dapat membuat method tambahan (deskripsi()) pada Subclass sebagai implementasi Polymorphism.
---

## Dasar Teori
1. Inheritance (Pewarisan): Mekanisme di mana sebuah kelas (Subclass) dapat mewarisi atribut dan method dari kelas lain (Superclass). Inheritance mempromosikan reusabilitas kode.
2. Superclass (Parent Class): Kelas dasar yang mewariskan properti dan perilaku kepada Subclass. Dalam kasus ini, Produk adalah Superclass.
3. Subclass (Child Class): Kelas turunan yang mewarisi Superclass. Subclass dapat memiliki atribut dan method spesifiknya sendiri. Subclass dideklarasikan menggunakan kata kunci extends.
4. super() dan super.method(): super() digunakan untuk memanggil constructor Superclass dari dalam constructor Subclass, memastikan inisialisasi atribut yang diwarisi. super.method() digunakan untuk memanggil implementasi method dari Superclass yang mungkin telah di-override.
5. Method Overriding: Kemampuan Subclass untuk mendefinisikan ulang (menulis ulang) sebuah method yang sudah didefinisikan di Superclass, tujuannya adalah memberikan implementasi yang spesifik untuk Subclass tersebut (misalnya, tampilkanData()).

---

## Langkah Praktikum
1. Setup Proyek: Membuat struktur direktori dan package sesuai standar (com.upb.agripos.model/, com.upb.agripos.util/, dll.).
2. Implementasi Superclass: Membuat kelas Produk.java sebagai Superclass dengan atribut dasar (kode, nama, harga, stok) dan method tampilkanData(). Atribut disetel sebagai protected.
3. Implementasi Subclass (Tugas 1 & 2): Membuat tiga Subclass (Benih, Pupuk, AlatPertanian) yang menggunakan extends Produk. Masing-masing Subclass menambahkan atribut khusus (varietas, jenis, material).
4. Overriding dan Latihan Mandiri: Meng-override method tampilkanData() di setiap Subclass untuk menyertakan atribut spesifiknya. Menambahkan method deskripsi() untuk menunjukkan informasi produk yang lebih rinci.
5. Implementasi Utilitas: Membuat kelas CreditBy.java di package com.upb.agripos.util untuk menampilkan identitas mahasiswa.
6. Implementasi Main Class (Tugas 3): Membuat MainInheritance.java untuk menginstansiasi objek dari setiap Subclass (termasuk objek tambahan seperti Pupuk NPK, Alat Kayu, dan Plastik). Program mengimpor semua kelas yang diperlukan.
7. Uji Coba: Menjalankan program dan menguji semua method: getter (quick check), tampilkanData(), dan deskripsi().

---

## Kode Program
1. Produk.java
package main.java.com.upb.agripos.model;
// Produk.java

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    private int stok;

    public Produk(String kode, String nama, double harga, int stok) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.stok = stok;
    }

    public String getKode() { return kode; }
    public void setKode(String kode) { this.kode = kode; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public double getHarga() { return harga; }
    public void setHarga(double harga) { this.harga = harga; }

    public int getStok() { return stok; }
    public void setStok(int stok) { this.stok = stok; }

    public void tambahStok(int jumlah) {
        if (jumlah > 0) {
            this.stok += jumlah;
        } else {
            System.out.println("Jumlah stok yang ditambahkan tidak boleh ditambahkan!");
            
        }
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
        } else {
            System.out.println("Stok tidak mencukupi!");
        }
    }
    
    public void tampilkanData() {
        System.out.println("  Kode Produk: " + kode);
        System.out.println("  Nama Produk: " + nama);
        System.out.println("  Harga (Rp): " + harga);
        System.out.println("  Stok Tersedia: " + stok);
    }
}

2. Benih.java
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

3. Pupuk.java
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

4. AlatPertanian.java
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

5. MainInheritance
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.util.CreditBy;

public class MainInheritance {
    public static void main(String[] args) {
        
        System.out.println("=============== PRAKTIKUM INHERITANCE (WEEK 3) ===============");
        
        
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000.0, 100, "IR64");
        

        Pupuk p_urea = new Pupuk("PPK-101", "Pupuk Urea", 350000.0, 40, "Urea");
        Pupuk p_npk = new Pupuk("PPK-102", "Pupuk NPK Phonska", 450000.0, 60, "NPK"); 

    
        AlatPertanian a_baja = new AlatPertanian("ALT-501", "Cangkul Baja", 90000.0, 15, "Baja");
        AlatPertanian a_kayu = new AlatPertanian("ALT-502", "Gagang Pacul", 25000.0, 50, "Kayu");
        AlatPertanian a_plastik = new AlatPertanian("ALT-503", "Sprayer", 150000.0, 25, "Plastik");

        // =============================================================
        // Uji method deskripsi()
        // =============================================================

        System.out.println("\n=============== DATA PRODUK DENGAN DESKRIPSI =================");
        b.deskripsi();
        System.out.println("---------------------------------------------------------------");
        p_urea.deskripsi();
        System.out.println();
        p_npk.deskripsi(); 
        System.out.println("---------------------------------------------------------------");
        a_baja.deskripsi();
        System.out.println();
        a_kayu.deskripsi();
        System.out.println();
        a_plastik.deskripsi();

        System.out.println("\n-------------------------------------------------------------");
        
        System.out.println("INFO QUICK CHECK: Produk " + b.getNama() + ", " + p_urea.getNama() + ", " + p_npk.getNama() + ", " + a_baja.getNama()
         + ", " + a_kayu.getNama() + ", dan " + a_plastik.getNama() + " telah berhasil di-load.");
        
        System.out.println("\n==============================================================");
        
        CreditBy.print("240202837", "Kayla Putri Arsonisr");
    }
    
}

6. CreditBy.java
package main.java.com.upb.agripos.util; 

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil] ![alt text] ![alt text] ![alt text](<Screenshot (128).png>) ![alt text](<Screenshot (129).png>)
)
---

## Analisis
1. Praktikum minggu ini berfokus pada Inheritance. Konsep ini memungkinkan Benih, Pupuk, dan AlatPertanian mewarisi semua properti dasar dari Produk tanpa harus mendefinisikan ulang atribut seperti kode, nama, harga, dan stok.

2. Pendekatan praktikum Minggu 2 dan Minggu 3 memiliki perbedaan fokus yang mendasar dalam implementasi OOP. Minggu 2, yang berfokus pada Class dan Object serta Enkapsulasi, bertujuan utama pada keamanan data (data hiding) dan kontrol vertikal terhadap sebuah unit data tunggal, di mana atribut seperti stok dideklarasikan sebagai private dan diakses melalui getter serta setter yang ketat. Sebaliknya, pendekatan Minggu 3, yaitu Pewarisan (Inheritance), bertujuan pada reusabilitas kode dan spesialisasi kelas. Inheritance menciptakan hierarki horizontal di mana Subclass (seperti Benih) dapat memperluas fungsionalitas Superclass (Produk) menggunakan kata kunci extends. Dalam konteks ini, hak akses atribut Superclass diubah menjadi protected untuk memfasilitasi akses langsung oleh Subclass, memungkinkan pengembangan fitur khusus (seperti deskripsi()) tanpa perlu menulis ulang kode dasar yang sudah diwarisi.

3. a. Kendala: Kesalahan kompilasi saat membuat constructor Subclass karena lupa memanggil super().
   b. Cara Mengatasi: Menambahkan super(kode, nama, harga, stok); sebagai baris pertama di setiap constructor Subclass untuk memenuhi persyaratan Java.

---

## Kesimpulan
Pewarisan (Inheritance) adalah mekanisme fundamental OOP yang sangat efektif untuk membangun hierarki kelas. Dengan Inheritance, kami berhasil membuat berbagai jenis produk pertanian yang memiliki sifat dasar yang sama (Produk) namun memiliki perilaku dan atribut spesifik yang berbeda, menunjukkan penerapan Polymorphism melalui Method Overriding secara efektif.

---

## Quiz
1. Apa keuntungan menggunakan inheritance dibanding membuat class terpisah tanpa hubungan?
    Jawaban: Keuntungan utama menggunakan Inheritance adalah reusabilitas kode dan kemudahan pemeliharaan. Subclass secara otomatis mewarisi atribut dan method dari Superclass, sehingga kode tidak perlu ditulis berulang kali. Ini juga menciptakan struktur hierarki yang jelas, mendukung prinsip Polymorphism dan mempermudah penambahan fitur baru di masa depan.

2. Bagaimana cara subclass memanggil konstruktor superclass?
    Jawaban: Subclass memanggil constructor Superclass menggunakan kata kunci super() sebagai baris perintah pertama di dalam constructor Subclass tersebut. Contoh: super(kode, nama, harga, stok);.

3. Berikan contoh kasus di POS pertanian selain Benih, Pupuk, dan Alat Pertanian yang bisa dijadikan subclass.
    Jawaban: Contoh lain yang dapat dijadikan Subclass dari Produk adalah Pestisida. Atribut spesifik yang dimilikinya bisa berupa zatAktif (String), dosisAnjuran (String), dan targetHama (String).