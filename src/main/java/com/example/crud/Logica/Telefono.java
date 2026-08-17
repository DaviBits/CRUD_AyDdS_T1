package com.example.crud.Logica;

public class Telefono {
    private int id;
    private int personaID;
    private String telefono;

    // Constructor completo (para reconstruir desde la BD)
    public Telefono(int id, int personaID, String telefono){
        this.id = id;
        this.personaID = personaID;
        this.telefono = telefono;
    }

    // Constructor auxiliar (para teléfono nuevo, todavía sin id de la BD)
    public Telefono (int personaID, String telefono){
        this(0, personaID, telefono);
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPersonaID(int personaID) {
        this.personaID = personaID;
    }

    public String toString (){return telefono;}

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getId() {
        return id;
    }

    public int getPersonaID() {
        return personaID;
    }

    public String getTelefono() {
        return telefono;
    }
}