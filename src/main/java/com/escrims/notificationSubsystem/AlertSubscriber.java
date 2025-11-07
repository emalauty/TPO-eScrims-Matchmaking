package com.escrims.notificationSubsystem;

import com.escrims.application.TestContext;
import com.escrims.domainCore.Scrim;
import com.escrims.notificationSubsystem.events.DomainEvent;
import com.escrims.notificationSubsystem.events.ScrimCreadoEvent;
// (Necesitariamos un 'BusquedaGuardadaRepository' para buscar las alertas)

/**
 * Patron Observer (Implementacion Concreta 2).
 * Escucha eventos de 'ScrimCreado' y los compara
 * contra las busquedas guardadas de los usuarios.
 * (Requisito: "Guardar busquedas favoritas y crear alertas")
 */
public class AlertSubscriber implements ISubscriber {

    private final INotifierFactory factory;
    // private final BusquedaGuardadaRepository busquedaRepo;

    public AlertSubscriber(INotifierFactory factory /*, BusquedaGuardadaRepository repo */) {
        this.factory = factory;
        // this.busquedaRepo = repo;
    }

    @Override
    public void onEvent(DomainEvent event) {

        // Este subscriber SOLO reacciona a eventos 'ScrimCreadoEvent'
        if (!(event instanceof ScrimCreadoEvent)) {
            return; // No es mi tipo de evento, no hago nada
        }

        ScrimCreadoEvent e = (ScrimCreadoEvent) event;
        Scrim scrimNuevo = e.getScrimCreado();

        System.out.println("ALERTS_SUBSCRIBER: Nuevo Scrim detectado (" + scrimNuevo.getJuego() + "). Chequeando alertas...");

        // --- LOGICA DE ALERTA (Simulada) ---
        // 1. Iriamos a la base de datos a buscar todas las "Busquedas Guardadas"
        // List<Busqueda> alertas = busquedaRepo.findAll();

        // 2. Simulamos una alerta que coincide
        boolean coincideConAlerta = true;

        if (coincideConAlerta) {
            System.out.println("ALERTS_SUBSCRIBER: ¡Coincidencia encontrada! Enviando notificacion de alerta.");

            // 3. Construimos la notificacion
            Notificacion n = new Notificacion();
            n.setId("noti-alerta-" + scrimNuevo.getId());
            n.setDestinatario(TestContext.getInstance().getEmailDePrueba()); // Usamos el "buzon" de prueba
            n.setPayload("¡Un Scrim que te interesa se acaba de crear! Juego: " + scrimNuevo.getJuego());

            // 4. Usamos la Factory y el Decorator (reutilizamos todo)
            IMetodoNotificacion emailer = new LoggingNotifierDecorator(factory.createEmail());
            emailer.notificar(n);
        }
    }
}