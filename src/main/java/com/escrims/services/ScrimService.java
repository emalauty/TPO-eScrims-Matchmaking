package com.escrims.services;

import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.IScrimRepository;
import java.util.Optional;

/**
 * Servicio principal para la logica de negocio de Scrims.
 * Es el "Invocador" del Patron Command.
 * Orquesta la creacion (Builder) y guardado (Repository).
 */
public class ScrimService {

    private final IScrimRepository scrimRepo;

    // Se "inyecta" el repositorio (la BD)
    public ScrimService(IScrimRepository repo) {
        this.scrimRepo = repo;
    }

    /**
     * Ejecuta un comando sobre un Scrim.
     */
// En: ScrimService.java

    public void ejecutarComando(String scrimId, IScrimCommand comando) {

        // 1. Buscar el Scrim
        // Aca estaba el error: usaba 'id' en vez de 'scrimId'
        Scrim scrim = scrimRepo.findById(scrimId)
                .orElseThrow(() -> new RuntimeException("Scrim no encontrado: " + scrimId)); // <-- ¡ARREGLADO!

        // 2. Ejecutar
        comando.execute(scrim);

        // 3. Guardar
        scrimRepo.save(scrim);
        System.out.println("Comando ejecutado y scrim guardado.");
    }

    /**
     * Guarda un Scrim nuevo (creado por el Builder).
     * ¡¡ESTE METODO ESTA AHORA CORREGIDO!!
     */
    public Scrim guardar(Scrim scrim) {
        System.out.println("Servicio: Guardando nuevo scrim...");

        // ESTA ES LA LINEA QUE ARREGLA TODO:
        // Llama al repositorio, que le asigna el ID y lo devuelve.
        return scrimRepo.save(scrim);

        // (La linea "rota" que yo te habia dado era: return scrim;)
    }
}