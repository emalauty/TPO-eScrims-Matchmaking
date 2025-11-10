package com.escrims.infrastructure;

import com.escrims.domainCore.Comentario;
import com.escrims.domainCore.IComentarioRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

// DB Falsa para Comentarios
public class InMemoryComentarioRepository implements IComentarioRepository {

    private final Map<String, Comentario> db = new HashMap<>();

    @Override
    public Comentario save(Comentario comentario) {
        if (comentario.getId() == null) {
            comentario.setId(UUID.randomUUID().toString());
        }
        db.put(comentario.getId(), comentario);
        System.out.println("REPOSITORIO (Comentario): Comentario guardado: " + comentario.getId());
        return comentario;
    }

    @Override
    public Optional<Comentario> findById(String id) {
        return Optional.ofNullable(db.get(id));
    }

    @Override
    public List<Comentario> findByScrimId(String scrimId) {
        return db.values().stream()
                .filter(c -> c.getScrim().getId().equals(scrimId))
                .collect(Collectors.toList());
    }
}