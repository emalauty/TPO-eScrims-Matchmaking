package com.escrims.notificationSubsystem;

import java.util.ArrayList;
import java.util.List;
import com.escrims.notificationSubsystem.events.DomainEvent;

/**
 * Patron Singleton (y Subject del Observer).
 * Es el "diario" central. Todos publican aca,
 * y los suscriptores leen de aca.
 */
public class DomainEventBus {

    private static DomainEventBus instance;
    private final List<ISubscriber> subscribers = new ArrayList<>();

    // Constructor privado para que nadie pueda hacer 'new'
    private DomainEventBus() {}

    /**
     * Metodo estatico para obtener la unica instancia.
     */
    public static DomainEventBus getInstance() {
        if (instance == null) {
            instance = new DomainEventBus();
        }
        return instance;
    }

    public void subscribe(ISubscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void publish(DomainEvent event) {
        // Notifica a todos los suscriptores
        System.out.println("EVENT_BUS: Publicando evento: " + event.getClass().getSimpleName());
        for (ISubscriber s : subscribers) {
            s.onEvent(event);
        }
    }
}