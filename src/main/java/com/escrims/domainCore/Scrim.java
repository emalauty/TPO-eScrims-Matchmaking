package com.escrims.domainCore;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

/**
 * Entidad central y Contexto del Patron State.
 * Esta es la version "inteligente" (Modo Complejo)
 * que chequea el estado antes de actuar.
 */
public class Scrim {

    // --- Atributos de Datos ---
    private String id;
    private String juego;
    private String formato;
    private int rangoMin;
    private int rangoMax;
    private LocalDateTime fechaHora;

    // --- Atributos de Relacion ---
    private IScrimState estadoActual; // El estado (etiqueta)
    private Usuario organizador;
    private List<Equipo> equipos;
    private List<Postulacion> postulaciones;
    private List<Confirmacion> confirmaciones;
    private List<Estadistica> estadisticas;
    private List<ReporteConducta> reportes;
    private List<Rol> rolesObligatorios;

    // --- Constructor ---
    public Scrim() {
        this.estadoActual = new BuscandoState();
        // Inicializar listas para evitar NullPointerExceptions
        this.equipos = new ArrayList<>();
        this.postulaciones = new ArrayList<>();
        this.confirmaciones = new ArrayList<>();
        this.estadisticas = new ArrayList<>();
        this.reportes = new ArrayList<>();
        this.rolesObligatorios = new ArrayList<>();
    }

    // --- METODOS DE ACCION "INTELIGENTES" (Patron State) ---

    /**
     * Metodo base para la transicion de estado.
     */
    public void setState(IScrimState nuevoEstado) {
        System.out.println("CAMBIO DE ESTADO: De " +
                (this.estadoActual != null ? this.estadoActual.getClass().getSimpleName() : "null") +
                " a " + nuevoEstado.getClass().getSimpleName());
        this.estadoActual = nuevoEstado;
    }

    /**
     * Metodo publico para postularse.
     * Chequea si el estado actual ES 'IPostulable' antes de actuar.
     */
    public void postular(Usuario usuario, Rol rol) {
        if (estadoActual instanceof IPostulable) {
            // Si es, delega la accion al estado
            ((IPostulable)estadoActual).postular(this, usuario, rol);
        } else {
            // Si no es, falla
            throw new UnsupportedOperationException("No se puede postular en el estado actual: " + estadoActual.getClass().getSimpleName());
        }
    }

    /**
     * Metodo publico para confirmar.
     * Chequea si el estado actual ES 'IConfirmable'.
     */
    public void confirmar(Usuario usuario) {
        if (estadoActual instanceof IConfirmable) {
            ((IConfirmable)estadoActual).confirmar(this, usuario);
        } else {
            throw new UnsupportedOperationException("No se puede confirmar en el estado actual: " + estadoActual.getClass().getSimpleName());
        }
    }

    /**
     * Metodo publico para iniciar.
     * Chequea si el estado actual ES 'IIniciable'.
     */
    public void iniciar() {
        if (estadoActual instanceof IIniciable) {
            ((IIniciable)estadoActual).iniciar(this);
        } else {
            throw new UnsupportedOperationException("No se puede iniciar en el estado actual: " + estadoActual.getClass().getSimpleName());
        }
    }

    /**
     * Metodo publico para finalizar.
     * Chequea si el estado actual ES 'IFinalizable'.
     */
    public void finalizar() {
        if (estadoActual instanceof IFinalizable) {
            ((IFinalizable)estadoActual).finalizar(this);
        } else {
            throw new UnsupportedOperationException("No se puede finalizar en el estado actual: " + estadoActual.getClass().getSimpleName());
        }
    }

    /**
     * Metodo publico para cancelar.
     * Chequea si el estado actual ES 'ICancelable'.
     */
    public void cancelar() {
        if (estadoActual instanceof ICancelable) {
            ((ICancelable)estadoActual).cancelar(this);
        } else {
            throw new UnsupportedOperationException("No se puede cancelar en el estado actual: " + estadoActual.getClass().getSimpleName());
        }
    }

    // --- GETTERS Y SETTERS ---

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getJuego() { return juego; }
    public void setJuego(String juego) { this.juego = juego; }

    public String getFormato() { return formato; }
    public void setFormato(String formato) { this.formato = formato; }

    public int getRangoMin() { return rangoMin; }
    public void setRangoMin(int rangoMin) { this.rangoMin = rangoMin; }

    public int getRangoMax() { return rangoMax; }
    public void setRangoMax(int rangoMax) { this.rangoMax = rangoMax; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public IScrimState getEstadoActual() {
        return estadoActual;
    }

    public Usuario getOrganizador() { return organizador; }
    public void setOrganizador(Usuario organizador) { this.organizador = organizador; }

    public List<Equipo> getEquipos() { return equipos; }
    public void setEquipos(List<Equipo> equipos) { this.equipos = equipos; }

    public List<Postulacion> getPostulaciones() { return postulaciones; }
    public void setPostulaciones(List<Postulacion> postulaciones) { this.postulaciones = postulaciones; }

    public List<Confirmacion> getConfirmaciones() { return confirmaciones; }
    public void setConfirmaciones(List<Confirmacion> confirmaciones) { this.confirmaciones = confirmaciones; }

    public List<Estadistica> getEstadisticas() { return estadisticas; }
    public void setEstadisticas(List<Estadistica> estadisticas) { this.estadisticas = estadisticas; }

    public List<ReporteConducta> getReportes() { return reportes; }
    public void setReportes(List<ReporteConducta> reportes) { this.reportes = reportes; }

    public List<Rol> getRolesObligatorios() { return rolesObligatorios; }
    public void setRolesObligatorios(List<Rol> rolesObligatorios) { this.rolesObligatorios = rolesObligatorios; }
}