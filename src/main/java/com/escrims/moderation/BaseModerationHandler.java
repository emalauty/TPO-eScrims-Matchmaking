package com.escrims.moderation;

import com.escrims.domainCore.ReporteConducta;

/**
 * Clase base abstracta que implementa la logica de "encadenamiento".
 * Los eslabones concretos heredaran de esta.
 */
public abstract class BaseModerationHandler implements IModerationHandler {

    protected IModerationHandler nextHandler;

    @Override
    public IModerationHandler setNext(IModerationHandler handler) {
        this.nextHandler = handler;
        return handler; // Permite el encadenamiento
    }

    /**
     * Logica base: si hay un siguiente eslabon, le pasa el reporte.
     * Si no hay mas eslabones, el reporte no se maneja (devuelve false).
     */
    protected boolean handleNext(ReporteConducta reporte) {
        if (this.nextHandler != null) {
            return this.nextHandler.handleReport(reporte);
        }
        return false;
    }

    // El metodo principal sigue siendo abstracto
    // para forzar a las subclases a implementarlo.
    @Override
    public abstract boolean handleReport(ReporteConducta reporte);
}