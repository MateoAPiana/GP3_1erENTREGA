package entidades;

public class Materia {
    private int id = -1;
    private String nombre;
    private int estado;

    public Materia() {
    }

    public Materia(int id, String nombre, int estado) {
        this.id = id;
        this.nombre = nombre;
        this.estado = estado;
    }

    public Materia(String nombre, int estado) {
        this.nombre = nombre;
        this.estado = estado;
    }
    
    @Override
    public String toString(){
        return "Materia " + nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }    
}
