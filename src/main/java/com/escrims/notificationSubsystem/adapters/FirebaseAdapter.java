package com.escrims.notificationSubsystem.adapters;

// (Clase trucha que simula la API externa)
class FirebaseAPI {
    public void sendCloudMessage(String token, String title, String body) {
        System.out.println("==================================================");
        System.out.println("PUSH (via Firebase API)");
        System.out.println("A: " + token);
        System.out.println("TITULO: " + title);
        System.out.println("CUERPO: " + body);
        System.out.println("==================================================");
    }
}

public class FirebaseAdapter implements IAdapterPush {

    private FirebaseAPI apiExterna = new FirebaseAPI();

    @Override
    public void enviarPush(String deviceToken, String message) {
        // "Traduccion"
        apiExterna.sendCloudMessage(deviceToken, "Notificacion de eScrims", message);
    }
}