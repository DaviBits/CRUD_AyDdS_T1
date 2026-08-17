package com.example.crud.Vista;


import com.example.crud.Logica.Persona;
import com.example.crud.Logica.Telefono;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

public class PanelDerecho extends VBox {
    private TextField nombre, direccion, nuevoTelefono;
    private ListView<Telefono> telefonos;
    private Button agregarTelefono, quitarTelefono, guardar, cancelar;
    private HBox filaNuevoTelefono, filaAccionesTelefono, filaAcciones;

    public PanelDerecho(){
        this.setPadding(new Insets(15));
        this.setSpacing(10);

        nombre = new TextField();
        nombre.setPromptText("Nombre");

        direccion = new TextField();
        direccion.setPromptText("Dirección");

        telefonos = new ListView<>();
        telefonos.setPrefHeight(150);

        nuevoTelefono = new TextField();
        nuevoTelefono.setPromptText("Nuevo teléfono...");
        agregarTelefono = new Button("+");
        filaNuevoTelefono = new HBox(6, nuevoTelefono, agregarTelefono);

        quitarTelefono = new Button("Quitar teléfono seleccionado");
        filaAccionesTelefono = new HBox(quitarTelefono);

        guardar = new Button("Guardar cambios");
        cancelar = new Button("Cancelar");
        filaAcciones = new HBox(10, guardar, cancelar);

        this.getChildren().addAll(
                new Label("Nombre"), nombre,
                new Label("Dirección"), direccion,
                new Label("Teléfonos"), telefonos,
                filaNuevoTelefono,
                filaAccionesTelefono,
                filaAcciones
        );
    }

    // Carga una persona existente en el formulario (modo edición)
    public void mostrar(Persona p, ArrayList<Telefono> t){
        if(p == null){
            limpiar();
            return;
        }
        nombre.setText(p.getNombre());
        direccion.setText(p.getDireccion());
        ObservableList<Telefono> datos = FXCollections.observableArrayList(t);
        telefonos.setItems(datos);
    }

    // Limpia el formulario (modo alta nueva)
    public void limpiar(){
        nombre.clear();
        direccion.clear();
        telefonos.setItems(FXCollections.observableArrayList());
        nuevoTelefono.clear();
    }

    // Arma un Persona con lo que hay actualmente en el formulario
    public Persona obtenerPersona(int id){
        Persona p = new Persona(id, nombre.getText(), direccion.getText());
        for(Telefono t : telefonos.getItems()){
            p.agregarTelefono(t);
        }
        return p;
    }

    public void agregarTelefonoALista(Telefono t){
        telefonos.getItems().add(t);
    }

    public void quitarTelefonoSeleccionado(){
        Telefono seleccionado = telefonos.getSelectionModel().getSelectedItem();
        if(seleccionado != null){
            telefonos.getItems().remove(seleccionado);
        }
    }

    // Getters para que el controlador enlace acciones (setOnAction, listeners, etc.)
    public TextField getNombre(){ return nombre; }
    public TextField getDireccion(){ return direccion; }
    public TextField getNuevoTelefono(){ return nuevoTelefono; }
    public ListView<Telefono> getTelefonos(){ return telefonos; }
    public Button getAgregarTelefono(){ return agregarTelefono; }
    public Button getQuitarTelefono(){ return quitarTelefono; }
    public Button getGuardar(){ return guardar; }
    public Button getCancelar(){ return cancelar; }
}