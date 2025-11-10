package com.escrims;

// Imports de JavaFX
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
<<<<<<< HEAD
import javafx.stage.StageStyle;
=======
import javafx.stage.StageStyle; // Para el login
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

// Imports de nuestro Backend (TODOS los paquetes)
import com.escrims.application.*;
import com.escrims.domainCore.*;
<<<<<<< HEAD
import com.escrims.infrastructure.InMemoryComentarioRepository; // ¡NUEVO!
=======
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
import com.escrims.infrastructure.InMemoryScrimRepository;
import com.escrims.infrastructure.InMemoryUsuarioRepository;
import com.escrims.moderation.*;
import com.escrims.notificationSubsystem.*;
<<<<<<< HEAD
import com.escrims.notificationSubsystem.events.*;
import com.escrims.services.*;
import com.escrims.notificationSubsystem.AlertSubscriber;
import com.escrims.notificationSubsystem.SuplenteSubscriber;
=======
import com.escrims.notificationSubsystem.events.*; // Importa todos los eventos
import com.escrims.services.*;
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

import java.io.IOException;
import java.net.URL;

/**
 * Clase principal de la Aplicacion (El "Arrancador").
<<<<<<< HEAD
 */
public class Main extends Application {

    // Fachadas
=======
 * Su trabajo es:
 * 1. Ensamblar todo el backend (Inyeccion de Dependencias).
 * 2. Lanzar la interfaz grafica (JavaFX).
 * 3. Manejar el flujo de Login -> Ventana Principal.
 */
public class Main extends Application {

    // Guardamos las fachadas para pasarselas a los controladores
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    private AuthFacade authFacade;
    private ScrimFacade scrimFacade;
    private ModerationFacade moderationFacade;

<<<<<<< HEAD
    private Usuario usuarioLogueado;

    /**
     * Metodo 1: Ensamblaje del Backend
=======
    // Guardamos el usuario que se logueo
    private Usuario usuarioLogueado;

    /**
     * Metodo 1: Ensamblaje del Backend (Inyeccion de Dependencias).
     * Esto se ejecuta ANTES de que se abra la ventana.
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    @Override
    public void init() throws Exception {
        System.out.println("--- [ENSAMBLANDO BACKEND] ---");

        // --- 1. NOTIFICACIONES ---
        INotifierFactory notifierFactory = new ProdNotifierFactory();
<<<<<<< HEAD
        ISubscriber notificationSubscriber = new NotificationSubscriber(notifierFactory);
        ISubscriber alertSubscriber = new AlertSubscriber(notifierFactory);
        ISubscriber suplenteSubscriber = new SuplenteSubscriber(notifierFactory);
        DomainEventBus.getInstance().subscribe(notificationSubscriber);
        DomainEventBus.getInstance().subscribe(alertSubscriber);
        DomainEventBus.getInstance().subscribe(suplenteSubscriber);

        // --- 2. MODERACION ---
        IModerationHandler moderationChain =
                new AutoStrikeHandler().setNext(new HumanModeratorHandler());

        // ============================================================
        // 3. INFRAESTRUCTURA (DBs Falsas) - ¡ACTUALIZADO!
        // ============================================================
        IScrimRepository scrimRepo = new InMemoryScrimRepository();
        IUsuarioRepository usuarioRepo = new InMemoryUsuarioRepository();
        IComentarioRepository comentarioRepo = new InMemoryComentarioRepository(); // ¡NUEVO!

        // --- 4. SERVICIOS ---
        // (Inyectamos la DB de usuarios al AuthService)
=======

        // Creamos el PRIMER oido (para notificar a jugadores)
        ISubscriber notificationSubscriber = new NotificationSubscriber(notifierFactory);

        // Creamos el SEGUNDO oido (para las alertas de busqueda)
        ISubscriber alertSubscriber = new AlertSubscriber(notifierFactory /*, busquedaRepo */);

        // Suscribimos AMBOS oidos al Bus
        DomainEventBus.getInstance().subscribe(notificationSubscriber);
        DomainEventBus.getInstance().subscribe(alertSubscriber);

        // --- 2. MODERACION ---
        // Creamos la Cadena de Responsabilidad
        IModerationHandler moderationChain =
                new AutoStrikeHandler()
                        .setNext(new HumanModeratorHandler()); // Encadenamos: Auto -> Humano

        // --- 3. INFRAESTRUCTURA (DBs Falsas) ---
        IScrimRepository scrimRepo = new InMemoryScrimRepository();
        IUsuarioRepository usuarioRepo = new InMemoryUsuarioRepository();

