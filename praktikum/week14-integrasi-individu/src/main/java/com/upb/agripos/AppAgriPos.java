package main.java.com.upb.agripos;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.java.com.upb.agripos.config.DatabaseConnection;
import main.java.com.upb.agripos.dao.SqlProductRepository;
import main.java.com.upb.agripos.service.CartService;
import main.java.com.upb.agripos.service.ProductService;
import main.java.com.upb.agripos.view.MainView;
import main.java.com.upb.agripos.controller.MainController;

import java.sql.Connection;
import java.sql.DriverManager;

public class AppAgriPos extends Application {

    @Override
    public void start(Stage stage) {
        // 1. Identitas (Syarat Week 1)
        System.out.println("Hello World, I am Kayla-240202837");

        try {
            // 2. Setup Database & Layering (Dependency Injection)
            Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/agripos", "postgres", "111123");
            
            // DAO -> Service -> Controller -> View
            var repo = new SqlProductRepository(conn);
            var productService = new ProductService(repo);
            var cartService = new CartService(); // Service baru
            var view = new MainView();

            // Wiring semuanya di Controller
            new MainController(productService, cartService, view);

            stage.setScene(new Scene(view.asParent(), 800, 600));
            stage.setTitle("Agri-POS Final Project - Kayla");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}