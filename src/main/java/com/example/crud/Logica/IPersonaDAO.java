package com.example.crud.Logica;

import java.util.ArrayList;

public interface IPersonaDAO {
    ArrayList<Persona> getPersonas();
    void agregar(Persona p);
    void eliminar(Persona p);
    void actualizar(Persona p);
}