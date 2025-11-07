package com.escrims.notificationSubsystem.adapters;

import com.escrims.domainCore.Scrim;
import java.io.File; // Usaremos java.io.File para el adjunto

/**
 * Contrato del Adapter para el sistema iCalendar.
 * Sabe como "traducir" un Scrim a un archivo .ics
 */
public interface ICalAdapter {
    /**
     * Crea un archivo .ics temporal a partir de un Scrim.
     * @param scrim El scrim con la fecha y datos.
     * @return Un objeto File que apunta al .ics creado.
     */
    File generarArchivoICS(Scrim scrim);
}