import java.util.ArrayList;
import java.util.Date;

public class Servicio {
    private int id;
    private String cliente;
    private float tarifaUso;
    private float saldoTotal;
    private ArrayList<String> serviciosRegistrados;
    private ArrayList<PuntoCargaEV> puntosCargaEV;
    private ArrayList<ServicioAdicional> serviciosAdicionales;

    public Servicio() {
        this.id = 0;
        this.cliente = "";
        this.tarifaUso = 0.0f;
        this.saldoTotal = 0.0f;
        this.serviciosRegistrados = new ArrayList<>();
        this.puntosCargaEV = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        
    }


    public String getCliente() {
        return cliente;
    }
    
    public float getSaldoTotal() {
        return saldoTotal;
    }
    

    public void registrarServicioLavadoAuto(String cliente, int tipoLavado, Date fecha) {    
        if (tipoLavado == 1) { // lavado basico
            this.tarifaUso = 25000.0f;
        } else if (tipoLavado == 2) { // lavado completo
            this.tarifaUso = 78000.0f;
        }

        if (this.tarifaUso > 0.0f) {
            this.saldoTotal += this.tarifaUso;
            String servicioRegistrado = "Cliente: " + cliente + ", Tipo de Lavado: " + tipoLavado + ", Fecha: " + fecha.toString() + ", Costo: $" + this.tarifaUso;
            serviciosRegistrados.add(servicioRegistrado);
        } else {
            System.out.println("Tipo de lavado no válido: " + tipoLavado);
        }
    }
     

     public void crearPuntoCargaEV(String ubicacion, int capacidad) {
        PuntoCargaEV nuevoPuntoCarga = new PuntoCargaEV(ubicacion, capacidad);
        puntosCargaEV.add(nuevoPuntoCarga);
    }

    public void actualizarPuntoCargaEV(String ubicacion, boolean ocupado) {
        for (PuntoCargaEV puntoCarga : puntosCargaEV) {
            if (puntoCarga.getUbicacion().equals(ubicacion)) {
                puntoCarga.setOcupado(ocupado);
                return;
            }
        }
    }

    public void eliminarPuntoCargaEV(String ubicacion) {
        puntosCargaEV.removeIf(puntoCarga -> puntoCarga.getUbicacion().equals(ubicacion));
    }

    public ArrayList<PuntoCargaEV> consultarPuntosCargaEVDisponibles() {
        ArrayList<PuntoCargaEV> puntosDisponibles = new ArrayList<>();
        for (PuntoCargaEV puntoCarga : puntosCargaEV) {
            if (!puntoCarga.isOcupado()) {
                puntosDisponibles.add(puntoCarga);
            }
        }
        return puntosDisponibles;
    }
    

    public boolean asignarPuntoCargaEV(String ubicacion) {
        for (PuntoCargaEV puntoCarga : puntosCargaEV) {
            if (puntoCarga.getUbicacion().equals(ubicacion) && !puntoCarga.isOcupado()) {
                puntoCarga.setOcupado(true);
                this.tarifaUso = 125000.0f;
                return true;
            }
        }
        return false; 
    }

    public void crearServicioAdicional(String nombre, int op) {
        ServicioAdicional nuevoServicioAdicional;
         for (ServicioAdicional sa : serviciosAdicionales) {
            if (sa.getNombre() == nombre) {
                sa.setNombre(nombre);
    
                switch (op) {
                    case 1: // encerar auto
                        sa.setTarifaUso(50000.0f);
                        nuevoServicioAdicional = new ServicioAdicional(id, nombre, sa.getTarifaUso());
                        serviciosAdicionales.add(nuevoServicioAdicional);
                        break;
                    case 2: // descontaminacion
                        sa.setTarifaUso(35000.0f);
                        nuevoServicioAdicional = new ServicioAdicional(id, nombre, sa.getTarifaUso());
                        serviciosAdicionales.add(nuevoServicioAdicional);
                        break;
                    case 3: // aromatizacion
                        sa.setTarifaUso(28000.0f);
                        nuevoServicioAdicional = new ServicioAdicional(id, nombre, sa.getTarifaUso());
                        serviciosAdicionales.add(nuevoServicioAdicional);
                        break;
                    case 4: // cambio de aceite
                        sa.setTarifaUso(76000.0f);
                        nuevoServicioAdicional = new ServicioAdicional(id, nombre, sa.getTarifaUso());
                        serviciosAdicionales.add(nuevoServicioAdicional);
                        break;
                    default:
                        System.out.println("Opción no válida para actualizar la tarifa de uso.");
                        return;
                    
                }
            }
        }

    if (tarifaUso > 0.0f) {
        this.saldoTotal += this.tarifaUso;
        System.out.println("Servicio Adicional:\n ID " + id + ", Nombre " + nombre + ", Costo: $" + tarifaUso);
    } else {
        System.out.println("No se pudo crear el Servicio Adicional debido a una tarifa inválida.");
    }

    }

