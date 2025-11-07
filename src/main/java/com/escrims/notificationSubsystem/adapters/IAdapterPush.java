package com.escrims.notificationSubsystem.adapters;

public interface IAdapterPush {
    void enviarPush(String deviceToken, String message);
}