# Laporan Praktikum Minggu 6
Topik: Perancangan Sistem Agri-POS dengan UML & SOLID

## Identitas
- Nama  : Kayla putri arsonisr
- NIM   : 240202837
- Kelas : 3ikra

---

## Tujuan
1. Pemahaman Pemodelan Visual (UML): Mampu menganalisis kebutuhan sistem dan menerjemahkannya ke dalam diagram standar industri, 
   meliputi Use Case Diagram, Activity Diagram, Sequence Diagram, dan Class Diagram.

2. Penerapan Prinsip SOLID: Mampu menerapkan prinsip desain berorientasi objek (SOLID Principles) khususnya Single Responsibility, 
   Open/Closed, dan Dependency Inversion untuk menciptakan arsitektur perangkat lunak yang fleksibel dan mudah dipelihara (maintainable).

3. Traceability Kebutuhan: Mampu memetakan Kebutuhan Fungsional (Functional Requirements) ke dalam desain teknis untuk memastikan 
   seluruh fitur terakomodasi dalam rancangan sistem.

---

## Dasar Teori
I. Deskripsi Sistem

Agri-POS (Agricultural Point of Sales) adalah sistem kasir berbasis desktop yang dirancang khusus untuk toko pertanian. Sistem ini bertujuan untuk mempermudah operasional transaksi penjualan benih, pupuk, dan alat tani, serta pengelolaan stok barang.

Sistem ini memiliki dua aktor utama:

   1. Admin: Bertanggung jawab atas manajemen data master 
      (produk, harga, stok) dan melihat laporan penjualan.

   2. Kasir: Bertanggung jawab atas operasional transaksi 
      harian, melayani pembayaran pelanggan, dan pencetakan struk.

II. Analisis & Perancangan UML

Berikut adalah penjelasan mengenai diagram-diagram yang digunakan untuk memodelkan sistem Agri-POS:
A. Use Case Diagram

   1. Fungsi: Menggambarkan batasan sistem dan interaksi 
      fungsional antara aktor dengan sistem.

   2. Keterkaitan: Diagram ini menjadi acuan utama untuk 
      fitur-fitur yang harus dikembangkan (Functional Requirements).

   3. Poin Penting: Terdapat relasi <<include>> pada Cetak 
      Struk (wajib setelah bayar) dan <<extend>> pada Identifikasi Member (opsional).

B. Activity Diagram

   1. Fungsi: Memodelkan alur kerja (workflow) dari fitur utama 
      "Proses Transaksi".

   2. Keterkaitan: Menjelaskan detail logika dari Use Case 
      "Proses Transaksi".

   3. Poin Penting: Menggunakan Swimlanes untuk memisahkan 
      tanggung jawab antara Kasir, Sistem, dan Payment Gateway. Terdapat Decision Node untuk pengecekan stok dan saldo.

C. Sequence Diagram

   1. Fungsi: Menggambarkan interaksi antar objek (instance) 
      dalam urutan waktu tertentu saat satu skenario transaksi berjalan.

   2. Keterkaitan: Menerjemahkan logika Activity Diagram 
      menjadi komunikasi antar Class.

   3. Poin Penting: Menunjukkan bagaimana CheckoutUI memanggil 
      TransactionService, yang kemudian berkoordinasi dengan DiscountStrategy dan PaymentMethod.

D. Class Diagram

   1. Fungsi: Menggambarkan struktur statis sistem, termasuk 
      atribut, method, dan hubungan antar kelas.

   2. Keterkaitan: Merupakan blueprint akhir untuk pengkodean 
      (coding).

   3. Poin Penting: Menggunakan konsep Packages (Model, 
      Service, Repository, Payment, Discount) untuk mengorganisir kode agar rapi dan terstruktur.

III. Penerapan Prinsip SOLID

Desain sistem Agri-POS telah menerapkan prinsip SOLID untuk memastikan kode mudah dipelihara dan dikembangkan. Berikut adalah 3 prinsip utama yang diterapkan:
1. Single Responsibility Principle (SRP)

    Konsep: Sebuah kelas hanya boleh memiliki satu alasan untuk berubah (satu tanggung jawab).

    Penerapan:

      a. Model.Product: Hanya bertanggung jawab menyimpan data 
         produk.

      b. Repository.ProductRepository: Hanya bertanggung jawab 
         untuk operasi database (Simpan/Cari).

      c. Service.TransactionService: Hanya bertanggung jawab 
         untuk logika bisnis transaksi.

      d. Manfaat: Jika kita ingin mengganti database (misal 
         dari SQL ke Firebase), kita hanya perlu mengubah Repository, tanpa mengganggu Service atau Model.

