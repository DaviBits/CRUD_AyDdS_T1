package com.example.crud.Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PersonaTest {

    private Persona persona;

    @BeforeEach
    void setUp(){
        // se ejecuta antes de CADA prueba, así cada una arranca con un objeto limpio
        persona = new Persona("Ana López", "Calle 5 #123");
    }

    @Test
    @DisplayName("Constructor sin id asigna id = 0")
    void constructorSinId_asignaIdCero(){
        assertEquals(0, persona.getId());
    }

    @Test
    @DisplayName("Constructor guarda nombre y dirección correctamente")
    void constructor_guardaNombreYDireccion(){
        assertEquals("Ana López", persona.getNombre());
        assertEquals("Calle 5 #123", persona.getDireccion());
    }

    @Test
    @DisplayName("Constructor completo permite asignar id directamente")
    void constructorCompleto_asignaIdCorrecto(){
        Persona p = new Persona(7, "Carlos Ruiz", "Av. Reforma 45");
        assertEquals(7, p.getId());
        assertEquals("Carlos Ruiz", p.getNombre());
    }

    @Test
    @DisplayName("Persona nueva empieza con lista de teléfonos vacía")
    void personaNueva_empiezaSinTelefonos(){
        assertNotNull(persona.getTelefonos());
        assertTrue(persona.getTelefonos().isEmpty());
    }

    @Test
    @DisplayName("setNombre actualiza el valor")
    void setNombre_actualizaElValor(){
        persona.setNombre("Ana María López");
        assertEquals("Ana María López", persona.getNombre());
    }

    @Test
    @DisplayName("setDireccion actualiza el valor")
    void setDireccion_actualizaElValor(){
        persona.setDireccion("Nueva dirección 99");
        assertEquals("Nueva dirección 99", persona.getDireccion());
    }

    @Test
    @DisplayName("setId actualiza el valor")
    void setId_actualizaElValor(){
        persona.setId(42);
        assertEquals(42, persona.getId());
    }

    @Test
    @DisplayName("agregarTelefono añade un teléfono a la lista")
    void agregarTelefono_loAnadeALaLista(){
        Telefono t = new Telefono(0, "686-123-4567");

        persona.agregarTelefono(t);

        assertEquals(1, persona.getTelefonos().size());
        assertEquals("686-123-4567", persona.getTelefonos().get(0).getTelefono());
    }

    @Test
    @DisplayName("agregarTelefono con varios teléfonos mantiene el orden de inserción")
    void agregarVariosTelefonos_seAcumulanEnOrden(){
        persona.agregarTelefono(new Telefono(0, "686-111-1111"));
        persona.agregarTelefono(new Telefono(0, "686-222-2222"));
        persona.agregarTelefono(new Telefono(0, "686-333-3333"));

        assertEquals(3, persona.getTelefonos().size());
        assertEquals("686-111-1111", persona.getTelefonos().get(0).getTelefono());
        assertEquals("686-222-2222", persona.getTelefonos().get(1).getTelefono());
        assertEquals("686-333-3333", persona.getTelefonos().get(2).getTelefono());
    }

    @Test
    @DisplayName("quitarTelefono elimina el teléfono correcto de la lista")
    void quitarTelefono_loEliminaDeLaLista(){
        Telefono t1 = new Telefono(0, "686-111-1111");
        Telefono t2 = new Telefono(0, "686-222-2222");
        persona.agregarTelefono(t1);
        persona.agregarTelefono(t2);

        persona.quitarTelefono(t1);

        assertEquals(1, persona.getTelefonos().size());
        assertEquals("686-222-2222", persona.getTelefonos().get(0).getTelefono());
    }

    @Test
    @DisplayName("quitarTelefono en lista vacía no lanza excepción")
    void quitarTelefono_listaVacia_noLanzaExcepcion(){
        Telefono t = new Telefono(0, "686-999-9999");
        assertDoesNotThrow(() -> persona.quitarTelefono(t));
        assertTrue(persona.getTelefonos().isEmpty());
    }
}