package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del Strategy: filtra por historial
 * (ej. compatibilidad, no abandono previo).
 */
public class ByHistoryStrategy implements IMatchmakingStrategy {

    @Override
    public List<Usuario> seleccionar(List<Usuario> candidatos, Scrim scrim) {
        System.out.println("Ejecutando estrategia de matchmaking por Historial...");

        // LOGICA REAL:
        // return candidatos.stream()
        //     .filter(u -> u.getRatioAbandonos() < 0.1) // Menos de 10%
        //     .filter(u -> !u.estaBloqueadoPor(scrim.getOrganizador()))
        //     .collect(Collectors.toList());

        return candidatos; // Simulacion
    }
}