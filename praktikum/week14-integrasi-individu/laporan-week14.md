# Laporan Praktikum Minggu 14
Topik: Integrasi Individu (Final Project Agri-POS)

## Identitas
- Nama  : kayla putri arsonisr
- NIM   : 240202837
- Kelas : 3ikra

---

## Tujuan
1. Mampu mengintegrasikan seluruh konsep OOP (Inheritance, Polymorphism, Encapsulation) ke dalam satu aplikasi utuh.
2. Mampu menerapkan arsitektur MVC (Model-View-Controller) dan prinsip SOLID dalam pengembangan perangkat lunak.
3. Mampu menghubungkan logika bisnis (Service), akses data (DAO/JDBC), dan antarmuka pengguna (JavaFX) secara terstruktur.
4. Mampu melakukan pengujian logika bisnis menggunakan Unit Testing.

---

## Dasar Teori
1. MVC Architecture: Pola desain yang memisahkan aplikasi menjadi tiga komponen utama: Model (Data), View (Tampilan), dan Controller (Logika Penghubung). Ini memudahkan pengelolaan kode yang kompleks.
2. DAO Pattern (Data Access Object): Pola desain untuk mengisolasi logika akses database (SQL) dari logika bisnis aplikasi. Interface ProductRepository bertindak sebagai kontrak, sedangkan SqlProductRepository adalah implementasinya.
3. Singleton Pattern: Pola desain yang menjamin sebuah class hanya memiliki satu instance. Digunakan pada DatabaseConnection untuk mengefisiensikan koneksi ke PostgreSQL.
4. Dependency Injection: Teknik menyuntikkan ketergantungan objek (misal: Repository dimasukkan ke Service) dari luar kelas, membuat sistem lebih fleksibel dan mudah diuji.

---

## Langkah Praktikum
1. Persiapan Struktur Proyek: Membuat folder week14-integrasi-individu dan menyusun paket berdasarkan layer (model, view, controller, service, dao).
2. Implementasi Model Keranjang: Membuat class Cart dan CartItem untuk menampung data belanjaan sementara menggunakan Java Collections (List).
3. Pembuatan Layanan (Service): Mengimplementasikan CartService untuk logika penambahan item, perhitungan total, dan pembersihan keranjang.
4. Desain GUI (View): Menggabungkan TableView (Manajemen Produk) dan ListView (Keranjang Belanja) dalam satu tampilan utama (MainView) menggunakan JavaFX.
5. Integrasi Logika (Controller): Menghubungkan Event Handler tombol (Simpan, Hapus, Checkout) dengan Service dan DAO.
6. Wiring Aplikasi: Menyusun semua komponen (Repository -> Service -> Controller -> View) pada class utama AppAgriPos menggunakan prinsip Dependency Injection.
7. Testing: Menjalankan Unit Test JUnit untuk memastikan logika perhitungan total belanja valid.

---

## Kode Program
1. Wiring Komponen (AppAgriPos.java)

Bagian ini menunjukkan penerapan Dependency Injection, di mana Repository disuntikkan ke Service, dan Service disuntikkan ke Controller.
Java

