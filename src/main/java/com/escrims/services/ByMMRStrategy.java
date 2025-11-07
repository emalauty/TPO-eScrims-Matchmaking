package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implementacion del Strategy: filtra por rango/MMR.
 * ¡AHORA CON LOGICA REAL!
 */
public class ByMMRStrategy implements IMatchmakingStrategy {

    @Override
    public List<Usuario> seleccionar(List<Usuario> candidatos, Scrim scrim) {

        // Comentario directo: log para ver que rangos esta buscando
        System.out.println("Estrategia: Buscando jugadores entre " +
                scrim.getRangoMin() + " y " + scrim.getRangoMax() + " MMR.");

        // ¡Logica de filtro real!
        List<Usuario> filtrados = candidatos.stream()
                .filter(usuario ->
                        usuario.getMmr() >= scrim.getRangoMin() &&
                                usuario.getMmr() <= scrim.getRangoMax()
                )
                .collect(Collectors.toList());

        System.out.println("Estrategia: Se encontraron " + filtrados.size() + " jugadores compatibles.");
        return filtrados;
    }
}