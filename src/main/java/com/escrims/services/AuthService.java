package com.escrims.services;

import com.escrims.domainCore.IUsuarioRepository;
import com.escrims.domainCore.Usuario;
import com.escrims.application.DatosPerfil;
import com.escrims.application.DatosRegistro;
import com.escrims.application.DatosLogin;
<<<<<<< HEAD
import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.UsuarioRegistradoEvent;
import java.util.UUID;

/**
 * Servicio "cerebro" para la autenticacion.
 * ¡AHORA CON EL CHEQUEO DE VERIFICACION DESACTIVADO PARA LA DEMO!
=======
import com.escrims.notificationSubsystem.DomainEventBus; // ¡IMPORT NUEVO!
import com.escrims.notificationSubsystem.events.UsuarioRegistradoEvent; // ¡IMPORT NUEVO!
import java.util.UUID; // ¡IMPORT NUEVO!

/**
 * Servicio "cerebro" para la autenticacion.
 * ¡AHORA PUBLICA EVENTOS DE REGISTRO!
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
 */
public class AuthService {

    private final IUsuarioRepository usuarioRepo;
<<<<<<< HEAD
    private final DomainEventBus eventBus;

    public AuthService(IUsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.eventBus = DomainEventBus.getInstance();
=======
    private final DomainEventBus eventBus; // El Bus de Eventos

    public AuthService(IUsuarioRepository usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
        this.eventBus = DomainEventBus.getInstance(); // Obtenemos el Singleton
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }

    /**
     * Registra un usuario nuevo.
<<<<<<< HEAD
     * (Esta parte funciona perfecto y manda el mail)
=======
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    public Usuario registrar(String username, String email, String passwordPlana) {
        if (usuarioRepo.findByEmail(email).isPresent()) {
            throw new RuntimeException("El email ya esta en uso");
        }

<<<<<<< HEAD
        String passwordHasheada = "hash_de_" + passwordPlana;
        String verificationToken = UUID.randomUUID().toString();
=======
        String passwordHasheada = "hash_de_" + passwordPlana; // Simulacion
        String verificationToken = UUID.randomUUID().toString(); // Token unico
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setUsername(username);
        nuevoUsuario.setEmail(email);
        nuevoUsuario.setPasswordHash(passwordHasheada);
        nuevoUsuario.setRolSistema("USER");
<<<<<<< HEAD
        nuevoUsuario.setEstadoEmail("Pendiente"); // Sigue guardandolo como Pendiente
        nuevoUsuario.setVerificationToken(verificationToken);

        Usuario usuarioGuardado = usuarioRepo.save(nuevoUsuario);
        eventBus.publish(new UsuarioRegistradoEvent(usuarioGuardado)); // Sigue mandando el mail
=======
        nuevoUsuario.setEstadoEmail("Pendiente"); // Estado inicial
        nuevoUsuario.setVerificationToken(verificationToken); // Guardamos el token

        // 1. Guardamos en la DB
        Usuario usuarioGuardado = usuarioRepo.save(nuevoUsuario);

        // 2. ¡PUBLICAMOS EL EVENTO!
        eventBus.publish(new UsuarioRegistradoEvent(usuarioGuardado));
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

        return usuarioGuardado;
    }

    /**
     * Loguea un usuario existente.
<<<<<<< HEAD
     * ¡AQUI ESTA EL CAMBIO!
=======
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    public Usuario login(String email, String passwordPlana) {
        Usuario usuario = usuarioRepo.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Credenciales invalidas"));

<<<<<<< HEAD
        // ============================================================
        // ¡¡AQUI ESTA EL CAMBIO!!
        // Comentamos el 'if' que nos bloquea el login.
        // Ahora podes loguearte INMEDIATAMENTE despues de registrarte.
        // ============================================================
        /*
        if (!"Verificado".equals(usuario.getEstadoEmail())) {
            throw new RuntimeException("Email no verificado. Revisa tu bandeja de entrada o haz clic en el link de verificacion.");
        }
        */
        // ============================================================

        // El resto del chequeo de password sigue funcionando
=======
        // Chequeamos si ya verifico el email
        if (!"Verificado".equals(usuario.getEstadoEmail())) {
            throw new RuntimeException("Email no verificado. Revisa tu bandeja de entrada.");
        }

>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        boolean coincide = usuario.getPasswordHash().equals("hash_de_" + passwordPlana);
        if (!coincide) {
            throw new RuntimeException("Credenciales invalidas");
        }
<<<<<<< HEAD

        System.out.println("AuthService: Login exitoso para " + usuario.getUsername() + " (Verificacion Omitida para la Demo)");
        return usuario;
    }

    /**
     * Verifica un email usando un token.
     * (Este metodo sigue existiendo, pero ya no es bloqueante para el login)
=======
        return usuario;
    }

    // ============================================================
    // ¡¡NUEVO METODO PARA EL LINK DEL EMAIL!!
    // ============================================================
    /**
     * Verifica un email usando un token.
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
     */
    public Usuario verificarEmail(String token) {
        System.out.println("AuthService: Buscando usuario con token: " + token);

<<<<<<< HEAD
        Usuario usuario = usuarioRepo.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Token de verificacion invalido o expirado"));

        usuario.verificarEmail();

=======
        // 1. Buscamos al usuario por el token
        Usuario usuario = usuarioRepo.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Token de verificacion invalido o expirado"));

        // 2. Cambiamos el estado (Patron State... pero en el Usuario)
        usuario.verificarEmail();

        // 3. Guardamos los cambios
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        System.out.println("AuthService: Email verificado para " + usuario.getUsername());
        return usuarioRepo.save(usuario);
    }

    /**
     * Actualiza el perfil.
     */
    public Usuario actualizarPerfil(String usuarioId, DatosPerfil data) {
<<<<<<< HEAD
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado para actualizar"));

=======
        // ... (Este metodo queda igual que antes) ...
        Usuario usuario = usuarioRepo.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        usuario.setUsername(data.getUsername());
        usuario.setRegion(data.getRegion());
        usuario.setMmr(data.getMmr());
        usuario.setRolesPreferidos(data.getRoles());
        usuario.setDisponibilidad(data.getDisponibilidad());
<<<<<<< HEAD

=======
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        return usuarioRepo.save(usuario);
    }
}