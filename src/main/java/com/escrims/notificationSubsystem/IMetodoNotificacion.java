package com.escrims.notificationSubsystem;

import java.io.File; // Import nuevo

public interface IMetodoNotificacion {
    // Sobrecargamos el metodo
    void notificar(Notificacion n);
    void notificar(Notificacion n, File adjunto); // ¡NUEVO METODO!
}