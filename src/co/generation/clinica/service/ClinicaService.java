package co.generation.clinica.service;

import co.generation.clinica.interfaces.Consultable;
import co.generation.clinica.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClinicaService implements Consultable {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();

    //getters de listas
    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getMedicos() { return medicos; }
    public List<Turno> getTurnos() { return turnos; }

    // buscar por id - los usa DatosCSV
    public Paciente buscarPacientePorId(int id) {
        for (Paciente p : pacientes) {
            if (p.getId() == id) return p;
        }
        return null;
    }

    public Medico buscarMedicoPorId(int id) {
        for (Medico m : medicos) {
            if (m.getId() == id) return m;
        }
        return null;
    }

    // --- PACIENTES ---
    public void registrarPaciente(Paciente p) {
        if (!p.esValido()) {
            System.out.println("Error: Datos del paciente no válidos.");
            return;
        }
        if (pacientes.contains(p)) {
            System.out.println("Error: Ya existe un paciente con esa cédula.");
            return;
        }
        int maxId = pacientes.stream().mapToInt(Paciente::getId).max().orElse(0);
        p.setId(maxId + 1);
        pacientes.add(p);
        System.out.println("Paciente registrado con éxito: " + p);
    }

    public Paciente buscarPorCedula(String cedula) {
        for (Paciente p : pacientes) {
            if (p.getCedula().equals(cedula)) return p;
        }
        return null;
    }

    public void listarPacientes() {
        if (pacientes.isEmpty()) {
            System.out.println("No hay pacientes registrados.");
            return;
        }
        List<Paciente> copia = new ArrayList<>(pacientes);
        copia.sort(Comparator.comparing(Paciente::getApellido).thenComparing(Paciente::getNombre));
        System.out.println("\n--- LISTADO DE PACIENTES ---");
        copia.forEach(System.out::println);
    }

    // --- MEDICOS ---
    public void registrarMedico(Medico m) {
        if (!m.esValido()) {
            System.out.println("Error: Datos del médico no válidos.");
            return;
        }
        if (medicos.contains(m)) {
            System.out.println("Error: El médico ya se encuentra registrado.");
            return;
        }
        int maxId = medicos.stream().mapToInt(Medico::getId).max().orElse(0);
        m.setId(maxId + 1);
        medicos.add(m);
        System.out.println("Médico registrado con éxito: " + m);
    }

    public Medico buscarPorNombreApellido(String nombre, String apellido) {
        for (Medico m : medicos) {
            if (m.getNombre().equalsIgnoreCase(nombre) && m.getApellido().equalsIgnoreCase(apellido)) {
                return m;
            }
        }
        return null;
    }

    public void listarMedicos() {
        if (medicos.isEmpty()) {
            System.out.println("No hay médicos registrados.");
            return;
        }
        List<Medico> copia = new ArrayList<>(medicos);
        copia.sort(Comparator.comparing(Medico::getEspecialidad).thenComparing(Medico::getApellido));
        System.out.println("\n--- LISTADO DE MÉDICOS ---");
        copia.forEach(System.out::println);
    }

    // --- TURNOS ---
    public void asignarTurno(Turno t) {
        if (buscarPorCedula(t.getPaciente().getCedula()) == null) {
            System.out.println("Error: El paciente no existe.");
            return;
        }
        if (buscarPorNombreApellido(t.getMedico().getNombre(), t.getMedico().getApellido()) == null) {
            System.out.println("Error: El médico no existe.");
            return;
        }
        if (turnos.contains(t)) {
            System.out.println("Error: El médico ya tiene un turno en ese horario.");
            return;
        }
        int maxId = turnos.stream().mapToInt(Turno::getId).max().orElse(0);
        t.setId(maxId + 1);
        turnos.add(t);
        System.out.println("Turno asignado: " + t);
    }

    public void cancelarTurno(int idTurno) {
        Turno encontrado = null;
        for (Turno t : turnos) {
            if (t.getId() == idTurno) {
                encontrado = t;
                break;
            }
        }
        if (encontrado == null) {
            System.out.println("Turno no encontrado.");
            return;
        }
        if (encontrado.getEstado() != EstadoTurno.PENDIENTE) {
            System.out.println("No se puede cancelar un turno en estado: " + encontrado.getEstado());
        } else {
            encontrado.setEstado(EstadoTurno.CANCELADO);
            System.out.println("Turno " + idTurno + " cancelado.");
        }
    }

    // cambia el estado de un turno a cualquier estado
    public void cambiarEstadoTurno(int idTurno, EstadoTurno nuevoEstado) {
        Turno encontrado = null;
        for (Turno t : turnos) {
            if (t.getId() == idTurno) {
                encontrado = t;
                break;
            }
        }
        if (encontrado == null) {
            System.out.println("Turno no encontrado.");
            return;
        }
        encontrado.setEstado(nuevoEstado);
        System.out.println("Estado del turno " + idTurno + " cambiado a: " + nuevoEstado);
    }

    // --- CONSULTABLE ---
    @Override
    public List<Turno> listarTurnosDelDia(LocalDate fecha) {
        List<Turno> resultado = new ArrayList<>();
        for (Turno t : turnos) {
            if (t.getFechaHora().toLocalDate().equals(fecha)) {
                resultado.add(t);
            }
        }
        resultado.sort(Comparator.comparing(Turno::getFechaHora));
        return resultado;
    }

    @Override
    public List<Turno> buscarPorMedico(Medico medico) {
        List<Turno> resultado = new ArrayList<>();
        for (Turno t : turnos) {
            if (t.getMedico().equals(medico)) {
                resultado.add(t);
            }
        }
        return resultado;
    }

    @Override
    public List<Turno> buscarPorPaciente(Paciente paciente) {
        List<Turno> resultado = new ArrayList<>();
        for (Turno t : turnos) {
            if (t.getPaciente().equals(paciente)) {
                resultado.add(t);
            }
        }
        return resultado;
    }
}