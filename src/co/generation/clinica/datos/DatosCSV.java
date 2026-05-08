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
                // formato: id,cedulaPaciente,nombreMedico,apellidoMedico,fechaHora,estado
                String[] t = linea.split(",", -1);
                Paciente pac = servicio.buscarPorCedula(t[1].trim());
                Medico med = servicio.buscarPorNombreApellido(t[2].trim(), t[3].trim());

                if (pac != null && med != null) {
                    LocalDateTime fecha = LocalDateTime.parse(t[4].trim(), FMT);
                    EstadoTurno estado = EstadoTurno.valueOf(t[5].trim().toUpperCase());
                    servicio.getTurnos().add(new Turno(
                            Integer.parseInt(t[0].trim()), pac, med, fecha, estado));
                }
            }
        } catch (IOException e) {
            System.out.println("Error en turnos: " + e.getMessage());
        }
    }

    public static void guardar(ClinicaService servicio) {
        // Guardar Pacientes
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_PACIENTES))) {
            for (Paciente p : servicio.getPacientes()) {
                pw.println(p.getId() + "," + p.getCedula() + "," + p.getNombre() + "," + p.getApellido() + "," + p.getTelefono());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar pacientes: " + e.getMessage());
        }

        // Guardar Medicos
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_MEDICOS))) {
            for (Medico m : servicio.getMedicos()) {
                pw.println(m.getId() + "," + m.getNombre() + "," + m.getApellido() + "," + m.getEspecialidad());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar medicos: " + e.getMessage());
        }

        // Guardar Turnos - faltaba este bloque
        try (PrintWriter pw = new PrintWriter(new FileWriter(F_TURNOS))) {
            for (Turno t : servicio.getTurnos()) {
                pw.println(t.getId() + "," + t.getPaciente().getCedula() + "," +
                        t.getMedico().getNombre() + "," + t.getMedico().getApellido() + "," +
                        t.getFechaHora().format(FMT) + "," + t.getEstado());
            }
        } catch (IOException e) {
            System.out.println("Error al guardar turnos: " + e.getMessage());
        }
    }
}