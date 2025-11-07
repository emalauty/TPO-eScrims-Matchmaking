package com.escrims.services;

import com.escrims.domainCore.Rol;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;

/**
 * Comando concreto para asignar un rol a un jugador.
 */
public class AsignarRolCommand implements IScrimCommand {

    // El comando "sabe" todo lo necesario para ejecutarse
    private final Usuario jugador;
    private final Rol nuevoRol;
    private Rol rolAntiguo; // Para poder hacer undo()

    public AsignarRolCommand(Usuario jugador, Rol nuevoRol) {
        this.jugador = jugador;
        this.nuevoRol = nuevoRol;
    }

    @Override
    public void execute(Scrim scrim) {
        // La logica real buscaria al jugador en los equipos del scrim
        // y guardaria su rol anterior.
        // this.rolAntiguo = scrim.getEquipoDelJugador(jugador).getRol(jugador);
        // scrim.getEquipoDelJugador(jugador).setRol(jugador, nuevoRol);
        System.out.println("EJECUTANDO: Asignar rol " + nuevoRol.getNombre() + " a " + jugador.getUsername());
    }

    @Override
    public void undo(Scrim scrim) {
        if (rolAntiguo == null) {
            System.out.println("UNDO: No se puede deshacer, no se ejecuto primero.");
            return;
        }
        // Logica para revertir
        // scrim.getEquipoDelJugador(jugador).setRol(jugador, rolAntiguo);
        System.out.println("UNDO: Devolviendo a " + jugador.getUsername() + " al rol " + rolAntiguo.getNombre());
    }
}