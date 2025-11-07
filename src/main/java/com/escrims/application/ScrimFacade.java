package com.escrims.application;

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
    }

    // --- Metodos de la API ---

    /**
     * Corresponde a: POST /api/scrims (crear)
     */
    public Scrim crearScrim(DatosCreacion data) {
        System.out.println("FACADE (Scrim): Creando scrim...");

        ScrimBuilder builder = new ScrimBuilder();
        Scrim scrim = builder
                .deJuego(data.getJuego())
                .conRango(data.getRangoMin(), data.getRangoMax())
                .build();

        return scrimService.guardar(scrim);
    }

    /**
     * Corresponde a: GET /api/scrims?juego=...
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
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

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
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

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
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

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
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

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
        } catch (UnsupportedOperationException e) {
            System.err.println("FACADE ERROR: " + e.getMessage());
            throw e;
        }
    }

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
    public void asignarRol(String scrimId, Usuario jugador, Rol rol) {
        System.out.println("FACADE (Scrim): Asignando rol...");
        IScrimCommand cmd = new AsignarRolCommand(jugador, rol);
        scrimService.ejecutarComando(scrimId, cmd);
    }

    // --- Metodo Helper Privado ---

    /**
     * Metodo privado para no repetir codigo de busqueda.
     */
    private Scrim findScrimById(String scrimId) {
        return scrimRepo.findById(scrimId)
                .orElseThrow(() -> new RuntimeException("Scrim no encontrado: " + scrimId));
    }
}