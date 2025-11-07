package com.escrims.domainCore;

import java.util.Optional;

// El "contrato" de persistencia vive en el dominio
public interface IScrimRepository {
    Optional<Scrim> findById(String id);
    Scrim save(Scrim scrim);
}