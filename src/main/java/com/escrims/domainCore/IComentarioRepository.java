package com.escrims.domainCore;

import java.util.List;
import java.util.Optional;

// Contrato para la DB de Comentarios
public interface IComentarioRepository {
    Comentario save(Comentario comentario);
    Optional<Comentario> findById(String id);
    List<Comentario> findByScrimId(String scrimId);
}