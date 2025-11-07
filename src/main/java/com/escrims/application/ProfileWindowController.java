package com.escrims.application;

import com.escrims.domainCore.Rol;
import com.escrims.domainCore.Usuario;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.stream.Collectors;

/**
 * Controlador para la ventana pop-up de "Editar Perfil".
 */
public class ProfileWindowController {

    // --- Componentes GUI ---
    @FXML private TextField txtUsername;
    @FXML private TextField txtRegion;
    @FXML private TextField txtDisponibilidad;
    @FXML private TextField txtMmr;
    @FXML private TextField txtNuevoRol;
    @FXML private ListView<String> rolesListView;
    @FXML private Label statusLabel;

    // --- Backend ---
    private AuthFacade authFacade;
    private Usuario usuarioActual;
    private ObservableList<String> rolesObservables = FXCollections.observableArrayList();

    /**
     * Inyecta la fachada y el usuario que queremos editar.
     */
    public void initData(AuthFacade authFacade, Usuario usuarioLogueado) {
        this.authFacade = authFacade;
        this.usuarioActual = usuarioLogueado;

        rolesListView.setItems(rolesObservables);

        // Cargar los datos actuales del usuario en el formulario
        cargarDatosUsuario();
    }

    /**
     * Rellena el formulario con los datos del usuario.
     */
    private void cargarDatosUsuario() {
        if (usuarioActual == null) return;

        txtUsername.setText(usuarioActual.getUsername());
        txtRegion.setText(usuarioActual.getRegion());
        txtMmr.setText(String.valueOf(usuarioActual.getMmr()));
        txtDisponibilidad.setText(usuarioActual.getDisponibilidad());

        rolesObservables.clear();
        if (usuarioActual.getRolesPreferidos() != null) {
            rolesObservables.addAll(
                    usuarioActual.getRolesPreferidos().stream()
                            .map(Rol::getNombre)
                            .collect(Collectors.toList())
            );
        }
    }

    /**
     * Se llama al hacer clic en "Agregar Rol".
     */
    @FXML
    private void onAgregarRolClicked() {
        String nuevoRol = txtNuevoRol.getText();
        if (nuevoRol != null && !nuevoRol.trim().isEmpty()) {
            if (!rolesObservables.contains(nuevoRol)) {
                rolesObservables.add(nuevoRol);
                txtNuevoRol.clear();
            }
        }
    }

    /**
     * Se llama al hacer clic en "Guardar Cambios".
     */
    @FXML
    private void onGuardarClicked() {
        try {
            // 1. Crear el DTO con los datos del formulario
            DatosPerfil data = new DatosPerfil();
            data.setUsername(txtUsername.getText());
            data.setRegion(txtRegion.getText());
            data.setDisponibilidad(txtDisponibilidad.getText());
            data.setMmr(Integer.parseInt(txtMmr.getText()));
            data.setRoles(
                    rolesObservables.stream()
                            .map(Rol::new) // Convierte los Strings de nuevo a objetos Rol
                            .collect(Collectors.toList())
            );

            // 2. ¡Llamar a la Fachada de Auth!
            // La fachada actualiza el usuario y nos devuelve la version nueva
            this.usuarioActual = authFacade.actualizarPerfil(usuarioActual.getId(), data);

            statusLabel.setText("¡Perfil actualizado con exito!");

        } catch (NumberFormatException e) {
            statusLabel.setText("Error: El MMR debe ser un numero.");
        } catch (Exception e) {
            statusLabel.setText("Error al guardar: " + e.getMessage());
            e.printStackTrace();
        }
    }
}