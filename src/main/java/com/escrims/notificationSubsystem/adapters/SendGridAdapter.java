package com.escrims.notificationSubsystem.adapters;

import com.sendgrid.*;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Attachments; // ¡IMPORT NUEVO!
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import java.io.File; // ¡IMPORT NUEVO!
import java.io.IOException;
import java.nio.file.Files; // ¡IMPORT NUEVO!
import java.util.Base64; // ¡IMPORT NUEVO!

// (Clase SendGridAPI...)
class SendGridAPI { /* ... */ }

/**
 * Implementacion del Adapter de SendGrid.
 * AHORA SOPORTA ADJUNTOS.
 */
public class SendGridAdapter implements IAdapterEmail {

    private static final String SENDGRID_API_KEY = "TU_CLAVE_VA_AQUI_PARA_SEGURIDAD";

    /**
     * Implementacion simple (llama a la completa con adjunto null)
     */
    @Override
    public void enviar(String from, String to, String subject, String body) {
        this.enviar(from, to, subject, body, null);
    }

    /**
     * Implementacion completa (con adjunto)
     */
    @Override
    public void enviar(String from, String to, String subject, String body, File adjunto) {

        Email fromEmail = new Email(from);
        Email toEmail = new Email(to);
        Content content = new Content("text/plain", body);
        Mail mail = new Mail(fromEmail, subject, toEmail, content);

        // --- ¡NUEVA LOGICA DE ADJUNTOS! ---
        if (adjunto != null) {
            try {
                // Lee el archivo .ics
                byte[] fileData = Files.readAllBytes(adjunto.toPath());
                // Lo convierte a Base64
                String base64Data = Base64.getEncoder().encodeToString(fileData);

                // Crea el adjunto de SendGrid
                Attachments attachments = new Attachments.Builder(adjunto.getName(), base64Data)
                        .withType("text/calendar") // Le decimos que es un archivo de calendario
                        .withDisposition("attachment")
                        .build();

                // Lo agrega al mail
                mail.addAttachments(attachments);
                System.out.println("SendGridAdapter: Adjuntando archivo: " + adjunto.getName());

            } catch (IOException e) {
                System.err.println("SendGridAdapter: Error al leer adjunto: " + e.getMessage());
            }
        }
        // --- FIN DE LOGICA DE ADJUNTOS ---

        SendGrid sg = new SendGrid(SENDGRID_API_KEY);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("SENDGRID: Email enviado! Status Code: " + response.getStatusCode());
        } catch (IOException ex) {
            System.err.println("SENDGRID: Error al enviar email: " + ex.getMessage());
        }
    }
}