# Laporan Praktikum Minggu 4
Topik: Polymorphism (Overloading, Overriding, dan Dynamic Binding)

## Identitas
- Nama  : kayla putri arsonisr
- NIM   : 240202837
- Kelas : 3ikra

---

## Tujuan
1. Memahami dan mengimplementasikan konsep Method Overloading dengan membuat dua versi method tambahStok().
2. Memahami dan mengimplementasikan konsep Method Overriding dengan meng-override method getInfo() pada setiap subclass (Benih, Pupuk, AlatPertanian, ObatHama).
3. Mendemonstrasikan Dynamic Binding (Polymorphism Dinamis) melalui penggunaan array bertipe superclass (Produk[]) yang berisi objek-objek subclass.
---

## Dasar Teori
1. Polymorphism (Banyak Bentuk): Kemampuan suatu objek untuk mengambil banyak bentuk. Dalam Java, polymorphism dibagi menjadi dua jenis:
2. Static Polymorphism (Overloading): Terjadi saat kompilasi (compile-time), di mana method yang dipanggil ditentukan oleh signature (jumlah dan tipe parameter) method.
3. Dynamic Polymorphism (Overriding & Dynamic Binding): Terjadi saat runtime, di mana method yang dieksekusi ditentukan oleh tipe objek aktual yang disimpan dalam variabel referensi.
4. Overloading: Mendefinisikan dua atau lebih method dalam satu class yang memiliki nama yang sama tetapi memiliki parameter yang berbeda (berbeda jumlah atau berbeda tipe data).
5. Overriding: Mendefinisikan ulang method yang sudah ada di superclass oleh subclass. Method di subclass harus memiliki signature yang sama persis dengan method di superclass.
6. Dynamic Binding: Mekanisme yang menentukan method mana yang akan dipanggil pada saat program berjalan (runtime), terutama ketika menggunakan variabel referensi superclass untuk menunjuk ke objek subclass.

---

## Langkah Praktikum
1. Modifikasi Superclass Produk.java:
   a. Menambahkan method overloading tambahStok(double jumlah) di samping tambahStok(int jumlah).
   b. Mengubah method tampilkanData() menjadi getInfo().
2. Implementasi Subclass:
   a. Membuat subclass Benih, Pupuk, AlatPertanian, dan ObatHama.
   b. Setiap subclass meng-override method getInfo() (Tugas 2) untuk menampilkan detail spesifik produk tersebut.
3. Implementasi Main Class MainPolymorphism.java:
   a. Mendemonstrasikan overloading dengan memanggil produkUmum.tambahStok() menggunakan argumen int dan double.
   b. Membuat array Produk[] daftarProduk yang menampung objek dari semua subclass (Tugas 3).
   c. Melakukan looping pada daftarProduk dan memanggil getInfo() pada setiap elemen untuk mendemonstrasikan dynamic binding.
4. Kompilasi dan Eksekusi: Seluruh kode dikompilasi dan dieksekusi, memastikan output sesuai dengan implementasi overriding.
---

## Kode Program
1. Produk.java
package main.java.com.upb.agripos.model;
// Produk.java

