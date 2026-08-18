package com.example.crud.Logica;

import org.junit.jupiter.api.*;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ManejadorDBTest {

    private ManejadorDB db;

    @BeforeEach
    void setUp(){
        db = new ManejadorDB();
    }

    @AfterEach
    void limpiar(){
        // borra cualquier residuo de prueba, para no ensuciar la BD real
        db.getPersonas().stream()
                .filter(p -> p.getNombre().startsWith("TEST_"))
                .forEach(p -> db.eliminar(p));
    }

    @Test
    @DisplayName("agregar guarda una persona y aparece en getPersonas")
    void agregar_persistePersonaCorrectamente(){
        Persona p = new Persona("TEST_Ana López", "Calle 5 #123");

        db.agregar(p);

        Optional<Persona> encontrada = db.getPersonas().stream()
                .filter(x -> x.getNombre().equals("TEST_Ana López"))
                .findFirst();

        assertTrue(encontrada.isPresent());
        assertEquals("Calle 5 #123", encontrada.get().getDireccion());
    }

    @Test
    @DisplayName("agregarTelefonoA asocia un teléfono a la persona correcta")
    void agregarTelefonoA_seAsociaCorrectamente(){
        Persona p = new Persona("TEST_Carlos Ruiz", "Av. Reforma 45");
        db.agregar(p);

        Persona guardada = db.getPersonas().stream()
                .filter(x -> x.getNombre().equals("TEST_Carlos Ruiz"))
                .findFirst().orElseThrow();

        db.agregarTelefonoA(guardada.getId(), "686-123-4567");

        List<Telefono> telefonos = db.getTelefonosDe(guardada);
        assertEquals(1, telefonos.size());
        assertEquals("686-123-4567", telefonos.get(0).getTelefono());
    }

    @Test
    @DisplayName("eliminarTelefono quita solo el teléfono indicado, no todos")
    void eliminarTelefono_quitaSoloElIndicado(){
        Persona p = new Persona("TEST_Diana Marín", "Blvd. Anáhuac 88");
        db.agregar(p);
        Persona guardada = db.getPersonas().stream()
                .filter(x -> x.getNombre().equals("TEST_Diana Marín"))
                .findFirst().orElseThrow();

        db.agregarTelefonoA(guardada.getId(), "686-111-1111");
        db.agregarTelefonoA(guardada.getId(), "686-222-2222");

        List<Telefono> antes = db.getTelefonosDe(guardada);
        Telefono aEliminar = antes.stream()
                .filter(t -> t.getTelefono().equals("686-111-1111"))
                .findFirst().orElseThrow();

        db.eliminarTelefono(aEliminar, guardada);

        List<Telefono> despues = db.getTelefonosDe(guardada);
        assertEquals(1, despues.size());
        assertEquals("686-222-2222", despues.get(0).getTelefono());
    }

    @Test
    @DisplayName("actualizarPersona modifica nombre y dirección en la BD")
    void actualizarPersona_modificaDatosCorrectamente(){
        Persona p = new Persona("TEST_Original", "Dirección vieja");
        db.agregar(p);
        Persona guardada = db.getPersonas().stream()
                .filter(x -> x.getNombre().equals("TEST_Original"))
                .findFirst().orElseThrow();

        Persona actualizada = new Persona(guardada.getId(), "TEST_Modificado", "Dirección nueva");
        db.actualizarPersona(actualizada);

        Persona releída = db.getPersonas().stream()
                .filter(x -> x.getId() == guardada.getId())
                .findFirst().orElseThrow();

        assertEquals("TEST_Modificado", releída.getNombre());
        assertEquals("Dirección nueva", releída.getDireccion());
    }

    @Test
    @DisplayName("eliminar borra la persona Y sus teléfonos (ON DELETE CASCADE)")
    void eliminar_borraPersonaYTelefonosPorCascade(){
        Persona p = new Persona("TEST_Cascade", "Dirección de prueba");
        db.agregar(p);
        Persona guardada = db.getPersonas().stream()
                .filter(x -> x.getNombre().equals("TEST_Cascade"))
                .findFirst().orElseThrow();

        db.agregarTelefonoA(guardada.getId(), "686-999-9999");

        db.eliminar(guardada);

        boolean sigueExistiendo = db.getPersonas().stream()
                .anyMatch(x -> x.getId() == guardada.getId());
        assertFalse(sigueExistiendo, "La persona no debería existir tras eliminar");

        List<Telefono> telefonosHuerfanos = db.getTelefonosDe(guardada);
        assertTrue(telefonosHuerfanos.isEmpty(), "El cascade debió borrar también sus teléfonos");
    }
}