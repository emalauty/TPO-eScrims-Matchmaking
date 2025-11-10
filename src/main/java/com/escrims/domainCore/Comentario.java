package com.escrims.domainCore;

/**
 * Entidad que representa un comentario o rating
 * de un jugador hacia otro, post-partida.
 * (Requisito: Rating y Comentarios)
 */
public class Comentario {

    private String id;
    private String texto;
    private int rating; // ej: 1 a 5 estrellas
    private String estado; // "Pendiente", "Aprobado", "Rechazado" (Moderacion Basica)

    // Relaciones
    private Scrim scrim;
    private Usuario autor; // Quien escribe el comentario
    private Usuario destinatario; // Sobre quien es el comentario

    public Comentario(Scrim scrim, Usuario autor, Usuario destinatario, String texto, int rating) {
        this.scrim = scrim;
        this.autor = autor;
        this.destinatario = destinatario;
        this.texto = texto;
        this.rating = rating;
        this.estado = "Pendiente"; // Requiere moderacion
    }

    public void aprobar() {
        this.estado = "Aprobado";
    }

    public void rechazar() {
        this.estado = "Rechazado";
    }

    // --- Getters y Setters ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }
    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
    public String getEstado() { return estado; }
    public Scrim getScrim() { return scrim; }
    public Usuario getAutor() { return autor; }
    public Usuario getDestinatario() { return destinatario; }
}