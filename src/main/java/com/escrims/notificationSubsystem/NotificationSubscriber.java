package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.events.*;
<<<<<<< HEAD
import com.escrims.application.TestContext; // El "buzon"
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import com.escrims.notificationSubsystem.adapters.ICalAdapter; // ¡IMPORT NUEVO!
import com.escrims.notificationSubsystem.adapters.ICalAdapterImpl; // ¡IMPORT NUEVO!
import java.io.File; // ¡IMPORT NUEVO!

/**
 * El Observer Concreto.
 * ¡AHORA ADJUNTA ARCHIVOS .ics (iCalendar)!
=======
import com.escrims.application.TestContext;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import com.escrims.notificationSubsystem.adapters.ICalAdapter;
import com.escrims.notificationSubsystem.adapters.ICalAdapterImpl;
import java.io.File;

/**
 * El Observer Concreto.
 * ¡AHORA TAMBIEN ESCUCHA 'UsuarioRegistradoEvent' (para verificacion)!
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
 */
public class NotificationSubscriber implements ISubscriber {

    private final INotifierFactory factory;
<<<<<<< HEAD

    // ============================================================
    // ¡¡NUEVO ATRIBUTO!!
    // ============================================================
    private final ICalAdapter icalAdapter; // El adapter de iCal
    // ============================================================

