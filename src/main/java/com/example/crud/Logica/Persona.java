package com.example.crud.Logica;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private int id;
    private String nombre;
    private String direccion;
    private List<Telefono> telefonos = new ArrayList<>();

    // Constructor completo (para reconstruir objetos que vienen de la BD)
    public Persona(int id, String nombre, String direccion){
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    // Constructor auxiliar (para alta nueva, todavía sin id de la BD)
    public Persona(String nombre, String direccion){
        this(0, nombre, direccion);
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getId() {
        return id;
    }

    public List<Telefono> getTelefonos() {
        return telefonos;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void agregarTelefono(Telefono t) {
        telefonos.add(t);
    }

    public void quitarTelefono(Telefono t) {
        telefonos.remove(t);
    }
}