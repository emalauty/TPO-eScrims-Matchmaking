package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.ScrimFinalizadoEvent;

public class EnJuegoState implements IScrimState, IFinalizable {
    // Nota: ¡Este estado no implementa ICancelable!

    @Override
    public void finalizar(Scrim scrim) {
        scrim.setState(new FinalizadoState());
        DomainEventBus.getInstance().publish(new ScrimFinalizadoEvent(scrim.getId()));
    }
}