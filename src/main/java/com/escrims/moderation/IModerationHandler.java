package com.escrims.moderation;

import com.escrims.domainCore.ReporteConducta;

/**
 * Contrato del Patron Chain of Responsibility.
 * Define un "eslabon" de la cadena de moderacion.
 */
public interface IModerationHandler {

    /**
     * Define el siguiente eslabon en la cadena.
     * Devuelve el proximo handler para permitir encadenamiento fluido.
     * ej: handler1.setNext(handler2).setNext(handler3);
     */
    IModerationHandler setNext(IModerationHandler handler);

    /**
     * Maneja el reporte.
     * @param reporte El objeto de dominio ReporteConducta.
     * @return true si el reporte fue manejado (fin de la cadena),
     * false si debe pasar al siguiente eslabon.
     */
    boolean handleReport(ReporteConducta reporte);
}