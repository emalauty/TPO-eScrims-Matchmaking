package com.escrims.application;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;

/**
 * El Controlador (la "C" de MVC).
 * Es tu "Cliente". Recibe las peticiones de la API
 * y DELEGA a la fachada correcta.
 */
public class ScrimController {

    // El Controlador conoce a los 3 "gerentes" (Fachadas)
    private final AuthFacade authFacade;
    private final ScrimFacade scrimFacade;
    private final ModerationFacade moderationFacade;

    // Se los "inyectamos" (Spring o un Main se encarga de esto)
    public ScrimController(AuthFacade af, ScrimFacade sf, ModerationFacade mf) {
        this.authFacade = af;
        this.scrimFacade = sf;
        this.moderationFacade = mf;
    }

    // --- Endpoints de API ---

    // POST /api/auth/register
    public Usuario registrarUsuario(DatosRegistro data) {
        return authFacade.registrarUsuario(data);
    }

    // POST /api/scrims
    public Scrim crearScrim(DatosCreacion data) {
        return scrimFacade.crearScrim(data);
    }

    // POST /api/reportes
    public void reportarConducta(DatosReporte data) {
        moderationFacade.procesarReporte(data);
    }

    // ... (Y asi con todos los demas endpoints de la API) ...
}