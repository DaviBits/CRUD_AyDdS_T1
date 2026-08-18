package com.example.crud.Controlador;

import com.example.crud.Logica.ManejadorDB;
import com.example.crud.Logica.Persona;
import com.example.crud.Logica.Telefono;
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
                if (p ==null) return;
                db.agregar(p);
                vista.getIzq().actualizar(db.getPersonas());

            }
        });

        vista.getIzq().getBaja().setOnMouseClicked(e->{
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p ==null) return;
            db.eliminar(p);
            vista.getIzq().actualizar(db.getPersonas());
        });

        vista.getDer().getAgregarTelefono().setOnMouseClicked(e->{
            String num =vista.getDer().getNuevoTelefono().getText();

            int perID= vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem().getId();
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            Telefono t = new Telefono(perID, num);
            if (p ==null) return;

            if(!num.isEmpty()){
                db.agregarTelefonoA(perID, num);
                vista.getDer().mostrar(p, db.getTelefonosDe(p));
            }

        });

        vista.getDer().getQuitarTelefono().setOnMouseClicked(e->{
            Telefono t = vista.getDer().getTelefonos().getSelectionModel().getSelectedItem();
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p ==null) return;
            db.eliminarTelefono(t, p);
            vista.getDer().mostrar(p, db.getTelefonosDe(p));
            System.out.println(t);
        });

        vista.getDer().getGuardar().setOnMouseClicked(e->{
            Persona p = new Persona(
                    vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem().getId(),
                    vista.getDer().getNombre().getText(),
                    vista.getDer().getDireccion().getText()

            );


            System.out.println(p.getNombre());

            db.actualizarPersona(p);
            vista.getDer().mostrar(p, db.getTelefonosDe(p));
            vista.getIzq().actualizar(db.getPersonas());
       //     vista.getIzq().getUsuarios().getSelectionModel().set;
        });

        vista.getDer().getCancelar().setOnMouseClicked(e->{
            vista.getIzq().getUsuarios().getSelectionModel().clearSelection();
            vista.getDer().getNombre().setText("");
            vista.getDer().getDireccion().setText("");
            vista.getIzq().actualizar(db.getPersonas());
            vista.getDer().mostrar(null, null);
        });



    }
}
