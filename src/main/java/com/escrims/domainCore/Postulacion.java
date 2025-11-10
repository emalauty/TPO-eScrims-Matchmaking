package com.escrims.domainCore;

<<<<<<< HEAD
/**
 * Entidad que registra el interes de un usuario por unirse a un Scrim.
 * ¡AHORA CON GETTERS Y SETTERS COMPLETOS!
 */
public class Postulacion {

    private String id;
    private String estado; // ej: "Pendiente", "Aceptada", "Rechazada"

    // Relaciones
    private Usuario usuario;
    private Scrim scrim;
    private Rol rolDeseado;

    public Postulacion(Usuario usuario, Scrim scrim, Rol rolDeseado) {
        this.usuario = usuario;
        this.scrim = scrim;
        this.rolDeseado = rolDeseado;
        this.estado = "Pendiente";
    }

    // --- Metodos de Logica ---

    public void aceptar() {
        this.estado = "Aceptada";
        // (Aqui podriamos agregar al usuario al 'Equipo' del Scrim)
    }

    public void rechazar() {
        this.estado = "Rechazada";
    }

    // --- GETTERS Y SETTERS (La solucion al error) ---

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        // ¡¡EL METODO QUE FALTABA!!
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Scrim getScrim() {
        return scrim;
    }

    public void setScrim(Scrim scrim) {
        this.scrim = scrim;
    }

    public Rol getRolDeseado() {
        return rolDeseado;
    }

    public void setRolDeseado(Rol rolDeseado) {
        this.rolDeseado = rolDeseado;
    }
=======
public class Postulacion {
    private String id;
    private Usuario usuario;
    private Scrim scrim;
    private Rol rolDeseado; // <-- Actualizado a la clase Rol
    private String estado;

    public Postulacion(Usuario u, Scrim s, Rol r) { /*...*/ }
    // ... Metodos ...
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
}