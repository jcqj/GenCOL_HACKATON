package co.generation.clinica.model;


public class Paciente {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;
    // Constructor con Id 
    public Paciente(int id, String cedula, String nombre, String apellido, String telefono) {
        this.id = id;
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        
    }

    // Constructor sin Id
    public Paciente(String cedula, String nombre, String apellido, String telefono) {
        this(0, cedula, nombre, apellido, telefono);
    }
    public void setCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("La cedula es obligatoria");
        }
        this.cedula = cedula;
    }
public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
    }
public void setTelefono(String telefono) {
        // Validación con expresión regular: ^[0-9]{7,10}$ 
        if (telefono == null || !telefono.matches("^[0-9]{7,10}$")) {
            throw new IllegalArgumentException("El telefono debe contener entre 7 y 10 digitos numericos.");
        }
        this.telefono = telefono;
    }
    
}
