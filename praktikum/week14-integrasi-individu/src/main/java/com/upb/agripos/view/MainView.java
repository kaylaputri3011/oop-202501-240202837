package main.java.com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import main.java.com.upb.agripos.model.Produk;

public class MainView {
    // Komponen Produk (Kiri)
    private TableView<Produk> tableProduk;
    private TextField txtKode, txtNama, txtHarga, txtStok;
    private Button btnSimpan, btnHapus;

    // Komponen Keranjang (Kanan)
    private ListView<String> listCart;
    private Label lblTotal;
    private Button btnAddToCart, btnCheckout;

    private BorderPane layout;

    public MainView() {
        initUI();
    }

    private void initUI() {
        // --- BAGIAN KIRI: MANAJEMEN PRODUK (Seperti Week 13) ---
        tableProduk = new TableView<>();
        setupTableColumns();
        
        txtKode = new TextField(); txtKode.setPromptText("Kode");
        txtNama = new TextField(); txtNama.setPromptText("Nama");
        txtHarga = new TextField(); txtHarga.setPromptText("Harga");
        txtStok = new TextField(); txtStok.setPromptText("Stok");
        btnSimpan = new Button("Simpan / Update");
        btnHapus = new Button("Hapus Produk");
        
        VBox leftSide = new VBox(10, new Label("=== Manajemen Produk ==="), tableProduk, 
                new HBox(5, txtKode, txtNama), new HBox(5, txtHarga, txtStok), 
                new HBox(5, btnSimpan, btnHapus));
        leftSide.setPadding(new Insets(10));
        leftSide.setPrefWidth(400);

        // --- BAGIAN KANAN: KASIR / KERANJANG (Fitur Week 14) ---
        listCart = new ListView<>();
        lblTotal = new Label("Total: Rp 0");
        lblTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold;");
        
        btnAddToCart = new Button("Masukan ke Keranjang ->");
        btnCheckout = new Button("Bayar / Checkout");
        
        VBox rightSide = new VBox(10, new Label("=== Keranjang Belanja ==="), 
                btnAddToCart, listCart, lblTotal, btnCheckout);
        rightSide.setPadding(new Insets(10));
        rightSide.setPrefWidth(300);

        // GABUNGKAN
        layout = new BorderPane();
        layout.setCenter(leftSide);
        layout.setRight(rightSide);
    }

    private void setupTableColumns() {
        TableColumn<Produk, String> colKode = new TableColumn<>("Kode");
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        TableColumn<Produk, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        TableColumn<Produk, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));
        TableColumn<Produk, Integer> colStok = new TableColumn<>("Stok");
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));
        tableProduk.getColumns().addAll(colKode, colNama, colHarga, colStok);
    }

    public Parent asParent() { return layout; }
    
    // Getter untuk Controller
    public TableView<Produk> getTableProduk() { return tableProduk; }
    public TextField getTxtKode() { return txtKode; }
    public TextField getTxtNama() { return txtNama; }
    public TextField getTxtHarga() { return txtHarga; }
    public TextField getTxtStok() { return txtStok; }
    public Button getBtnSimpan() { return btnSimpan; }
    public Button getBtnHapus() { return btnHapus; }
    public Button getBtnAddToCart() { return btnAddToCart; }
    public Button getBtnCheckout() { return btnCheckout; }
    public ListView<String> getListCart() { return listCart; }
    public Label getLblTotal() { return lblTotal; }
    public void clearForm() { txtKode.clear(); txtNama.clear(); txtHarga.clear(); txtStok.clear(); }
}