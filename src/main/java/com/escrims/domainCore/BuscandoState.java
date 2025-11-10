package com.escrims.domainCore;

import com.escrims.notificationSubsystem.DomainEventBus;
import com.escrims.notificationSubsystem.events.LobbyCompletoEvent;

public class BuscandoState implements IScrimState, IPostulable, ICancelable {

<<<<<<< HEAD
    // (Simulamos el cupo maximo)
    private static final int CUPO_MAXIMO = 10;

    @Override
    public void postular(Scrim scrim, Usuario usuario, Rol rol) {

        // Regla: No se puede postular si ya esta en la lista
        if (scrim.getPostulaciones().stream().anyMatch(p -> p.getUsuario().equals(usuario))) {
            System.out.println("BUSCANDO_STATE: El usuario " + usuario.getUsername() + " ya esta postulado.");
            return; // Ya esta adentro
        }

        // 1. Validar reglas de negocio (ej. rango)
        // ... (logica de validacion) ...

        // 2. Agregar postulacion
        Postulacion p = new Postulacion(usuario, scrim, rol);
        scrim.getPostulaciones().add(p);
        System.out.println("BUSCANDO_STATE: Usuario " + usuario.getUsername() + " postulado. Cupo: " + scrim.getPostulaciones().size() + "/" + CUPO_MAXIMO);

        // 3. Chequear si se lleno el cupo
        if (scrim.getPostulaciones().size() >= CUPO_MAXIMO) {
            System.out.println("BUSCANDO_STATE: ¡Cupo completo!");
=======
    @Override
    public void postular(Scrim scrim, Usuario usuario, Rol rol) {
        // ... (Logica para agregar postulacion) ...
        boolean cupoCompleto = true; // (Simulacion)

        if (cupoCompleto) {
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
            scrim.setState(new LobbyArmadoState());
            DomainEventBus.getInstance().publish(new LobbyCompletoEvent(scrim.getId()));
        }
    }

    @Override
    public void cancelar(Scrim scrim) {
        scrim.setState(new CanceladoState());
<<<<<<< HEAD
=======
        // DomainEventBus.getInstance().publish(new ScrimCanceladoEvent(scrim.getId()));
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
    }
}