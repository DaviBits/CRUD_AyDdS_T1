package com.example.crud.Logica;

import java.util.ArrayList;

public interface ITelefonoDAO {
    ArrayList<Telefono> getTelefonosDe(Persona p);
    void agregarA(int personaId, String telefono);
    void eliminar(Telefono t, Persona p);
    void editar(int id, String nuevoNumero);
}