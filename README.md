# CRUD Personas — JavaFX + MariaDB

Aplicación de escritorio en JavaFX para gestionar altas, bajas, modificaciones
y consultas de una tabla `Personas`, donde cada persona puede tener varios
teléfonos asociados.

Tarea Analisis y diseño de sistemas — David García

## Tecnologías

- Java 25
- JavaFX 21
- MariaDB
- Maven
- JUnit 5 (pruebas unitarias y de integración)

## Estructura del proyecto

- `Logica/` — Persona, Telefono, ManejadorDB (acceso a datos)
- `Vista/` — PantallaPrincipal, PanelIzquierdo, PanelDerecho
- `Controlador/` — Controlador (conecta vista con la lógica de datos)

## Base de datos

Requiere una base de datos MariaDB llamada `agenda` con las siguientes tablas:

\`\`\`sql
CREATE TABLE Personas (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL,
direccion VARCHAR(200)
);

CREATE TABLE Telefonos (
id INT AUTO_INCREMENT PRIMARY KEY,
personaId INT NOT NULL,
telefono VARCHAR(20) NOT NULL,
FOREIGN KEY (personaId) REFERENCES Personas(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);
\`\`\`

Actualiza las credenciales de conexión (usuario, contraseña, URL) en
`ManejadorDB.java` antes de ejecutar.

## Cómo ejecutar

\`\`\`bash
mvn clean javafx:run
\`\`\`

## Cómo correr las pruebas

\`\`\`bash
mvn test
\`\`\`

Nota: las pruebas de integración (`ManejadorDBTest`) requieren que MariaDB
esté corriendo y con las tablas ya creadas.

## Funcionalidad

- Alta, baja y modificación de personas
- Gestión de múltiples teléfonos por persona (agregar, editar, quitar)
- Interfaz de dos paneles: lista de personas (izquierda) y detalle/edición (derecha)