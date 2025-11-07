package com.escrims.application;

import com.escrims.domainCore.Usuario;
import com.escrims.services.AuthService;

/**
 * Fachada para todo lo relacionado a Autenticacion.
 * ¡AHORA CON METODO PARA ACTUALIZAR PERFIL!
 */
public class AuthFacade {

    private final AuthService authService;

    public AuthFacade(AuthService authService) {
        this.authService = authService;
    }

    /**
     * Delega el registro al servicio.
     */
    public Usuario registrarUsuario(DatosRegistro data) {
        System.out.println("FACADE (Auth): Registrando usuario...");
        return authService.registrar(data.getUsername(), data.getEmail(), data.getPassword());
    }

    /**
     * Delega el login al servicio.
     */
    public Token login(DatosLogin data) {
        System.out.println("FACADE (Auth): Logueando usuario...");
        Usuario usuario = authService.login(data.getEmail(), data.getPassword());

        // Creamos un token falso (simulacion)
        return new Token("token_jwt_falso_para_" + usuario.getUsername(), 3600);
    }

    public Usuario verificarEmailManualmente(String token) {
        System.out.println("FACADE (Auth): Verificando token manualmente...");
        // Llama al metodo que creamos en AuthService
        return authService.verificarEmail(token);
    }
    /**
     * Delega la actualizacion de perfil al servicio.
     * (Requisito: Perfil Editable)
     */
    public Usuario actualizarPerfil(String usuarioId, DatosPerfil data) {
        System.out.println("FACADE (Auth): Solicitud de actualizar perfil para " + usuarioId);
        // Llama al metodo que creamos en AuthService
        return authService.actualizarPerfil(usuarioId, data);
    }

}