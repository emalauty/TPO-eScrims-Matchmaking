package com.escrims.moderation;

import com.escrims.domainCore.ReporteConducta;

public class HumanModeratorHandler extends BaseModerationHandler {

    @Override
    public boolean handleReport(ReporteConducta reporte) {
        // Este es el eslabon final. No llama a handleNext().
        // Simplemente lo marca para revision humana.
        System.out.println("HumanModeratorHandler: Reporte no resuelto automaticamente. Marcando para revision humana.");
        reporte.setEstado("Pendiente Revision Humana");

        // (En un sistema real, esto lo guardaria en una cola de 'tickets')

        return true; // ¡Manejado!
    }
}