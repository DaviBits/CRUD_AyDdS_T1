package com.example.crud.Vista;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.BorderPane;

public class PantallaPrincipal extends BorderPane {
    private Label titulo;
    private SplitPane contenido;
    private PanelIzquierdo izq;
    private PanelDerecho der;

    public PantallaPrincipal(){
        titulo = new Label("CRUD ");
        titulo.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");
        BorderPane.setAlignment(titulo, Pos.CENTER);
        BorderPane.setMargin(titulo, new Insets(12));

        izq = new PanelIzquierdo();
        der = new PanelDerecho();

        contenido = new SplitPane();
        contenido.getItems().addAll(izq, der);
        contenido.setDividerPositions(0.4);// 40% izquierda, 60% derecha

        this.setTop(titulo);
        BorderPane.setAlignment(titulo, Pos.CENTER);
        this.setCenter(contenido);
    }

    public PanelIzquierdo getIzq(){
        return izq;
    }

    public PanelDerecho getDer(){
        return der;
    }
}