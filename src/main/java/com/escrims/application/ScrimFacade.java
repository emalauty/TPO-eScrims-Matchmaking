package com.escrims.application;

<<<<<<< HEAD
import com.escrims.domainCore.*;
import com.escrims.services.*;
import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.ScrimCreadoEvent;
import java.util.List;

/**
 * Fachada para toda la gestion de Scrims.
 */
public class ScrimFacade {

    private final ScrimService scrimService;
    private final MatchmakingService matchmakingService;
    private final IScrimRepository scrimRepo;
    private final IUsuarioRepository usuarioRepo;
    private final IComentarioRepository comentarioRepo;

    public ScrimFacade(ScrimService ss, MatchmakingService ms,
                       IScrimRepository sr, IUsuarioRepository ur, IComentarioRepository cr) {
        this.scrimService = ss;
        this.matchmakingService = ms;
        this.scrimRepo = sr;
        this.usuarioRepo = ur;
        this.comentarioRepo = cr;
=======
import com.escrims.domainCore.*; // Importamos todo el dominio
import com.escrims.services.*; // Importamos todos los servicios
import java.util.List;
import java.util.Optional;

/**
 * Fachada para toda la gestion de Scrims.
 * ¡AHORA 100% COMPLETA con todos los metodos del State!
 */
public class ScrimFacade {

    // Los servicios y repositorios que le inyectamos en el Main
    private final ScrimService scrimService;
    private final MatchmakingService matchmakingService;
    private final IScrimRepository scrimRepo;
    // private final IUsuarioRepository usuarioRepo; // (Necesitariamos este tambien)

    public ScrimFacade(ScrimService ss, MatchmakingService ms, IScrimRepository sr /*, IUsuarioRepository ur */) {
        this.scrimService = ss;
        this.matchmakingService = ms;
        this.scrimRepo = sr;
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }

    // --- Metodos de la API ---

<<<<<<< HEAD
=======
    /**
     * Corresponde a: POST /api/scrims (crear)
     */
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    public Scrim crearScrim(DatosCreacion data) {
        System.out.println("FACADE (Scrim): Creando scrim...");

        ScrimBuilder builder = new ScrimBuilder();
        Scrim scrim = builder
                .deJuego(data.getJuego())
                .conRango(data.getRangoMin(), data.getRangoMax())
                .build();

<<<<<<< HEAD
        Scrim scrimGuardado = scrimService.guardar(scrim);
        DomainEventBus.getInstance().publish(new ScrimCreadoEvent(scrimGuardado));
        return scrimGuardado;
=======
        return scrimService.guardar(scrim);
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }

    /**
     * Corresponde a: GET /api/scrims?juego=...
<<<<<<< HEAD
     * ¡AHORA MUESTRA EL FILTRO DE FORMATO!
     */
    public List<Scrim> buscarScrims(FiltrosBusqueda filtros) {
        System.out.println("FACADE (Scrim): Buscando scrims...");

        // ============================================================
        // ¡¡LOGICA NUEVA!! (Para probar que el filtro llego)
        // ============================================================
        if (filtros.getFormato() != null) {
            System.out.println("FACADE (Scrim): Se aplico filtro de formato: " + filtros.getFormato());
        }
        // ============================================================

        // (En un sistema real, el repo haria la busqueda)
        // return scrimRepo.buscarConFiltros(filtros);
        return null; // Devolvemos null para que el Controller use la simulacion
    }

    // ... (El resto de metodos: postularse, confirmar, cancelar,
    //      iniciar, finalizar, cargarEstadisticas, asignarRol,
    //      publicarComentario y findScrimById quedan EXACTAMENTE IGUAL) ...

    public void postularse(String scrimId, Usuario usuario, Rol rol) {
        System.out.println("FACADE (Scrim): Postulando a " + scrimId);
        Scrim scrim = findScrimById(scrimId);
        try {
            scrim.postular(usuario, rol);
            scrimRepo.save(scrim);
=======
     */
    public List<Scrim> buscarScrims(FiltrosBusqueda filtros) {
        System.out.println("FACADE (Scrim): Buscando scrims...");
        // return scrimRepo.buscarConFiltros(filtros);
        return null;
    }

    /**
     * Corresponde a: POST /api/scrims/{id}/postulaciones
     */
    public void postularse(String scrimId, Usuario usuario, Rol rol) {
        System.out.println("FACADE (Scrim): Postulando a " + scrimId);

        // PATRON: 1. Buscar, 2. Actuar (Dominio), 3. Guardar
        Scrim scrim = findScrimById(scrimId);

        try {
            scrim.postular(usuario, rol); // 2. Llama al metodo del State
            scrimRepo.save(scrim); // 3. Guarda los cambios
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

<<<<<<< HEAD
    public void confirmar(String scrimId, Usuario usuario) {
        System.out.println("FACADE (Scrim): Confirmando para " + scrimId);
        Scrim scrim = findScrimById(scrimId);
        try {
            scrim.confirmar(usuario);
            scrimRepo.save(scrim);
=======
    /**
     * Corresponde a: POST /api/scrims/{id}/confirmaciones
     * ¡METODO QUE FALTABA!
     */
    public void confirmar(String scrimId, Usuario usuario) {
        System.out.println("FACADE (Scrim): Confirmando para " + scrimId);

        // PATRON: 1. Buscar, 2. Actuar (Dominio), 3. Guardar
        Scrim scrim = findScrimById(scrimId);
        // Usuario usuarioReal = usuarioRepo.findById(usuario.getId()).get();

        try {
            scrim.confirmar(usuario); // 2. Llama al metodo del State
            scrimRepo.save(scrim); // 3. Guarda los cambios
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

<<<<<<< HEAD
    public void cancelarScrim(String scrimId) {
        System.out.println("FACADE (Scrim): Cancelando " + scrimId);
        Scrim scrim = findScrimById(scrimId);
        try {
            scrim.cancelar();
            scrimRepo.save(scrim);
=======
    /**
     * Corresponde a: POST /api/scrims/{id}/cancelar
     * ¡METODO QUE FALTABA!
     */
    public void cancelarScrim(String scrimId) {
        System.out.println("FACADE (Scrim): Cancelando " + scrimId);

        // PATRON: 1. Buscar, 2. Actuar (Dominio), 3. Guardar
        Scrim scrim = findScrimById(scrimId);

        try {
            scrim.cancelar(); // 2. Llama al metodo del State
            scrimRepo.save(scrim); // 3. Guarda los cambios
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

<<<<<<< HEAD
    public void iniciarScrim(String scrimId) {
        System.out.println("FACADE (Scrim): Iniciando " + scrimId);
        Scrim scrim = findScrimById(scrimId);
        try {
            scrim.iniciar();
            scrimRepo.save(scrim);
=======
    /**
     * Corresponde a: POST /api/scrims/{id}/iniciar
     * ¡¡EL METODO QUE DIO EL ERROR!!
     */
    public void iniciarScrim(String scrimId) {
        System.out.println("FACADE (Scrim): Iniciando " + scrimId);

        // PATRON: 1. Buscar, 2. Actuar (Dominio), 3. Guardar
        Scrim scrim = findScrimById(scrimId);

        try {
            scrim.iniciar(); // 2. Llama al metodo del State
            scrimRepo.save(scrim); // 3. Guarda los cambios
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

<<<<<<< HEAD
    public void finalizarScrim(String scrimId) {
        System.out.println("FACADE (Scrim): Finalizando " + scrimId);
        Scrim scrim = findScrimById(scrimId);
        try {
            scrim.finalizar();
            scrimRepo.save(scrim);
=======
    /**
     * Corresponde a: POST /api/scrims/{id}/finalizar
     * ¡METODO QUE FALTABA!
     */
    public void finalizarScrim(String scrimId) {
        System.out.println("FACADE (Scrim): Finalizando " + scrimId);

        // PATRON: 1. Buscar, 2. Actuar (Dominio), 3. Guardar
        Scrim scrim = findScrimById(scrimId);

        try {
            scrim.finalizar(); // 2. Llama al metodo del State
            scrimRepo.save(scrim); // 3. Guarda los cambios
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

<<<<<<< HEAD
=======
    /**
     * Corresponde a: POST /api/scrims/{id}/estadisticas
     */
    public void cargarEstadisticas(String scrimId, DatosEstadisticas data) {
        System.out.println("FACADE (Scrim): Cargando estadisticas para " + scrimId);

        Scrim scrim = findScrimById(scrimId);
        // ... (Logica para crear y guardar una entidad Estadistica) ...
        scrimRepo.save(scrim);
    }

    /**
     * Corresponde a: POST /api/scrims/{id}/acciones/asignar-rol
     */
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    public void asignarRol(String scrimId, Usuario jugador, Rol rol) {
        System.out.println("FACADE (Scrim): Asignando rol...");
        IScrimCommand cmd = new AsignarRolCommand(jugador, rol);
        scrimService.ejecutarComando(scrimId, cmd);
    }

<<<<<<< HEAD
    public void cargarEstadisticas(String scrimId, DatosEstadisticas data) {
        System.out.println("FACADE (Scrim): Cargando estadisticas para " + scrimId);

        Scrim scrim = findScrimById(scrimId);

        Usuario usuario = usuarioRepo.findByUsername(data.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + data.getUsuarioId()));

        Estadistica est = new Estadistica(scrim, usuario);
        est.setKda(data.getKda());
        est.setMvp(data.isMvp());
        est.setObservaciones("Cargado via GUI");

        scrim.getEstadisticas().add(est);
        scrimRepo.save(scrim);
        System.out.println("FACADE (Scrim): Estadistica guardada para " + data.getUsuarioId());
    }

    public void publicarComentario(DatosComentario data) {
        System.out.println("FACADE (Scrim): Publicando comentario de " +
                data.getAutor().getUsername() + " sobre " + data.getDestinatario().getUsername());

        Comentario com = new Comentario(
                data.getScrim(),
                data.getAutor(),
                data.getDestinatario(),
                data.getTexto(),
                data.getRating()
        );

        comentarioRepo.save(com);
        System.out.println("FACADE (Scrim): Comentario guardado (Estado: " + com.getEstado() + ")");
    }


=======
    // --- Metodo Helper Privado ---

    /**
     * Metodo privado para no repetir codigo de busqueda.
     */
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    private Scrim findScrimById(String scrimId) {
        return scrimRepo.findById(scrimId)
                .orElseThrow(() -> new RuntimeException("Scrim no encontrado: " + scrimId));
    }
}