# 🎮 TPO Final | eScrims: Plataforma de Matchmaking (ADOO)

Plataforma diseñada para la organización de scrims y partidas de práctica para distintos eSports (ej.: Valorant, LoL). [cite_start]El proyecto se enfocó en la implementación de patrones de diseño [cite: 72] para garantizar la máxima mantenibilidad, escalabilidad y bajo acoplamiento.

[cite_start]El proyecto cumple con la arquitectura de 4 capas requerida[cite: 71]: `Application` (Fachadas), `Services` (Lógica), `DomainCore` (Entidades), e `Infrastructure` (Persistencia/Adapters).

---

## ✨ Patrones de Diseño Implementados (12)

[cite_start]El proyecto excede el mínimo de cuatro patrones [cite: 72] [cite_start]e implementa una arquitectura basada en **12 patrones** que resuelven los requisitos funcionales y no funcionales (NFR) de trazabilidad y desacoplamiento[cite: 79].

| Tipo | Patrones | Uso en eScrims |
| :--- | :--- | :--- |
| **Comportamiento** | **State, Observer, Strategy, Command, Chain of Responsibility** | [cite_start]Gestión del ciclo de vida del Scrim, implementación de algoritmos intercambiables (Strategy) y manejo de eventos asíncronos (Observer) [cite: 82-84]. |
| **Estructural** | **Facade, Adapter, Decorator** | [cite_start]**Adapter** para `SendGrid` e `iCal`[cite: 88]. [cite_start]**Decorator** para agregar trazabilidad (logs) a las notificaciones[cite: 79]. |
| **Creacional** | **Builder, Abstract Factory, Singleton** | **Singleton** (`DomainEventBus`) para la centralización de eventos. [cite_start]**Abstract Factory** para la creación de `Notifiers` por canal/entorno[cite: 85]. |

---

## 🛠️ Guía de Ejecución

El proyecto usa Maven para la gestión de dependencias y JavaFX para la GUI.

### Requisitos

* Java JDK 21+ (Configurado en el `pom.xml`)
* Maven

### Pasos para Arrancar

1.  **Clonar el Repositorio:**
    ```bash
    git clone [URL_DE_TU_REPOSITORIO]
    cd TPO-eScrims-Patrones-Diseno
    ```

2.  **Configuración Inicial (Obligatoria):**
    * Busca el archivo `SendGridAdapter.java` en `src/main/java/com/escrims/notificationSubsystem/adapters/`.
    * Reemplaza el valor `SENDGRID_API_KEY` con tu clave API Key real de SendGrid para que la funcionalidad de email (Patrón Adapter) funcione.

3.  **Ejecutar la Aplicación:**
    **NO** usar el botón de "Play" de IntelliJ. Usar el plugin de Maven para cargar los módulos de JavaFX:
    ```bash
    mvn clean javafx:run
    ```

### Guía de Demo

* **Flujo de Seguridad:** Al iniciar, se abrirá la ventana de Login. [cite_start]Primero se debe usar el botón **"Registrar"** y luego **"Login"** (el sistema chequea el estado de `Email Verificado` [cite: 24]).
* **Flujo Crítico:** Probar la cadena completa (requiere un mail válido en el campo "Email para Pruebas"):
    * 1. Crear Scrim (Patrón Builder).
    * 2. Postularse (Dispara el `LobbyCompletoEvent`).
    * 3. Confirmar Asistencia (Dispara el `ScrimConfirmadoEvent`).
    * **Resultado Esperado:** Un mail con un archivo **`.ics` adjunto** (Patrón Adapter).

---

## 💻 Comandos Finales de Git

Si ya creaste el `README.md` en la carpeta raíz de tu proyecto, ejecuta estos comandos en tu terminal para subirlo:

```bash
# 1. Asegurarse de rastrear todos los archivos nuevos (incluido el README)
git add .

# 2. Guardar los cambios en el historial local
git commit -m "docs: Agrega README y finaliza la implementacion del modulo de Perfil Editable."

# 3. Subir la version final a GitHub
git push origin main 
# (O 'git push origin master' si tu rama se llama master)
