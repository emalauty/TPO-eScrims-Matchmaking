package com.escrims.application;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Rol;
import com.escrims.domainCore.Usuario;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox; // Import para el panel de botones
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * El "Controlador" de la GUI.
 * Esta es la version 100% COMPLETA Y FINAL.
 * Maneja Login, State, Kill Feed, Reportes y Perfil.
 */
public class MainWindowController implements Initializable {

    // --- FACHADAS (Backend) ---
    private ScrimFacade scrimFacade;
    private AuthFacade authFacade;
    private ModerationFacade moderationFacade;
    private Usuario usuarioLogueado;

    // --- Componentes GUI ---
    @FXML private VBox leftPanelVBox; // El panel de botones
    @FXML private ListView<String> scrimListView;
    @FXML private Label statusBar;
    @FXML private TextField txtEmailDePrueba;
    @FXML private TextArea logPartidaArea; // El "Kill Feed"
    @FXML private Label lblNickname; // Para el nombre

    // Botones
    @FXML private Button btnCrearScrim;
    @FXML private Button btnPostularse;
    @FXML private Button btnConfirmar;
    @FXML private Button btnIniciar;
    @FXML private Button btnFinalizar;
    @FXML private Button btnReportar;
    @FXML private Button btnEditarPerfil; // El boton nuevo

