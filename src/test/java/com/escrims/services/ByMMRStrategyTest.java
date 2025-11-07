package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

/**
 * Test Unitario para el Patron Strategy.
 * Prueba que el algoritmo de ByMMRStrategy filtre correctamente.
 */
public class ByMMRStrategyTest {

    private IMatchmakingStrategy strategy;
    private Scrim scrimRangoMedio;
    private List<Usuario> poolDeCandidatos;

    // Metodo helper para crear jugadores de prueba
    private Usuario crearJugador(String nombre, int mmr) {
        Usuario u = new Usuario();
        u.setUsername(nombre);
        u.setMmr(mmr);
        return u;
    }

    // Esto se ejecuta antes de CADA test
    @BeforeEach
    public void setUp() {
        // 1. Creamos la estrategia a probar
        strategy = new ByMMRStrategy();

        // 2. Creamos un Scrim que busca entre 800 y 1200 MMR
        scrimRangoMedio = new Scrim();
        scrimRangoMedio.setRangoMin(800);
        scrimRangoMedio.setRangoMax(1200);

        // 3. Creamos un pool de jugadores de todos los niveles
        poolDeCandidatos = new ArrayList<>();
        poolDeCandidatos.add(crearJugador("Bronce", 500)); // (Fuera de rango)
        poolDeCandidatos.add(crearJugador("Plata", 900));  // <-- Deberia entrar
        poolDeCandidatos.add(crearJugador("Oro", 1100));   // <-- Deberia entrar
        poolDeCandidatos.add(crearJugador("Platino", 1500)); // (Fuera de rango)
    }

    @Test
    public void test_EstrategiaMMR_SeleccionaJugadoresEnRango() {
        // Ejecutamos la estrategia
        List<Usuario> seleccionados = strategy.seleccionar(poolDeCandidatos, scrimRangoMedio);

        // Verificamos que solo esten los 2 jugadores correctos
        assertEquals(2, seleccionados.size(), "Deberia haber seleccionado solo 2 jugadores.");

        // Verificamos que esten los que tienen que estar
        assertTrue(
                seleccionados.stream().anyMatch(u -> u.getUsername().equals("Plata")),
                "El jugador 'Plata' deberia estar en la lista."
        );
        assertTrue(
                seleccionados.stream().anyMatch(u -> u.getUsername().equals("Oro")),
                "El jugador 'Oro' deberia estar en la lista."
        );
    }

    @Test
    public void test_EstrategiaMMR_ExcluyeJugadoresFueraDeRango() {
        // Ejecutamos
        List<Usuario> seleccionados = strategy.seleccionar(poolDeCandidatos, scrimRangoMedio);

        // Verificamos que los 2 incorrectos NO esten
        assertFalse(
                seleccionados.stream().anyMatch(u -> u.getUsername().equals("Bronce")),
                "El jugador 'Bronce' NO deberia estar en la lista."
        );
        assertFalse(
                seleccionados.stream().anyMatch(u -> u.getUsername().equals("Platino")),
                "El jugador 'Platino' NO deberia estar en la lista."
        );
    }

    @Test
    public void test_EstrategiaMMR_DevuelveVacioSiNadieCumple() {
        // Creamos un pool donde nadie cumple
        List<Usuario> poolDificil = new ArrayList<>();
        poolDificil.add(crearJugador("Bronce", 500));
        poolDificil.add(crearJugador("Platino", 1500));

        // Ejecutamos
        List<Usuario> seleccionados = strategy.seleccionar(poolDificil, scrimRangoMedio);

        // Verificamos que la lista este vacia
        assertTrue(seleccionados.isEmpty(), "La lista de seleccionados deberia estar vacia.");
    }
}