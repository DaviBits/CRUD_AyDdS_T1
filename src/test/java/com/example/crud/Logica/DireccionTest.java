package com.example.crud.Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class DireccionTest {

    private Direccion direccion;

    @BeforeEach
    void setUp(){
        direccion = new Direccion("Calle 5 #123", "Mexicali");
    }

    @Test
    @DisplayName("Constructor sin id asigna id = 0")
    void constructorSinId_asignaIdCero(){
        assertEquals(0, direccion.getId());
    }

    @Test
    @DisplayName("Constructor guarda calle y ciudad correctamente")
    void constructor_guardaCalleYCiudad(){
        assertEquals("Calle 5 #123", direccion.getCalle());
        assertEquals("Mexicali", direccion.getCiudad());
    }

    @Test
    @DisplayName("Constructor completo permite asignar id directamente")
    void constructorCompleto_asignaIdCorrecto(){
        Direccion d = new Direccion(3, "Av. Reforma 45", "Tijuana");
        assertEquals(3, d.getId());
        assertEquals("Av. Reforma 45", d.getCalle());
    }

    @Test
    @DisplayName("setCalle actualiza el valor")
    void setCalle_actualizaElValor(){
        direccion.setCalle("Blvd. Anáhuac 88");
        assertEquals("Blvd. Anáhuac 88", direccion.getCalle());
    }

    @Test
    @DisplayName("setCiudad actualiza el valor")
    void setCiudad_actualizaElValor(){
        direccion.setCiudad("Tijuana");
        assertEquals("Tijuana", direccion.getCiudad());
    }

    @Test
    @DisplayName("setId actualiza el valor")
    void setId_actualizaElValor(){
        direccion.setId(15);
        assertEquals(15, direccion.getId());
    }

    @Test
    @DisplayName("toString incluye calle y ciudad cuando ambas existen")
    void toString_incluyeCalleYCiudad(){
        assertEquals("Calle 5 #123, Mexicali", direccion.toString());
    }

    @Test
    @DisplayName("toString solo muestra la calle cuando no hay ciudad")
    void toString_soloCalleSinCiudad(){
        Direccion d = new Direccion("Calle Sola 10", "");
        assertEquals("Calle Sola 10", d.toString());
    }

    @Test
    @DisplayName("toString maneja ciudad null sin lanzar excepción")
    void toString_ciudadNull_noLanzaExcepcion(){
        Direccion d = new Direccion("Calle Sola 10", null);
        assertDoesNotThrow(d::toString);
        assertEquals("Calle Sola 10", d.toString());
    }
}