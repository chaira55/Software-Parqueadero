package java;

public class Servicio {
    private int id;
    private String nombre;
    private String descripcion;
    private float tarifaUso;

    public Servicio() {
        this.id = 0;
        this.nombre = "";
        this.descripcion = "";
        this.tarifaUso = 0.0f;
    }

    public Servicio(int id, String nombre, String descripcion, float tarifaUso){
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tarifaUso = tarifaUso;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getTarifaUso() {
        return tarifaUso;
    }

    public void setTarifaUso(float tarifaUso) {
        this.tarifaUso = tarifaUso;
    }

    

}
