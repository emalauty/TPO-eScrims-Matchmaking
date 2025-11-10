package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
<<<<<<< HEAD
import com.escrims.notificationSubsystem.events.PlazaDisponibleEvent; // ¡IMPORT NUEVO!
import com.escrims.notificationSubsystem.events.ScrimConfirmadoEvent;

// ¡Implementa la nueva interfaz!
public class LobbyArmadoState implements IScrimState, IConfirmable, ICancelable, IBajarseDelScrim {

    @Override
    public void confirmar(Scrim scrim, Usuario usuario) {
        // ... (Logica de confirmacion) ...
=======
import com.escrims.notificationSubsystem.events.ScrimConfirmadoEvent;

public class LobbyArmadoState implements IScrimState, IConfirmable, ICancelable {

    @Override
    public void confirmar(Scrim scrim, Usuario usuario) {
        // ... (Logica para confirmar al usuario) ...
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
        boolean todosConfirmaron = true; // (Simulacion)

        if (todosConfirmaron) {
            scrim.setState(new ConfirmadoState());
            DomainEventBus.getInstance().publish(new ScrimConfirmadoEvent(scrim.getId()));
        }
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
        System.out.println("LOBBY_ARMADO_STATE: Jugador " + usuario.getUsername() + " se bajo del scrim.");

        // 1. Quitar al jugador de la lista de postulados/confirmados
        // ... (scrim.getPostulaciones().removeIf(p -> p.getUsuario().equals(usuario))) ...
        // ... (scrim.getConfirmaciones().removeIf(c -> c.getUsuario().equals(usuario))) ...

        // 2. ¡Volvemos al estado anterior!
        scrim.setState(new BuscandoState());

        // 3. Publicamos el evento para que el SuplenteSubscriber se entere
        DomainEventBus.getInstance().publish(new PlazaDisponibleEvent(scrim));
    }
}
=======
        // DomainEventBus.getInstance().publish(new ScrimCanceladoEvent(scrim.getId()));
    }
}
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
