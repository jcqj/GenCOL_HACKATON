package co.generation.clinica;

import co.generation.clinica.datos.DatosCSV;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ClinicaService servicio = new ClinicaService();

    public static void main(String[] args) {
        DatosCSV.cargar(servicio);

        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            System.out.println("Selecciona una opción: ");
            opcion = sc.nextInt();
            sc.nextLine();
            switch (opcion) {
                case 1 -> registrarPaciente(servicio);
                case 2 -> registrarMedico(servicio);
                case 3 -> asignarTurno(servicio);
                case 4 -> listarTurnosDelDia(servicio);
                case 5 -> cancelarTurno(servicio);
                case 6 -> turnosPorMedico(servicio);
                case 7 -> turnosPorPaciente(servicio);
                case 8 -> cambiarEstado(servicio);
                case 9 -> servicio.listarPacientes();
                case 10 -> servicio.listarMedicos();
                case 0 -> {
                    System.out.println("Guardando datos...");
                    DatosCSV.guardar(servicio);
                    System.out.println("Hasta pronto. Datos guardados.");
                }
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    public static void registrarPaciente(ClinicaService servicio) {
        System.out.println("Ingrese Cedula");
        String cedula = sc.nextLine();

        System.out.println("Ingrese Nombre");
        String nombre = sc.nextLine();

        System.out.println("Ingrese Apellido");
        String apellido = sc.nextLine();

        System.out.println("Ingrese Telefono");
        String telefono = sc.nextLine();

        Paciente p1 = new Paciente(cedula, nombre, apellido, telefono);
        servicio.registrarPaciente(p1); // faltaba esta línea
    }

    public static void registrarMedico(ClinicaService servicio) {
        System.out.println("Ingrese Nombre");
        String nombre = sc.nextLine();

        System.out.println("Ingrese Apellido");
        String apellido = sc.nextLine();

        System.out.println("Ingrese Especialidad (GENERAL, PEDIATRIA, CARDIOLOGIA, URGENCIAS)");
        Especialidad especialidad = Especialidad.valueOf(sc.nextLine().toUpperCase());

        Medico m1 = new Medico(nombre, apellido, especialidad);
        servicio.registrarMedico(m1);
    }

    public static void asignarTurno(ClinicaService servicio) {
        System.out.println("Ingrese la cédula del paciente");
        String cedula = sc.nextLine();

        Paciente paciente = servicio.buscarPorCedula(cedula);
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        System.out.println("Ingrese el nombre del médico");
        String nombre = sc.nextLine();

        System.out.println("Ingrese el apellido del médico");
        String apellido = sc.nextLine();

        Medico medico = servicio.buscarPorNombreApellido(nombre, apellido);
        if (medico == null) {
            System.out.println("Médico no encontrado.");
            return;
        }

        System.out.print("Año: ");
        int anio = sc.nextInt();
        System.out.print("Mes: ");
        int mes = sc.nextInt();
        System.out.print("Día: ");
        int dia = sc.nextInt();
        System.out.print("Hora: ");
        int hora = sc.nextInt();
        System.out.print("Minuto: ");
        int minuto = sc.nextInt();
        sc.nextLine();

        LocalDateTime fechaHora = LocalDateTime.of(anio, mes, dia, hora, minuto);
        Turno t = new Turno(paciente, medico, fechaHora);
        servicio.asignarTurno(t);
    }

    public static void cancelarTurno(ClinicaService servicio) {
        System.out.println("Ingrese el ID del turno a cancelar");
        int id = sc.nextInt();
        sc.nextLine();
        servicio.cancelarTurno(id);
    }

    public static void cambiarEstado(ClinicaService servicio) {
        System.out.println("Ingrese el ID del turno");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.println("Ingrese el nuevo estado (PENDIENTE, ATENDIDO, CANCELADO)");
        EstadoTurno nuevoEstado = EstadoTurno.valueOf(sc.nextLine().toUpperCase());

        servicio.cambiarEstadoTurno(id, nuevoEstado);
    }

    public static void turnosPorMedico(ClinicaService servicio) {
        System.out.print("Nombre del médico: ");
        String nombre = sc.nextLine();

        System.out.print("Apellido del médico: ");
        String apellido = sc.nextLine();

        Medico medico = servicio.buscarPorNombreApellido(nombre, apellido);
        if (medico == null) {
            System.out.println("Médico no encontrado.");
            return;
        }

        List<Turno> turnos = servicio.buscarPorMedico(medico);
        if (turnos.isEmpty()) {
            System.out.println("El médico no tiene turnos asignados.");
            return;
        }
        turnos.forEach(System.out::println);
    }

    public static void turnosPorPaciente(ClinicaService servicio) {
        System.out.println("Ingrese la cédula del paciente");
        String cedula = sc.nextLine();

        Paciente paciente = servicio.buscarPorCedula(cedula);
        if (paciente == null) {
            System.out.println("Paciente no encontrado.");
            return;
        }

        List<Turno> turnos = servicio.buscarPorPaciente(paciente);
        if (turnos.isEmpty()) {
            System.out.println("El paciente no tiene turnos asignados.");
            return;
        }
        turnos.forEach(System.out::println);
    }

    public static void listarTurnosDelDia(ClinicaService servicio) {
        System.out.print("Año: ");
        int anio = sc.nextInt();
        System.out.print("Mes: ");
        int mes = sc.nextInt();
        System.out.print("Día: ");
        int dia = sc.nextInt();
        sc.nextLine();

        List<Turno> turnos = servicio.listarTurnosDelDia(LocalDate.of(anio, mes, dia));
        if (turnos.isEmpty()) {
            System.out.println("No hay turnos para ese día.");
            return;
        }
        turnos.forEach(System.out::println);
    }

    private static void mostrarMenu() {
        System.out.println("""
                
                ==================================
                       CLINICAAPP - MENÚ
                ==================================
                1. Registrar paciente
                2. Registrar médico
                3. Asignar turno
                4. Listar turnos del día
                5. Cancelar turno
                6. Ver turnos por médico
                7. Ver turnos por paciente
                8. Cambiar estado turno
                9. Listar pacientes
                10. Listar médicos
                0. Salir
                ==================================
                """);
    }
}