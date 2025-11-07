package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del Strategy: filtra por latencia.
 */
public class ByLatencyStrategy implements IMatchmakingStrategy {

    @Override
    public List<Usuario> seleccionar(List<Usuario> candidatos, Scrim scrim) {
        System.out.println("Ejecutando estrategia de matchmaking por Latencia...");

        // LOGICA REAL:
        // int latenciaMaxima = scrim.getLatenciaMaxima();
        // return candidatos.stream()
        //     .filter(u -> u.getLatenciaPromedio() <= latenciaMaxima)
        //     .collect(Collectors.toList());

        return candidatos; // Simulacion
    }
}