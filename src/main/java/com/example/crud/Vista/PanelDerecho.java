package com.example.crud.Vista;


import com.example.crud.Logica.Direccion;
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
    private TextField nombre, nuevoTelefono, nuevaCalle, nuevaCiudad;
    private ListView<Telefono> telefonos;
    private ListView<Direccion> direcciones;
    private Button agregarTelefono, quitarTelefono;
    private Button agregarDireccion, quitarDireccion;
    private Button guardar, cancelar;
    private Button guardarEdicionTelefono;

    public PanelDerecho(){
        this.setPadding(new Insets(15));
        this.setSpacing(10);

        nombre = new TextField();
        nombre.setPromptText("Nombre");

        // --- Teléfonos (igual que antes) ---
        telefonos = new ListView<>();
        telefonos.setPrefHeight(100);
        nuevoTelefono = new TextField();
        nuevoTelefono.setPromptText("Nuevo teléfono...");
        agregarTelefono = new Button("+");
        quitarTelefono = new Button("Quitar teléfono seleccionado");

        // --- Direcciones (nuevo, mismo patrón) ---
        direcciones = new ListView<>();
        direcciones.setPrefHeight(100);
        nuevaCalle = new TextField();
        nuevaCalle.setPromptText("Calle y número");
        nuevaCiudad = new TextField();
        nuevaCiudad.setPromptText("Ciudad");
        agregarDireccion = new Button("+");
        quitarDireccion = new Button("Quitar dirección seleccionada");

        guardar = new Button("Guardar cambios");
        cancelar = new Button("Cancelar");

        guardarEdicionTelefono = new Button("Guardar edición");

        this.getChildren().addAll(

                new Label("Nombre"), nombre,

                new Label("Teléfonos"), telefonos,
                new HBox(6, nuevoTelefono, agregarTelefono),
                quitarTelefono, guardarEdicionTelefono,

                new Label("Direcciones"), direcciones,
                new HBox(6, nuevaCalle, nuevaCiudad, agregarDireccion),
                quitarDireccion,

                new HBox(10, guardar, cancelar)
        );
    }

    // CAMBIÓ: ahora recibe también la lista de direcciones
    public void mostrar(Persona p, List<Telefono> tels, List<Direccion> dirs){
        if(p == null){
            limpiar();
            return;
        }
        nombre.setText(p.getNombre());
        telefonos.setItems(FXCollections.observableArrayList(tels));
        direcciones.setItems(FXCollections.observableArrayList(dirs));
    }

    public void limpiar(){
        nombre.clear();
        telefonos.setItems(FXCollections.observableArrayList());
        direcciones.setItems(FXCollections.observableArrayList());
        nuevoTelefono.clear();
        nuevaCalle.clear();
        nuevaCiudad.clear();
    }

    public Persona obtenerPersona(int id){
        return new Persona(id, nombre.getText());
    }

    public void quitarTelefonoSeleccionado(){
        Telefono t = telefonos.getSelectionModel().getSelectedItem();
        if(t != null) telefonos.getItems().remove(t);
    }

    public void quitarDireccionSeleccionada(){
        Direccion d = direcciones.getSelectionModel().getSelectedItem();
        if(d != null) direcciones.getItems().remove(d);
    }

    // Getters
    public TextField getNombre(){ return nombre; }
    public TextField getNuevoTelefono(){ return nuevoTelefono; }
    public TextField getNuevaCalle(){ return nuevaCalle; }
    public TextField getNuevaCiudad(){ return nuevaCiudad; }
    public ListView<Telefono> getTelefonos(){ return telefonos; }
    public ListView<Direccion> getDirecciones(){ return direcciones; }
    public Button getAgregarTelefono(){ return agregarTelefono; }
    public Button getQuitarTelefono(){ return quitarTelefono; }
    public Button getAgregarDireccion(){ return agregarDireccion; }
    public Button getQuitarDireccion(){ return quitarDireccion; }
    public Button getGuardar(){ return guardar; }
    public Button getCancelar(){ return cancelar; }
    public Button getGuardarEdicionTelefono(){ return guardarEdicionTelefono; }
}