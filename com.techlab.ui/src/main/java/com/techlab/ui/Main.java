package com.techlab.ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.techlab.business.orders.Order;
import com.techlab.business.products.Product;
import com.techlab.ui.actions.*;
import com.techlab.ui.utilities.ConsoleUtil;

public class Main {
    public static void main(String[] args) {
        boolean continueLoop = true;

        final Scanner scanner = new Scanner(System.in);
        final ArrayList<Product> products = new ArrayList<>();
        final ArrayList<Order> orders = new ArrayList<>();

        while (continueLoop) {
            ConsoleUtil.clearConsole();

            Main.printHomeTitle();
            Main.printHomeOptions();

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                switch (option) {
                    case 1 -> new AddProductAction(scanner, products).performAction();
                    case 2 -> new ListProductsAction(scanner, products).performAction();
                    case 3 -> new FindAndUpdateProductAction(scanner, products).performAction();
                    case 4 -> new DeleteProductAction(scanner, products).performAction();
                    case 5 -> new CreateOrderAction(scanner, products, orders).performAction();
                    case 6 -> new ListOrdersAction(scanner, products, orders).performAction();
                    case 7 -> continueLoop = false;
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una opción numérica válida.");
                ConsoleUtil.pressEnterToContinue(scanner);
            }
        }

        scanner.close();
    }

    public static void printHomeTitle() {
        System.out.println("============================");
        System.out.println("Sistema de gestión - TechLab");
        System.out.println("============================\n");
    }

    public static void printHomeOptions() {
        System.out.println("1) Agregar producto.");
        System.out.println("2) Listar productos.");
        System.out.println("3) Buscar y actualizar producto.");
        System.out.println("4) Eliminar producto.");
        System.out.println("5) Crear un pedido.");
        System.out.println("6) Listar pedidos.");
        System.out.println("7) Salir");
    }
}
