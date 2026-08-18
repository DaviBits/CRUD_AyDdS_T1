package com.example.crud.Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class TelefonoTest {

    private Telefono telefono;

    @BeforeEach
    void setUp(){
        telefono = new Telefono(5, "686-123-4567");
    }

    @Test
    @DisplayName("Constructor sin id asigna id = 0")
    void constructorSinId_asignaIdCero(){
        assertEquals(0, telefono.getId());
    }

    @Test
    @DisplayName("Constructor guarda personaID y telefono correctamente")
    void constructor_guardaPersonaIdYTelefono(){
        assertEquals(5, telefono.getPersonaID());
        assertEquals("686-123-4567", telefono.getTelefono());
    }

    @Test
    @DisplayName("Constructor completo permite asignar id directamente")
    void constructorCompleto_asignaIdCorrecto(){
        Telefono t = new Telefono(10, 5, "686-999-0001");
        assertEquals(10, t.getId());
        assertEquals(5, t.getPersonaID());
        assertEquals("686-999-0001", t.getTelefono());
    }

    @Test
    @DisplayName("setId actualiza el valor")
    void setId_actualizaElValor(){
        telefono.setId(99);
        assertEquals(99, telefono.getId());
    }

    @Test
    @DisplayName("setPersonaID actualiza el valor")
    void setPersonaID_actualizaElValor(){
        telefono.setPersonaID(20);
        assertEquals(20, telefono.getPersonaID());
    }

    @Test
    @DisplayName("setTelefono actualiza el valor")
    void setTelefono_actualizaElValor(){
        telefono.setTelefono("686-555-9999");
        assertEquals("686-555-9999", telefono.getTelefono());
    }

    @Test
    @DisplayName("Dos teléfonos con los mismos datos son objetos distintos (sin equals sobreescrito)")
    void dosTelefonosConMismosDatos_noSonElMismoObjeto(){
        Telefono otro = new Telefono(5, "686-123-4567");
        assertNotSame(telefono, otro);
    }
}