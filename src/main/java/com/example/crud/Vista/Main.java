package com.example.crud.Vista;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) {
        PantallaPrincipal pantalla = new PantallaPrincipal();
        Scene scene = new Scene(pantalla, 900, 500);
        stage.setScene(scene);
        stage.setTitle("CRUD Prueba David García");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}