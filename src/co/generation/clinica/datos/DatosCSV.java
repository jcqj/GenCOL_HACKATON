package co.generation.clinica.datos;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DatosCSV {
    private static final String DIR = "datos/";
    private static final String F_PACIENTES = DIR + "pacientes.csv";
    private static final String F_MEDICOS = DIR + "medicos.csv";
    private static final String F_TURNOS = DIR + "turnos.csv";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void cargar(ClinicaService servicio) {
        new File(DIR).mkdirs();
        cargarPacientes(servicio);
        cargarMedicos(servicio);
        cargarTurnos(servicio);
    }

    private static void cargarPacientes(ClinicaService servicio) {
        File f = new File(F_PACIENTES);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] p = linea.split(",", -1);
                servicio.getPacientes().add(new Paciente(
                    Integer.parseInt(p[0].trim()),
                    p[1].trim(), p[2].trim(), p[3].trim(), p[4].trim()
                ));
            }
        } catch (IOException e) {
            System.out.println("Error en pacientes: " + e.getMessage());
        }
    }

    private static void cargarMedicos(ClinicaService servicio) {
        File f = new File(F_MEDICOS);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] m = linea.split(",", -1);
                servicio.getMedicos().add(new Medico(
                    Integer.parseInt(m[0].trim()),
                    m[1].trim(), m[2].trim(),
                    Especialidad.valueOf(m[3].trim().toUpperCase())
                ));
            }
        } catch (IOException e) {
            System.out.println("Error en medicos: " + e.getMessage());
        }
    }

    private static void cargarTurnos(ClinicaService servicio) {
        File f = new File(F_TURNOS);
        if (!f.exists()) return;
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] t = linea.split(",", -1);
                
                // Buscamos los objetos para que el turno no sea solo texto
                Paciente pac = servicio.buscarPacientePorId(Integer.parseInt(t[0].trim()));
                Medico med = servicio.buscarMedicoPorId(Integer.parseInt(t[1].trim()));

                if (pac != null && med != null) {
                    LocalDateTime fecha = LocalDateTime.parse(t[2].trim(), FMT);
                    EstadoTurno estado = EstadoTurno.valueOf(t[3].trim().toUpperCase());
                    
                    // Aquí se crea el objeto Turno real y se guarda en la lista
                    servicio.getTurnos().add(new Turno(pac, med, fecha, estado));
                }
            }
        } catch (IOException e) {
            System.out.println("Error en turnos: " + e.getMessage());
        }
    }
}