        // --- 4. SERVICIOS ---
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        AuthService authService = new AuthService(usuarioRepo);
        ScrimService scrimService = new ScrimService(scrimRepo);
        IMatchmakingStrategy mmrStrategy = new ByMMRStrategy();
        MatchmakingService matchmakingService = new MatchmakingService(mmrStrategy);

<<<<<<< HEAD
        // ============================================================
        // 5. FACHADAS (Los "Gerentes") - ¡ACTUALIZADO!
        // (Inyectamos los repositorios nuevos)
        // ============================================================
        this.authFacade = new AuthFacade(authService);
        this.moderationFacade = new ModerationFacade(moderationChain, scrimRepo, usuarioRepo);
        this.scrimFacade = new ScrimFacade(scrimService, matchmakingService, scrimRepo, usuarioRepo, comentarioRepo);
=======
        // --- 5. FACHADAS (Los "Gerentes") ---
        // Creamos las 3 fachadas y les inyectamos sus dependencias
        this.authFacade = new AuthFacade(authService);
        this.moderationFacade = new ModerationFacade(moderationChain, scrimRepo /*, usuarioRepo */);
        this.scrimFacade = new ScrimFacade(scrimService, matchmakingService, scrimRepo /*, usuarioRepo */);
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

        System.out.println("--- [ENSAMBLAJE COMPLETO] ---");
    }

    /**
<<<<<<< HEAD
     * Metodo 2: Lanzamiento de la Aplicacion
=======
     * Metodo 2: Lanzamiento de la Aplicacion.
     * Esto se ejecuta DESPUES de init().
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    @Override
    public void start(Stage primaryStage) throws IOException {

<<<<<<< HEAD
        // 1. Mostramos el Login
        if (mostrarLoginWindow() == false) {
=======
        // 1. Primero, mostramos el Login
        if (mostrarLoginWindow() == false) {
            // Si el login falla o se cierra, termina la app
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
            System.out.println("Login fallido o cancelado. Saliendo.");
            return;
        }

<<<<<<< HEAD
        // 2. Si el login fue exitoso, mostramos la ventana principal
        URL fxmlUrl = getClass().getResource("/com/escrims/MainWindow.fxml");
        if (fxmlUrl == null) { /* ... (error) ... */ return; }
=======
        // 2. Si el login fue exitoso (usuarioLogueado != null),
        //    recien ahi mostramos la ventana principal.

        URL fxmlUrl = getClass().getResource("/com/escrims/MainWindow.fxml");
        if (fxmlUrl == null) {
            System.err.println("Error fatal: No se pudo encontrar MainWindow.fxml en resources/com/escrims/");
            return;
        }
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

<<<<<<< HEAD
        MainWindowController controller = loader.getController();

        // 3. ¡INYECCION DE DEPENDENCIAS A LA GUI!
=======
        // Obtenemos el "electricista" de la ventana principal
        MainWindowController controller = loader.getController();

        // 3. ¡INYECCION DE DEPENDENCIAS A LA GUI!
        // Le pasamos las TRES fachadas Y el usuario que se logueo
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        controller.init(scrimFacade, authFacade, moderationFacade, usuarioLogueado);

        // 4. Configuramos y mostramos la ventana principal
        primaryStage.setTitle("eScrims - Conectado como: " + usuarioLogueado.getUsername());
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.show();
    }

    /**
     * Metodo helper que abre el Pop-up de Login y ESPERA.
<<<<<<< HEAD
=======
     * @return true si el login fue exitoso, false si no.
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    private boolean mostrarLoginWindow() throws IOException {

        URL fxmlUrl = getClass().getResource("/com/escrims/LoginWindow.fxml");
<<<<<<< HEAD
        if (fxmlUrl == null) { /* ... (error) ... */ return false; }
=======
        if (fxmlUrl == null) {
            System.err.println("Error fatal: No se pudo encontrar LoginWindow.fxml en resources/com/escrims/");
            return false;
        }
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

        FXMLLoader loader = new FXMLLoader(fxmlUrl);
        Parent root = loader.load();

<<<<<<< HEAD
        LoginWindowController loginController = loader.getController();
        loginController.initData(this.authFacade);

        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.initStyle(StageStyle.UNDECORATED);
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));

        loginStage.showAndWait();

=======
        // Obtenemos el "electricista" del pop-up
        LoginWindowController loginController = loader.getController();

        // Le inyectamos la fachada de Auth al pop-up
        loginController.initData(this.authFacade);

        // Creamos la ventana (Stage) de login
        Stage loginStage = new Stage();
        loginStage.initModality(Modality.APPLICATION_MODAL); // Bloquea la app
        loginStage.initStyle(StageStyle.UNDECORATED); // Sin bordes
        loginStage.setTitle("Login");
        loginStage.setScene(new Scene(root));

        // Esperamos a que la ventana se cierre
        loginStage.showAndWait();

        // Cuando se cierra, le preguntamos al controller si el login fue OK
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        this.usuarioLogueado = loginController.getUsuarioLogueado();
        return this.usuarioLogueado != null;
    }

    /**
     * Metodo 3: El punto de entrada de Java.
<<<<<<< HEAD
=======
     * Solo se usa para lanzar la app JavaFX.
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    public static void main(String[] args) {
        launch(args);
    }
}