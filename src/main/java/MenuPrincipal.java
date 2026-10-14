package com.mycompany.tecnostore_juan;

import com.mycompany.tecnostore_juan.modelo.*;
import com.mycompany.tecnostore_juan.servicio.*;

import java.util.Scanner;

public class MenuPrincipal {

    private static final Scanner teclado = new Scanner(System.in);

    private static final GestorCelulares gestorCelulares =
            new GestorCelulares();

    private static final GestorClientes gestorClientes =
            new GestorClientes();

    private static final GestorVentas gestorVentas =
            new GestorVentas();

    public static void main(String[] args) {

        int opcion;

        do {
            System.out.println("\n===== TECNOSTORE =====");
            System.out.println("1. Registrar celular");
            System.out.println("2. Listar celulares");
            System.out.println("3. Registrar cliente");
            System.out.println("4. Listar clientes");
            System.out.println("5. Registrar venta");
            System.out.println("6. Listar ventas");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opcion: ");

            opcion = leerEntero();

            try {
                switch (opcion) {
                    case 1 -> registrarCelular();
                    case 2 -> listarCelulares();
                    case 3 -> registrarCliente();
                    case 4 -> listarClientes();
                    case 5 -> registrarVenta();
                    case 6 -> listarVentas();
                    case 0 -> System.out.println("Hasta pronto.");
                    default -> System.out.println("Opcion no valida.");
                }
            } catch (IllegalArgumentException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (opcion != 0);
    }

    private static void registrarCelular() {

        System.out.println("\n--- Registrar celular ---");

        System.out.print("ID: ");
        int id = leerEntero();

        System.out.print("Marca: ");
        String marca = teclado.nextLine();

        System.out.print("Modelo: ");
        String modelo = teclado.nextLine();

        System.out.print("Precio: ");
        double precio = leerDouble();

        System.out.print("Stock: ");
        int stock = leerEntero();

        System.out.print("Sistema operativo: ");
        String sistema = teclado.nextLine();

        System.out.print("Gama (ALTA, MEDIA, BAJA): ");
        CategoriaGama gama = CategoriaGama.valueOf(
                teclado.nextLine().trim().toUpperCase()
        );

        Celular celular = new Celular(
                id, marca, modelo, precio, stock, sistema, gama
        );

        gestorCelulares.registrar(celular);

        System.out.println("Celular registrado correctamente.");
    }

    private static void listarCelulares() {

        System.out.println("\n--- Lista de celulares ---");

        if (gestorCelulares.listar().isEmpty()) {
            System.out.println("No hay celulares registrados.");
            return;
        }

        for (Celular celular : gestorCelulares.listar()) {
            System.out.println(celular);
        }
    }

    private static void registrarCliente() {

        System.out.println("\n--- Registrar cliente ---");

        System.out.print("ID: ");
        int id = leerEntero();

        System.out.print("Nombre: ");
        String nombre = teclado.nextLine();

        System.out.print("Identificacion: ");
        String identificacion = teclado.nextLine();

        System.out.print("Correo: ");
        String correo = teclado.nextLine();

        System.out.print("Telefono: ");
        String telefono = teclado.nextLine();

        Cliente cliente = new Cliente(
                id, nombre, identificacion, correo, telefono
        );

        gestorClientes.registrar(cliente);

        System.out.println("Cliente registrado correctamente.");
    }

    private static void listarClientes() {

        System.out.println("\n--- Lista de clientes ---");

        if (gestorClientes.listar().isEmpty()) {
            System.out.println("No hay clientes registrados.");
            return;
        }

        for (Cliente cliente : gestorClientes.listar()) {
            System.out.println(cliente);
        }
    }

    private static void registrarVenta() {

        System.out.println("\n--- Registrar venta ---");

        if (gestorClientes.listar().isEmpty()
                || gestorCelulares.listar().isEmpty()) {
            System.out.println(
                    "Primero registra al menos un cliente y un celular."
            );
            return;
        }

        System.out.println("Clientes disponibles:");

        for (Cliente cliente : gestorClientes.listar()) {
            System.out.println(cliente);
        }

        System.out.print("ID del cliente: ");
        int idCliente = leerEntero();

        Cliente cliente = gestorClientes.buscarPorId(idCliente);

        if (cliente == null) {
            System.out.println("No existe ese cliente.");
            return;
        }

        System.out.print("ID de la venta: ");
        int idVenta = leerEntero();

        Venta venta = new Venta(idVenta, cliente);

        String continuar;

        do {
            System.out.println("Celulares disponibles:");

            for (Celular celular : gestorCelulares.listar()) {
                System.out.println(celular);
            }

            System.out.print("ID del celular que desea vender: ");
            int idCelular = leerEntero();

            Celular celular = gestorCelulares.buscarPorId(idCelular);

            if (celular == null) {
                System.out.println("No existe ese celular.");
                return;
            }

            System.out.print("Cantidad: ");
            int cantidad = leerEntero();

            venta.agregarItem(new ItemVenta(celular, cantidad));

            System.out.print("¿Agregar otro producto? (s/n): ");
            continuar = teclado.nextLine().trim();

        } while (continuar.equalsIgnoreCase("s"));

        gestorVentas.registrar(venta);

        System.out.println("Venta registrada correctamente.");
        System.out.printf(
                "Subtotal: $%.2f%n", venta.calcularSubtotal()
        );
        System.out.printf(
                "IVA (19%%): $%.2f%n", venta.calcularIVA()
        );
        System.out.printf(
                "TOTAL: $%.2f%n", venta.calcularTotal()
        );
    }

    private static void listarVentas() {

        System.out.println("\n--- Lista de ventas ---");

        if (gestorVentas.listar().isEmpty()) {
            System.out.println("No hay ventas registradas.");
            return;
        }

        for (Venta venta : gestorVentas.listar()) {
            System.out.println(venta);
            System.out.printf(
                    "Total con IVA: $%.2f%n",
                    venta.calcularTotal()
            );
        }
    }

    private static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(teclado.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un numero entero valido: ");
            }
        }
    }

    private static double leerDouble() {
        while (true) {
            try {
                return Double.parseDouble(
                        teclado.nextLine().trim().replace(",", ".")
                );
            } catch (NumberFormatException e) {
                System.out.print("Ingresa un numero valido: ");
            }
        }
    }
}