package com.escrims.notificationSubsystem;

/**
 * Entidad de datos que transporta la informacion
 * de una notificacion.
 */
public class Notificacion {

    private String id;
    private String tipo; // "Email", "Push"
    private String payload; // "Tu lobby esta listo"
    private String destinatario; // "usuario@mail.com" o "ID_Firebase_Token"

    // --- Getters y Setters ---
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public String getPayload() { return payload; }
    public void setPayload(String payload) { this.payload = payload; }
    public String getDestinatario() { return destinatario; }
    public void setDestinatario(String destinatario) { this.destinatario = destinatario; }
}