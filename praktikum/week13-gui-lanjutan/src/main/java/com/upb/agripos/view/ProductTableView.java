package main.java.com.upb.agripos.view;

import main.java.com.upb.agripos.model.Produk;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductTableView {
    private TextField txtKode, txtNama, txtHarga, txtStok;
    private Button btnAdd, btnDelete; // Tambah tombol Delete
    
    // GANTI ListView JADI TableView
    private TableView<Produk> tableView;
    private VBox layout;

    public ProductTableView() {
        initUI();
    }

    private void initUI() {
        // 1. Form Input
        txtKode = new TextField(); txtKode.setPromptText("Kode");
        txtNama = new TextField(); txtNama.setPromptText("Nama Produk");
        txtHarga = new TextField(); txtHarga.setPromptText("Harga");
        txtStok = new TextField(); txtStok.setPromptText("Stok");
        
        btnAdd = new Button("Simpan");
        btnDelete = new Button("Hapus");
        
        // HBox untuk tombol
        HBox buttonBox = new HBox(10, btnAdd, btnDelete);

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10);
        form.add(new Label("Kode:"), 0, 0); form.add(txtKode, 1, 0);
        form.add(new Label("Nama:"), 0, 1); form.add(txtNama, 1, 1);
        form.add(new Label("Harga:"), 0, 2); form.add(txtHarga, 1, 2);
        form.add(new Label("Stok:"), 0, 3); form.add(txtStok, 1, 3);
        form.add(buttonBox, 1, 4);

        // 2. Setup TableView
        tableView = new TableView<>();
        
        // Kolom Kode (Sesuai getter: getKode)
        TableColumn<Produk, String> colKode = new TableColumn<>("Kode");
        colKode.setCellValueFactory(new PropertyValueFactory<>("kode"));
        
        // Kolom Nama (Sesuai getter: getNama)
        TableColumn<Produk, String> colNama = new TableColumn<>("Nama");
        colNama.setCellValueFactory(new PropertyValueFactory<>("nama"));
        colNama.setMinWidth(150);

        // Kolom Harga (Sesuai getter: getHarga)
        TableColumn<Produk, Double> colHarga = new TableColumn<>("Harga");
        colHarga.setCellValueFactory(new PropertyValueFactory<>("harga"));

        // Kolom Stok (Sesuai getter: getStok)
        TableColumn<Produk, Integer> colStok = new TableColumn<>("Stok");
        colStok.setCellValueFactory(new PropertyValueFactory<>("stok"));

        // Masukkan kolom ke tabel
        tableView.getColumns().add(colKode);
        tableView.getColumns().add(colNama);
        tableView.getColumns().add(colHarga);
        tableView.getColumns().add(colStok);

        // 3. Layout Utama
        layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(new Label("=== Agri-POS V2 (TableView) ==="), form, tableView);
    }

    public Parent asParent() { return layout; }
    
    public void clearFields() { 
        txtKode.clear(); txtNama.clear(); txtHarga.clear(); txtStok.clear(); 
    }

    // Getters
    public TextField getTxtKode() { return txtKode; }
    public TextField getTxtNama() { return txtNama; }
    public TextField getTxtHarga() { return txtHarga; }
    public TextField getTxtStok() { return txtStok; }
    public Button getBtnAdd() { return btnAdd; }
    public Button getBtnDelete() { return btnDelete; } // Getter tombol hapus
    public TableView<Produk> getTableView() { return tableView; } // Getter tabel
}