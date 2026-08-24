package com.example.crud.Logica;

public class Direccion {
    private int id;
    private String calle;
    private String ciudad;

    public Direccion(int id, String calle, String ciudad){
        this.id = id;
        this.calle = calle;
        this.ciudad = ciudad;
    }

    public Direccion(String calle, String ciudad){
        this(0, calle, ciudad);
    }

    public int getId(){ return id; }
    public String getCalle(){ return calle; }
    public String getCiudad(){ return ciudad; }
    public void setId(int id){ this.id = id; }
    public void setCalle(String calle){ this.calle = calle; }
    public void setCiudad(String ciudad){ this.ciudad = ciudad; }


    @Override
    public String toString(){
        return calle + (ciudad != null && !ciudad.isEmpty() ? ", " + ciudad : "");
    }
}