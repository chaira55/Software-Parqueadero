package java;

public class Vehiculo {
    private int id;
    private String tipoVehiculo;
    private String placaVehiculo;
    private String modelo;

    

    public Vehiculo() {
        this.id = 0;
        this.tipoVehiculo = "";
        this.placaVehiculo = "";
        this.modelo = "";
    
    }

    public Vehiculo(int id, String tipoVehiculo, String placaVehiculo, String modelo) {
        this.id = id;
        this.tipoVehiculo = tipoVehiculo;
        this.placaVehiculo = placaVehiculo;
        this.modelo = modelo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(String tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public String getPlacaVehiculo() {
        return placaVehiculo;
    }

    public void setPlacaVehiculo(String placaVehiculo) {
        this.placaVehiculo = placaVehiculo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
    

}
