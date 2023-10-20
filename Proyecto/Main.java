import java.util.Scanner;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Servicio servicio = new Servicio();

        int opcion;
        do {
            System.out.println("Menu:");
            System.out.println("1. Crear cliente");
            System.out.println("2. Registrar lavado de auto");
            System.out.println("3. Crear puntos de carga");
            System.out.println("4. Registrar punto de carga");
            System.out.println("5. Actualizar puntos de carga");
            System.out.println("6. Eliminar puntos de carga");
            System.out.println("7. Consultar puntos de carga");
            System.out.println("8. Cancelar carga de auto");
            System.out.println("9. Asignar punto de carga");
            System.out.println("10. Registrar servicio adicional");
            System.out.println("11. Cancelar servicio adicional");
            System.out.println("12. Consultar saldo del cliente");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine();  // Limpia el buffer

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    String nombreCliente = scanner.nextLine();
                    System.out.print("Saldo inicial: ");
                    float saldoInicial = scanner.nextFloat();
                    servicio.crearCliente(nombreCliente, saldoInicial);
                    break;
                case 2:
                    System.out.print("Nombre del cliente: ");
                    nombreCliente = scanner.nextLine();
                    if (servicio.buscarCliente(nombreCliente) != null) {
                        System.out.print("Tipo de lavado (1 para básico, 2 para completo): ");
                        int tipoLavado = scanner.nextInt();
                        scanner.nextLine();
                        Date fecha = new Date(); // Obtener la fecha actual
                        servicio.registrarServicioLavadoAuto(servicio.buscarCliente(nombreCliente), tipoLavado, fecha);
                    } else {
                        System.out.println("Cliente no encontrado.");
                    }
                    break;
                case 3:
                    System.out.print("Ubicación del punto de carga: ");
                    String ubicacionPuntoCarga = scanner.nextLine();
                    System.out.print("Capacidad del punto de carga: ");
                    int capacidadPuntoCarga = scanner.nextInt();
                    scanner.nextLine();
                    servicio.crearPuntoCargaEV(ubicacionPuntoCarga, capacidadPuntoCarga);
                    break;
                case 4:
                    System.out.print("Ubicación del punto de carga a registrar: ");
                    ubicacionPuntoCarga = scanner.nextLine();
                    if (servicio.buscarPuntoCargaEV(ubicacionPuntoCarga) != null) {
                        System.out.print("Nombre del cliente: ");
                        nombreCliente = scanner.nextLine();
                        servicio.registrarCargaAuto(servicio.buscarCliente(nombreCliente), ubicacionPuntoCarga);
                    } else {
                        System.out.println("Punto de carga no encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Ubicación del punto de carga a actualizar: ");
                    ubicacionPuntoCarga = scanner.nextLine();
                    scanner.nextLine();
                    servicio.actualizarPuntoCargaEV(ubicacionPuntoCarga);
                    break;
                case 6:
                    System.out.print("Ubicación del punto de carga a eliminar: ");
                    ubicacionPuntoCarga = scanner.nextLine();
                    servicio.eliminarPuntoCargaEV(ubicacionPuntoCarga);
                    break;
                case 7:
                    ArrayList<PuntoCargaEV> puntosDisponibles = servicio.consultarPuntosCargaEVDisponibles();
                    for (PuntoCargaEV punto : puntosDisponibles) {
                        System.out.println("Ubicación: " + punto.getUbicacion() + ", Cupos disponibles: " + punto.getCuposDisponibles());
                    }
                    break;
                case 8:
                    System.out.print("Nombre del cliente: ");
                    nombreCliente = scanner.nextLine();
                    System.out.print("Ubicación del punto de carga: ");
                    ubicacionPuntoCarga = scanner.nextLine();
                    servicio.cancelarCargaAuto(nombreCliente, ubicacionPuntoCarga);
                    break;
                case 9:
                    System.out.print("Nombre del cliente: ");
                    nombreCliente = scanner.nextLine();
                    System.out.print("Ubicación del punto de carga a asignar: ");
                    ubicacionPuntoCarga = scanner.nextLine();
                    if (servicio.asignarPuntoCargaEV(servicio.buscarCliente(nombreCliente), ubicacionPuntoCarga)) {
                        System.out.println("Punto de carga asignado exitosamente.");
                    } else {
                        System.out.println("No se pudo asignar el punto de carga.");
                    }
                    break;
                case 10:
                    System.out.print("Nombre del cliente: ");
                    nombreCliente = scanner.nextLine();
                    System.out.println("Selecciona el servicio adicional a registrar:");
                    System.out.println("1. Encerar Auto");
                    System.out.println("2. Descontaminación");
                    System.out.println("3. Aromatización");
                    System.out.println("4. Cambio de Aceite");
                    int opcionServicioAdicional = scanner.nextInt();
                    scanner.nextLine();
                    servicio.registrarServicioAdicional(nombreCliente, opcionServicioAdicional);
                    break;
                case 11:
                    System.out.print("Nombre del cliente: ");
                    nombreCliente = scanner.nextLine();
                    System.out.println("Selecciona el servicio adicional a registrar:");
                    System.out.println("1. Encerar Auto");
                    System.out.println("2. Descontaminación");
                    System.out.println("3. Aromatización");
                    System.out.println("4. Cambio de Aceite");
                    int op = scanner.nextInt();
                    servicio.cancelarServicioAdicional(nombreCliente, op);
                    break;
                case 12:
                    System.out.print("Nombre del cliente: ");
                    nombreCliente = scanner.nextLine();
                    servicio.obtenerSaldoCliente(nombreCliente);
                    break;
                case 0:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, seleccione una opción válida.");
                    break;
            }
        } while (opcion != 0);

        scanner.close();
    }
}
