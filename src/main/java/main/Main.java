/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author javi
 */
import entidades.Cliente;
import clientesDAO.ClientesDAO;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Cliente cliente = new Cliente();
        ClientesDAO clientes = new ClientesDAO();
        System.out.println("\t\tAPP CLIENTES:\n");
        siguientesAnteriores(0);

        menu();

    }

    public static void menu() {
        boolean parar = false;
        Integer previousNext = 0;

        while (!parar) {
            Scanner sc = new Scanner(System.in);

            System.out.println();
            System.out.println("=========================================");
            System.out.println("\tAPP DE CLIENTES");
            System.out.println("=========================================");
            System.out.println("1- 10 siguientes");
            System.out.println("2- 10 anteriores");
            System.out.println("3- Buscar cliente por id");
            System.out.println("4- Añadir cliente");
            System.out.println("5- Eliminar cliente");
            System.out.println("6- Actualizar cliente\n");
            System.out.println("0- Salir");
            System.out.println("=========================================");
            int eleccion = 0;

            while (true) {
                try {
                    String opcion = sc.nextLine();
                    eleccion = Integer.parseInt(opcion);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Error. Introduzca un número:");
                }
            }

            switch (eleccion) {
                case 1:
                    previousNext += 10;
                    siguientesAnteriores(previousNext);
                    break;
                case 2:
                    if (previousNext == 0) {
                        System.out.println("Error. No hay menos clientes.");
                    } else {
                        previousNext -= 10;
                        siguientesAnteriores(previousNext);
                    }
                    break;
                case 3:
                    readCliente();
                    break;
                case 4:
                    insertCliente();
                    break;
                case 5:
                    deleteCliente();
                    break;
                case 6:
                    updateCliente();
                    break;
                case 0:
                    System.out.println("\tGRACIAS POR USAR LA APP.\n\tHasta la próxima!");
                    parar = true;
                    break;
                default:
                    System.out.println("Error. Introduzca una opción válida:");
            }
        }
    }

    public static void siguientesAnteriores(Integer previousNext) {
        Cliente cliente = new Cliente();
        ClientesDAO clientes = new ClientesDAO();
        System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
        System.out.println("| ID | Código  | Empresa                                  | Contacto                       | CargoContacto                  |");
        System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
        for (Cliente e : clientes.verClientes(previousNext)) {
            System.out.printf("| %02d | %-7s | %-40s | %-30s | %-30s |\n", e.getId(), e.getCodigo(), e.getEmpresa(), e.getContacto(), e.getCargo_contacto());
        }
        System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
    }

    public static Integer comprobar(Integer siguientes) {
        Cliente cliente = new Cliente();
        ClientesDAO clientes = new ClientesDAO();
        if (siguientes == 0) {
            siguientes = 80;
        } else {
            siguientes -= 10;
        }
        return siguientes;
    }

    public static Cliente existe() {
        Cliente cliente = null;
        ClientesDAO clientes = new ClientesDAO();
        Scanner sc = new Scanner(System.in);

        System.out.println("Introduzca el id del cliente: ");
        Integer idCliente = sc.nextInt();
        cliente = clientes.read(idCliente);
        return cliente;

    }

    public static void readCliente() {
        Cliente cliente = existe();
        ClientesDAO clientes = new ClientesDAO();
        Scanner in = new Scanner(System.in);
        System.out.println();

        if (cliente == null) {
            System.out.println("El cliente no existe.");
        } else {
            System.out.println("El cliente seleccionado es: ");
            System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
            System.out.println("| ID | Código  | Empresa                                  | Contacto                       | CargoContacto                  |");
            System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
            System.out.printf("| %02d | %-7s | %-40s | %-30s | %-30s |\n", cliente.getId(), cliente.getCodigo(), cliente.getEmpresa(), cliente.getContacto(), cliente.getCargo_contacto());
            System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
        }
    }

    public static void insertCliente() {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = new Cliente();
        ClientesDAO clientes = new ClientesDAO();

        System.out.println("Introduzca el código del cliente:");
        cliente.setCodigo(sc.nextLine());

        System.out.println("Introduzca el nombre de la empresa:");
        cliente.setEmpresa(sc.nextLine());

        System.out.println("Introduzca el contacto del cliente:");
        cliente.setContacto(sc.nextLine());

        System.out.println("Introduzca el cargo del contacto:");
        cliente.setCargo_contacto(sc.nextLine());

        System.out.println("Introduzca la direccion:");
        cliente.setDireccion(sc.nextLine());

        System.out.println("Introduzca la ciudad:");
        cliente.setCiudad(sc.nextLine());

        System.out.println("Introduzca la Region:");
        cliente.setRegion(sc.nextLine());

        System.out.println("Introduzca el código postal:");
        cliente.setCp(sc.nextLine());

        System.out.println("Introduzca el país:");
        cliente.setPais(sc.nextLine());

        System.out.println("Introduzca el teléfono del contacto:");
        cliente.setTelefono(sc.nextLine());

        System.out.println("Introduzca el fax:");
        cliente.setFax(sc.nextLine());

        if (clientes.insert(cliente)) {
            System.out.println("CLIENTE AÑADIDO CORRECTAMENTE: ");
            System.out.println("+---------+------------------------------------------+--------------------------------+--------------------------------+");
            System.out.println("| Código  | Empresa                                  | Contacto                       | CargoContacto                  |");
            System.out.println("+---------+------------------------------------------+--------------------------------+--------------------------------+");
            System.out.printf("| %-7s | %-40s | %-30s | %-30s |\n", cliente.getCodigo(), cliente.getEmpresa(), cliente.getContacto(), cliente.getCargo_contacto());
            System.out.println("+---------+------------------------------------------+--------------------------------+--------------------------------+");
        } else {
            System.out.println("Error. Cliente introducido no válido.");
        }

    }

    public static void deleteCliente() {
        Cliente cliente = existe();
        ClientesDAO clientes = new ClientesDAO();

        String respuesta = "";

        if (cliente == null) {
            System.out.println("Error. Cliente no válido.");
        } else {
            clientes.delete(cliente.getId());
            System.out.println("Cliente eliminado con éxito.");
        }

    }

    public static void updateCliente() {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = existe();
        ClientesDAO clientes = new ClientesDAO();
        if (cliente == null) {
            System.out.println("Error. Cliente no válido.");
            return;
        }

        while (true) {
                System.out.println();
                System.out.println("El cliente seleccionado es: ");
                System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
                System.out.println("| ID | Código  | Empresa                                  | Contacto                       | CargoContacto                  |");
                System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");
                System.out.printf("| %02d | %-7s | %-40s | %-30s | %-30s |\n", cliente.getId(), cliente.getCodigo(), cliente.getEmpresa(), cliente.getContacto(), cliente.getCargo_contacto());
                System.out.println("+----+---------+------------------------------------------+--------------------------------+--------------------------------+");

                Integer eleccion = 0;
                System.out.println("\tACTUALIZA EL CLIENTE");
                System.out.println("-----------------------------------------------------");
                System.out.println("1. Introduzca nuevo ID.");
                System.out.println("2. Introduzca nuevo nombre de la empresa.");
                System.out.println("3. Introduzca nuevo nombre del contacto.");
                System.out.println("4. Introduzca nuevo cargo del contacto.");
                System.out.println("5. Introduzca nueva direccion.");
                System.out.println("6. Introduzca nuevo ciudad.");
                System.out.println("7. Introduzca nuevo region.");
                System.out.println("8. Introduzca nuevo código postal.");
                System.out.println("9. Introduzca nuevo país.");
                System.out.println("10. Introduzca nuevo teléfono.");
                System.out.println("11. Introduzca nuevo fax.\n");
                System.out.println("0. Salir.");
                System.out.println("----------------------------------------------------");

                String opcion = sc.nextLine();
                eleccion = Integer.parseInt(opcion);

                if (eleccion > 0 && eleccion < 12) {
                    System.out.print("Introduzca la modificacion: ");
                }
                switch (eleccion) {
                    case 1:
                        cliente.setCodigo(sc.next());
                        clientes.update(cliente);
                        break;
                    case 2:
                        cliente.setEmpresa(sc.next());
                        clientes.update(cliente);
                        break;
                    case 3:
                        cliente.setContacto(sc.next());
                        clientes.update(cliente);
                        break;
                    case 4:
                        cliente.setCargo_contacto(sc.next());
                        clientes.update(cliente);
                        break;
                    case 5:
                        cliente.setDireccion(sc.next());
                        clientes.update(cliente);
                        break;
                    case 6:
                        cliente.setCiudad(sc.next());
                        clientes.update(cliente);
                        break;
                    case 7:
                        cliente.setRegion(sc.next());
                        clientes.update(cliente);
                        break;
                    case 8:
                        cliente.setCp(sc.next());
                        clientes.update(cliente);
                        break;
                    case 9:
                        cliente.setPais(sc.next());
                        clientes.update(cliente);
                        break;
                    case 10:
                        cliente.setTelefono(sc.next());
                        clientes.update(cliente);
                        break;
                    case 11:
                        cliente.setFax(sc.next());
                        clientes.update(cliente);
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Error. Introduzca un número válido.:");
                        break;
                }
            }
        }
    }
