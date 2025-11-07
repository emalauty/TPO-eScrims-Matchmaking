package com.escrims.domainCore;

import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findById(String id);
    Optional<Usuario> findByEmail(String email);
    Usuario save(Usuario usuario);

    // --- METODO NUEVO ---
    Optional<Usuario> findByVerificationToken(String token);
}