public class Produk {
    private String kode;
    private String nama;
    private double harga;
    protected int stok;

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
            System.out.println("Stok " + nama + " bertambah " + jumlah + " (int). Stok baru: " + this.stok);
        } else {
            // Mengubah pesan agar lebih sesuai konteks
            System.out.println("Gagal: Jumlah stok yang ditambahkan harus lebih dari nol!");
        }
    }

    public void tambahStok(double jumlah) {
        int jumlahInt = (int) Math.round(jumlah);
        if (jumlahInt > 0) {
            this.stok += jumlahInt;
            System.out.println("Stok " + nama + " bertambah " + jumlah + " (double/dibulatkan jadi " + jumlahInt + "). Stok baru: " + this.stok);
        } else {
            System.out.println("Gagal: Jumlah stok yang ditambahkan (setelah dibulatkan) harus lebih dari nol!");
        }
    }

    public void kurangiStok(int jumlah) {
        if (this.stok >= jumlah) {
            this.stok -= jumlah;
            System.out.println("Stok " + nama + " berkurang " + jumlah + ". Stok baru: " + this.stok);
        } else {
            System.out.println("Stok tidak mencukupi untuk " + nama + "! Tersedia: " + this.stok);
        }
    }

    public void tampilkanData() {
        System.out.println("  Kode Produk: " + kode);
        System.out.println("  Nama Produk: " + nama);
        System.out.println("  Harga (Rp): " + harga);
        System.out.println("  Stok Tersedia: " + stok);
    }

    public void getInfo() {
        System.out.println("--- Detail Produk Umum ---");
        System.out.println(" Kode: " + kode);
        System.out.println(" Nama: " + nama);
        System.out.println(" Harga (Rp): " + harga);
        System.out.println(" Stok Tersedia: " + stok);
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

    @Override
    public void getInfo() {
        System.out.println("--- Detail BENIH ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Varietas: " + varietas);
        // Menggunakan getHarga() dan getStok() dari Produk untuk keamanan
        System.out.println(" Harga (Rp): " + getHarga()); 
        System.out.println(" Stok: " + getStok() + " bungkus.");
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

    @Override
    public void getInfo() {
        System.out.println("--- Detail PUPUK ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Jenis Pupuk: " + jenis);
        System.out.println(" Harga (Rp): " + getHarga()); 
        System.out.println(" Stok: " + getStok() + " karung.");
    }
}
4.ObatHama.java
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
5. AlatPertanian.java
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

    @Override
    public void getInfo() {
        System.out.println("--- Detail ALAT PERTANIAN ---");
        System.out.println(" Kode: " + getKode());
        System.out.println(" Nama: " + getNama());
        System.out.println(" Material Utama: " + material);
        System.out.println(" Harga (Rp): " + getHarga());
        System.out.println(" Stok: " + getStok() + " unit.");
    }
}
6. CreditBy.java
package main.java.com.upb.agripos.util; 

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
7. MainPolymorphism.java
package main.java.com.upb.agripos;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.model.Benih;
import main.java.com.upb.agripos.model.Pupuk;
import main.java.com.upb.agripos.model.AlatPertanian;
import main.java.com.upb.agripos.model.ObatHama;
import main.java.com.upb.agripos.util.CreditBy; 

public class MainPolymorphism {
    public static void main(String[] args) {
        
        System.out.println("=== WEEK4 POLYMORPHISM ===");
        
        System.out.println("\n--- Overloading (tambahStok) ---");
        Produk produkUmum = new Produk("PRD001", "Goni Karung", 15000, 100);

        System.out.print("Panggilan INT: ");
        produkUmum.tambahStok(50); 
        
        System.out.print("Panggilan DOUBLE: ");
        produkUmum.tambahStok(15.9); 

        
        System.out.println("\n--- Overriding (getInfo) & Dynamic Binding ---");
        
        Benih b = new Benih("BNH-001", "Benih Padi IR64", 25000.0, 100, "IR64");
        

        Pupuk p_urea = new Pupuk("PPK-101", "Pupuk Urea", 350000.0, 40, "Urea");
        Pupuk p_npk = new Pupuk("PPK-102", "Pupuk NPK Phonska", 450000.0, 60, "NPK"); 

    
        AlatPertanian a_baja = new AlatPertanian("ALT-501", "Cangkul Baja", 90000.0, 15, "Baja");
        AlatPertanian a_kayu = new AlatPertanian("ALT-502", "Gagang Pacul", 25000.0, 50, "Kayu");
        AlatPertanian a_plastik = new AlatPertanian("ALT-503", "Sprayer", 150000.0, 25, "Plastik");

        ObatHama obat1 = new ObatHama("OBT033", "Pestisida Cair", 45000, 25, "Cair"); 
        
        Produk[] daftarProduk = {
            b, 
            p_urea, 
            p_npk,
            a_baja,
            a_kayu,
            a_plastik, 
            obat1, 
            produkUmum 
        };
        
        System.out.println("\nHasil getInfo() melalui array Produk[]:");
        for (Produk p : daftarProduk) {
            
            p.getInfo(); 
        }

        
        CreditBy.print("240202837", "Kayla Putri Arsonisr"); 
    }
}

---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![alt text](<Screenshot (142).png>) ![alt text](<Screenshot (143).png>)
)
---

