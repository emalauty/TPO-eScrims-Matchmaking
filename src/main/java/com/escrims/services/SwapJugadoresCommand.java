package com.escrims.services;

import com.escrims.domainCore.Equipo;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;

/**
 * Comando concreto para intercambiar dos jugadores
 * entre equipos (ej. Equipo A <-> Equipo B).
 */
public class SwapJugadoresCommand implements IScrimCommand {

    private final Usuario jugadorA;
    private final Usuario jugadorB;
    // No necesitamos 'undo' data porque el swap es su propio undo

    public SwapJugadoresCommand(Usuario jugadorA, Usuario jugadorB) {
        this.jugadorA = jugadorA;
        this.jugadorB = jugadorB;
    }

    @Override
    public void execute(Scrim scrim) {
        System.out.println("EJECUTANDO: Swap entre " + jugadorA.getUsername() + " y " + jugadorB.getUsername());

        // LOGICA REAL:
        // Equipo equipoA = scrim.getEquipoDelJugador(jugadorA);
        // Equipo equipoB = scrim.getEquipoDelJugador(jugadorB);

        // if (equipoA != null && equipoB != null && equipoA != equipoB) {
        //     equipoA.removeJugador(jugadorA);
        //     equipoB.removeJugador(jugadorB);
        //     equipoA.addJugador(jugadorB);
        //     equipoB.addJugador(jugadorA);
        // } else {
        //     throw new IllegalStateException("No se pueden swapear jugadores del mismo equipo o que no estan en el scrim.");
        // }
    }

    @Override
    public void undo(Scrim scrim) {
        // Un swap es su propia operacion de undo.
        System.out.println("UNDO: Swapeando de vuelta a " + jugadorA.getUsername() + " y " + jugadorB.getUsername());
        execute(scrim); // Volvemos a ejecutar el mismo swap
    }
}