2. Open/Closed Principle (OCP)

    Konsep: Kode harus terbuka untuk ekstensi (penambahan fitur), tapi tertutup untuk modifikasi.

    Penerapan:

      a. Sistem menggunakan interface PaymentMethod.

      b. Saat ini tersedia CashPayment dan EWalletPayment.

      c. Jika di masa depan ingin menambahkan pembayaran QRIS, 
         kita cukup membuat class baru QrisPayment yang mengimplementasikan PaymentMethod. Kita tidak perlu mengubah kode di TransactionService.

3. Dependency Inversion Principle (DIP)

    Konsep: Modul tingkat tinggi tidak boleh bergantung pada modul tingkat rendah. Keduanya harus bergantung pada abstraksi (Interface).

    Penerapan:

      a. Class TransactionService (High Level) tidak bergantung 
         langsung pada MemberDiscount (Low Level).

      b. Sebaliknya, TransactionService bergantung pada 
         interface DiscountStrategy.

      c. Manfaat: Ini mengurangi ketergantungan antar-class 
         (Decoupling), membuat sistem lebih fleksibel dan mudah di-test.

IV Tabel Traceability

Tabel ini memetakan kebutuhan fungsional (FR) ke dalam desain diagram dan implementasi kelas untuk memastikan semua fitur ter-cover.

| FR (Functional Requirement) | Use Case               | Activity/Sequence                 | Class/Interface Realisasi                                                      |
|-----------------------------|------------------------|-----------------------------------|--------------------------------------------------------------------------------|
| Manajemen Produk            | UC-02 Kelola Produk    | -                                 | Model.Product, Repository.ProductRepository, Repository.SqlProductRepository   |
| Proses Transaksi            | UC-05 Proses Transaksi | Activity Transaksi / Seq Checkout | Service.TransactionService, Model.Transaction, Model.TransactionDetail         |
| Pembayaran                  | UC-05 Proses Transaksi | Seq Pembayaran (Success/Fail)     | Payment.PaymentMethod (Interface), Payment CashPayment, Payment.EWalletPayment |
| Manajemen Diskon            | UC-04 Kelola Diskon    | Seq Hitung Diskon                 | Discount.DiscountStrategy (Interface), Discount MemberDiscount, Model.Customer |
| Penyimpanan Data            | -                      | -                                 | Repository.ProductRepository, Repository. SqlProductRepository                 |

Tabel Non-fungsional

| ID               | Kategori        | Deskripsi Kebutuhan                                                                                           | Implementasi di Desain                                         |
|------------------|-----------------|---------------------------------------------------------------------------------------------------------------|----------------------------------------------------------------|
| NFR-01           | Performance     | Waktu respon saat scan barcode produk harus di bawah 2 detik.                                                 | Indexing pada ProductRepository (findByCode).                  |
| NFR-02           | Security        | Password pengguna tidak boleh disimpan sebagai teks biasa (harus di-hash).                                    | Enkripsi pada modul Login (Use Case).                          |
| NFR-03           | Maintainability | Kode program harus mudah dikembangkan untuk metode pembayaran baru.                                           | Penerapan OCP & DIP menggunakan Interface PaymentMethod.       |
| NFR-04           | Usability       | Antarmuka kasir harus dapat dioperasikan menggunakan keyboard (meminimalkan penggunaan mouse) demi kecepatan. | Desain CheckoutUI (Sequence Diagram).                          |
| Penyimpanan Data | -               | -                                                                                                             | Repository.ProductRepository, Repository. SqlProductRepository |

# #Diagram

1. activity
![alt text](src/uml/activity.png)
2. class
![alt text](src/uml/class.png)
3. sequens
![alt text](src/uml/sequens.png)
4. usecase
![alt text](src/uml/usecase.png)

## Kesimpulan dan Refleksi

Keunggulan Sistem

Desain Agri-POS yang dirancang memiliki struktur yang sangat modular berkat penggunaan Packages dan penerapan SOLID. Pemisahan antara logika bisnis (Service), data (Model), dan akses data (Repository) membuat sistem ini mudah dipahami oleh tim pengembang dan mudah untuk dilakukan Unit Testing.
Potensi Pengembangan

Sistem ini masih dapat dikembangkan lebih lanjut, antara lain:

   1. Interface User (GUI): Mengimplementasikan desain Class 
      Diagram ke dalam JavaFX atau Web-based frontend.

   2. Database Nyata: Mengganti mock repository dengan koneksi 
      PostgreSQL atau MySQL yang sesungguhnya.

   3. Laporan Dinamis: Menambahkan Strategy Pattern untuk 
      berbagai jenis ekspor laporan (PDF, Excel, CSV).

