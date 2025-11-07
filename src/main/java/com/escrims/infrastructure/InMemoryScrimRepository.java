package com.escrims.infrastructure;

import com.escrims.domainCore.IScrimRepository;
import com.escrims.domainCore.Scrim;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * Implementacion CONCRETA del Patron Repository.
 * Simula una base de datos usando un HashMap en memoria.
 * ¡ESTA ES LA VERSION QUE ASIGNA IDs!
 */
public class InMemoryScrimRepository implements IScrimRepository {

    private final Map<String, Scrim> database = new HashMap<>();

    @Override
    public Optional<Scrim> findById(String id) {
        return Optional.ofNullable(database.get(id));
    }

    @Override
    public Scrim save(Scrim scrim) {

        // ============================================================
        // ¡¡ESTA ES LA LOGICA QUE ARREGLA EL BUG!!
        // ============================================================
        if (scrim.getId() == null) {
            String newId = UUID.randomUUID().toString();
            // Esta linea usa el setId() que ya agregamos a Scrim.java
            scrim.setId(newId);
        }

        // Guarda o actualiza el scrim en el mapa
        database.put(scrim.getId(), scrim);

        // ¡¡ESTE ES EL LOG QUE FALTABA EN TU CONSOLA!!
        System.out.println("REPOSITORIO: Scrim guardado/actualizado con ID: " + scrim.getId());

        return scrim;
    }

    public void printDatabase() {
        System.out.println("--- Contenido de la DB Falsa ---");
        database.forEach((id, scrim) -> System.out.println("ID: " + id + " | Estado: " + scrim.getEstadoActual().getClass().getSimpleName()));
        System.out.println("---------------------------------");
    }
}