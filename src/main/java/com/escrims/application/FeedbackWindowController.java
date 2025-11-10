package com.escrims.application;

import com.escrims.domainCore.Rol;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors; // Import para el .map

/**
 * Controlador para la ventana pop-up de "Feedback Post-Partida".
 */
public class FeedbackWindowController {

    // --- Componentes GUI ---
    @FXML private ListView<String> playerListView;
    @FXML private TextField txtKDA;
    @FXML private CheckBox chkMVP;
    @FXML private TextField txtRating;
    @FXML private TextField txtComentario;
    @FXML private Label statusLabel;
    @FXML private Button btnGuardarEstadisticas;
    @FXML private Button btnGuardarComentario;

    // --- Backend ---
    private ScrimFacade scrimFacade;
    private Usuario usuarioLogueado;
    private Scrim scrimFinalizado;

    // Lista simulada de usuarios para el demo
    private List<Usuario> jugadoresSimulados;

    /**
     * Inyecta la fachada y los datos de la partida.
     */
    public void initData(ScrimFacade facade, Usuario usuarioLogueado, Scrim scrimFinalizado) {
        this.scrimFacade = facade;
        this.usuarioLogueado = usuarioLogueado;
        this.scrimFinalizado = scrimFinalizado;

        // Simulamos la lista de jugadores de la partida
        this.jugadoresSimulados = List.of(
                usuarioLogueado,
                crearUsuarioSimulado("Jugador_2"),
                crearUsuarioSimulado("Jugador_3"),
                crearUsuarioSimulado("Jugador_4"),
                crearUsuarioSimulado("Jugador_5")
        );

        // Llenamos la lista de la GUI
        ObservableList<String> nombres = FXCollections.observableArrayList();
        nombres.addAll(
                jugadoresSimulados.stream()
                        .map(Usuario::getUsername) // Convierte List<Usuario> a List<String>
                        .collect(Collectors.toList())
        );
        playerListView.setItems(nombres);
    }

    // Helper para crear usuarios simulados
    private Usuario crearUsuarioSimulado(String nombre) {
        Usuario u = new Usuario();
        u.setUsername(nombre);
        u.setId(nombre + "_id");
        return u;
    }

    /**
     * Se llama al hacer clic en "Guardar Estadísticas".
     */
    @FXML
    private void onGuardarEstadisticasClicked() {
        String jugadorSeleccionadoNombre = playerListView.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionadoNombre == null) {
            statusLabel.setText("Error: Debes seleccionar un jugador.");
            return;
        }

        try {
            // 1. Crear el DTO
            DatosEstadisticas data = new DatosEstadisticas();
            data.setUsuarioId(jugadorSeleccionadoNombre); // (Simulado, pasamos el nombre)
            data.setKda(txtKDA.getText());
            data.setMvp(chkMVP.isSelected());

            // 2. ¡Llamar a la Fachada! (Esto aun no hace nada, lo rellenamos despues)
            // scrimFacade.cargarEstadisticas(scrimFinalizado.getId(), data);

            statusLabel.setText("Estadisticas de " + jugadorSeleccionadoNombre + " guardadas (simulado).");
            txtKDA.clear();
            chkMVP.setSelected(false);

        } catch (Exception e) {
            statusLabel.setText("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Se llama al hacer clic en "Guardar Comentario".
     */
    @FXML
    private void onGuardarComentarioClicked() {
        String jugadorSeleccionadoNombre = playerListView.getSelectionModel().getSelectedItem();
        if (jugadorSeleccionadoNombre == null) {
            statusLabel.setText("Error: Debes seleccionar un jugador.");
            return;
        }

        try {
            // 1. Crear el DTO (DatosComentario - aun no existe)
            String textoComentario = txtComentario.getText();
            int rating = Integer.parseInt(txtRating.getText());

            // 2. ¡Llamar a la Fachada! (Esto aun no hace nada, lo rellenamos despues)
            // scrimFacade.publicarComentario(scrimFinalizado.getId(), usuarioLogueado, jugadorSeleccionadoNombre, textoComentario, rating);

            statusLabel.setText("Comentario sobre " + jugadorSeleccionadoNombre + " guardado (simulado).");
            txtComentario.clear();
            txtRating.clear();

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: El Rating debe ser un numero (1-5).");
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