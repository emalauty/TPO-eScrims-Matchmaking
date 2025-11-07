package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.ScrimConfirmadoEvent;

public class LobbyArmadoState implements IScrimState, IConfirmable, ICancelable {

    @Override
    public void confirmar(Scrim scrim, Usuario usuario) {
        // ... (Logica para confirmar al usuario) ...
        boolean todosConfirmaron = true; // (Simulacion)

        if (todosConfirmaron) {
            scrim.setState(new ConfirmadoState());
            DomainEventBus.getInstance().publish(new ScrimConfirmadoEvent(scrim.getId()));
        }
    }

    @Override
    public void cancelar(Scrim scrim) {
        scrim.setState(new CanceladoState());
        // DomainEventBus.getInstance().publish(new ScrimCanceladoEvent(scrim.getId()));
    }
}
