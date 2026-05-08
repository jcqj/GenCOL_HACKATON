package co.generation.clinica.model;
import java.time.LocalDateTime;
import java.util.Objects;

public class Turno {
    private int id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private EstadoTurno estado;

    // Constructor para cargar desde CSV - necesita todos los datos incluido el estado
    public Turno(int id, Paciente paciente, Medico medico, LocalDateTime fechaHora, EstadoTurno estado) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = estado;
    }

    // Constructor para crear un turno nuevo desde el menú - el id lo asigna ClinicaService
    public Turno(int id, Paciente paciente, Medico medico, LocalDateTime fechaHora) {
        this.id = id;
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = EstadoTurno.PENDIENTE;
    }

    public Turno(Paciente paciente, Medico medico, LocalDateTime fechaHora) {
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = EstadoTurno.PENDIENTE;
    }

    //getter
    public int getId() {return id;}
    public Paciente getPaciente() {return paciente;}
    public Medico getMedico() {return medico;}
    public LocalDateTime getFechaHora() {return fechaHora;}
    public EstadoTurno getEstado() {return estado;}

    //setter
    public void setId(int id) {this.id = id;}

    public void setPaciente(Paciente paciente) {
        if (paciente == null) {
            System.out.println("Error: El paciente no puede ser nulo.");
        } else {
            this.paciente = paciente;
        }
    }

    public void setMedico(Medico medico) {
        if (medico == null) {
            System.out.println("Error: El medico no puede ser nulo.");
        } else {
            this.medico = medico;
        }
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        if (fechaHora == null) {
            System.out.println("Error: La fecha no puede ser nula.");
        } else {
            this.fechaHora = fechaHora;
        }
    }

    public void setEstado(EstadoTurno estado) {
        if (estado == null) {
            System.out.println("Error: El estado no puede ser nulo.");
        } else {
            this.estado = estado;
        }
    }

    @Override
    public boolean equals(Object objetoTurno) {
        if (this == objetoTurno) return true;
        if (objetoTurno.getClass() != this.getClass()) return false;

        Turno otroTurno = (Turno) objetoTurno;

        boolean mismoMedico = this.medico.equals(otroTurno.medico);
        boolean mismaFecha = this.fechaHora.equals(otroTurno.fechaHora);

        if (mismoMedico && mismaFecha) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(medico, fechaHora);
    }

    // formato: [PENDIENTE] María García — Dr. Carlos Pérez (CARDIOLOGIA) — 2026-06-10T09:30
    @Override
    public String toString() {
        return "[" + estado + "] " + paciente.getNombre() + " " + paciente.getApellido() +
                " — " + medico.toString() + " — " + fechaHora;
    }
}