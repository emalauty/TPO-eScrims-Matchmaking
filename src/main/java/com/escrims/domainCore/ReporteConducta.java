package com.escrims.domainCore;

/**
 * Entidad para registrar reportes de conducta.
 */
public class ReporteConducta {

    private String id;
    private String motivo;
    private String estado; // ej: "Pendiente", "Resuelto"
    private String sancion; // ej: "Cooldown 24h"

    // Relacion: El reporte ocurrio en un Scrim
    private Scrim scrim;

    // Relacion: Quien reporta
    private Usuario reportador;

    // Relacion: A quien reportan
    private Usuario reportado;

    public ReporteConducta(Scrim scrim, Usuario reportador, Usuario reportado, String motivo) {
        this.scrim = scrim;
        this.reportador = reportador;
        this.reportado = reportado;
        this.motivo = motivo;
        this.estado = "Pendiente"; // Estado inicial
    }

    public void resolver(String sancion) {
        this.estado = "Resuelto";
        this.sancion = sancion;
    }

    // --- GETTERS Y SETTERS (Los que faltaban) ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getMotivo() { return motivo; }

    public String getEstado() {
        return estado; // <-- Agregado
    }

    public void setEstado(String estado) { // <-- ¡EL METODO QUE FALTABA!
        this.estado = estado;
    }

    public String getSancion() { return sancion; }
    public void setSancion(String sancion) { this.sancion = sancion; } // <-- Y este, por si acaso

    public Scrim getScrim() { return scrim; }
    public Usuario getReportador() { return reportador; }
    public Usuario getReportado() { return reportado; }
}