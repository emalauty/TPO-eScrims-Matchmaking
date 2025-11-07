package com.escrims.notificationSubsystem.events;

import com.escrims.domainCore.Usuario;

/**
 * Evento que se publica cuando un Usuario
 * completa el registro.
 * (Para el requisito de Verificacion de Email)
 */
public class UsuarioRegistradoEvent implements DomainEvent {

    private final Usuario usuarioRegistrado;

    public UsuarioRegistradoEvent(Usuario usuario) {
        this.usuarioRegistrado = usuario;
    }

    public Usuario getUsuario() {
        return usuarioRegistrado;
    }
}