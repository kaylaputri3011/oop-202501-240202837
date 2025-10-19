# Laporan Praktikum Minggu 2
Topik: Praktik Class dan Object((Enkapsulasi))

## Identitas
- Nama  : kayla putri arsonisr
- NIM   : 240202837
- Kelas : 3IKRA

---

## Tujuan
Mahasiswa memahami konsep Class, Object, dan Enkapsulasi dalam pemrograman berorientasi objek (OOP) serta mampu mengimplementasikannya untuk membuat struktur data produk yang terstruktur dan aman.
---

## Dasar Teori
 1. class (kelas): Adalah blueprint atau cetak biru yang mendefinisikan sifat (atribut/variabel) dan perilaku (metode/fungsi) dari semua objek yang akan dibuat darinya. Dalam praktikum ini, Produk adalah sebuah kelas.
2. Object (Objek): Adalah instansiasi (perwujudan nyata) dari suatu kelas. Objek memiliki state (nilai dari atribut) dan perilaku. Dalam praktikum ini, p1, p2, dan p3 adalah objek dari kelas Produk.
3. Encapsulation (Enkapsulasi): Prinsip OOP untuk membungkus data (atribut) dan metode yang memprosesnya ke dalam satu unit (kelas). Dalam implementasi Java, ini dilakukan dengan membuat atribut menjadi private dan menyediakan akses melalui metode public seperti getter (getKode()) dan setter.
---

## Langkah Praktikum
1. buka visual code, buat folder baru dengan nama model, buat file dengan file Produk.java;
2. buat folder lagi dengan nama util, buat file dengan nama CrediBy.java;
3. buat file MainProduk.java diluar folder util dan model;
4. buat codingan untuk ketiganya sesuai tugas yang diberikan;
5. memastikan folder atau file tersebut sudah di tempat yang seharusnya;
6. running dan memunculkan hasilnya.


---

## Kode Program
1. Produk.java:
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
}
2. MainProduk.java:
package main.java.com.upb.agripos; 

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.util.CreditBy;

public class MainProduk {
    public static void main(String[] args) {
        Produk p1 = new Produk("BNH-001", "Benih Padi IR64", 25000, 100);
        Produk p2 = new Produk("PPK-101", "Pupuk Urea 50kg", 350000, 40);
        Produk p3 = new Produk("ALT-501", "Cangkul Baja", 90000, 15);

        System.out.println("Kode: " + p1.getKode() + ", Nama: " + p1.getNama() + ", Harga: " + p1.getHarga() + ", Stok: " + p1.getStok());
        System.out.println("Kode: " + p2.getKode() + ", Nama: " + p2.getNama() + ", Harga: " + p2.getHarga() + ", Stok: " + p2.getStok());
        System.out.println("Kode: " + p3.getKode() + ", Nama: " + p3.getNama() + ", Harga: " + p3.getHarga() + ", Stok: " + p3.getStok());

        System.out.println(); 
        System.out.println("Benih Padi IR64: " + p1.getStok());
        p1.kurangiStok(23); // Memicu pesan error
        System.out.println("sisa stok " + p1.getNama() + " setelah berkurang 23 produk: " + p1.getStok());

        System.out.println(); 
        System.out.println("Pupuk Urea 50kg: " + p2.getStok());
        p2.tambahStok(250); // Memicu pesan error
        System.out.println("sisa stok " + p2.getNama() + " setelah bertambah 250 produk: " + p2.getStok());

        System.out.println(); 
        System.out.println("Stok awal Cangkul Baja: " + p3.getStok());
        p3.kurangiStok(2005); // Memicu pesan error
        System.out.println("sisa stok " + p3.getNama() + " setelah berkurang 2005 produk: " + p3.getStok());
        // Tampilkan identitas mahasiswa
        CreditBy.print("240202837", "Kayla Putri Arsonisr");
    }
    
}
3. CreditBy.java:
package main.java.com.upb.agripos.util; 

public class CreditBy {
    public static void print(String nim, String nama) {
        System.out.println("\ncredit by: " + nim + " - " + nama);
    }
}
---

## Hasil Eksekusi
(Sertakan screenshot hasil eksekusi program.  
![Screenshot hasil] ![alt text](<Screenshot week2.png>)
) 
---

## Analisis
- Jelaskan bagaimana kode berjalan
    Kode terdiri dari tiga kelas: Produk mendefinisikan item dengan stok yang bisa ditambah atau dikurangi; MainProduk menginisiasi tiga objek Produk, menampilkan detailnya, lalu mencoba memanipulasi stok (p1 berhasil dikurangi, p2 gagal ditambah karena bug di pesan validasi, dan p3 gagal dikurangi karena stok kurang), dan diakhiri dengan mencetak kredit penulis menggunakan kelas CreditBy.  
