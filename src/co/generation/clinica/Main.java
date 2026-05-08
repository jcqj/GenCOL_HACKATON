package co.generation.clinica;
import co.generation.clinica.model.*;
import co.generation.clinica.service.ClinicaService;
import java.util.Scanner;
import co.generation.clinica.datos.DatosCSV;
public class Main { 

   static Scanner sc = new Scanner(System.in);
   static ClinicaService servicio = new ClinicaService();
    public static void main(String[] args) {
        // --- AQUÍ VA LA CARGA DEL CSV ---
        // Se ejecuta una sola vez al iniciar
        DatosCSV.cargar(servicio);
   
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
}
