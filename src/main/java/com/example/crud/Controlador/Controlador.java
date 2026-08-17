package com.example.crud.Controlador;

import com.example.crud.Logica.ManejadorDB;
import com.example.crud.Logica.Persona;
import com.example.crud.Vista.PantallaPrincipal;

public class Controlador {
    private PantallaPrincipal vista;
    private ManejadorDB db;

    public Controlador(){
        vista=new PantallaPrincipal();
        db=new ManejadorDB();

        vista.getIzq().actualizar(db.getPersonas());
        setActions();
    }

    public PantallaPrincipal getVista() {
        return vista;
    }

    public void setActions(){
        vista.getIzq().getUsuarios().setOnMouseClicked(e->{
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            vista.getDer().mostrar(p, db.getTelefonosDe(p));
        });
        vista.getIzq().getAlta().setOnMouseClicked(e->{
            if(!vista.getDer().getNombre().getText().isEmpty() &&
                    !vista.getDer().getDireccion().getText().isEmpty() ){
                Persona p =  vista.getDer().obtenerPersona(0);
                db.agregar(p);
                vista.getIzq().actualizar(db.getPersonas());

            }
        });

        vista.getIzq().getBaja().setOnMouseClicked(e->{
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            db.eliminar(p);
            vista.getIzq().actualizar(db.getPersonas());
        });
    }
}
