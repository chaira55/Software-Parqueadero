public class Espacio {
    private int id;
    private String tipoVehiculo;
    private boolean disponibilidad;
    private float tarifaHora;
    private float tarifaDia;
    private boolean reserva;

    public Espacio(){
        this.id = 0;
        this.tipoVehiculo = "";
        this.disponibilidad = false;
        this.tarifaHora = 0.0f;
        this.tarifaDia = 0.0f;
        this.reserva = false;
    }

    public Espacio(int id, String tipoVehiculo, boolean disponibilidad, float tarifaHora, float tarifaDia, boolean reserva){
        this.id = id;
        this.tipoVehiculo = tipoVehiculo;
        this.disponibilidad = disponibilidad;
        this.tarifaHora = tarifaHora;
        this.tarifaDia = tarifaDia;
        this.reserva = reserva;
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

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public float getTarifaHora() {
        return tarifaHora;
    }

    public void setTarifaHora(float tarifaHora) {
        this.tarifaHora = tarifaHora;
    }

    public float getTarifaDia() {
        return tarifaDia;
    }

    public void setTarifaDia(float tarifaDia) {
        this.tarifaDia = tarifaDia;
    }

    public boolean isReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }


    

}
