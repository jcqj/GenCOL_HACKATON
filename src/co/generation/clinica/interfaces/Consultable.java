package co.generation.clinica.interfaces;

import co.generation.clinica.model.*;
import java.time.LocalDate;
import java.util.List;

public interface Consultable {
    List<Turno> listarTurnosDelDia(LocalDate fecha);
    List<Turno> buscarPorMedico(Medico medico);
    List<Turno> buscarPorPaciente(Paciente paciente);
}
