package com.example.crud.Controlador;

import com.example.crud.Logica.*;
import com.example.crud.Vista.PantallaPrincipal;

public class Controlador {
    private PantallaPrincipal vista;
    private IPersonaDAO personaDAO;
    private ITelefonoDAO telefonoDAO;
    private IDireccionDAO direccionDAO;

    public Controlador(){
        vista = new PantallaPrincipal();
        personaDAO = new PersonaDAO();
        telefonoDAO = new TelefonoDAO();
        direccionDAO = new DireccionDAO();

        vista.getIzq().actualizar(personaDAO.getPersonas());
        setActions();
    }

    public PantallaPrincipal getVista() {
        return vista;
    }

    public void setActions(){
        vista.getIzq().getUsuarios().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getSeleccionada();
            if (p == null) return;
            vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
        });

        vista.getIzq().getAlta().setOnMouseClicked(e -> {
            if (!vista.getDer().getNombre().getText().isEmpty()) {
                Persona p = vista.getDer().obtenerPersona(0);
                personaDAO.agregar(p);
                vista.getIzq().actualizar(personaDAO.getPersonas());
            }
        });

        vista.getIzq().getBaja().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getSeleccionada();
            if (p == null) return;
            personaDAO.eliminar(p);
            vista.getIzq().actualizar(personaDAO.getPersonas());
        });

        vista.getDer().getAgregarTelefono().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getSeleccionada();
            if (p == null) return;

            String num = vista.getDer().getNuevoTelefono().getText();
            if (!num.isEmpty()) {
                telefonoDAO.agregarA(p.getId(), num);
                vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
            }
        });

        vista.getDer().getQuitarTelefono().setOnMouseClicked(e -> {
            Telefono t = vista.getDer().getTelefonoSeleccionado();
            Persona p = vista.getIzq().getSeleccionada();
            if (p == null || t == null) return;
            telefonoDAO.eliminar(t, p);
            vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
        });

        vista.getDer().getTelefonos().setOnMouseClicked(e -> {
            if (e.getClickCount() == 2) {
                Telefono seleccionado = vista.getDer().getTelefonoSeleccionado();
                if (seleccionado != null) {
                    vista.getDer().getNuevoTelefono().setText(seleccionado.getTelefono());
                }
            }
        });

        vista.getDer().getGuardarEdicionTelefono().setOnMouseClicked(e -> {
            Telefono seleccionado = vista.getDer().getTelefonoSeleccionado();
            Persona p = vista.getIzq().getSeleccionada();
            if (seleccionado == null || p == null) return;

            String nuevoNumero = vista.getDer().getNuevoTelefono().getText();
            if (nuevoNumero.isEmpty()) return;

            telefonoDAO.editar(seleccionado.getId(), nuevoNumero);
            vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
            vista.getDer().getNuevoTelefono().clear();
        });

        vista.getDer().getAgregarDireccion().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getSeleccionada();
            if (p == null) return;

            String calle = vista.getDer().getNuevaCalle().getText();
            String ciudad = vista.getDer().getNuevaCiudad().getText();
            if (calle.isEmpty()) return;

            direccionDAO.agregarA(p.getId(), calle, ciudad);
            vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
            vista.getDer().getNuevaCalle().clear();
            vista.getDer().getNuevaCiudad().clear();
        });

        vista.getDer().getQuitarDireccion().setOnMouseClicked(e -> {
            Persona p = vista.getIzq().getSeleccionada();
            Direccion d = vista.getDer().getDireccionSeleccionada();
            if (p == null || d == null) return;

            direccionDAO.quitarDe(p.getId(), d.getId());
            vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
        });

        vista.getDer().getGuardar().setOnMouseClicked(e -> {
            Persona seleccionada = vista.getIzq().getSeleccionada();
            if (seleccionada == null) return;

            Persona p = new Persona(seleccionada.getId(), vista.getDer().getNombre().getText());
            personaDAO.actualizar(p);
            vista.getDer().mostrar(p, telefonoDAO.getTelefonosDe(p), direccionDAO.getDireccionesDe(p));
            vista.getIzq().actualizar(personaDAO.getPersonas());
        });

        vista.getDer().getCancelar().setOnMouseClicked(e -> {
            vista.getIzq().limpiarSeleccion();
            vista.getDer().mostrar(null, null, null);
        });
    }
}