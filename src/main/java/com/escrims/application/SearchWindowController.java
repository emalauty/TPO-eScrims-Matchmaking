package com.escrims.application;

import com.escrims.domainCore.Scrim;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable; // ¡IMPORT NUEVO!
import javafx.scene.control.ComboBox; // ¡IMPORT NUEVO!
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL; // ¡IMPORT NUEVO!
import java.util.List;
import java.util.ResourceBundle; // ¡IMPORT NUEVO!

/**
 * Controlador para la ventana pop-up de "Búsqueda de Scrims".
 * ¡AHORA CON FILTRO DE FORMATO!
 */
public class SearchWindowController implements Initializable { // ¡IMPLEMENTA INICIALIZABLE!

    // --- Componentes GUI ---
    @FXML private TextField txtJuego;
    @FXML private TextField txtRegion;
    @FXML private TextField txtRangoMin;
    @FXML private TextField txtRangoMax;
    @FXML private ListView<String> resultadosListView;
    @FXML private Label statusLabel;

    // ============================================================
    // ¡¡NUEVO @FXML!!
    // ============================================================
    @FXML private ComboBox<String> cmbFormato;
    // ============================================================

    // --- Backend ---
    private ScrimFacade scrimFacade;

    /**
     * Inyecta la fachada.
     */
    public void initData(ScrimFacade facade) {
        this.scrimFacade = facade;
    }

    /**
     * Se llama cuando el FXML se carga (NUEVO METODO)
     * Lo usamos para rellenar el ComboBox.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Llenamos el ComboBox de Formatos
        cmbFormato.setItems(FXCollections.observableArrayList(
                "Todos", "1v1", "2v2", "5v5"
        ));
        cmbFormato.setValue("Todos"); // Valor por defecto
    }

    /**
     * Se llama al hacer clic en "Buscar Partidas".
     * ¡AHORA USA EL NUEVO FILTRO!
     */
    @FXML
    private void onBuscarClicked() {
        if (scrimFacade == null) {
            statusLabel.setText("Error: Fachada no conectada.");
            return;
        }

        try {
            // 1. Crear el DTO de Filtros
            FiltrosBusqueda filtros = new FiltrosBusqueda();
            filtros.setJuego(txtJuego.getText());
            filtros.setRegion(txtRegion.getText());
            filtros.setRangoMin(Integer.parseInt(txtRangoMin.getText()));
            filtros.setRangoMax(Integer.parseInt(txtRangoMax.getText()));

            // ============================================================
            // ¡¡AGREGAMOS EL FILTRO NUEVO!!
            // ============================================================
            if (!cmbFormato.getValue().equals("Todos")) {
                filtros.setFormato(cmbFormato.getValue());
            }
            // ============================================================

            // 2. ¡Llamar a la Fachada!
            List<Scrim> resultados = scrimFacade.buscarScrims(filtros);

            // 3. Llenar la lista (simulado)
            ObservableList<String> items = FXCollections.observableArrayList();
            if (resultados == null || resultados.isEmpty()) {
                // Simulacion si la busqueda real no devuelve nada
                items.add("SIMULACION: Scrim de Valorant (" + filtros.getFormato() + ") encontrado.");
            } else {
                // (logica real)
            }
            resultadosListView.setItems(items);
            statusLabel.setText(items.size() + " resultados encontrados.");

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Los rangos deben ser numericos.");
        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void onCerrarClicked() {
        Stage stage = (Stage) statusLabel.getScene().getWindow();
        stage.close();
    }
}