package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;

import java.util.Objects;

public class Paciente implements Registrable {

    private int id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String telefono;

    // Constructor con id
    public Paciente(int id, String cedula, String nombre,
                    String apellido, String telefono) {

        this(cedula, nombre, apellido, telefono);
        this.id = id;
    }

    // Constructor sin id
    public Paciente(String cedula, String nombre,
                    String apellido, String telefono) {
        setCedula(cedula);
        setNombre(nombre);
        setApellido(apellido);
        setTelefono(telefono);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        if (cedula == null || cedula.isBlank()) {
            throw new IllegalArgumentException("La cédula es obligatoria");
        }

        this.cedula = cedula.trim();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {

        if (nombre == null || nombre.isBlank()) {
            throw new IllegalArgumentException("El nombre es obligatorio");
        }

        this.nombre = nombre.trim();
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {

        if (apellido == null || apellido.isBlank()) {
            throw new IllegalArgumentException("El apellido es obligatorio");
        }

        this.apellido = apellido.trim();
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {

        if (telefono == null || !telefono.matches("^[0-9]{7,10}$")) {
            throw new IllegalArgumentException(
                    "El teléfono debe tener entre 7 y 10 dígitos"
            );
        }

        this.telefono = telefono;
    }

    @Override
    public String getDatosRegistro() {
        return toString();
    }

    @Override
    public boolean esValido() {

        return cedula != null &&
                !cedula.isBlank() &&
                nombre != null &&
                !nombre.isBlank() &&
                apellido != null &&
                !apellido.isBlank() &&
                telefono != null;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;

        if (!(o instanceof Paciente)) return false;

        Paciente paciente = (Paciente) o;

        return cedula.equals(paciente.cedula);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cedula);
    }

    @Override
    public String toString() {

        return nombre + " " + apellido +
                " - " + cedula +
                " - " + telefono;
    }
}