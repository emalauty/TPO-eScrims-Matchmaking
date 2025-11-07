package com.escrims.application;

import com.escrims.domainCore.Usuario;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Controlador para la ventana pop-up de Login/Registro.
 */
public class LoginWindowController {

    // --- Componentes GUI ---
    @FXML private TextField txtEmail;
    @FXML private PasswordField txtPassword;
    @FXML private TextField txtUsername;
    @FXML private Label statusLabel;
    @FXML private Button btnLogin;
    @FXML private Button btnRegistrar;
    @FXML private TextField txtToken; // ¡NUEVO!
    @FXML private Button btnVerificar; // ¡NUEVO!

    // --- Backend ---
    private AuthFacade authFacade;
    private Usuario usuarioLogueado = null;

    public void initData(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    public Usuario getUsuarioLogueado() {
        return usuarioLogueado;
    }

    /**
     * Se llama al hacer clic en "Login".
     */
    @FXML
    private void onLoginClicked() {
        if (authFacade == null) { statusLabel.setText("Error: Fachada no conectada."); return; }

        try {
            DatosLogin data = new DatosLogin();
            data.setEmail(txtEmail.getText());
            data.setPassword(txtPassword.getText());

            // El AuthService chequea si el email esta "Verificado" antes de dar el token
            Token token = authFacade.login(data);

            // Simular el usuario logueado
            this.usuarioLogueado = new Usuario();
            this.usuarioLogueado.setUsername("Usuario Logueado (Simulado)");
            this.usuarioLogueado.setEmail(data.getEmail());

            closeWindow();

        } catch (Exception e) {
            statusLabel.setText("Error al loguear: " + e.getMessage());
            // Si el error es "Email no verificado", el usuario debe usar el boton 'Verificar'
        }
    }

    /**
     * Se llama al hacer clic en "Registrar".
     * ¡AHORA MUESTRA EL TOKEN MANUALMENTE!
     */
    @FXML
    private void onRegistrarClicked() {
        if (authFacade == null) { statusLabel.setText("Error: Fachada no conectada."); return; }

        try {
            DatosRegistro data = new DatosRegistro();
            data.setUsername(txtUsername.getText());
            data.setEmail(txtEmail.getText());
            data.setPassword(txtPassword.getText());

            // 1. ¡Llamamos a la Fachada! (Esto dispara el mail con el token)
            Usuario nuevoUsuario = authFacade.registrarUsuario(data);

            // 2. OBTENEMOS EL TOKEN GENERADO (para el input manual)
            String token = nuevoUsuario.getVerificationToken();

            // 3. ¡MOSTRAMOS EL TOKEN EN UN POP-UP PARA EL USUARIO!
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("¡Registro Exitoso!");
            alert.setHeaderText("Verificación Requerida");
            alert.setContentText("Tu email fue enviado. \n\n" +
                    "\n\nPor favor, copia el token que te llego y pegalo en el campo 'Token de Verificación Manual' para poder iniciar sesión.");
            alert.showAndWait();

            statusLabel.setText("Registro exitoso. Pega el token y haz clic en 'VERIFICAR EMAIL'.");
            txtEmail.setText(nuevoUsuario.getEmail()); // Llenamos el campo de email para el login
            txtUsername.setText(nuevoUsuario.getUsername()); // Opcional, para que lo vea

        } catch (Exception e) {
            statusLabel.setText("Error al registrar: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * ¡¡NUEVO METODO!! Simula la verificación del link.
     */
    @FXML
    private void onVerificarClicked() {
        String token = txtToken.getText();
        if (token.isEmpty()) {
            statusLabel.setText("Error: Pega el token en el campo de arriba.");
            return;
        }

        try {
            // 1. Llamamos al servicio de Backend para validar y cambiar el estado del usuario
            authFacade.verificarEmailManualmente(token);

            statusLabel.setText("¡Verificación Exitosa! Ahora puedes iniciar sesión.");

        } catch (Exception e) {
            statusLabel.setText("Error de Verificación: " + e.getMessage());
        }
    }

    /**
     * Helper para cerrar la ventana.
     */
    private void closeWindow() {
        Stage stage = (Stage) btnLogin.getScene().getWindow();
        stage.close();
    }
}