package com.example.crud.Controlador;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        Controlador c = new Controlador();
        Scene scene = new Scene(c.getVista());
        stage.setScene(scene);
        stage.setTitle("CRUD");
        stage.show();
    }
}
