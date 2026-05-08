package co.generation.clinica.model;
import co.generation.clinica.interfaces.Registrable;
import java.util.Objects;

public class Paciente implements Registrable {
    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;


    public Paciente(int id, String cedula, String nombre, String apellido, String telefono) {
        this.id = id;

        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }


    public Paciente(String cedula, String nombre, String apellido, String telefono) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
    }
    public void setCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("La cedula es obligatoria");
        }
        this.cedula = cedula;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }
        this.nombre = nombre.trim();
    }
    public void setApellido(String apellido) {
        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }
        this.apellido = apellido.trim();
    }
    public void setTelefono(String telefono) {
        if (telefono == null || !telefono.matches("^[0-9]{7,10}$")) {
            throw new IllegalArgumentException("El telefono debe contener entre 7 y 10 digitos numericos.");
        }
        this.telefono = telefono;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getCedula() { return cedula; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }

    //sobre-escritura
    //Metodo De la Iterfaz Registable
    @Override
    public String getDatosRegistro() {
        return toString();
    }

    @Override
    public boolean esValido() {
        // Verificando que los atributos obligatorios no sean nulos o vacios
        return (cedula != null && !cedula.isBlank()) &&
               (nombre != null && !nombre.isBlank()) &&
               (telefono != null && telefono.matches("^[0-9]{7,10}$"));
    }
    //Metodo fundamtl
    @Override
    public boolean equals(Object o) {
        // Dos pacientes son iguales si tienen la misma cedula
        if (this == o) return true;
        if (!(o instanceof Paciente)) return false;
        Paciente paciente = (Paciente) o;
        return Objects.equals(cedula, paciente.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }

    @Override
    public String toString() {
        // El Formato
        return nombre + " " + apellido + " - " + cedula + " - " + telefono;
    }
}
