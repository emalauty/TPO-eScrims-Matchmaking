package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.LobbyCompletoEvent;

public class BuscandoState implements IScrimState, IPostulable, ICancelable {

    @Override
    public void postular(Scrim scrim, Usuario usuario, Rol rol) {
        // ... (Logica para agregar postulacion) ...
        boolean cupoCompleto = true; // (Simulacion)

        if (cupoCompleto) {
            scrim.setState(new LobbyArmadoState());
            DomainEventBus.getInstance().publish(new LobbyCompletoEvent(scrim.getId()));
        }
    }

    @Override
    public void cancelar(Scrim scrim) {
        scrim.setState(new CanceladoState());
        // DomainEventBus.getInstance().publish(new ScrimCanceladoEvent(scrim.getId()));
    }
}