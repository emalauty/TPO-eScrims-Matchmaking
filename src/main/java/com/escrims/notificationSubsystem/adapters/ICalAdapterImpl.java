package com.escrims.notificationSubsystem.adapters;

import com.escrims.domainCore.Scrim;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.CalScale;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Version;

<<<<<<< HEAD
// ============================================================
// ¡¡ESTOS SON LOS IMPORTS QUE ARREGLAN EL ERROR!!
// ============================================================
import net.fortuna.ical4j.util.UidGenerator;
import net.fortuna.ical4j.util.RandomUidGenerator; // <-- ¡Usamos este!
// (Borramos DefaultUidGenerator y UidGeneratorFactory)
// ============================================================
=======
// ¡¡ESTE ES EL GENERADOR CONCRETO QUE DEBE FUNCIONAR!!
import net.fortuna.ical4j.util.RandomUidGenerator;
import net.fortuna.ical4j.util.UidGenerator;
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

import java.io.File;
import java.io.FileOutputStream;
import java.time.LocalDateTime;

/**
 * Implementacion del Adapter de iCal.
 */
public class ICalAdapterImpl implements ICalAdapter {

    @Override
    public File generarArchivoICS(Scrim scrim) {
        try {
            // 1. Convertir la fecha de Java a la fecha de iCal
            LocalDateTime fecha = scrim.getFechaHora();
            if (fecha == null) fecha = LocalDateTime.now().plusHours(1);

            DateTime inicio = new DateTime(fecha.atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());
            DateTime fin = new DateTime(fecha.plusHours(1).atZone(java.time.ZoneId.systemDefault()).toInstant().toEpochMilli());

            // 2. Crear el Evento de Calendario
            String nombreEvento = "Partida de eScrims: " + (scrim.getJuego() != null ? scrim.getJuego() : "Test");
            VEvent eventoScrim = new VEvent(inicio, fin, nombreEvento);

<<<<<<< HEAD
            // ============================================================
            // 3. ¡¡ARREGLO DEFINITIVO!! Usamos RandomUidGenerator
            // ============================================================
            UidGenerator ug = new RandomUidGenerator();
            eventoScrim.getProperties().add(ug.generateUid());
            // ============================================================
=======
            // 3. ¡¡ARREGLO FINAL!! Usamos la clase CONCRETA RandomUidGenerator
            UidGenerator ug = new RandomUidGenerator();
            eventoScrim.getProperties().add(ug.generateUid());
>>>>>>> ad1453e53f104d63332e08304b5af2ec07582588

            // 4. Crear el calendario
            net.fortuna.ical4j.model.Calendar calendario = new net.fortuna.ical4j.model.Calendar();
            calendario.getProperties().add(new ProdId("-//eScrims//TPO ADOO 1.0//EN"));
            calendario.getProperties().add(Version.VERSION_2_0);
            calendario.getProperties().add(CalScale.GREGORIAN);

            // 5. Agregar el evento al calendario
            calendario.getComponents().add(eventoScrim);

            // 6. Crear un archivo temporal
            File archivoTemporal = File.createTempFile("scrim-" + (scrim.getId() != null ? scrim.getId() : "temp"), ".ics");
            FileOutputStream fout = new FileOutputStream(archivoTemporal);

            // 7. Escribir el calendario en el archivo
            net.fortuna.ical4j.data.CalendarOutputter outputter = new net.fortuna.ical4j.data.CalendarOutputter();
            outputter.output(calendario, fout);
            fout.close();

            System.out.println("ICalAdapter: Archivo .ics temporal creado en: " + archivoTemporal.getAbsolutePath());
            return archivoTemporal;

        } catch (Exception e) {
            System.err.println("ICalAdapter: Error al generar archivo .ics: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}