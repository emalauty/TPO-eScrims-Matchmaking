package com.escrims.notificationSubsystem;

import com.escrims.notificationSubsystem.adapters.DiscordAdapter;
import com.escrims.notificationSubsystem.adapters.FirebaseAdapter;
import com.escrims.notificationSubsystem.adapters.SendGridAdapter;

/**
 * Implementacion CONCRETA de la fabrica.
 * Esta crea los notificadores REALES que hablan
 * con APIs externas (SendGrid, Firebase...).
 */
public class ProdNotifierFactory implements INotifierFactory {

    @Override
    public IMetodoNotificacion createEmail() {
        // La fabrica conoce al Adapter y se lo "inyecta" al Notificador
        return new EmailNotifier(new SendGridAdapter());
    }

    @Override
    public IMetodoNotificacion createPush() {
        return new PushNotifier(new FirebaseAdapter());
    }

    @Override
    public IMetodoNotificacion createChat() {
        return new ChatNotifier(new DiscordAdapter());
    }
}