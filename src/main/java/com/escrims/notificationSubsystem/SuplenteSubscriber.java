package com.escrims.notificationSubsystem;

import com.escrims.application.TestContext;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import com.escrims.notificationSubsystem.events.DomainEvent;
import com.escrims.notificationSubsystem.events.PlazaDisponibleEvent;

/**
 * Patron Observer (Implementacion Concreta 3).
 * Escucha eventos de 'PlazaDisponibleEvent' y notifica
 * al primer suplente de la lista de espera.
 */
public class SuplenteSubscriber implements ISubscriber {

    private final INotifierFactory factory;

    public SuplenteSubscriber(INotifierFactory factory) {
        this.factory = factory;
    }

    @Override
    public void onEvent(DomainEvent event) {

        // Este subscriber SOLO reacciona a 'PlazaDisponibleEvent'
        if (!(event instanceof PlazaDisponibleEvent)) {
            return;
        }

        PlazaDisponibleEvent e = (PlazaDisponibleEvent) event;
        Scrim scrim = e.getScrim();

        System.out.println("SUPLENTE_SUBSCRIBER: Plaza disponible en Scrim " + scrim.getId() + ". Buscando suplentes...");

        // 1. Chequeamos si hay alguien en la lista de espera
        if (scrim.getListaDeEspera() == null || scrim.getListaDeEspera().isEmpty()) {
            System.out.println("SUPLENTE_SUBSCRIBER: No hay suplentes en lista de espera.");
            return;
        }

        // 2. Obtenemos al primer suplente
        // (En un sistema real, el ScrimService haria la logica de "promover" al suplente)
        Usuario primerSuplente = scrim.getListaDeEspera().get(0);

        System.out.println("SUPLENTE_SUBSCRIBER: ¡Suplente encontrado! (" + primerSuplente.getUsername() + "). Enviando notificacion.");

        // Usamos el "buzon" de prueba para la demo
        String emailDePrueba = TestContext.getInstance().getEmailDePrueba();

        // 3. Construimos la notificacion
        Notificacion n = new Notificacion();
        n.setId("noti-suplente-" + scrim.getId());
        n.setDestinatario(emailDePrueba); // (En la vida real: primerSuplente.getEmail())
        n.setPayload("¡Una plaza se libero en el Scrim " + scrim.getId() + " (" + scrim.getJuego() + ")!\n\n" +
                "¡Apresurate y postulate ahora!");

        // 4. Usamos la Factory y el Decorator (reutilizamos todo)
        IMetodoNotificacion emailer = new LoggingNotifierDecorator(factory.createEmail());
        emailer.notificar(n);
    }
}