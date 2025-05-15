package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.business.products.Product;
import com.techlab.business.exceptions.InvalidProductDataException;
import com.techlab.ui.utilities.ConsoleUtil;

public class AddProductAction extends AbstractProductAction {
    public AddProductAction(Scanner scanner, ArrayList<Product> products) {
        super(scanner, products);
    }

    public void performAction() throws InvalidProductDataException {
        ConsoleUtil.clearConsole();

        String name = this.requestName(scanner);
        if (name == null) {
            this.printActionCancelled(scanner);
            scanner.close();
            return;
        }

        int stock = this.requestStock(scanner);
        if (stock == 0) {
            this.printActionCancelled(scanner);
            scanner.close();
            return;
        }

        double price = this.requestPrice(scanner);
        if (price == 0) {
            this.printActionCancelled(scanner);
            scanner.close();
            return;
        }

        ConsoleUtil.clearConsole();
        this.printActionTitle();

        try {
            Product product = new Product(name, stock, price);
            this.products.add(product);

            System.out.println("Producto añadido exitósamente: " + product);
        } catch (InvalidProductDataException exception) {
            System.out.println("No pudimos añadir el producto: " + exception.getMessage());
        }

        ConsoleUtil.pressEnterToContinue(scanner);
    }

    protected void printActionTitle() {
        System.out.println("=========================\n");
        System.out.println("Añadir producto - TechLab");
        System.out.println("=========================\n");
    }

    private void printActionCancelled(Scanner scanner) {
        System.out.println("Acción de añadir producto cancelada.");
        ConsoleUtil.pressEnterToContinue(scanner);
    }

    private String requestName(Scanner scanner) {
        String name = null;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.print("Ingrese el nombre del producto: ");
            String rawProductName = scanner.nextLine();
            if (rawProductName.trim().equalsIgnoreCase("x")) {
                break;
            }

            if (!rawProductName.trim().isEmpty()) {
                name = rawProductName.trim();
                break;
            }

            System.out.println("Ingresa un nombre válido. Ingresa \"x\" para salir de esta acción.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return name;
    }

    private int requestStock(Scanner scanner) {
        int stock = 0;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.print("Ingrese el stock del producto: ");
            int rawProductStock = scanner.nextInt();
            scanner.nextLine();

            if (rawProductStock == 0) {
                break;
            }

            if (rawProductStock > 0) {
                stock = rawProductStock;
                break;
            }

            System.out.println("Ingresa un stock válido. Ingresa \"0\" para salir de esta acción.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return stock;
    }

    private double requestPrice(Scanner scanner) {
        double price = 0;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.print("Ingrese el precio del producto: ");
            double rawProductPrice = scanner.nextDouble();
            scanner.nextLine();

            if (rawProductPrice == 0) {
                break;
            }

            if (rawProductPrice > 0) {
                price = rawProductPrice;
                break;
            }

            System.out.println("Ingresa un precio válido. Ingresa \"0\" para salir de esta acción.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return price;
    }
}