## quiz

Jawaban

1. Perbedaan Aggregation dan Composition

   Secara visual di UML, keduanya menggambarkan hubungan "Whole-Part" (Keseluruhan-Bagian), namun perbedaannya terletak pada Kekuatan Hubungan dan Siklus Hidup (Lifecycle) objeknya.

    1) Aggregation (Agregasi):

        a. Definisi: Hubungan yang "lemah". Bagian (Part) dapat berdiri sendiri meskipun Keseluruhan (Whole) dihancurkan. Objek bagian bisa 
           dimiliki oleh banyak objek lain.

        b. Simbol: Belah ketupat kosong (putih).

        c. Contoh Konsep: Sebuah "Kelas" memiliki "Mahasiswa". Jika "Kelas" dibubarkan, "Mahasiswa" tidak ikut musnah (mereka masih ada).

    2) Composition (Komposisi):

        a. Definisi: Hubungan yang "kuat" (Death Relationship). Bagian (Part) sepenuhnya bergantung pada Keseluruhan (Whole). Jika Keseluruhan 
           dihancurkan, maka Bagian di dalamnya juga ikut musnah.

        b. Simbol: Belah ketupat isi (hitam).

        c. Contoh Konsep: Sebuah "Rumah" memiliki "Kamar". Jika "Rumah" hancur, "Kamar" di dalamnya juga otomatis hancur.

Penerapan pada Desain Agri-POS: Pada Class Diagram, terdapat hubungan Composition antara Transaction dan Product (sebagai item belanja).
Dalam diagram relasinya adalah Transaction *-- Product. Ini diartikan sebagai "Sebuah Transaksi terdiri dari kumpulan Produk (Item Belanja). Jika Transaksi dihapus, maka daftar item belanja di dalamnya tidak lagi relevan (ikut terhapus), meskipun data master produk di gudang tetap ada."

2. Bagaimana Prinsip Open/Closed (OCP) Memastikan Sistem Mudah Dikembangkan?

   Prinsip Open/Closed (OCP) menyatakan: "Software entities should be Open for Extension, but Closed for Modification."

   Artinya, kita harus bisa menambah fitur baru tanpa mengacak-acak kode lama yang sudah berjalan stabil. Ini meminimalkan risiko munculnya bug baru pada fitur lama.

Penerapan di Agri-POS: Paket Payment.

    Desain Lama (Tanpa OCP): Jika menulis logika bayar pakai if-else di dalam TransactionService:
    Java

    // Kode BURUK (Melanggar OCP)
    if (type == "CASH") { ... }
    else if (type == "EWALLET") { ... }
    // Mau nambah QRIS? Harus bongkar file ini dan nambah 'else if' lagi. Berisiko error!

    Desain (Dengan OCP): Menggunakan Interface PaymentMethod.

        a. Open for Extension: Jika besok toko ingin menerima pembayaran QRIS, cukup membuat class baru QrisPayment yang mengimplementasikan 
           PaymentMethod.

        b. Closed for Modification: TIDAK PERLU menyentuh atau mengubah satu baris pun kode di dalam TransactionService. Class tersebut akan 
           otomatis menerima QrisPayment karena ia hanya peduli pada interface-nya, bukan jenisnya.

3. Mengapa Dependency Inversion Principle (DIP) Meningkatkan Testability?

Dependency Inversion Principle (DIP) menyatakan bahwa High-level modules (Logika Bisnis) tidak boleh bergantung pada Low-level modules (Detail Teknis seperti Database/API). Keduanya harus bergantung pada Abstraction (Interface).

a. Hubungan dengan Testability (Kemudahan Pengujian): Unit Testing bertujuan menguji logika bisnis secara terisolasi tanpa koneksi lambat ke 
   database nyata atau API eksternal.

b. Tanpa DIP (Sulit di-test): Jika TransactionService langsung memanggil SqlProductRepository (Koneksi Database Langsung):

    Setiap kali test dijalankan, ia harus konek ke database asli.

    Lambat.

    Jika database mati, test gagal (padahal logikanya benar).

c. Dengan DIP (Sesuai Desain Anda): TransactionService hanya bergantung pada interface ProductRepository.

d. Contoh Penerapan saat Testing: Karena TransactionService tidak peduli dari mana asalnya data (asal sesuai interface), kita bisa melakukan 
   Mocking (Palsu) saat testing:

    Kita buat MockRepository (Repository Palsu) yang datanya cuma di memori (bukan SQL).

    Kita suntikkan Mock ini ke TransactionService.

    Hasilnya: Kita bisa mengetes logika diskon atau total harga dalam hitungan milidetik tanpa perlu menginstall database SQL sama sekali.