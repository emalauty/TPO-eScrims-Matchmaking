package com.escrims.notificationSubsystem.adapters;

// Imports de SendGrid
import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

// Imports para el Adjunto (iCal)
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Base64;

/**
 * (Clase 'package-private' para simular la API externa
 * cuando la API Key no esta configurada).
 */
class SendGridAPI {
    public void enviarCorreo(String from, String to, String subject, String body) {
        System.out.println("==================================================");
        System.out.println("EMAIL (via SendGrid API - SIMULADO)");
        System.out.println("A: " + to + " | DE: " + from);
        System.out.println("ASUNTO: " + subject);
        System.out.println("CUERPO: " + body);
        System.out.println("==================================================");
    }
}

/**
 * Implementacion del Adapter de SendGrid.
 * Traduce llamadas internas a la API de SendGrid.
 * Incluye logica para adjuntos (iCal) y modo de simulacion.
 */
public class SendGridAdapter implements IAdapterEmail {

    // =======================================================================
    // ¡¡AQUI ESTA LA IDEA QUE ACORDAMOS!!
    // =======================================================================

    // 1. LA CLAVE REAL (COMENTADA PARA GITHUB)
    // Para probar la demo, descomenta esta linea:

    // 2. EL PLACEHOLDER (SEGURO PARA GITHUB)
    // Esta es la linea que se sube a Git.
    private static final String SENDGRID_API_KEY = "TU_API_KEY_VA_AQUI_PARA_PROBAR_LA_DEMO";

    // =======================================================================


    // Instancia de la simulacion
    private SendGridAPI apiExternaSimulada = new SendGridAPI();

    /**
     * Implementacion simple (llama a la completa con adjunto null)
     */
    @Override
    public void enviar(String from, String to, String subject, String body) {
        // Delega a la implementacion completa
        this.enviar(from, to, subject, body, null);
    }

    /**
     * Implementacion completa (con adjunto)
     * Esta es la que usa la API real de SendGrid
     */
    @Override
    public void enviar(String from, String to, String subject, String body, File adjunto) {

        // 1. Construir el mail base
        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        // 2. Logica de Adjuntos (para el iCal)
        if (adjunto != null) {
            try {
                // Lee el archivo .ics
                byte[] fileData = Files.readAllBytes(adjunto.toPath());
                // Lo convierte a Base64
                String base64Data = Base64.getEncoder().encodeToString(fileData);

                // Crea el adjunto de SendGrid
                Attachments attachments = new Attachments.Builder(adjunto.getName(), base64Data)
                        .withType("text/calendar")
                        .withDisposition("attachment")
                        .build();

                // Lo agrega al mail
                mail.addAttachments(attachments);
                System.out.println("SendGridAdapter: Adjuntando archivo: " + adjunto.getName());

            } catch (IOException e) {
                System.err.println("SendGridAdapter: Error al leer adjunto: " + e.getMessage());
            }
        }

        // 3. Logica de Envio (Real o Simulado)
        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());

            // ¡¡ESTA ES LA LOGICA CORREGIDA!!
            // Si la clave es el placeholder, usamos la simulacion
            if (SENDGRID_API_KEY.equals("TU_API_KEY_VA_AQUI_PARA_PROBAR_LA_DEMO")) {
                System.out.println("SendGridAdapter: MODO SIMULACION (API Key no configurada).");
                apiExternaSimulada.enviarCorreo(from, to, subject, body);
            } else {
                // Si la clave es REAL (porque la descomentaste), usa la API real
                Response response = sg.api(request); // ¡Llamada real!
                System.out.println("SENDGRID: Email enviado! Status Code: " + response.getStatusCode());
            }

        } catch (IOException ex) {
            System.err.println("SENDGRID: Error al enviar email: " + ex.getMessage());
        }
    }
}