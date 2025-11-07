package com.escrims.notificationSubsystem.adapters;

// (Clase trucha que simula la API externa)
class DiscordWebhookAPI {
    public void executeWebhook(String url, String jsonPayload) {
        System.out.println("==================================================");
        System.out.println("CHAT (via Discord Webhook)");
        System.out.println("URL: " + url);
        System.out.println("PAYLOAD: " + jsonPayload);
        System.out.println("==================================================");
    }
}

public class DiscordAdapter implements IAdapterChat {

    private DiscordWebhookAPI apiExterna = new DiscordWebhookAPI();

    @Override
    public void enviarMensajeWebhook(String webhookURL, String messageContent) {
        // "Traduccion" (ej. crear el JSON que Discord espera)
        String jsonPayload = "{ \"content\": \"" + messageContent + "\" }";
        apiExterna.executeWebhook(webhookURL, jsonPayload);
    }
}