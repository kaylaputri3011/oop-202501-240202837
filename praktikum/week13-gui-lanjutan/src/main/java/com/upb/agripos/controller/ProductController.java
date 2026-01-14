package main.java.com.upb.agripos.controller;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.service.ProductService;
import main.java.com.upb.agripos.view.ProductTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import java.util.List;

public class ProductController {
    private final ProductService service;
    private final ProductTableView view;

    public ProductController(ProductService service, ProductTableView view) {
        this.service = service;
        this.view = view;
        initController();
        loadData();
    }

    private void initController() {
        // --- EVENT HANDLING DENGAN LAMBDA EXPRESSION ---
        
        // 1. Tombol Simpan
        view.getBtnAdd().setOnAction(e -> simpan());

        // 2. Tombol Hapus (Lambda)
        view.getBtnDelete().setOnAction(e -> {
            // Ambil item yang dipilih di tabel
            Produk selected = view.getTableView().getSelectionModel().getSelectedItem();
            
            if (selected != null) {
                hapus(selected.getKode());
            } else {
                showAlert("Peringatan", "Pilih produk di tabel dulu!");
            }
        });
    }

    private void simpan() {
        try {
            service.addProduct(
                view.getTxtKode().getText(),
                view.getTxtNama().getText(),
                Double.parseDouble(view.getTxtHarga().getText()),
                Integer.parseInt(view.getTxtStok().getText())
            );
            showAlert("Sukses", "Data berhasil disimpan!");
            view.clearFields();
            loadData();
        } catch (Exception e) {
            showAlert("Error", e.getMessage());
        }
    }

    private void hapus(String code) {
        try {
            // Panggil service untuk hapus
            service.deleteProduct(code);
            
            showAlert("Sukses", "Data berhasil dihapus!");
            loadData(); // Refresh tabel
        } catch (Exception e) {
            showAlert("Gagal Hapus", e.getMessage());
        }
    }

    private void loadData() {
        try {
            // Ambil List biasa dari Service
            List<Produk> list = service.getAllProducts();
            
            // Konversi ke ObservableList agar bisa masuk TableView
            ObservableList<Produk> observableList = FXCollections.observableArrayList(list);
            
            // Set ke TableView
            view.getTableView().setItems(observableList);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title); a.setContentText(msg); a.show();
    }
}