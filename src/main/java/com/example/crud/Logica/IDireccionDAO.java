package com.example.crud.Logica;

import java.util.ArrayList;

public interface IDireccionDAO {
    ArrayList<Direccion> getDireccionesDe(Persona p);
    void agregarA(int personaId, String calle, String ciudad);
    void quitarDe(int personaId, int direccionId);
}