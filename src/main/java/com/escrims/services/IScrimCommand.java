package com.escrims.services;

import com.escrims.domainCore.Scrim;

/**
 * Contrato del Patron Command.
 * Encapsula una accion que se puede ejecutar y (opcionalmente) deshacer.
 */
public interface IScrimCommand {
    void execute(Scrim scrim);
    void undo(Scrim scrim);
}