package co.generation.clinica.datos;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class DatosCSV {
    private static final String DIR = "datos/";
    private static final String F_PACIENTES = DIR + "pacientes.csv";
    private static final String F_MEDICOS = DIR + "medicos.csv";
    private static final String F_TURNOS = DIR + "turnos.csv";
    private static final DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public static void cargar(ClinicaService servicio) {
        // Crea la carpeta 'datos' si no existe
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
                String[] p = linea.split(",", -1); // id, cedula, nombre, apellido, telefono
                
                servicio.getPacientes().add(new Paciente(
                    Integer.parseInt(p[0].trim()),
                    p[1].trim(),
                    p[2].trim(),
                    p[3].trim(),
                    p[4].trim()
                ));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar pacientes: " + e.getMessage());
        }
    }

    private static void cargarMedicos(ClinicaService servicio) {
        File f = new File(F_MEDICOS);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] m = linea.split(",", -1); // id, nombre, apellido, especialidad
                
                servicio.getMedicos().add(new Medico(
                    Integer.parseInt(m[0].trim()),
                    m[1].trim(),
                    m[2].trim(),
                    Especialidad.valueOf(m[3].trim().toUpperCase())
                ));
            }
        } catch (IOException e) {
            System.out.println("Error al cargar medicos: " + e.getMessage());
        }
    }

    private static void cargarTurnos(ClinicaService servicio) {
        File f = new File(F_TURNOS);
        if (!f.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (linea.isBlank()) continue;
                String[] t = linea.split(",", -1); // id,cedula,nombre,apellido,telefono
                
            
        } catch (IOException e) {
            System.out.println("Error al cargar turnos: " + e.getMessage());
            }
        }
    }   
}
