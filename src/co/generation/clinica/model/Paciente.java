package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;
import java.util.Objects;

public class Paciente implements Registrable {

    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;

    // Constructor con id - para reconstruir desde CSV
    public Paciente(int id, String cedula, String nombre, String apellido, String telefono) {
        this(cedula, nombre, apellido, telefono);
        this.id = id;
    }

    // Constructor sin id - para registrar desde el menú
    public Paciente(String cedula, String nombre, String apellido, String telefono) {
        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    //getter
    public int getId() {return id;}
    public String getCedula() {return cedula;}
    public String getNombre() {return nombre;}
    public String getApellido() {return apellido;}
    public String getTelefono() {return telefono;}

    //setter
    public void setId(int id) {this.id = id;}

    public void setCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            System.out.println("Error: La cédula no puede estar vacía.");
        } else {
            this.cedula = cedula.trim();
        }
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            System.out.println("Error: El nombre no puede estar vacío.");
        } else {
            this.nombre = nombre.trim();
        }
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            System.out.println("Error: El apellido no puede estar vacío.");
        } else {
            this.apellido = apellido.trim();
        }
    }

    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("^[0-9]{7,10}$")) {
            System.out.println("Error: El teléfono debe tener entre 7 y 10 dígitos.");
        } else {
            this.telefono = telefono;
        }
    }

    //funciones
    @Override
    public String getDatosRegistro() {
        return toString();
    }

    @Override
    public boolean esValido() {
        if (cedula == null || cedula.isBlank()) return false;
        if (nombre == null || nombre.isBlank()) return false;
        if (apellido == null || apellido.isBlank()) return false;
        if (telefono == null) return false;
        return true;
    }

    @Override
    public boolean equals(Object objetoPaciente) {
        if (this == objetoPaciente) return true;
        if (objetoPaciente.getClass() != this.getClass()) return false;

        Paciente otroPaciente = (Paciente) objetoPaciente;

        boolean mismaCedula = this.cedula.equals(otroPaciente.cedula);

        if (mismaCedula) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }

    // formato: "María García - 1020304050 - 3001234567"
    @Override
    public String toString() {
        return nombre + " " + apellido + " - " + cedula + " - " + telefono;
    }
}