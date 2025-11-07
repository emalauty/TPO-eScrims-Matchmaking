package com.escrims.application;

import com.escrims.domainCore.ReporteConducta;
import com.escrims.domainCore.Scrim;
import com.escrims.domainCore.Usuario;
import com.escrims.domainCore.IScrimRepository;
// (Necesitariamos un IUsuarioRepository y IReporteRepository)

import com.escrims.moderation.IModerationHandler;

/**
 * Fachada para todo lo relacionado a Moderacion.
 * Utiliza el patron Chain of Responsibility.
 */
public class ModerationFacade {

    // Tiene el "inicio" de la cadena de responsabilidad
    private final IModerationHandler moderationChainHead;

    // Necesita los repositorios para buscar los objetos
    private final IScrimRepository scrimRepo;
    // private final IUsuarioRepository usuarioRepo;
    // private final IReporteRepository reporteRepo;

    public ModerationFacade(IModerationHandler moderationChainHead,
                            IScrimRepository scrimRepo /*, IUsuarioRepository usuarioRepo, IReporteRepository reporteRepo */) {
        this.moderationChainHead = moderationChainHead;
        this.scrimRepo = scrimRepo;
        // this.usuarioRepo = usuarioRepo;
        // this.reporteRepo = reporteRepo;
    }

    public void procesarReporte(DatosReporte data) {
        System.out.println("FACADE (Moderation): Procesando reporte...");

        // 1. Buscamos los objetos de dominio reales usando los IDs
        // (Simulamos la busqueda si los repos no estan)
        Scrim scrim = scrimRepo.findById(data.getScrimId()).orElseThrow(() -> new RuntimeException("Scrim no encontrado"));
        Usuario reportador = new Usuario(); // usuarioRepo.findById(data.getReportadorId()).orElseThrow();
        Usuario reportado = new Usuario(); // usuarioRepo.findById(data.getReportadoId()).orElseThrow();
        String motivo = data.getMotivo();

        // 2. Crear el objeto de dominio
        ReporteConducta reporte = new ReporteConducta(scrim, reportador, reportado, motivo);

        // 3. Iniciar la cadena
        boolean manejado = moderationChainHead.handleReport(reporte);

        // 4. Guardar el resultado (el reporte fue modificado por la cadena)
        // reporteRepo.save(reporte);
        System.out.println("FACADE (Moderation): Reporte procesado. Estado final: " + reporte.getEstado() + ". Manejado: " + manejado);
    }
}