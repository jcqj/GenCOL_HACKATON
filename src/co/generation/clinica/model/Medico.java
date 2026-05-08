package co.generation.clinica.model;

import co.generation.clinica.interfaces.Registrable;

public class Medico implements Registrable {

    private int id;
    private String nombre;
    private String apellido;
    private Especialidad especialidad;

    public Medico(int id, String nombre, String apellido, Especialidad especialidad) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public Medico(String nombre, String apellido, Especialidad especialidad) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.especialidad = especialidad;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        if (nombre == null || nombre.isEmpty()) {
            System.out.println("Error: El nombre no puede estar vacío.");
        } else {
            this.nombre = nombre.trim().toLowerCase();
        }
    }

    public void setApellido(String apellido) {
        if (apellido == null || apellido.isEmpty()) {
            System.out.println("Error: El apellido no puede estar vacío.");
        } else {
            this.apellido = apellido.trim().toLowerCase();
        }
    }

    public void setEspecialidad(Especialidad especialidad) {
        if (especialidad == null) {
            System.out.println("Error: La especialidad no puede ser nula.");
        } else {
            this.especialidad = especialidad;
        }
    }

    @Override
    public String getDatosRegistro() {
        return "Dr. " + nombre + " " + apellido + " - " + especialidad;
    }

    @Override
    public boolean esValido() {
        if (nombre == null || nombre.isEmpty()) {
            return false;
        }

        if (apellido == null || apellido.isEmpty()) {
            return false;
        }

        if (especialidad == null) {
            return false;
        }

        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medico)) return false;

        Medico otro = (Medico) o;

        String nombreOtro   = otro.nombre;
        String apellidoOtro = otro.apellido;

        boolean mismoNombre   = this.nombre.equals(nombreOtro);
        boolean mismoApellido = this.apellido.equals(apellidoOtro);

        if (mismoNombre && mismoApellido) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return (nombre + apellido).hashCode();
    }

    @Override
    public String toString() {
        return "Dr. " + nombre + " " + apellido + " - " + especialidad;
    }
}