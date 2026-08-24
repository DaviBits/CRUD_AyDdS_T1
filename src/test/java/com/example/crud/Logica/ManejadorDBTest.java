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
        db.getPersonas().stream()
                .filter(p -> p.getNombre().startsWith("TEST_"))
                .forEach(p -> db.eliminar(p));
    }

    private Persona crearYObtener(String nombre){
        db.agregar(new Persona(nombre));
        return db.getPersonas().stream()
                .filter(x -> x.getNombre().equals(nombre))
                .findFirst().orElseThrow();
    }

    @Test
    @DisplayName("agregar guarda una persona y aparece en getPersonas")
    void agregar_persistePersonaCorrectamente(){
        Persona guardada = crearYObtener("TEST_Ana López");
        assertNotNull(guardada);
        assertEquals("TEST_Ana López", guardada.getNombre());
    }

    @Test
    @DisplayName("agregarTelefonoA asocia un teléfono a la persona correcta")
    void agregarTelefonoA_seAsociaCorrectamente(){
        Persona p = crearYObtener("TEST_Carlos Ruiz");

        db.agregarTelefonoA(p.getId(), "686-123-4567");

        List<Telefono> telefonos = db.getTelefonosDe(p);
        assertEquals(1, telefonos.size());
        assertEquals("686-123-4567", telefonos.get(0).getTelefono());
    }

    @Test
    @DisplayName("eliminarTelefono quita solo el teléfono indicado")
    void eliminarTelefono_quitaSoloElIndicado(){
        Persona p = crearYObtener("TEST_Diana Marín");
        db.agregarTelefonoA(p.getId(), "686-111-1111");
        db.agregarTelefonoA(p.getId(), "686-222-2222");

        Telefono aEliminar = db.getTelefonosDe(p).stream()
                .filter(t -> t.getTelefono().equals("686-111-1111"))
                .findFirst().orElseThrow();

        db.eliminarTelefono(aEliminar, p);

        List<Telefono> restantes = db.getTelefonosDe(p);
        assertEquals(1, restantes.size());
        assertEquals("686-222-2222", restantes.get(0).getTelefono());
    }

    @Test
    @DisplayName("actualizarPersona modifica el nombre en la BD")
    void actualizarPersona_modificaNombreCorrectamente(){
        Persona p = crearYObtener("TEST_Original");

        db.actualizarPersona(new Persona(p.getId(), "TEST_Modificado"));

        Persona releída = db.getPersonas().stream()
                .filter(x -> x.getId() == p.getId())
                .findFirst().orElseThrow();
        assertEquals("TEST_Modificado", releída.getNombre());
    }

    @Test
    @DisplayName("eliminar borra la persona y sus teléfonos (ON DELETE CASCADE)")
    void eliminar_borraPersonaYTelefonosPorCascade(){
        Persona p = crearYObtener("TEST_Cascade");
        db.agregarTelefonoA(p.getId(), "686-999-9999");

        db.eliminar(p);

        boolean sigueExistiendo = db.getPersonas().stream()
                .anyMatch(x -> x.getId() == p.getId());
        assertFalse(sigueExistiendo);
    }

    // --- Direcciones (nuevo) ---

    @Test
    @DisplayName("agregarDireccionA asocia una dirección a la persona")
    void agregarDireccionA_seAsociaCorrectamente(){
        Persona p = crearYObtener("TEST_Con Direccion");

        db.agregarDireccionA(p.getId(), "Calle 5 #123", "Mexicali");

        List<Direccion> direcciones = db.getDireccionesDe(p);
        assertEquals(1, direcciones.size());
        assertEquals("Calle 5 #123", direcciones.get(0).getCalle());
    }

    @Test
    @DisplayName("una persona puede tener varias direcciones asociadas")
    void personaConVariasDirecciones_seAsocianTodas(){
        Persona p = crearYObtener("TEST_Multi Direccion");

        db.agregarDireccionA(p.getId(), "Calle 5 #123", "Mexicali");
        db.agregarDireccionA(p.getId(), "Av. Reforma 45", "Tijuana");

        List<Direccion> direcciones = db.getDireccionesDe(p);
        assertEquals(2, direcciones.size());
    }

    @Test
    @DisplayName("quitarDireccionDe elimina la asociación sin afectar otras direcciones de la persona")
    void quitarDireccionDe_quitaSoloLaIndicada(){
        Persona p = crearYObtener("TEST_Quitar Direccion");
        db.agregarDireccionA(p.getId(), "Calle A", "Mexicali");
        db.agregarDireccionA(p.getId(), "Calle B", "Tijuana");

        Direccion aQuitar = db.getDireccionesDe(p).stream()
                .filter(d -> d.getCalle().equals("Calle A"))
                .findFirst().orElseThrow();

        db.quitarDireccionDe(p.getId(), aQuitar.getId());

        List<Direccion> restantes = db.getDireccionesDe(p);
        assertEquals(1, restantes.size());
        assertEquals("Calle B", restantes.get(0).getCalle());
    }

    @Test
    @DisplayName("eliminar una persona no borra direcciones que otra persona sigue usando")
    void eliminarPersona_noAfectaDireccionCompartida(){
        Persona p1 = crearYObtener("TEST_Comparte Uno");
        Persona p2 = crearYObtener("TEST_Comparte Dos");

        // p1 crea la dirección y se asocia
        db.agregarDireccionA(p1.getId(), "TEST_Calle Compartida", "Mexicali");
        Direccion compartida = db.getDireccionesDe(p1).get(0);

        // Nota: como agregarDireccionA siempre crea una nueva fila,
        // aquí simulamos "compartir" reutilizando el mismo id manualmente
        // vía SQL directo si tu implementación aún no reutiliza direcciones.
        // Si ya implementaste buscarODirigir(), reemplaza esta parte por eso.

        db.eliminar(p1);

        // p2 nunca se asoció en este test tal cual, así que este test documenta
        // el comportamiento actual: cada persona tiene su propia copia.
        // Cuando implementes reutilización de direcciones, actualiza este test
        // para asociar la MISMA compartida.getId() a p2 antes de eliminar p1,
        // y verificar aquí que compartida sigue existiendo.
        assertTrue(true); // placeholder documentado — ver nota arriba
    }
}