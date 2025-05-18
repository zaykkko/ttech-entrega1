package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.techlab.business.exceptions.InvalidProductDataException;
import com.techlab.business.exceptions.UnprocesableOrderException;
import com.techlab.business.orders.Order;
import com.techlab.business.products.Product;
import com.techlab.ui.utilities.ConsoleUtil;

public class CreateOrderAction extends AbstractOrderAction {
    public CreateOrderAction(Scanner scanner, ArrayList<Product> products, ArrayList<Order> orders) {
        super(scanner, products, orders);
    }

    public void performAction() {
        Order order = new Order();

        this.askForProducts(order, scanner);

        if (order.getTotalProducts() > 0) {
            System.out.println("Resumen de orden:");
            order.printOrder();

            try {
                while (true) {
                    System.out.print("¿Confirmamos la orden? (S/N)");
                    String response = scanner.nextLine();

                    if (response.trim().equalsIgnoreCase("S")) {
                        try {
                            order.confirm();
                            orders.add(order);
                            System.out.println("Orden agregada y confirmada exitósamente.");
                        } catch (InvalidProductDataException e) {
                            System.out.println("No se pudo confirmar el pedido: " + e.getMessage());
                        }
                        break;
                    }

                    if (response.trim().equalsIgnoreCase("N")) {
                        System.out.println("Orden descartada exitósamente.");
                        break;
                    }
                }
            } catch (UnprocesableOrderException e) {
                System.out.println("No se pudo procesar la orden. Error:" + e.getMessage());
            }
        } else {
            System.out.println("Orden descartada exitósamente.");
        }

        ConsoleUtil.pressEnterToContinue(scanner);
    }

    protected void printActionTitle() {
        System.out.println("======================");
        System.out.println("Crear pedido - TechLab");
        System.out.println("======================\n");
    }

    private void askForProducts(Order order, Scanner scanner) {
        while (true) {
            Product product = this.requestProduct(scanner, String.format("Estás añadiendo productos a la orden N %d.", order.getId()));
            if (product == null) {
                break;
            }

            if (product.getQuantity() == 0) {
                System.out.println("No hay suficiente stock de dicho producto, intentá con otro.");
                ConsoleUtil.pressEnterToContinue(scanner);
                continue;
            }

            int quantity = this.requestQuantity(product, scanner);
            if (quantity == 0) {
                break;
            }

            order.addProduct(product, quantity);

            System.out.println("\nProducto añadido exitósamente.");
            order.printOrder();

            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    private int requestQuantity(Product product, Scanner scanner) {
        int quantity = 0;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("¿Cuánto vas a comprar de \"" + product.getCapitalizedName() + "\"?");
            System.out.println("Stock disponible: " + product.getQuantity() + ". Precio: " + product.getPrice() + ".");

            try {
                int rawQuantity = scanner.nextInt();
                scanner.nextLine();

                if (rawQuantity == 0) {
                    break;
                }

                if (rawQuantity > 0 && rawQuantity <= product.getQuantity()) {
                    quantity = rawQuantity;
                    break;
                }

                System.out.println("Ingresa una cantidad válida. Ingresa \"0\" para cancelar esta acción.");
            } catch (InputMismatchException e) {
                System.out.println("Ingresa una cantidad que sea numérica. Ingresa \"0\" para cancelar esta acción.");
            }

            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return quantity;
    }
}
