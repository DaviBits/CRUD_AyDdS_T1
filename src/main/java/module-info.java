module com.example.crud {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires java.sql;

    opens com.example.crud to javafx.fxml;
    opens com.example.crud.Vista;
    opens com.example.crud.Controlador;
    opens com.example.crud.Logica;

    exports com.example.crud;
}