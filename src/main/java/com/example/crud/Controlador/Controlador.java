package com.example.crud.Controlador;

import com.example.crud.Logica.Direccion;
import com.example.crud.Logica.ManejadorDB;
import com.example.crud.Logica.Persona;
import com.example.crud.Logica.Telefono;
import com.example.crud.Vista.PantallaPrincipal;

public class Controlador {
    private PantallaPrincipal vista;
    private ManejadorDB db;

    public Controlador(){
        vista = new PantallaPrincipal();
        db = new ManejadorDB();

        vista.getIzq().actualizar(db.getPersonas());
        setActions();
    }

    public PantallaPrincipal getVista() {
        return vista;
    }

    public void setActions(){
        vista.getIzq().getUsuarios().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p == null) return;
            vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
        });

        vista.getIzq().getAlta().setOnMouseClicked(e -> {
            if (!vista.getDer().getNombre().getText().isEmpty()) {
                Persona p = vista.getDer().obtenerPersona(0);
                db.agregar(p);
                vista.getIzq().actualizar(db.getPersonas());
            }
        });

        vista.getIzq().getBaja().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p == null) return;
            db.eliminar(p);
            vista.getIzq().actualizar(db.getPersonas());
        });

        vista.getDer().getAgregarTelefono().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p == null) return;

            String num = vista.getDer().getNuevoTelefono().getText();
            if (!num.isEmpty()) {
                db.agregarTelefonoA(p.getId(), num);
                vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
            }
        });

        vista.getDer().getQuitarTelefono().setOnMouseClicked(e -> {
            Telefono t = vista.getDer().getTelefonos().getSelectionModel().getSelectedItem();
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p == null || t == null) return;
            db.eliminarTelefono(t, p);
            vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
        });

        vista.getDer().getTelefonos().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Telefono seleccionado = vista.getDer().getTelefonos().getSelectionModel().getSelectedItem();
                if (seleccionado != null) {
                    vista.getDer().getNuevoTelefono().setText(seleccionado.getTelefono());
                }
            }
        });

        vista.getDer().getGuardarEdicionTelefono().setOnMouseClicked(e -> {
            Telefono seleccionado = vista.getDer().getTelefonos().getSelectionModel().getSelectedItem();
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (seleccionado == null || p == null) return;

            String nuevoNumero = vista.getDer().getNuevoTelefono().getText();
            if (nuevoNumero.isEmpty()) return;

            db.editarTelefono(seleccionado.getId(), nuevoNumero);
            vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
            vista.getDer().getNuevoTelefono().clear();
        });

        vista.getDer().getAgregarDireccion().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (p == null) return;

            String calle = vista.getDer().getNuevaCalle().getText();
            String ciudad = vista.getDer().getNuevaCiudad().getText();
            if (calle.isEmpty()) return;

            db.agregarDireccionA(p.getId(), calle, ciudad);
            vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
            vista.getDer().getNuevaCalle().clear();
            vista.getDer().getNuevaCiudad().clear();
        });

        vista.getDer().getQuitarDireccion().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            Direccion d = vista.getDer().getDirecciones().getSelectionModel().getSelectedItem();
            if (p == null || d == null) return;

            db.quitarDireccionDe(p.getId(), d.getId());
            vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
        });

        vista.getDer().getGuardar().setOnMouseClicked(e -> {
            Persona seleccionada = vista.getIzq().getUsuarios().getSelectionModel().getSelectedItem();
            if (seleccionada == null) return;

            Persona p = new Persona(seleccionada.getId(), vista.getDer().getNombre().getText());
            db.actualizarPersona(p);
            vista.getDer().mostrar(p, db.getTelefonosDe(p), db.getDireccionesDe(p));
            vista.getIzq().actualizar(db.getPersonas());
        });

        vista.getDer().getCancelar().setOnMouseClicked(e -> {
            vista.getIzq().getUsuarios().getSelectionModel().clearSelection();
            vista.getDer().mostrar(null, null, null);
        });
    }
}