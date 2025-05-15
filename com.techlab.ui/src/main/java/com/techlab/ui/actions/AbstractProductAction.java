package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.business.products.Product;
import com.techlab.ui.utilities.ConsoleUtil;

public abstract class AbstractProductAction {
    protected Scanner scanner;
    protected ArrayList<Product> products;

    public AbstractProductAction(Scanner scanner, ArrayList<Product> products) {
        this.scanner = scanner;
        this.products = products;
    }

    public abstract void performAction();

    protected abstract void printActionTitle();

    protected Product requestProduct(Scanner scanner) {
        int findType = this.requestFindType(scanner);
        if (findType == 3) {
            return null;
        }

        Product product = null;
        if (findType == 1) {
            product = this.findProductByRequestedId(scanner);
        } else {
            product = this.findProductByRequestedName(scanner);
        }

        return product;
    }

    private int requestFindType(Scanner scanner) {
        int pickedOption = 0;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Buscar producto por:");
            System.out.println("1) Su ID.");
            System.out.println("2) Su nombre.");
            System.out.println("3) Cancelar.");

            int rawPickedOption = scanner.nextInt();
            scanner.nextLine();

            if (rawPickedOption >= 1 || rawPickedOption <= 3) {
                pickedOption = rawPickedOption;
                break;
            }

            System.out.println("Ingresa una opción válida.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return pickedOption;
    }

    private Product findProductByRequestedId(Scanner scanner) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Ingresa la id del producto:");
            int id = scanner.nextInt();
            scanner.nextLine();

            if (id == 0) {
                return null;
            }

            for (Product product : this.products) {
                if (product.getId() == id) {
                    return product;
                }
            }

            System.out.println("Producto no encontrado. Ingresa \"0\" para salir.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    private Product findProductByRequestedName(Scanner scanner) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Ingresa el nombre del producto:");
            String name = scanner.nextLine();

            if (name.trim().equalsIgnoreCase("x")) {
                return null;
            }

            for (Product product : this.products) {
                if (product.getName().equalsIgnoreCase(name)) {
                    return product;
                }
            }

            System.out.println("Producto no encontrado. Ingresa \"x\" para salir.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }
}
