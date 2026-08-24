# CRUD Personas — JavaFX + MariaDB

Aplicación de escritorio en JavaFX para gestionar altas, bajas, modificaciones
y consultas de una tabla `Personas`, donde cada persona puede tener varios
teléfonos y varias direcciones asociadas, y varias personas pueden compartir
una misma dirección.

CRUD para AyDdS — David García

## Tecnologías

- Java 25
- JavaFX 21
- MariaDB
- Maven
- JUnit 5 (pruebas unitarias y de integración)

## Estructura del proyecto

- `Logica/` — Persona, Telefono, Direccion, ManejadorDB (acceso a datos)
- `Vista/` — PantallaPrincipal, PanelIzquierdo, PanelDerecho
- `Controlador/` — Controlador (conecta vista con la lógica de datos)

## Base de datos

Requiere una base de datos MariaDB llamada `agenda` con las siguientes tablas:

\`\`\`sql
CREATE TABLE Personas (
id INT AUTO_INCREMENT PRIMARY KEY,
nombre VARCHAR(100) NOT NULL
);

CREATE TABLE Telefonos (
id INT AUTO_INCREMENT PRIMARY KEY,
personaId INT NOT NULL,
telefono VARCHAR(20) NOT NULL,
FOREIGN KEY (personaId) REFERENCES Personas(id)
ON DELETE CASCADE
ON UPDATE CASCADE
);

CREATE TABLE Direcciones (
id INT AUTO_INCREMENT PRIMARY KEY,
calle VARCHAR(200) NOT NULL,
ciudad VARCHAR(100)
);

CREATE TABLE PersonaDireccion (
personaId INT NOT NULL,
direccionId INT NOT NULL,
PRIMARY KEY (personaId, direccionId),
FOREIGN KEY (personaId) REFERENCES Personas(id) ON DELETE CASCADE,
FOREIGN KEY (direccionId) REFERENCES Direcciones(id) ON DELETE CASCADE
);
\`\`\`

`PersonaDireccion` es una tabla puente que modela la relación muchos-a-muchos
entre personas y direcciones: cada fila asocia una persona con una dirección,
permitiendo que una persona tenga varias direcciones y que una dirección sea
compartida por varias personas sin duplicar el texto de la dirección.

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
- Gestión de múltiples direcciones por persona (agregar, quitar)
- Interfaz de dos paneles: lista de personas (izquierda) y detalle/edición (derecha)

## Limitación conocida

Actualmente, cada dirección capturada se guarda como una fila nueva en
`Direcciones`, incluso si el texto coincide con una dirección ya existente
asociada a otra persona. Falta implementar una búsqueda de direcciones
existentes antes de crear una nueva, para que dos personas puedan compartir
literalmente el mismo registro de dirección en vez de copias duplicadas con
el mismo texto.