- Apa perbedaan pendekatan minggu ini dibanding minggu sebelumnya.  
    Diminggu sebelumnya hanya menampilkan output hello, nama dan nim saja. diminggu ini ada class publik dan privat serta lebih struktur karena sudah ada objek yang dibahas di dalamnya. 
- Kendala yang dihadapi dan cara mengatasinya.
    kesalahan pada penempatan folder maupun file tapi bisa diseleisaikan dengan cara memindahkan folder dan file tersebut di tempat yang sesuai, kesalahan penulisan pada code program bisa diatasi dengan membetulkan penulisan codenya
---

## Kesimpulan
Dalam pemrograman berorientasi objek, Kelas (Class) adalah cetak biru atau template yang mendefinisikan sifat (atribut) dan perilaku (metode) dari suatu entitas (contohnya Produk), sementara Objek (Object) adalah instansi nyata atau perwujudan konkret dari kelas tersebut, yang memiliki nilai spesifik untuk atributnya dan mampu melakukan tindakan yang didefinisikan oleh kelas. Singkatnya, kelas adalah konsep abstrak yang mendefinisikan tipe, sedangkan objek adalah entitas spesifik yang berinteraksi dalam eksekusi program.

---

## Quiz
1. Mengapa atribut sebaiknya dideklarasikan sebagai private dalam class?
    Atribut sebaiknya dideklarasikan sebagai private (private) dalam sebuah class untuk mencapai enkapsulasi (encapsulation). Ini berarti:
    a. Melindungi Data: Atribut hanya dapat diakses atau dimodifikasi dari dalam class itu sendiri. Hal ini mencegah modifikasi data secara langsung dan tidak terkontrol dari luar, yang dapat menyebabkan data menjadi tidak valid atau tidak konsisten.
    b. Kontrol Akses: Memberikan kontrol penuh atas bagaimana data diubah. Jika suatu atribut diubah, perubahan tersebut harus melalui mekanisme yang ditentukan oleh class (biasanya melalui setter), memungkinkan adanya validasi atau logika bisnis lain sebelum perubahan diterapkan.
    c. Fleksibilitas: Memungkinkan implementasi internal class diubah (misalnya, mengganti tipe data atribut) tanpa memengaruhi kode di luar class yang menggunakannya, selama antarmuka publiknya (seperti getter dan setter) tetap sama.

2. Apa fungsi getter dan setter dalam enkapsulasi?
    Dalam konteks enkapsulasi, getter dan setter berfungsi sebagai antarmuka publik untuk mengakses dan memodifikasi atribut private:
    a. Getter (Metode Aksesor): Berfungsi untuk mengambil atau membaca nilai dari atribut private. Karena atributnya private, getter adalah satu-satunya cara eksternal untuk mendapatkan nilainya.
    b. Setter (Metode Mutator): Berfungsi untuk mengubah atau menetapkan nilai baru ke atribut private. Setter adalah tempat untuk meletakkan logika validasi atau aturan bisnis lainnya sebelum nilai atribut diperbarui. Misalnya, memastikan harga yang dimasukkan tidak negatif.
    Kedua metode ini memastikan bahwa akses dan modifikasi data dilakukan secara terkontrol dan aman, mempertahankan integritas data.

3. Bagaimana cara class Produk mendukung pengembangan aplikasi POS yang lebih kompleks?
    Class Produk (Product) mendukung pengembangan aplikasi Point of Sale (POS) yang lebih kompleks dengan cara:
    a. Struktur Data Terpusat: Class ini mengenkapsulasi semua properti (seperti nama, kode, harga, stok) dan perilaku (misalnya, diskon, pengurangan stok) yang berkaitan dengan satu jenis produk. Ini membuat data produk terorganisir dan mudah dikelola.
    b. Modularitas dan Reusabilitas: Class Produk dapat digunakan kembali di berbagai bagian aplikasi (misalnya, inventaris, transaksi penjualan, laporan) tanpa perlu mendefinisikan ulang data produk setiap kali. Hal ini membuat kode lebih bersih (clean), terstruktur, dan mudah dipelihara.
    c. Abstraksi: Pengembang lain yang menggunakan class Produk tidak perlu tahu detail bagaimana data produk disimpan secara internal (misalnya, di database); mereka hanya berinteraksi melalui metode publiknya. Ini memungkinkan pemisahan tanggung jawab dan memfasilitasi penambahan fitur kompleks, seperti:
    * Penghitungan Otomatis: Menambahkan logika diskon, pajak, atau mark-up langsung di dalam class.
    * Manajemen Stok: Mengimplementasikan metode untuk mengurangi stok saat terjadi penjualan dan memicu peringatan jika stok menipis.
    * Polimorfisme: Memungkinkan pembuatan sub-kelas untuk jenis produk khusus (misalnya, DigitalProduct, PhysicalProduct) yang memiliki perilaku berbeda, sehingga mendukung sistem POS yang lebih fleksibel dan skalabel.


[def]: screenshot(117).png
