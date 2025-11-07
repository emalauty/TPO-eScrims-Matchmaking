package com.escrims.services;

import com.escrims.domainCore.IUsuarioRepository;
import com.escrims.domainCore.Usuario;
import com.escrims.application.DatosPerfil;
import com.escrims.application.DatosRegistro;
import com.escrims.application.DatosLogin;
import com.escrims.notificationSubsystem.DomainEventBus; // ¡IMPORT NUEVO!
import com.escrims.notificationSubsystem.events.UsuarioRegistradoEvent; // ¡IMPORT NUEVO!
import java.util.UUID; // ¡IMPORT NUEVO!

/**
 * Servicio "cerebro" para la autenticacion.
 * ¡AHORA PUBLICA EVENTOS DE REGISTRO!
 */
public class AuthService {

    private final IUsuarioRepository usuarioRepo;
    private final DomainEventBus eventBus; // El Bus de Eventos

    public AuthService(IUsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.eventBus = DomainEventBus.getInstance(); // Obtenemos el Singleton
    }

    /**
     * Registra un usuario nuevo.
     */
    public Usuario registrar(String username, String email, String passwordPlana) {
        if (usuarioRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email ya esta en uso");
        }

        String passwordHasheada = "hash_de_" + passwordPlana; // Simulacion
        String verificationToken = UUID.randomUUID().toString(); // Token unico

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPasswordHash(passwordHasheada);
        nuevoUsuario.setRolSistema("USER");
        nuevoUsuario.setEstadoEmail("Pendiente"); // Estado inicial
        nuevoUsuario.setVerificationToken(verificationToken); // Guardamos el token

        // 1. Guardamos en la DB
        Usuario usuarioGuardado = usuarioRepo.save(nuevoUsuario);

        // 2. ¡PUBLICAMOS EL EVENTO!
        eventBus.publish(new UsuarioRegistradoEvent(usuarioGuardado));

        return usuarioGuardado;
    }

    /**
     * Loguea un usuario existente.
     */
    public Usuario login(String email, String passwordPlana) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

        // Chequeamos si ya verifico el email
        if (!"Verificado".equals(usuario.getEstadoEmail())) {
            throw new RuntimeException("Email no verificado. Revisa tu bandeja de entrada.");
        }

        boolean coincide = usuario.getPasswordHash().equals("hash_de_" + passwordPlana);
        if (!coincide) {
            throw new RuntimeException("Credenciales invalidas");
        }
        return usuario;
    }

    // ============================================================
    // ¡¡NUEVO METODO PARA EL LINK DEL EMAIL!!
    // ============================================================
    /**
     * Verifica un email usando un token.
     */
    public Usuario verificarEmail(String token) {
        System.out.println("AuthService: Buscando usuario con token: " + token);

        // 1. Buscamos al usuario por el token
        Usuario usuario = usuarioRepo.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Token de verificacion invalido o expirado"));

        // 2. Cambiamos el estado (Patron State... pero en el Usuario)
        usuario.verificarEmail();

        // 3. Guardamos los cambios
        System.out.println("AuthService: Email verificado para " + usuario.getUsername());
        return usuarioRepo.save(usuario);
    }

    /**
     * Actualiza el perfil.
     */
    public Usuario actualizarPerfil(String usuarioId, DatosPerfil data) {
        // ... (Este metodo queda igual que antes) ...
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setUsername(data.getUsername());
        usuario.setRegion(data.getRegion());
        usuario.setMmr(data.getMmr());
        usuario.setRolesPreferidos(data.getRoles());
        usuario.setDisponibilidad(data.getDisponibilidad());
        return usuarioRepo.save(usuario);
    }
}