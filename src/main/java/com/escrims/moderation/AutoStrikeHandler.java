package com.escrims.moderation;

import com.escrims.domainCore.ReporteConducta;

public class AutoStrikeHandler extends BaseModerationHandler {

    @Override
    public boolean handleReport(ReporteConducta reporte) {
        // LOGICA DE AUTO-RESOLUCION
        if (reporte.getMotivo().toLowerCase().contains("insulto grave")) {

            System.out.println("AutoStrikeHandler: Detectada toxicidad. Resolviendo...");
            reporte.resolver("STRIKE 1 - Automatico por toxicidad");
            return true; // ¡Manejado! La cadena termina aca.

        } else {
            // No es mi problema, se lo paso al siguiente eslabon
            System.out.println("AutoStrikeHandler: No es mi tipo de reporte. Pasando al siguiente...");
            return super.handleNext(reporte);
        }
    }
}