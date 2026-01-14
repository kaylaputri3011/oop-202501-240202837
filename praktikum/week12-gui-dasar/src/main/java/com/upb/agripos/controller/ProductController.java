package main.java.com.upb.agripos.controller;

import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.service.ProductService;
import main.java.com.upb.agripos.view.ProductFormView;
import javafx.scene.control.Alert;

public class ProductController {
    private final ProductService service;
    private final ProductFormView view;

    public ProductController(ProductService service, ProductFormView view) {
        this.service = service;
        this.view = view;
        initController();
        refreshData();
    }

    private void initController() {
        // Event Handler JavaFX
        view.getBtnAdd().setOnAction(e -> simpan());
    }

    private void simpan() {
        try {
            String kode = view.getTxtKode().getText();
            String nama = view.getTxtNama().getText();
            double harga = Double.parseDouble(view.getTxtHarga().getText());
            int stok = Integer.parseInt(view.getTxtStok().getText());

            // Panggil Service (Integrasi GUI -> Service -> Backend)
            service.addProduct(kode, nama, harga, stok);

            showAlert("Sukses", "Data berhasil disimpan!");
            view.clear();
            refreshData();

        } catch (NumberFormatException e) {
            showAlert("Error", "Harga/Stok harus angka!");
        } catch (Exception e) {
            showAlert("Gagal", e.getMessage());
        }
    }

    private void refreshData() {
        try {
            view.getListView().getItems().clear();
            for (Produk p : service.getAllProducts()) {
                view.getListView().getItems().add(p.getKode() + " - " + p.getNama() + " (Stok: " + p.getStok() + ")");
            }
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void showAlert(String title, String msg) {
        Alert a = new Alert(Alert.AlertType.INFORMATION);
        a.setTitle(title); a.setContentText(msg); a.show();
    }
}