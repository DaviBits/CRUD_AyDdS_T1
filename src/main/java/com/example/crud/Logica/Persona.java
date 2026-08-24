package com.example.crud.Logica;

import java.util.ArrayList;
import java.util.List;

public class Persona {
    private int id;
    private String nombre;
    private List<Telefono> telefonos = new ArrayList<>();
    private List<Direccion> direcciones = new ArrayList<>();

    public Persona(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    public Persona(String nombre, String direccion){
        this(0, nombre);
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

    public List<Direccion> getDirecciones(){ return direcciones; }
    public void agregarDireccion(Direccion d){ direcciones.add(d); }
    public void quitarDireccion(Direccion d){ direcciones.remove(d); }
}