package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.events.*;
import com.escrims.application.TestContext;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import com.escrims.notificationSubsystem.adapters.ICalAdapter;
import com.escrims.notificationSubsystem.adapters.ICalAdapterImpl;
import java.io.File;

/**
 * El Observer Concreto.
 * ¡AHORA TAMBIEN ESCUCHA 'UsuarioRegistradoEvent' (para verificacion)!
 */
public class NotificationSubscriber implements ISubscriber {

    private final INotifierFactory factory;
    private final ICalAdapter icalAdapter;

    public NotificationSubscriber(INotifierFactory factory) {
        this.factory = factory;
        this.icalAdapter = new ICalAdapterImpl();
    }

    @Override
    public void onEvent(DomainEvent event) {

        IMetodoNotificacion emailer;
        Notificacion nEmail = new Notificacion();
        String emailDePrueba = TestContext.getInstance().getEmailDePrueba();

        // Chequeo de mail de prueba (solo para eventos de Scrim)
        boolean emailDePruebaValido = emailDePrueba != null && !emailDePrueba.isEmpty() && emailDePrueba.contains("@");

        // ----------------------------------------------------
        // ¡¡NUEVO EVENTO!! USUARIO REGISTRADO
        // ----------------------------------------------------
        if (event instanceof UsuarioRegistradoEvent) {
            UsuarioRegistradoEvent e = (UsuarioRegistradoEvent) event;
            Usuario usuario = e.getUsuario();
            System.out.println("SUBSCRIBER: Recibido UsuarioRegistradoEvent para " + usuario.getEmail());

            // Simulamos el link
            String token = usuario.getVerificationToken();
            String verificationLink = "http://localhost:8080/api/auth/verify?token=" + token; // URL de la API

            nEmail.setId("noti-registro-" + usuario.getId());
            nEmail.setDestinatario(usuario.getEmail()); // ¡Usa el mail real del usuario!
            nEmail.setPayload("¡Bienvenido a eScrims, " + usuario.getUsername() + "!\n\n" +
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
        // ----------------------------------------------------
        else if (event instanceof ScrimConfirmadoEvent) {
            ScrimConfirmadoEvent e = (ScrimConfirmadoEvent) event;
            System.out.println("SUBSCRIBER: Recibido ScrimConfirmadoEvent para Scrim " + e.getScrimId());

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
            emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(nEmail);
        }
    }
}