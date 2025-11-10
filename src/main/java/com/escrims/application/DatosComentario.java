package com.escrims.application;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;

/**
 * DTO (Data Transfer Object).
 * Transporta los datos del formulario de Feedback
 * (para la entidad Comentario) a la fachada.
 * ¡ESTE ES EL ARCHIVO QUE FALTABA!
 */
public class DatosComentario {

    // Estos son los atributos que la fachada espera
    private Scrim scrim;
    private Usuario autor;
    private Usuario destinatario;
    private String texto;
    private int rating;

    // --- Getters y Setters ---

    public Scrim getScrim() { // <-- ¡EL METODO QUE TE DA ERROR!
        return scrim;
    }
    public void setScrim(Scrim scrim) { this.scrim = scrim; }

    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }

    public Usuario getDestinatario() { return destinatario; }
    public void setDestinatario(Usuario destinatario) { this.destinatario = destinatario; }

    public String getTexto() { return texto; }
    public void setTexto(String texto) { this.texto = texto; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }
}