package co.generation.clinica;

import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;
import java.util.Scanner;

public class Main {
    // Las variables static van aquí (fuera del main)
    static Scanner sc = new Scanner(System.in);
    static ClinicaService servicio = new ClinicaService();

    public static void main(String[] args) {
        int opcion = -1;
        while (opcion != 0) {
            mostrarMenu();
            opcion = leerInt("Selecciona una opción: ");
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
                case 0 -> System.out.println("Guardando datos...");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    // --- MÉTODOS AUXILIARES (Debes tenerlos definidos abajo) ---

    private static void mostrarMenu() {
        System.out.println("\n--- SISTEMA DE CLÍNICA ---");
        System.out.println("1. Registrar Paciente");
        System.out.println("2. Registrar Médico");
        System.out.println("0. Salir");
    }

    private static int leerInt(String mensaje) {
        System.out.print(mensaje);
        try {
            return Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    // Agrega aquí los métodos que faltan (registrarPaciente, registrarMedico, etc.)
    private static void registrarPaciente(ClinicaService s) { /* lógica */ }
    private static void registrarMedico(ClinicaService s) { /* lógica */ }
    private static void asignarTurno(ClinicaService s) { /* lógica */ }
    private static void listarTurnosDelDia(ClinicaService s) { /* lógica */ }
    private static void cancelarTurno(ClinicaService s) { /* lógica */ }
    private static void turnosPorMedico(ClinicaService s) { /* lógica */ }
    private static void turnosPorPaciente(ClinicaService s) { /* lógica */ }
    private static void cambiarEstado(ClinicaService s) { /* lógica */ }
}
