package com.escrims.notificationSubsystem.events;

import com.escrims.domainCore.Scrim;

/**
 * Evento que se publica cuando un Scrim
 * es creado exitosamente.
<<<<<<< HEAD
 * (Para el requisito de Alertas )
=======
 * (Para el requisito de Alertas)
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
 */
public class ScrimCreadoEvent implements DomainEvent {

    // Pasamos el Scrim entero, porque el Subscriber
    // necesita chequear el juego, rango, region, etc.
    private final Scrim scrimCreado;

    public ScrimCreadoEvent(Scrim scrimCreado) {
        this.scrimCreado = scrimCreado;
    }

    public Scrim getScrimCreado() {
        return scrimCreado;
    }
}