@Override
public void start(Stage stage) {
    try {
        // 1. Setup Koneksi Database & Repository (DAO Layer)
        Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agripos", "postgres", "111123");
        ProductRepository repo = new SqlProductRepository(conn);

        // 2. Setup Service (Business Logic Layer)
        ProductService productService = new ProductService(repo);
        CartService cartService = new CartService(); // Service baru untuk keranjang

        // 3. Setup View & Controller (Presentation Layer)
        MainView view = new MainView();
        
        // Controller menghubungkan semuanya: Service Database, Service Keranjang, dan Tampilan
        new MainController(productService, cartService, view);

        stage.setScene(new Scene(view.asParent(), 800, 600));
        stage.show();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

2. Logika Checkout (MainController.java)

Bagian ini menunjukkan integrasi antara User Event (klik tombol) dengan Service keranjang belanja.
Java

// Event Handler untuk Tombol Checkout
view.getBtnCheckout().setOnAction(e -> {
    // Validasi: Cek apakah keranjang kosong
    if (cartService.getCartItems().isEmpty()) {
        showAlert("Warning", "Keranjang belanja kosong!");
        return;
    }

    // 1. Hitung Total Belanja dari Service
    double total = cartService.calculateTotal();
    System.out.println("Transaski Berhasil. Total: " + total);

    // 2. Bersihkan Keranjang (Logic di Service)
    cartService.clearCart();
    
    // 3. Update Tampilan GUI (Logic di View)
    refreshCartView();
    
    // 4. Feedback ke User
    showAlert("Sukses", "Transaksi Berhasil! Struk dicetak di Console.");
});

---

## Hasil Eksekusi
![alt text](<Screenshot (261).png>) ![alt text](<Screenshot (262).png>) ![alt text](<Screenshot (263).png>) ![alt text](<Screenshot (264).png>) ![alt text](<Screenshot (265).png>)

---

## Analisis
1. Integrasi Arsitektur

Aplikasi Agri-POS berhasil menerapkan pemisahan tugas yang jelas (Separation of Concerns):

    View (MainView) hanya mengurus tata letak tombol dan tabel. Tidak ada kode SQL atau logika bisnis di sini.

    Controller (MainController) bertindak sebagai "otak" yang merespons input user. Ia meminta data dari Service dan memberikannya ke View.

    DAO (SqlProductRepository) murni hanya menangani query SQL, menjaga agar kode aplikasi tidak bergantung pada jenis database tertentu.

2. Tabel Traceability
| Artefak UML | Referensi | Implementasi Kode (Controller -> Service -> DAO) | Dampak |
| :--- | :--- | :--- | :--- |
| **Use Case** | UC-01 Tambah Produk | `MainController` $\rightarrow$ `ProductService.addProduct` $\rightarrow$ `SqlProductRepository.save` | Data baru tersimpan di tabel PostgreSQL dan tampilan tabel di-refresh. |
| **Sequence** | SD-02 Hapus Produk | `MainController` $\rightarrow$ `ProductService.deleteProduct` $\rightarrow$ `SqlProductRepository.delete` | Data terhapus permanen dari PostgreSQL dan tampilan tabel di-refresh. |
| **Activity** | AD-03 Checkout | `MainController` $\rightarrow$ `CartService.calculateTotal` & `clearCart` | Menghitung total belanja, mencetak struk di Console, dan mengosongkan keranjang (Memory). |
| **Class** | Singleton DB | `DatabaseConnection.getInstance()` (Dipanggil di `AppAgriPos`) | Koneksi database dibuat efisien (hanya 1 instance selama aplikasi berjalan). |

3. Kendala & Solusi

   a. Kendala: Error JavaFX runtime components are missing saat menjalankan file AppAgriPos.java secara langsung karena konfigurasi Module Path.

   b. Solusi: Menggunakan teknik "Fake Main" dengan membuat class MainLauncher.java yang memanggil method main aplikasi utama, sehingga JVM memuat library dengan benar.

   a. Kendala: Kompleksitas update stok real-time saat transaksi.

   b. Solusi: Memutuskan untuk menggunakan simulasi transaksi (menghitung total & reset keranjang) untuk memastikan alur aplikasi stabil dan memenuhi syarat dasar penggunaan Collections.

---

## Kesimpulan
Praktikum Minggu 14 ini menyimpulkan seluruh pembelajaran satu semester. Saya berhasil membangun aplikasi Agri-POS yang fungsional dengan fitur CRUD lengkap ke database PostgreSQL dan fitur keranjang belanja menggunakan Java Collections. Penggunaan arsitektur MVC, DAO Pattern, dan Dependency Injection terbukti membuat kode program lebih rapi, terstruktur, mudah diuji (testable), dan mudah dikembangkan (maintainable).