    public NotificationSubscriber(INotifierFactory factory) {
        this.factory = factory;
        // Creamos la instancia del adapter de iCal
=======
    private final ICalAdapter icalAdapter;

    public NotificationSubscriber(INotifierFactory factory) {
        this.factory = factory;
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        this.icalAdapter = new ICalAdapterImpl();
    }

    @Override
    public void onEvent(DomainEvent event) {

        IMetodoNotificacion emailer;
        Notificacion nEmail = new Notificacion();
        String emailDePrueba = TestContext.getInstance().getEmailDePrueba();

<<<<<<< HEAD
        if (emailDePrueba == null || emailDePrueba.isEmpty() || !emailDePrueba.contains("@")) {
            // Si el evento no es de registro Y el mail de prueba es invalido, abortamos.
            if (!(event instanceof UsuarioRegistradoEvent)) {
                System.err.println("SUBSCRIBER: No hay email de prueba valido. Abortando notificacion de Scrim.");
                return;
            }
        }

        // ----------------------------------------------------
        // EVENTO 1: Registro de Usuario (Sin adjunto)
=======
        // Chequeo de mail de prueba (solo para eventos de Scrim)
        boolean emailDePruebaValido = emailDePrueba != null && !emailDePrueba.isEmpty() && emailDePrueba.contains("@");

        // ----------------------------------------------------
        // ¡¡NUEVO EVENTO!! USUARIO REGISTRADO
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        // ----------------------------------------------------
        if (event instanceof UsuarioRegistradoEvent) {
            UsuarioRegistradoEvent e = (UsuarioRegistradoEvent) event;
            Usuario usuario = e.getUsuario();
            System.out.println("SUBSCRIBER: Recibido UsuarioRegistradoEvent para " + usuario.getEmail());

<<<<<<< HEAD
            String token = usuario.getVerificationToken();
            String verificationLink = "http://localhost:8080/api/auth/verify?token=" + token;
=======
            // Simulamos el link
            String token = usuario.getVerificationToken();
            String verificationLink = "http://localhost:8080/api/auth/verify?token=" + token; // URL de la API
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

            nEmail.setId("noti-registro-" + usuario.getId());
            nEmail.setDestinatario(usuario.getEmail()); // ¡Usa el mail real del usuario!
            nEmail.setPayload("¡Bienvenido a eScrims, " + usuario.getUsername() + "!\n\n" +
<<<<<<< HEAD
                    "Por favor, verifica tu email (o usa el token manual):\n" +
                    verificationLink + "\n\n" +
                    "Token: " + token);

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail); // Llama al metodo simple
        }

        // ----------------------------------------------------
        // EVENTO 2: Lobby Lleno (Sin adjunto)
        // ----------------------------------------------------
        else if (event instanceof LobbyCompletoEvent) {
            LobbyCompletoEvent e = (LobbyCompletoEvent) event;
            System.out.println("SUBSCRIBER: Recibido LobbyCompletoEvent para Scrim " + e.getScrimId());

            nEmail.setId("noti-lobby-" + e.getScrimId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("Tu lobby para " + e.getScrimId() + " esta lleno! Por favor, confirma tu asistencia en la app.");

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail); // Llama al metodo simple
        }

        // ----------------------------------------------------
        // EVENTO 3: Todos Confirmaron (¡¡CON ADJUNTO!!)
=======
                    "Por favor, verifica tu email haciendo clic en el siguiente link:\n" +
                    verificationLink + "\n\n" +
                    "Token para prueba manual: " + token);

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail); // Envia el mail de verificacion
        }

        // Si es un evento de Scrim, requiere un email de prueba valido
        if (!emailDePruebaValido) {
            if (!(event instanceof UsuarioRegistradoEvent)) {
                System.err.println("SUBSCRIBER: No hay email de prueba valido. Abortando notificacion de Scrim.");
            }
            return;
        }

        // ----------------------------------------------------
        // EVENTO 2: Todos Confirmaron (Con adjunto iCal)
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        // ----------------------------------------------------
        else if (event instanceof ScrimConfirmadoEvent) {
            ScrimConfirmadoEvent e = (ScrimConfirmadoEvent) event;
            System.out.println("SUBSCRIBER: Recibido ScrimConfirmadoEvent para Scrim " + e.getScrimId());

<<<<<<< HEAD
            // 1. Simulamos el Scrim (en un sistema real lo buscariamos en el repo)
            Scrim scrim = new Scrim();
            scrim.setId(e.getScrimId());
            scrim.setJuego("Valorant (Test)");
            // scrim.setFechaHora(LocalDateTime.now().plusDays(1)); // (Le pondriamos la fecha real)

            // 2. ¡Llamamos al Adapter de iCal!
            File icsFile = icalAdapter.generarArchivoICS(scrim);

            // 3. Construimos la notificacion
            nEmail.setId("noti-confirmado-" + e.getScrimId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡Todos confirmaron! El Scrim " + e.getScrimId() + " esta listo para iniciar. Adjuntamos el evento de calendario.");

            // 4. Usamos el Decorator
            emailer = new LoggingNotifierDecorator(factory.createEmail());

            // 5. ¡Llamamos al metodo 'notificar' CON adjunto!
            emailer.notificar(nEmail, icsFile);
        }

        // ----------------------------------------------------
        // EVENTO 4: La Partida Empezo (Sin adjunto)
        // ----------------------------------------------------
        else if (event instanceof ScrimIniciadoEvent) {
            ScrimIniciadoEvent e = (ScrimIniciadoEvent) event;
            System.out.println("SUBSCRIBER: Recibido ScrimIniciadoEvent para Scrim " + e.getScrimId());

            nEmail.setId("noti-iniciado-" + e.getScrimId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡GLHF! La partida " + e.getScrimId() + " acaba de empezar. ¡Conectate ya!");

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }

        // ----------------------------------------------------
        // EVENTO 5: La Partida Termino (Sin adjunto)
        // ----------------------------------------------------
        else if (event instanceof ScrimFinalizadoEvent) {
            ScrimFinalizadoEvent e = (ScrimFinalizadoEvent) event;
            System.out.println("SUBSCRIBER: Recibido ScrimFinalizadoEvent para Scrim " + e.getScrimId());

            nEmail.setId("noti-finalizado-" + e.getScrimId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡GG! La partida " + e.getScrimId() + " ha finalizado. No olvides cargar tus estadisticas.");

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }

        // ----------------------------------------------------
        // EVENTO 6: Scrim Cancelado (Sin adjunto)
        // ----------------------------------------------------
        else if (event instanceof ScrimCanceladoEvent) {
            ScrimCanceladoEvent e = (ScrimCanceladoEvent) event;
            System.out.println("SUBSCRIBER: Recibido ScrimCanceladoEvent para Scrim " + e.getScrimId());

            nEmail.setId("noti-cancelado-" + e.getScrimId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("El scrim " + e.getScrimId() + " fue cancelado. Motivo: " + e.getMotivo());

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }

        // ----------------------------------------------------
        // EVENTO 7: Plaza de Suplente (Sin adjunto)
        // ----------------------------------------------------
        else if (event instanceof PlazaDisponibleEvent) {
            PlazaDisponibleEvent e = (PlazaDisponibleEvent) event;
            Scrim scrim = e.getScrim();
            System.out.println("SUBSCRIBER: Recibido PlazaDisponibleEvent para Scrim " + scrim.getId());

            // (Logica de busqueda de suplentes)

            nEmail.setId("noti-suplente-" + scrim.getId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡Una plaza se libero en el Scrim " + scrim.getId() + "!");

=======
            // Simulamos el Scrim para el iCal
            Scrim scrim = new Scrim();
            scrim.setId(e.getScrimId());
            scrim.setJuego("Valorant (Test)");

            File icsFile = icalAdapter.generarArchivoICS(scrim);

            nEmail.setId("noti-confirmado-" + e.getScrimId());
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡Todos confirmaron! El Scrim " + e.getScrimId() + " esta listo. Adjuntamos el evento de calendario.");

            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail, icsFile); // Llama al metodo con adjunto
        }

        // ----------------------------------------------------
        // EVENTO 3, 4, 5, 6 (Lobby Lleno, Iniciado, Finalizado, Cancelado)
        // (La logica para estos queda igual que antes, usando emailDePrueba)
        // ----------------------------------------------------
        else if (event instanceof LobbyCompletoEvent) {
            LobbyCompletoEvent e = (LobbyCompletoEvent) event;
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("Tu lobby para " + e.getScrimId() + " esta lleno! Por favor, confirma tu asistencia en la app.");
            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }
        else if (event instanceof ScrimIniciadoEvent) {
            ScrimIniciadoEvent e = (ScrimIniciadoEvent) event;
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡GLHF! La partida " + e.getScrimId() + " acaba de empezar. ¡Conectate ya!");
            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }
        else if (event instanceof ScrimFinalizadoEvent) {
            ScrimFinalizadoEvent e = (ScrimFinalizadoEvent) event;
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("¡GG! La partida " + e.getScrimId() + " ha finalizado. No olvides cargar tus estadisticas.");
            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }
        else if (event instanceof ScrimCanceladoEvent) {
            ScrimCanceladoEvent e = (ScrimCanceladoEvent) event;
            nEmail.setDestinatario(emailDePrueba);
            nEmail.setPayload("El scrim " + e.getScrimId() + " fue cancelado. Motivo: " + e.getMotivo());
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }
    }
}