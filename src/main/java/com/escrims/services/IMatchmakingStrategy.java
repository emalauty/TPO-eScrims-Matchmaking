package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import java.util.List;

/**
 * Contrato del Patron Strategy[cite: 43].
 * Define un algoritmo intercambiable para el matchmaking.
 */
public interface IMatchmakingStrategy {
    List<Usuario> seleccionar(List<Usuario> candidatos, Scrim scrim);
}