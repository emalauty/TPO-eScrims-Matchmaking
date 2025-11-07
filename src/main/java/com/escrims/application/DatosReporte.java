package com.escrims.application;

/**
 * DTO (Data Transfer Object).
 * Transporta los datos "crudos" del reporte
 * desde el Controller hasta la Fachada.
 */
public class DatosReporte {

    private String scrimId;
    private String reportadorId;
    private String reportadoId;
    private String motivo;

    // --- Getters y Setters ---

    public String getScrimId() { return scrimId; }
    public void setScrimId(String scrimId) { this.scrimId = scrimId; }

    public String getReportadorId() { return reportadorId; }
    public void setReportadorId(String reportadorId) { this.reportadorId = reportadorId; }

    public String getReportadoId() { return reportadoId; }
    public void setReportadoId(String reportadoId) { this.reportadoId = reportadoId; }

    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
}