    /**
     * Inyecta el backend en el frontend (llamado por Main.java).
     * Recibe las 3 Fachadas y el Usuario logueado.
     */
    public void init(ScrimFacade scrimFacade, AuthFacade authFacade,
                     ModerationFacade moderationFacade, Usuario usuarioLogueado) {

        this.scrimFacade = scrimFacade;
        this.authFacade = authFacade;
        this.moderationFacade = moderationFacade;
        this.usuarioLogueado = usuarioLogueado;

        // --- PERSONALIZACION DE LA GUI (Login) ---
        statusBar.setText("Usuario: " + usuarioLogueado.getUsername() + ". Listo.");
        lblNickname.setText(usuarioLogueado.getUsername());
        txtEmailDePrueba.setText(usuarioLogueado.getEmail());

        // ¡Habilitamos los botones!
        leftPanelVBox.setDisable(false);

        // Seteamos el "buzon" de prueba
        TestContext.getInstance().setEmailDePrueba(usuarioLogueado.getEmail());

        // Listeners
        txtEmailDePrueba.textProperty().addListener((obs, oldVal, newVal) -> {
            TestContext.getInstance().setEmailDePrueba(newVal);
        });
        scrimListView.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal != null) {
                statusBar.setText("Scrim seleccionado: " + newVal.split(" \\| ")[0]);
            }
        });
    }

    /**
     * Deshabilita los botones al inicio (esperando el login).
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        statusBar.setText("Esperando login...");
        leftPanelVBox.setDisable(true); // ¡BLOQUEADO!
    }

    // ============================================================
    // METODOS DE ACCION (CICLO DE VIDA)
    // ============================================================

    @FXML
    private void onCrearScrimClicked() {
        if (scrimFacade == null) { statusBar.setText("Error: Backend no conectado."); return; }

        statusBar.setText("Creando scrim de prueba...");

        try {
            DatosCreacion datos = new DatosCreacion();
            datos.setJuego("Valorant (desde GUI)");
            datos.setRangoMin(1000);
            datos.setRangoMax(3000);

            Scrim nuevoScrim = scrimFacade.crearScrim(datos);

            String scrimInfo = "ID: " + nuevoScrim.getId() + " | Juego: " + datos.getJuego() + " | Estado: Buscando";
            scrimListView.getItems().add(scrimInfo);
            statusBar.setText("¡Scrim " + nuevoScrim.getId() + " creado!");

        } catch (Exception e) {
            statusBar.setText("Error al crear scrim: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onPostularseClicked() {
        String scrimId = getSelectedScrimId();
        if (scrimId == null) return;

        statusBar.setText("Postulando al Scrim " + scrimId + "...");

        try {
            Rol rolFalso = new Rol("Duelista");
            scrimFacade.postularse(scrimId, this.usuarioLogueado, rolFalso);
            statusBar.setText("Postulacion exitosa para " + scrimId + ". Revisa tu mail.");
        } catch (Exception e) {
            statusBar.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onConfirmarClicked() {
        String scrimId = getSelectedScrimId();
        if (scrimId == null) return;

        statusBar.setText("Confirmando asistencia para " + scrimId + "...");

        try {
            scrimFacade.confirmar(scrimId, this.usuarioLogueado);
            statusBar.setText("Asistencia confirmada para " + scrimId + ". Revisa tu mail.");
        } catch (Exception e) {
            statusBar.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onIniciarClicked() {
        String scrimId = getSelectedScrimId();
        if (scrimId == null) return;

        statusBar.setText("Iniciando partida " + scrimId + "...");
        logPartidaArea.clear();

        try {
            scrimFacade.iniciarScrim(scrimId);
            statusBar.setText("Partida " + scrimId + " EN JUEGO. Simulando 5 segundos...");

            // --- SIMULACION DE "KILL FEED" ---
            Task<Void> gameSimulationTask = new Task<Void>() {

                private void logToGui(String message) {
                    Platform.runLater(() -> logPartidaArea.appendText(message + "\n"));
                }

                @Override
                protected Void call() throws Exception {
                    Random rand = new Random();
                    List<String> jugadores = List.of(usuarioLogueado.getUsername(), "Jugador_AFK", "Smurf_Bronce", "El_Buena_Onda", "Carry_Frustrado");

                    logToGui("[00:00] ¡La partida ha comenzado!");
                    Thread.sleep(1000);
                    logToGui("[00:45] " + jugadores.get(0) + " (KDA: 1/0/0) elimino a " + jugadores.get(2));
                    Thread.sleep(500);
                    logToGui("[01:10] " + jugadores.get(4) + " (KDA: 1/0/0) elimino a " + jugadores.get(1));
                    Thread.sleep(1000);
                    logToGui("[02:00] ¡Bomba plantada por " + jugadores.get(3) + "!");
                    Thread.sleep(1500);
                    logToGui("[02:30] " + jugadores.get(0) + " (KDA: 1/1/0) fue eliminado por " + jugadores.get(3));
                    Thread.sleep(1000);
                    logToGui("[02:50] ¡RONDA GANADA POR ATACANTES!");
                    return null;
                }
            };

            gameSimulationTask.setOnSucceeded(e -> {
                statusBar.setText("Partida " + scrimId + " termino. Llamando a finalizar...");
                onFinalizarClicked();
            });

            new Thread(gameSimulationTask).start();

        } catch (Exception e) {
            statusBar.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onFinalizarClicked() {
        String scrimId = getSelectedScrimId();
        if (scrimId == null) return;

        statusBar.setText("Finalizando partida " + scrimId + "...");

        try {
            scrimFacade.finalizarScrim(scrimId);
            statusBar.setText("Partida " + scrimId + " ha finalizado. Revisa tu mail.");
        } catch (Exception e) {
            statusBar.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ============================================================
    // METODOS DE ACCION (MODERACION Y PERFIL)
    // ============================================================

    @FXML
    private void onReportarClicked() {
        String scrimId = getSelectedScrimId();
        if (scrimId == null) {
            statusBar.setText("Error: Selecciona un Scrim para reportar.");
            return;
        }
        if (moderationFacade == null) { /* ... (error) ... */ }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/escrims/ReportWindow.fxml"));
            Parent root = loader.load();
            ReportWindowController reportController = loader.getController();

            List<String> jugadoresSimulados = List.of(
                    usuarioLogueado.getUsername(), "Jugador_AFK", "Smurf_Bronce",
                    "El_Buena_Onda", "Carry_Frustrado"
            );

            reportController.initData(this.moderationFacade, scrimId, jugadoresSimulados, this.usuarioLogueado);

            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Reportar Jugador del Scrim " + scrimId);
            popupStage.setScene(new Scene(root));

            statusBar.setText("Mostrando ventana de reporte...");
            popupStage.showAndWait();
            statusBar.setText("Ventana de reporte cerrada. Revisa la consola.");

        } catch (IOException e) {
            statusBar.setText("Error fatal: No se pudo abrir 'ReportWindow.fxml'");
            e.printStackTrace();
        } catch (Exception e) {
            statusBar.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ¡NUEVO METODO PARA EL BOTON "EDITAR PERFIL"!
     */
    @FXML
    private void onEditarPerfilClicked() {
        if (authFacade == null || usuarioLogueado == null) {
            statusBar.setText("Error: No hay un usuario logueado para editar.");
            return;
        }

        try {
            // 1. Cargar el "plano" (FXML) de la ventana de perfil
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/escrims/ProfileWindow.fxml"));
            Parent root = loader.load();

            // 2. Obtener el "electricista" (Controller) de ESA ventana
            ProfileWindowController profileController = loader.getController();

            // 3. ¡INYECCION DE DEPENDENCIAS!
            // Le pasamos la fachada de Auth y el USUARIO ACTUAL
            profileController.initData(this.authFacade, this.usuarioLogueado);

            // 4. Crear y mostrar la nueva ventana (Stage)
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Editar Perfil - " + usuarioLogueado.getUsername());
            popupStage.setScene(new Scene(root));

            statusBar.setText("Mostrando ventana de perfil...");
            popupStage.showAndWait(); // Muestra la ventana y espera

            // 5. Actualizar la GUI principal si los datos cambiaron
            lblNickname.setText(this.usuarioLogueado.getUsername());
            statusBar.setText("Perfil actualizado.");

        } catch (IOException e) {
            statusBar.setText("Error fatal: No se pudo abrir 'ProfileWindow.fxml'");
            e.printStackTrace();
        } catch (Exception e) {
            statusBar.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // ============================================================
    // METODO HELPER
    // ============================================================

    private String getSelectedScrimId() {
        String scrimInfo = scrimListView.getSelectionModel().getSelectedItem();
        if (scrimInfo == null) {
            statusBar.setText("Error: Primero selecciona un Scrim de la lista.");
            return null;
        }
        return scrimInfo.split(" \\| ")[0].replace("ID: ", "");
    }
}