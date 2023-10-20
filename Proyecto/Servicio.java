import java.util.ArrayList;
import java.util.Date;

public class Servicio {
    private int id;
    private String cliente;
    private float tarifaUso;
    private ArrayList<String> serviciosRegistrados;
    private ArrayList<Cliente> clientes;
    private ArrayList<PuntoCargaEV> puntosCargaEV;
    private ArrayList<ServicioAdicional> serviciosAdicionales;

    public Servicio() {
        this.id = 0;
        this.cliente = "";
        this.tarifaUso = 0.0f;
        this.serviciosRegistrados = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.puntosCargaEV = new ArrayList<>();
        this.serviciosAdicionales = new ArrayList<>();
        
    }


    public void crearCliente(String nombre, float saldoInicial) {
        Cliente nuevoCliente = new Cliente(nombre, saldoInicial);
        clientes.add(nuevoCliente);
    }
    

    public void registrarServicioLavadoAuto(Cliente cliente, int tipoLavado, Date fecha) {
        float tarifaUso = 0.0f;
        if (tipoLavado == 1) { // lavado básico
            tarifaUso = 25000.0f;
        } else if (tipoLavado == 2) { // lavado completo
            tarifaUso = 78000.0f;
        }
    
        if (tarifaUso > 0.0f) {
            cliente.incrementarSaldo(tarifaUso);
            String servicioRegistrado = "Cliente: " + cliente.getNombre() + ", Tipo de Lavado: " + tipoLavado + ", Fecha: " + fecha.toString() + ", Costo: $" + tarifaUso;
            serviciosRegistrados.add(servicioRegistrado);
        } else {
            System.out.println("Tipo de lavado no válido: " + tipoLavado);
        }
    }
     
     public void crearPuntoCargaEV(String ubicacion, int capacidad) {
        PuntoCargaEV nuevoPuntoCarga = new PuntoCargaEV(ubicacion, capacidad);
        puntosCargaEV.add(nuevoPuntoCarga);
    }

    public void actualizarPuntoCargaEV(String ubicacion) {
        for (PuntoCargaEV puntoCarga : puntosCargaEV) {
            if (puntoCarga.getUbicacion().equals(ubicacion)) {
                puntoCarga.isOcupado();
                return;
            }
        }
    }

