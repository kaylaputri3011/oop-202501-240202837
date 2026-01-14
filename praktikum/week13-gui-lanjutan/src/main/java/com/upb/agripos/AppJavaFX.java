package main.java.com.upb.agripos;

import main.java.com.upb.agripos.controller.ProductController;
import main.java.com.upb.agripos.dao.*;
import main.java.com.upb.agripos.service.ProductService;
import main.java.com.upb.agripos.view.ProductTableView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.sql.DriverManager;

public class AppJavaFX extends Application {
    @Override
    public void start(Stage stage) {
        try {
            var conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/agripos", 
                "postgres", 
                "111123"
            );

            ProductRepository repo = new SqlProductRepository(conn);
            ProductService service = new ProductService(repo);
            ProductTableView view = new ProductTableView();
            new ProductController(service, view);

            stage.setScene(new Scene(view.asParent(), 400, 500));
            stage.setTitle("Tugas Week 13 - Kayla (TableView)");
            stage.show();
        } catch (Exception e) { e.printStackTrace(); }
    }

    public static void main(String[] args) {
        launch(args);
    }
}