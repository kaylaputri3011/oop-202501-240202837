package main.java.com.upb.agripos.controller;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import main.java.com.upb.agripos.model.CartItem;
import main.java.com.upb.agripos.model.Produk;
import main.java.com.upb.agripos.service.CartService;
import main.java.com.upb.agripos.service.ProductService;
import main.java.com.upb.agripos.view.MainView;

public class MainController {
    private final ProductService productService;
    private final CartService cartService;
    private final MainView view;

    public MainController(ProductService ps, CartService cs, MainView view) {
        this.productService = ps;
        this.cartService = cs;
        this.view = view;
        initEventHandlers();
        loadProductTable();
    }

    private void initEventHandlers() {
        // 1. Simpan Produk (Database)
        view.getBtnSimpan().setOnAction(e -> {
            try {
                productService.addProduct(
                    view.getTxtKode().getText(),
                    view.getTxtNama().getText(),
                    Double.parseDouble(view.getTxtHarga().getText()),
                    Integer.parseInt(view.getTxtStok().getText())
                );
                loadProductTable();
                view.clearForm();
                showAlert("Sukses", "Produk disimpan ke DB!");
            } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
        });

        // 2. Hapus Produk (Database)
        view.getBtnHapus().setOnAction(e -> {
            Produk selected = view.getTableProduk().getSelectionModel().getSelectedItem();
            if (selected != null) {
                try {
                    productService.deleteProduct(selected.getKode());
                    loadProductTable();
                } catch (Exception ex) { showAlert("Error", ex.getMessage()); }
            }
        });

        // 3. Masuk Keranjang (Memory Collection)
        view.getBtnAddToCart().setOnAction(e -> {
            Produk selected = view.getTableProduk().getSelectionModel().getSelectedItem();
            if (selected != null) {
                // Ambil 1 item (bisa dikembangkan pakai input dialog jumlah)
                cartService.addToCart(selected, 1);
                refreshCartView();
            } else {
                showAlert("Warning", "Pilih produk dulu!");
            }
        });

        // 4. Checkout (Simulasi)
        view.getBtnCheckout().setOnAction(e -> {
            cartService.clearCart();
            refreshCartView();
            showAlert("Info", "Transaksi Selesai. Struk dicetak di console.");
        });
    }

    private void loadProductTable() {
        try {
            view.getTableProduk().setItems(
                FXCollections.observableArrayList(productService.getAllProducts())
            );
        } catch (Exception e) { e.printStackTrace(); }
    }

    private void refreshCartView() {
        view.getListCart().getItems().clear();
        for (CartItem item : cartService.getCartItems()) {
            view.getListCart().getItems().add(item.toString());
        }
        view.getLblTotal().setText("Total: Rp " + String.format("%,.0f", cartService.calculateTotal()));
    }

    private void showAlert(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title); alert.setContentText(content); alert.show();
    }
}