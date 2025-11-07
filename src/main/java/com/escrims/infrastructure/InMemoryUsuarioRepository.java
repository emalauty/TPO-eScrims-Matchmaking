package com.escrims.infrastructure;

import com.escrims.domainCore.IUsuarioRepository;
import com.escrims.domainCore.Usuario;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public class InMemoryUsuarioRepository implements IUsuarioRepository {

    private final Map<String, Usuario> db = new HashMap<>();
    private final Map<String, String> emailToId = new HashMap<>();

    @Override
    public Optional<Usuario> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public Optional<Usuario> findByEmail(String email) {
        String id = emailToId.get(email.toLowerCase());
        if (id == null) return Optional.empty();
        return findById(id);
    }

    @Override
    public Usuario save(Usuario usuario) {
        if (usuario.getId() == null) {
            usuario.setId(UUID.randomUUID().toString());
        }
        db.put(usuario.getId(), usuario);
        emailToId.put(usuario.getEmail().toLowerCase(), usuario.getId());
        System.out.println("REPOSITORIO (Usuario): Usuario guardado: " + usuario.getUsername());
        return usuario;
    }

    // --- METODO NUEVO ---
    @Override
    public Optional<Usuario> findByVerificationToken(String token) {
        // Esto es ineficiente (recorre toda la DB), pero
        // para nuestra DB falsa, funciona.
        return db.values().stream()
                .filter(u -> token.equals(u.getVerificationToken()))
                .findFirst();
    }
}