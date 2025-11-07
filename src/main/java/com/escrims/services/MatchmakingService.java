package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import java.util.List;

/**
 * Servicio "Contexto" del Patron Strategy.
 * Su trabajo es EJECUTAR una estrategia de matchmaking,
 * pero no sabe CUAL.
 */
public class MatchmakingService {

    // El servicio "tiene una" estrategia, pero no sabe cual es.
    // Se la inyectamos.
    private IMatchmakingStrategy estrategia;

    public MatchmakingService(IMatchmakingStrategy estrategia) {
        this.estrategia = estrategia;
    }

    /**
     * Permite cambiar la estrategia en caliente (ej. si el usuario
     * cambia "buscar por MMR" a "buscar por Latencia").
     */
    public void setEstrategia(IMatchmakingStrategy nuevaEstrategia) {
        this.estrategia = nuevaEstrategia;
    }

    /**
     * Metodo principal: delega el trabajo pesado a la estrategia
     * que tenga configurada en ese momento.
     */
    public List<Usuario> encontrarPartida(List<Usuario> candidatos, Scrim scrim) {
        System.out.println("MatchmakingService: Buscando partida con " + estrategia.getClass().getSimpleName());

        // Delega la llamada
        return estrategia.seleccionar(candidatos, scrim);
    }
}