    public void actualizarServicioAdicional(int id, float tarifaUso) {
        for (ServicioAdicional sa : serviciosAdicionales) {
            if (sa.getId() == id) {
                sa.setTarifaUso(tarifaUso);
            }
        }
    }
    

    public void eliminarServicioAdicional(int id) {
        serviciosAdicionales.removeIf(sa -> sa.getId() == id);
    }

    public ServicioAdicional consultarServicioAdicional(int id) {
        for (ServicioAdicional sa : serviciosAdicionales) {
            if (sa.getId() == id) {
                return sa;
            }
        }
        return null;
    }


    public void registrarServicioAdicional(String cliente, int idServicioAdicional) {
        ServicioAdicional servicioAdicional = consultarServicioAdicional(idServicioAdicional);
        if (servicioAdicional != null) {
            this.saldoTotal += servicioAdicional.getTarifaUso();
            String servicioRegistrado = "Cliente: " + cliente + ", Servicio Adicional: " + servicioAdicional.getNombre() + ", Costo: $" + servicioAdicional.getTarifaUso();
            serviciosRegistrados.add(servicioRegistrado);
        } else {
            System.out.println("Servicio adicional no válido: " + idServicioAdicional);
        }
    }

    public void registrarCargaAuto(String cliente, String ubicacion) {
        PuntoCargaEV puntoCarga = buscarPuntoCargaEV(ubicacion);
        if (puntoCarga != null && !puntoCarga.isOcupado()) {
            this.tarifaUso = 125000.0f;
            puntoCarga.setOcupado(true);
            this.saldoTotal += this.tarifaUso;
            String servicioRegistrado = "Cliente: " + cliente + ", Carga de Auto en: " + ubicacion + ", Costo: $" + this.tarifaUso;
            serviciosRegistrados.add(servicioRegistrado);
        } else {
            System.out.println("No se pudo registrar la carga de auto en: " + ubicacion);
        }
    }

    public void cancelarServicio(String cliente, String descripcion) {
        RegistroServicio servicioAEliminar = null;
        for (RegistroServicio servicio : serviciosRegistrados) {
            if (servicio.getCliente().equals(cliente) && servicio.getDescripcion().equals(descripcion)) {
                servicioAEliminar = servicio;
                break;
            }
        }
        
        if (servicioAEliminar != null) {
            serviciosRegistrados.remove(servicioAEliminar);
            this.saldoTotal -= servicioAEliminar.getCosto();
            System.out.println("Servicio cancelado: " + descripcion);
        } else {
            System.out.println("El servicio no se encontró en los registros: " + descripcion);
        }
    }
    
    
    public void obtenerSaldoCliente(String nombreCliente) {
        float saldoCliente = 0.0f;
        for (String servicio : serviciosRegistrados) {
            if (servicio.startsWith("Cliente: " + nombreCliente)) {
                // Analiza la descripción para obtener el costo del servicio
                String[] partes = servicio.split(", Costo: \\$");
                if (partes.length == 2) {
                    try {
                        saldoCliente += Float.parseFloat(partes[1]);
                    } catch (NumberFormatException e) {
                        // Manejo de error en caso de que no se pueda analizar el costo
                    }
                }
            }
        }
    
        if (saldoCliente > 0.0f) {
            System.out.println("Cliente: " + nombreCliente);
            System.out.println("Saldo Total: $" + saldoCliente);
        } else {
            System.out.println("Cliente no encontrado o saldo total no disponible.");
        }
    }
    
    private PuntoCargaEV buscarPuntoCargaEV(String ubicacion) {
    for (PuntoCargaEV puntoCarga : puntosCargaEV) {
        if (puntoCarga.getUbicacion().equals(ubicacion)) {
            return puntoCarga;
        }
    }
    return null;
}

    
}


class PuntoCargaEV {
    private String ubicacion;
    private int capacidad;
    private boolean ocupado;

    public PuntoCargaEV(String ubicacion, int capacidad) {
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.ocupado = false;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}


class ServicioAdicional {
    private int id;
    private String nombre;
    private float tarifaUso;
    public Object mostrarSaldo;

    private static ArrayList<ServicioAdicional> serviciosAdicionales = new ArrayList<>();

    public ServicioAdicional(int id, String nombre, float tarifaUso) {
        this.id = id;
        this.nombre = nombre;
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

    public float getTarifaUso() {
        return tarifaUso;
    }

    public void setTarifaUso(float tarifaUso) {
        this.tarifaUso = tarifaUso;
    }
}

class RegistroServicio {
    private String cliente;
    private String descripcion;
    private float costo;

    public RegistroServicio(String cliente, String descripcion, float costo) {
        this.cliente = cliente;
        this.descripcion = descripcion;
        this.costo = costo;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public float getCosto() {
        return costo;
    }
}