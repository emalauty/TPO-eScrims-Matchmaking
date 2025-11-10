package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
<<<<<<< HEAD
import com.escrims.notificationSubsystem.events.PlazaDisponibleEvent; // ¡IMPORT NUEVO!
import com.escrims.notificationSubsystem.events.ScrimIniciadoEvent;

// ¡Implementa la nueva interfaz!
public class ConfirmadoState implements IScrimState, IIniciable, ICancelable, IBajarseDelScrim {
=======
import com.escrims.notificationSubsystem.events.ScrimIniciadoEvent;

public class ConfirmadoState implements IScrimState, IIniciable, ICancelable {
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

    @Override
    public void iniciar(Scrim scrim) {
        scrim.setState(new EnJuegoState());
        DomainEventBus.getInstance().publish(new ScrimIniciadoEvent(scrim.getId()));
    }

    @Override
    public void cancelar(Scrim scrim) {
        scrim.setState(new CanceladoState());
<<<<<<< HEAD
    }

    // ============================================================
    // ¡¡NUEVO METODO IMPLEMENTADO!!
    // ============================================================
    @Override
    public void bajarJugador(Scrim scrim, Usuario usuario) {
        System.out.println("CONFIRMADO_STATE: Jugador " + usuario.getUsername() + " se bajo del scrim (¡No-Show!).");

        // 1. Quitar al jugador de la lista
        // ... (logica) ...

        // 2. ¡Volvemos al estado Buscando!
        scrim.setState(new BuscandoState());

        // 3. Publicamos el evento para que el SuplenteSubscriber se entere
        DomainEventBus.getInstance().publish(new PlazaDisponibleEvent(scrim));
=======
        // DomainEventBus.getInstance().publish(new ScrimCanceladoEvent(scrim.getId()));
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }
}