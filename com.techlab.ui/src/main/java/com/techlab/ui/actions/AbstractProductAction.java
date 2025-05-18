package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.InputMismatchException;
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

    protected Product requestProduct(Scanner scanner, String subtitle) {
        int findType = this.requestFindType(scanner, subtitle);
        if (findType == 3) {
            return null;
        }

        Product product = null;
        if (findType == 1) {
            product = this.findProductByRequestedId(scanner, subtitle);
        } else {
            product = this.findProductByRequestedName(scanner, subtitle);
        }

        return product;
    }

    protected Product requestProduct(Scanner scanner) {
        return requestProduct(scanner, null);
    }

    private int requestFindType(Scanner scanner, String subtitle) {
        int pickedOption = 0;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            if (subtitle != null) {
                System.out.println(subtitle);
            }

            System.out.println("Buscar producto por:");
            System.out.println("1) Su ID.");
            System.out.println("2) Su nombre.");
            System.out.println("3) Cancelar.");

            try {
                int rawPickedOption = scanner.nextInt();
                scanner.nextLine();

                if (rawPickedOption >= 1 && rawPickedOption <= 3) {
                    pickedOption = rawPickedOption;
                    break;
                }

                System.out.println("Ingresa una opción que exista.");
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una que sea numérica.");
            }

            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return pickedOption;
    }

    private Product findProductByRequestedId(Scanner scanner, String subtitle) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            if (subtitle != null) {
                System.out.println(subtitle);
            }

            System.out.println("Ingresa la id del producto:");

            try {
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
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una id de producto numérica. Ingresa \"0\" para salir.");
            }

            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    private Product findProductByRequestedName(Scanner scanner, String subtitle) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            if (subtitle != null) {
                System.out.println(subtitle);
            }

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
