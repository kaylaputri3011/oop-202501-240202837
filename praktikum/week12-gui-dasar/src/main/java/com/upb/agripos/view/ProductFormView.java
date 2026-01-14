package main.java.com.upb.agripos.view;

import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

public class ProductFormView {
    private TextField txtKode, txtNama, txtHarga, txtStok;
    private Button btnAdd;
    private ListView<String> listView;
    private VBox layout;

    public ProductFormView() {
        initUI();
    }

    private void initUI() {
        txtKode = new TextField();
        txtNama = new TextField();
        txtHarga = new TextField();
        txtStok = new TextField();
        btnAdd = new Button("Simpan Produk");
        listView = new ListView<>();

        GridPane form = new GridPane();
        form.setHgap(10); form.setVgap(10); form.setPadding(new Insets(10));
        
        form.add(new Label("Kode:"), 0, 0); form.add(txtKode, 1, 0);
        form.add(new Label("Nama:"), 0, 1); form.add(txtNama, 1, 1);
        form.add(new Label("Harga:"), 0, 2); form.add(txtHarga, 1, 2);
        form.add(new Label("Stok:"), 0, 3); form.add(txtStok, 1, 3);
        form.add(btnAdd, 1, 4);

        layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(new Label("=== Agri-POS Week 12 ==="), form, new Label("Data Database:"), listView);
    }

    public Parent asParent() { return layout; }
    public void clear() { txtKode.clear(); txtNama.clear(); txtHarga.clear(); txtStok.clear(); }
    
    // Getters
    public TextField getTxtKode() { return txtKode; }
    public TextField getTxtNama() { return txtNama; }
    public TextField getTxtHarga() { return txtHarga; }
    public TextField getTxtStok() { return txtStok; }
    public Button getBtnAdd() { return btnAdd; }
    public ListView<String> getListView() { return listView; }
}