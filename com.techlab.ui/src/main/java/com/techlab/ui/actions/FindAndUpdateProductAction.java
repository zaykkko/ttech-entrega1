package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.techlab.business.exceptions.InvalidProductDataException;
import com.techlab.business.products.Product;
import com.techlab.ui.utilities.ConsoleUtil;

public class FindAndUpdateProductAction extends AbstractProductAction {
    public FindAndUpdateProductAction(Scanner scanner, ArrayList<Product> products) {
        super(scanner, products);
    }

    public void performAction() {
        Product product = this.requestProduct(scanner);
        if (product == null) {
            return;
        }

        this.askForUpdates(product);
    }

    protected void printActionTitle() {
        System.out.println("=========================");
        System.out.println("Editar producto - TechLab");
        System.out.println("=========================\n");
    }

    private void askForUpdates(Product product) {
        Scanner scanner = new Scanner(System.in);
        boolean continueUpdating = true;

        while (continueUpdating) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Producto: " + product + ".\n");

            System.out.println("¿Qué quiere actualizar?");
            System.out.println("1) Nombre.");
            System.out.println("2) Precio.");
            System.out.println("3) Añadir stock.");
            System.out.println("4) Salir.");

            try {
                int option = scanner.nextInt();
                scanner.nextLine();

                try {
                    switch (option) {
                        case 1 -> this.updateProductName(product, scanner);
                        case 2 -> this.updateProductPrice(product, scanner);
                        case 3 -> this.increaseProductStock(product, scanner);
                        case 4 -> continueUpdating = false;
                        default -> {
                            System.out.println("Ingresa una opción válida.");
                            ConsoleUtil.pressEnterToContinue(scanner);
                        }
                    }
                } catch (InvalidProductDataException exception) {
                    System.out.println("No se pudo actualizar el producto: " + exception.getMessage());
                }
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una opción que sea numérica.");
            }
        }
    }

    private void updateProductName(Product product, Scanner scanner) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Producto: " + product + ".\n");

            System.out.println("Ingresa el nuevo nombre (\"x\" para cancelar): ");
            String name = scanner.nextLine();

            if (name.trim().equalsIgnoreCase("x")) {
                break;
            }

            if (!name.trim().isEmpty()) {
                product.setName(name.trim());
                break;
            }

            System.out.println("Ingresa un nombre válido.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    private void updateProductPrice(Product product, Scanner scanner) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Producto: " + product + ".\n");

            System.out.println("Ingresa el nuevo precio (0 para cancelar): ");

            try {
                double price = scanner.nextDouble();
                scanner.nextLine();

                if (price == 0) {
                    break;
                }

                if (price > 0) {
                    product.setPrice(price);
                    break;
                }

                System.out.println("Ingresa un precio válido. Ingresa \"0\" para cancelar esta acción.");
            } catch (InputMismatchException e) {
                System.out.println("Ingresa un precio numérico. Ingresa \"0\" para cancelar esta acción.");
            }

            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    private void increaseProductStock(Product product, Scanner scanner) {
        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("Producto: " + product + ".\n");

            System.out.println("Ingresa la cantidad a añadir (0 para cancelar): ");

            try {
                int stock = scanner.nextInt();
                scanner.nextLine();

                if (stock == 0) {
                    return;
                }

                if (stock > 0) {
                    product.increaseQuantity(stock);
                    break;
                }

                System.out.println("Ingresa una cantidad positiva. Ingresa \"0\" para cancelar esta acción.");
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una cantidad numérica. Ingresa \"0\" para cancelar esta acción.");
            }

            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }
}
