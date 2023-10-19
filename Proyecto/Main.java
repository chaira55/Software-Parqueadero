import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Servicio servicio = new Servicio();

        int opcion;
        do {
            System.out.println("=== Menú de Servicio ===");
            System.out.println("1. Registrar Servicio de Lavado de Auto");
            System.out.println("2. Crear Punto de Carga para Vehículos Eléctricos");
            System.out.println("3. Actualizar Punto de Carga para Vehículos Eléctricos");
            System.out.println("4. Eliminar Punto de Carga para Vehículos Eléctricos");
            System.out.println("5. Registrar Carga de Auto");
            System.out.println("6. Consultar Puntos de Carga para Vehículos Eléctricos Disponibles");
            System.out.println("7. Asignar Punto de Carga para Vehículos Eléctricos");
            System.out.println("8. Crear Servicio Adicional");
            System.out.println("9. Actualizar Servicio Adicional");
            System.out.println("10. Eliminar Servicio Adicional");
            System.out.println("11. Consultar Saldo del Cliente");
            System.out.println("12. Cancelar Carga de Auto");
            System.out.println("0. Salir");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String cliente = scanner.nextLine();
                    System.out.print("Tipo de Lavado (1: Básico, 2: Completo): ");
                    int tipoLavado = scanner.nextInt();
                    servicio.registrarServicioLavadoAuto(cliente, tipoLavado, new Date());
                    break;
                case 2:
                    System.out.print("Ubicación del Punto de Carga: ");
                    String ubicacion = scanner.nextLine();
                    System.out.print("Capacidad del Punto de Carga: ");
                    int capacidad = scanner.nextInt();
                    servicio.crearPuntoCargaEV(ubicacion, capacidad);
                    break;
                case 3:
                    System.out.print("Ubicación del Punto de Carga a actualizar: ");
                    ubicacion = scanner.nextLine();
                    System.out.print("¿Está ocupado? (true o false): ");
                    boolean ocupado = scanner.nextBoolean();
                    servicio.actualizarPuntoCargaEV(ubicacion, ocupado);
                    break;
                case 4:
                    System.out.print("Ubicación del Punto de Carga a eliminar: ");
                    ubicacion = scanner.nextLine();
                    servicio.eliminarPuntoCargaEV(ubicacion);
                    break;
                case 5:
                    System.out.print("Nombre del cliente para registrar la carga de auto: ");
                    cliente = scanner.nextLine();
                    System.out.print("Ubicación para la carga de auto: ");
                    String ubicacionCarga = scanner.nextLine();
                    servicio.registrarCargaAuto(cliente, ubicacionCarga);
                    break;
                case 6:
                    ArrayList<PuntoCargaEV> puntosDisponibles = servicio.consultarPuntosCargaEVDisponibles();
                    System.out.println("Puntos de Carga para Vehículos Eléctricos Disponibles:");
                    for (PuntoCargaEV punto : puntosDisponibles) {
                        System.out.println("Ubicación: " + punto.getUbicacion() + ", Capacidad: " + punto.getCapacidad() + ", Cupos Disponibles: " + punto.getCuposDisponibles());
                    }
                    break;
                case 7:
                    System.out.print("Ubicación del Punto de Carga a asignar: ");
                    ubicacion = scanner.nextLine();
                    boolean asignado = servicio.asignarPuntoCargaEV(ubicacion);
                    if (asignado) {
                        System.out.println("Punto de Carga asignado exitosamente.");
                    } else {
                        System.out.println("No se pudo asignar el Punto de Carga.");
                    }
                    break;
                case 8:
                    System.out.print("Nombre del Servicio Adicional: ");
                    String nombreServicio = scanner.nextLine();
                    System.out.println("Opciones para el Servicio Adicional:");
                    System.out.println("1. Encerar Auto");
                    System.out.println("2. Descontaminación");
                    System.out.println("3. Aromatización");
                    System.out.println("4. Cambio de Aceite");
                    System.out.print("Seleccione una opción: ");
                    int opcionServicioAdicional = scanner.nextInt();
                    servicio.crearServicioAdicional(nombreServicio, opcionServicioAdicional);
                    break;
                case 9:
                    System.out.print("ID del Servicio Adicional a actualizar: ");
                    int idServicioAdicional = scanner.nextInt();
                    System.out.print("Nueva tarifa de uso: ");
                    float nuevaTarifaUso = scanner.nextFloat();
                    servicio.actualizarServicioAdicional(idServicioAdicional, nuevaTarifaUso);
                    break;
                case 10:
                    System.out.print("ID del Servicio Adicional a eliminar: ");
                    idServicioAdicional = scanner.nextInt();
                    servicio.eliminarServicioAdicional(idServicioAdicional);
                    break;
                case 11:
                    System.out.print("Nombre del cliente para consultar el saldo: ");
                    String nombreCliente = scanner.nextLine();
                    servicio.obtenerSaldoCliente(nombreCliente);
                    break;                
                case 12:
                    System.out.print("Nombre del cliente para cancelar la carga de auto: ");
                    cliente = scanner.nextLine();
                    System.out.print("Ubicación de la carga de auto a cancelar: ");
                    String ubicacionCancelar = scanner.nextLine();
                    servicio.cancelarCargaAuto(cliente, ubicacionCancelar);
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);
    }
}
