package com.escrims.domainCore;

import java.util.Optional;

public interface IUsuarioRepository {
    Optional<Usuario> findById(String id);
    Optional<Usuario> findByEmail(String email);
<<<<<<< HEAD
    Optional<Usuario> findByVerificationToken(String token);
    Usuario save(Usuario usuario);

    // --- METODO NUEVO ---
    Optional<Usuario> findByUsername(String username);
=======
    Usuario save(Usuario usuario);

    // --- METODO NUEVO ---
    Optional<Usuario> findByVerificationToken(String token);
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588
}