package com.escrims.notificationSubsystem.adapters;

import java.io.File; // Import nuevo

/**
 * Contrato del Adapter de Email.
 * AHORA SOPORTA ADJUNTOS.
 */
public interface IAdapterEmail {
    // Metodo simple
    void enviar(String from, String to, String subject, String body);

    // Metodo sobrecargado con adjunto
    void enviar(String from, String to, String subject, String body, File adjunto);
}