    public void registrarCargaAuto(Cliente cliente, String ubicacion) {
        PuntoCargaEV puntoCarga = buscarPuntoCargaEV(ubicacion);
        if (puntoCarga != null && puntoCarga.getCuposDisponibles() > 0) {
            puntoCarga.ocuparCupo(); // Ocupamos un cupo
            float tarifaUso = 125000.0f;
            cliente.incrementarSaldo(tarifaUso);
            String servicioRegistrado = "Cliente: " + cliente.getNombre() + ", Carga de Auto en: " + ubicacion + ", Costo: $" + tarifaUso;
            serviciosRegistrados.add(servicioRegistrado);
        } else {
            System.out.println("No se pudo registrar la carga de auto en: " + ubicacion);
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
    
    public boolean asignarPuntoCargaEV(Cliente cliente, String ubicacion) {
        for (PuntoCargaEV puntoCarga : puntosCargaEV) {
            if (puntoCarga.getUbicacion().equals(ubicacion) && !puntoCarga.isOcupado()) {
                puntoCarga.ocuparCupo();
                float tarifaUso = 125000.0f;
                cliente.incrementarSaldo(tarifaUso);
                return true;
            }
        }
        return false;
    }

    public void cancelarCargaAuto(String nombreCliente, String ubicacion) {
        Cliente cliente = buscarCliente(nombreCliente);
        PuntoCargaEV puntoCarga = buscarPuntoCargaEV(ubicacion);
    
        if (cliente != null && puntoCarga != null && !puntoCarga.isOcupado()) {
            float tarifaCargaAuto = 125000.0f;
            if (cliente.getSaldo() >= tarifaCargaAuto) {
                cliente.decrementarSaldo(tarifaCargaAuto);
                puntoCarga.liberarCupo();
                System.out.println("Carga de auto cancelada para el cliente " + nombreCliente + " en " + ubicacion);
            } else {
                System.out.println("El cliente " + nombreCliente + " no tiene saldo suficiente para cancelar la carga de auto.");
            }
        } else {
            System.out.println("No se pudo cancelar la carga de auto para el cliente " + nombreCliente + " en " + ubicacion);
        }
    }
    
    public void registrarServicioAdicional(String nombre, int op) {
        // Primero, busca al cliente en el array de clientes
        Cliente cliente = buscarCliente(nombre);
        
        if (cliente != null) {
            ServicioAdicional nuevoServicioAdicional;
            
            switch (op) {
                case 1: // encerar auto
                    float tarifaEncerarAuto = 50000.0f;
                    nuevoServicioAdicional = new ServicioAdicional(id, "Encerar Auto", tarifaEncerarAuto);
                    serviciosAdicionales.add(nuevoServicioAdicional);
                    cliente.incrementarSaldo(tarifaEncerarAuto);
                    break;
                case 2: // descontaminacion
                    float tarifaDescontaminacion = 35000.0f;
                    nuevoServicioAdicional = new ServicioAdicional(id, "Descontaminación", tarifaDescontaminacion);
                    serviciosAdicionales.add(nuevoServicioAdicional);
                    cliente.incrementarSaldo(tarifaDescontaminacion);
                    break;
                case 3: // aromatizacion
                    float tarifaAromatizacion = 28000.0f;
                    nuevoServicioAdicional = new ServicioAdicional(id, "Aromatización", tarifaAromatizacion);
                    serviciosAdicionales.add(nuevoServicioAdicional);
                    cliente.incrementarSaldo(tarifaAromatizacion);
                    break;
                case 4: // cambio de aceite
                    float tarifaCambioAceite = 76000.0f;
                    nuevoServicioAdicional = new ServicioAdicional(id, "Cambio de Aceite", tarifaCambioAceite);
                    serviciosAdicionales.add(nuevoServicioAdicional);
                    cliente.incrementarSaldo(tarifaCambioAceite);
                    break;
                default:
                    System.out.println("Opción no válida para crear el servicio adicional.");
                    return;
            }
            System.out.println("Servicio Adicional creado: Nombre " + nuevoServicioAdicional.getNombre() + ", Costo: $" + nuevoServicioAdicional.getTarifaUso());
        } else {
            System.out.println("Cliente no encontrado: " + nombre);
        }
    }
    
    public void cancelarServicioAdicional(String nombreCliente, int opcion) {
        Cliente cliente = buscarCliente(nombreCliente);
        
        if (cliente != null) {
            float tarifaServicioAdicional = 0.0f;
            
            switch (opcion) {
                case 1: // Cancelar servicio adicional: Encerar Auto
                    tarifaServicioAdicional = 50000.0f;
                    break;
                case 2: // Cancelar servicio adicional: Descontaminación
                    tarifaServicioAdicional = 35000.0f;
                    break;
                case 3: // Cancelar servicio adicional: Aromatización
                    tarifaServicioAdicional = 28000.0f;
                    break;
                case 4: // Cancelar servicio adicional: Cambio de Aceite
                    tarifaServicioAdicional = 76000.0f;
                    break;
                default:
                    System.out.println("Opción no válida para cancelar el servicio adicional.");
                    return;
            }
    
            if (cliente.getSaldo() >= tarifaServicioAdicional) {
                cliente.decrementarSaldo(tarifaServicioAdicional);
                System.out.println("Servicio adicional cancelado para el cliente " + nombreCliente + ": " + obtenerNombreServicioAdicional(opcion));
            } else {
                System.out.println("El cliente " + nombreCliente + " no tiene saldo suficiente para cancelar el servicio adicional.");
            }
        } else {
            System.out.println("Cliente no encontrado: " + nombreCliente);
        }
    }
    
    private String obtenerNombreServicioAdicional(int opcion) {
        switch (opcion) {
            case 1:
                return "Encerar Auto";
            case 2:
                return "Descontaminación";
            case 3:
                return "Aromatización";
            case 4:
                return "Cambio de Aceite";
            default:
                return "Opción no válida";
        }
    }
    
    public void obtenerSaldoCliente(String nombreCliente) {
        Cliente cliente = buscarCliente(nombreCliente);
        if (cliente != null) {
            System.out.println("Cliente: " + nombreCliente);
            System.out.println("Saldo Total: $" + cliente.getSaldo());
        } else {
            System.out.println("Cliente no encontrado: " + nombreCliente);
        }
    }
    
    protected PuntoCargaEV buscarPuntoCargaEV(String ubicacion) {
    for (PuntoCargaEV puntoCarga : puntosCargaEV) {
        if (puntoCarga.getUbicacion().equals(ubicacion)) {
            return puntoCarga;
        }
    }
    return null;
}

    protected Cliente buscarCliente(String nombreCliente) {
        for (Cliente cliente : clientes) {
            if (cliente.getNombre().equals(nombreCliente)) {
                return cliente;
            }
        }
        return null;
    }
    
    
}


class PuntoCargaEV {
    private String ubicacion;
    private int capacidad;
    private int cuposDisponibles; 

    public PuntoCargaEV(String ubicacion, int capacidad) {
        this.ubicacion = ubicacion;
        this.capacidad = capacidad;
        this.cuposDisponibles = capacidad; 
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public int getCuposDisponibles() {
        return cuposDisponibles;
    }

    public boolean isOcupado() {
        return cuposDisponibles == 0;
    }

    public void ocuparCupo() {
        if (cuposDisponibles > 0) {
            cuposDisponibles--;
        }
    }

    public void liberarCupo() {
        if (cuposDisponibles < capacidad) {
            cuposDisponibles++;
        }
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

class Cliente {
    private int id;
    private String nombre;
    private float saldo;
    private static int totalClientes = 0;

    public Cliente(String nombre, float saldo) {
        this.id = ++totalClientes;        
        this.nombre = nombre;
        this.saldo = saldo;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public float getSaldo() {
        return saldo;
    }

    public void incrementarSaldo(float cantidad) {
        saldo += cantidad;
    }

    public void decrementarSaldo(float cantidad) {
        saldo -= cantidad;
    }
}
