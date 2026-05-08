package co.generation.clinica.service;

import co.generation.clinica.model.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ClinicaService {

    private List<Paciente> pacientes = new ArrayList<>();
    private List<Medico> medicos = new ArrayList<>();
    private List<Turno> turnos = new ArrayList<>();


    public List<Paciente> getPacientes() { return pacientes; }
    public List<Medico> getMedicos() { return medicos; }
    public List<Turno> getTurnos() { return turnos; }



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
        // Orden natural del Enum Especialidad y luego por Apellido
        copia.sort(Comparator.comparing(Medico::getEspecialidad)
                             .thenComparing(Medico::getApellido));
        
        for (Medico m : copia) {
            System.out.println(m);
        }
    }



    public void asignarTurno(Turno t) {
        // 1. Verificar existencia del paciente
        if (buscarPorCedula(t.getPaciente().getCedula()) == null) {
            System.out.println("Error: El paciente no existe en el sistema.");
            return;
        }
        // 2. Verificar existencia del médico
        if (buscarPorNombreApellido(t.getMedico().getNombre(), t.getMedico().getApellido()) == null) {
            System.out.println("Error: El médico no existe en el sistema.");
            return;
        }
        // 3. Verificar conflicto de agenda (usa equals de Turno que compara médico y fechaHora)
        if (turnos.contains(t)) {
            System.out.println("Error: El médico ya tiene un turno asignado en esa fecha y hora.");
            return;
        }

        int maxId = turnos.stream().mapToInt(Turno::getId).max().orElse(0);
        t.setId(maxId + 1);
        
        turnos.add(t);
        System.out.println("Turno asignado con éxito: " + t);
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

        // Verificar estados usando el Enum EstadoTurno
        if (encontrado.getEstado() == EstadoTurno.ATENDIDO || encontrado.getEstado() == EstadoTurno.CANCELADO) {
            System.out.println("No se puede cancelar un turno que ya está " + encontrado.getEstado());
        } else {
            encontrado.setEstado(EstadoTurno.CANCELADO);
            System.out.println("Confirmación: El turno " + idTurno + " ha sido cancelado.");
        }
    }
}