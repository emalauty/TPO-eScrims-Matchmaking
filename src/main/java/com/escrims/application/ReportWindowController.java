package com.escrims.application;

import com.escrims.domainCore.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage; // Importante para cerrar la ventana

import java.util.List;

/**
 * Controlador para la ventana pop-up de Reporte.
 * ¡AHORA CORREGIDO!
 */
public class ReportWindowController {

    // --- Componentes GUI del FXML ---
    @FXML private ListView<String> playerListView;
    @FXML private TextField txtMotivo;
    @FXML private Button btnEnviarReporte;
    @FXML private Label statusLabel;

    // --- El "Motor" (inyectado) ---
    private ModerationFacade moderationFacade;
    private String scrimId;

    // ============================================================
    // ¡¡AQUI ESTA EL CAMBIO!!
    // ============================================================
    private Usuario reportador; // El usuario que esta logueado

    /**
     * Metodo de inicializacion.
     * ¡AHORA RECIBE 4 ARGUMENTOS!
     */
    public void initData(ModerationFacade facade, String scrimId,
                         List<String> jugadores, Usuario reportadorLogueado) {

        this.moderationFacade = facade;
        this.scrimId = scrimId;

        // Ya no creamos un reportador falso, usamos el real
        this.reportador = reportadorLogueado;

        // Llenamos la lista de jugadores
        playerListView.setItems(FXCollections.observableArrayList(jugadores));
    }
    // ============================================================

    /**
     * Se llama al hacer clic en "Enviar Reporte".
     */
    @FXML
    private void onEnviarReporteClicked() {
        // 1. Validar datos
        String jugadorReportadoNombre = playerListView.getSelectionModel().getSelectedItem();
        String motivo = txtMotivo.getText();

        if (jugadorReportadoNombre == null || jugadorReportadoNombre.isEmpty()) {
            statusLabel.setText("Error: Debes seleccionar un jugador.");
            return;
        }
        if (motivo == null || motivo.trim().isEmpty()) {
            statusLabel.setText("Error: Debes escribir un motivo.");
            return;
        }

        System.out.println("POP-UP: Reporte listo. Llamando a la fachada...");

        try {
            // 2. Preparar el DTO
            DatosReporte data = new DatosReporte();
            data.setScrimId(scrimId);

            // ¡Usamos el ID del usuario real!
            data.setReportadorId(this.reportador.getId());

            // (En un sistema real, buscariamos el ID del jugador por su nombre)
            data.setReportadoId("user-ID-de-" + jugadorReportadoNombre);
            data.setMotivo(motivo);

            // 3. ¡Llamar a la Fachada de Moderacion!
            moderationFacade.procesarReporte(data);

            // 4. Cerrar el pop-up
            Stage stage = (Stage) btnEnviarReporte.getScene().getWindow();
            stage.close();

        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}