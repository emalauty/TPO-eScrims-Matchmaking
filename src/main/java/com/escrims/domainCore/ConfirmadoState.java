package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.ScrimIniciadoEvent;

public class ConfirmadoState implements IScrimState, IIniciable, ICancelable {

    @Override
    public void iniciar(Scrim scrim) {
        scrim.setState(new EnJuegoState());
        DomainEventBus.getInstance().publish(new ScrimIniciadoEvent(scrim.getId()));
    }

    @Override
    public void cancelar(Scrim scrim) {
        scrim.setState(new CanceladoState());
        // DomainEventBus.getInstance().publish(new ScrimCanceladoEvent(scrim.getId()));
    }
}