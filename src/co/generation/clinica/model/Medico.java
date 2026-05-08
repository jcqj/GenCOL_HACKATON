package co.generation.clinica.model;

public class Medico {

    private int id;
    private String nombre;
    private String apellido;
   
    public Medico(int id,String apellido, String nombre) {
        this.apellido = apellido;
        this.id = id;
        this.nombre = nombre;
    }
    //getter
    public String getApellido() {return apellido;}
    public int getId() {return id;}
    public String getNombre() {return nombre;}
    //setter
    public void setApellido(String apellido) {this.apellido = apellido;}
    public void setId(int id) {this.id = id;}
    public void setNombre(String nombre) {this.nombre = nombre;}

   
   
}
