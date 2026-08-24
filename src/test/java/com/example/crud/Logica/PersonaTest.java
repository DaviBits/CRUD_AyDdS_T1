package com.example.crud.Logica;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;

import static org.junit.jupiter.api.Assertions.*;

public class PersonaTest {

    private Persona persona;

    @BeforeEach
    void setUp(){
        persona = new Persona("Ana López");
    }

    @Test
    @DisplayName("Constructor sin id asigna id = 0")
    void constructorSinId_asignaIdCero(){
        assertEquals(0, persona.getId());
    }

    @Test
    @DisplayName("Constructor guarda el nombre correctamente")
    void constructor_guardaNombre(){
        assertEquals("Ana López", persona.getNombre());
    }

    @Test
    @DisplayName("Constructor completo permite asignar id directamente")
    void constructorCompleto_asignaIdCorrecto(){
        Persona p = new Persona(7, "Carlos Ruiz");
        assertEquals(7, p.getId());
        assertEquals("Carlos Ruiz", p.getNombre());
    }

    @Test
    @DisplayName("Persona nueva empieza sin teléfonos ni direcciones")
    void personaNueva_empiezaVacia(){
        assertTrue(persona.getTelefonos().isEmpty());
        assertTrue(persona.getDirecciones().isEmpty());
    }

    @Test
    @DisplayName("setNombre actualiza el valor")
    void setNombre_actualizaElValor(){
        persona.setNombre("Ana María López");
        assertEquals("Ana María López", persona.getNombre());
    }

    @Test
    @DisplayName("setId actualiza el valor")
    void setId_actualizaElValor(){
        persona.setId(42);
        assertEquals(42, persona.getId());
    }

    // --- Teléfonos ---

    @Test
    @DisplayName("agregarTelefono añade un teléfono a la lista")
    void agregarTelefono_loAnadeALaLista(){
        Telefono t = new Telefono(0, "686-123-4567");
        persona.agregarTelefono(t);

        assertEquals(1, persona.getTelefonos().size());
        assertEquals("686-123-4567", persona.getTelefonos().get(0).getTelefono());
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

    // --- Direcciones (nuevo) ---

    @Test
    @DisplayName("agregarDireccion añade una dirección a la lista")
    void agregarDireccion_laAnadeALaLista(){
        Direccion d = new Direccion("Calle 5 #123", "Mexicali");
        persona.agregarDireccion(d);

        assertEquals(1, persona.getDirecciones().size());
        assertEquals("Calle 5 #123", persona.getDirecciones().get(0).getCalle());
    }

    @Test
    @DisplayName("agregarDireccion con varias direcciones mantiene el orden de inserción")
    void agregarVariasDirecciones_seAcumulanEnOrden(){
        persona.agregarDireccion(new Direccion("Calle 5", "Mexicali"));
        persona.agregarDireccion(new Direccion("Av. Reforma 45", "Tijuana"));

        assertEquals(2, persona.getDirecciones().size());
        assertEquals("Calle 5", persona.getDirecciones().get(0).getCalle());
        assertEquals("Av. Reforma 45", persona.getDirecciones().get(1).getCalle());
    }

    @Test
    @DisplayName("quitarDireccion elimina la dirección correcta de la lista")
    void quitarDireccion_laEliminaDeLaLista(){
        Direccion d1 = new Direccion("Calle 5", "Mexicali");
        Direccion d2 = new Direccion("Av. Reforma 45", "Tijuana");
        persona.agregarDireccion(d1);
        persona.agregarDireccion(d2);

        persona.quitarDireccion(d1);

        assertEquals(1, persona.getDirecciones().size());
        assertEquals("Av. Reforma 45", persona.getDirecciones().get(0).getCalle());
    }

    @Test
    @DisplayName("quitarDireccion en lista vacía no lanza excepción")
    void quitarDireccion_listaVacia_noLanzaExcepcion(){
        Direccion d = new Direccion("Calle X", "Ciudad Y");
        assertDoesNotThrow(() -> persona.quitarDireccion(d));
    }
}