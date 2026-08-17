package com.example.crud.Vista;


import com.example.crud.Logica.Persona;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.List;

public class PanelIzquierdo extends VBox {
    private HBox accionesAltaBaja;
    private TableView<Persona> usuarios;
    private Button alta, baja;

    public PanelIzquierdo(){
        accionesAltaBaja = new HBox();
        usuarios = new TableView<>();
        alta = new Button("ALTA");
        baja = new Button("BAJA");

        // Columnas definidas una sola vez, aquí en el constructor
        TableColumn<Persona, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        TableColumn<Persona, String> colDireccion = new TableColumn<>("Dirección");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));

        TableColumn<Persona, String> colID = new TableColumn<>("ID");
        colID.setCellValueFactory(new PropertyValueFactory<>("id"));

        usuarios.getColumns().addAll(colNombre, colDireccion, colID);

        accionesAltaBaja.getChildren().addAll(alta, baja);
        accionesAltaBaja.setAlignment(Pos.CENTER);
        this.getChildren().addAll(accionesAltaBaja, usuarios);
    }

    // Solo reemplaza los datos, no toca columnas
    public void actualizar(List<Persona> p){
        ObservableList<Persona> datos = FXCollections.observableArrayList(p);
        usuarios.setItems(datos);
    }

    public TableView<Persona> getUsuarios(){
        return usuarios;
    }

    public Button getAlta(){
        return alta;
    }

    public Button getBaja(){
        return baja;
    }
}