## Analisi
- Jelaskan bagaimana kode berjalan. 
1. Overloading (tambahStok):
Method tambahStok() berhasil di-overload di superclass Produk. Ketika dipanggil dengan argumen integer (50), Java memanggil versi tambahStok(int). Ketika dipanggil dengan argumen double (15.9), Java memanggil versi tambahStok(double). Ini adalah bentuk Static Polymorphism karena method yang dipanggil ditentukan saat kompilasi berdasarkan tipe argumen.
2. Overriding dan Dynamic Binding (getInfo()):
Method getInfo() berhasil di-override di semua subclass. Pada MainPolymorphism.java, array Produk[] daftarProduk dibuat. Meskipun tipe referensi adalah Produk, setiap elemen array sebenarnya menyimpan objek dari subclass yang berbeda (Benih, Pupuk, dll.).
Ketika looping memanggil p.getInfo(), Java menggunakan mekanisme Dynamic Binding untuk menentukan method mana yang harus dieksekusi. Hasilnya, untuk benih1, method yang dieksekusi adalah Benih.getInfo(), bukan Produk.getInfo(). Inilah inti dari Dynamic Polymorphism, di mana resolusi method terjadi pada saat program dijalankan (runtime), memungkinkan kode yang sama (p.getInfo()) menghasilkan output yang berbeda-beda tergantung tipe objek aktual. 
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
Pada minggu sebelumnya membuat deskripsi untuk produknya lalu minggu ini membuat tambah produk atau stok menggunakan overloading dan info produk mengguunakan get info serta dynamic binding.
- Kendala yang dihadapi dan cara mengatasinya.  
a. Kendala: Awalnya, superclass menggunakan tampilkanData(). Jika subclass meng-override ini, Dynamic Binding tidak akan bekerja jika superclass diubah menjadi getInfo() tetapi subclass tidak ikut diubah.
b. Solusi: Memastikan semua subclass (termasuk ObatHama dari Latihan Mandiri) mengubah nama method override menjadi getInfo() dan menggunakan anotasi @Override untuk mencegah kesalahan penulisan (typo).

---

## Kesimpulan
Praktikum ini berhasil mendemonstrasikan ketiga pilar Polymorphism dalam Java:
1. Overloading memungkinkan satu nama method (tambahStok) digunakan untuk operasi serupa dengan input yang berbeda.
2. Overriding memungkinkan subclass untuk memberikan implementasi spesifik terhadap method yang diwariskan (getInfo).
3. Dynamic Binding memastikan bahwa ketika objek subclass diakses melalui referensi superclass (Produk[]), method yang paling spesifik (override) dari objek aktual akan selalu dipanggil pada saat runtime, menghasilkan kode yang fleksibel dan mudah diperluas.

---

## Quiz
1. Apa perbedaan overloading dan overriding?
    Jawaban: Overloading (Polymorphism Statis) terjadi ketika sebuah class memiliki dua atau lebih method dengan nama yang sama tetapi memiliki signature (jumlah atau tipe parameter) yang berbeda. Resolusinya terjadi saat Compile Time. Sementara itu, Overriding (Polymorphism Dinamis) terjadi ketika subclass mendefinisikan ulang method yang sudah ada di superclass dengan signature yang persis sama. Resolusinya terjadi saat Runtime (Dynamic Binding).
2. Bagaimana Java menentukan method mana yang dipanggil dalam dynamic binding?
    Jawaban: Dalam Dynamic Binding, Java menentukan method mana yang akan dieksekusi berdasarkan tipe objek aktual (tipe objek yang dibuat menggunakan kata kunci new), bukan berdasarkan tipe referensi yang digunakan untuk menunjuk objek tersebut. Saat method dipanggil, Java Virtual Machine (JVM) akan mencari implementasi method ter-override yang paling spesifik, dimulai dari subclass hingga superclass, dan menjalankan versi yang ditemukan pada tipe objek aktual.
3. Berikan contoh kasus polymorphism dalam sistem POS selain produk pertanian.
    Jawaban: Contoh kasus polymorphism di sistem POS (Point of Sale) dapat ditemukan pada pemrosesan pembayaran. Kita dapat memiliki superclass bernama MetodePembayaran dengan method prosesPembayaran(). Subclass seperti BayarKartuDebit, BayarKartuKredit, dan BayarQRIS masing-masing meng-override method prosesPembayaran() untuk mengimplementasikan logika yang berbeda (misalnya, memverifikasi PIN/CVV untuk kartu, atau memindai kode QR). Ketika kasir memanggil metode.prosesPembayaran(), Java menggunakan Dynamic Binding untuk memastikan method yang tepat dijalankan, terlepas dari apakah variabel metode saat ini menyimpan objek Debit, Kredit, atau QRIS.