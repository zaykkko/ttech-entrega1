package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.Scanner;

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

            while (true) {
                System.out.print("¿Confirmamos la orden? (S/N)");
                String response = scanner.nextLine();

                if (response.trim().equalsIgnoreCase("S")) {
                    orders.add(order);
                    order.confirm();
                    System.out.println("Orden agregada y confirmada exitósamente.");
                    break;
                }

                if (response.trim().equalsIgnoreCase("N")) {
                    System.out.println("Orden descartada exitósamente.");
                    break;
                }
            }

            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    protected void printActionTitle() {
        System.out.println("======================\n");
        System.out.println("Crear pedido - TechLab");
        System.out.println("======================\n");
    }

    private void askForProducts(Order order, Scanner scanner) {
        while (true) {
            Product product = this.requestProduct(scanner);
            if (product == null) {
                break;
            }

            int quantity = this.requestQuantity(product, scanner);
            if (quantity == 0) {
                break;
            }

            order.addProduct(product, quantity);

            System.out.println("Producto añadido exitósamente a la orden " + order.getId() + ".");
            ConsoleUtil.pressEnterToContinue(scanner);
        }
    }

    private int requestQuantity(Product product, Scanner scanner) {
        int quantity = 0;

        while (true) {
            ConsoleUtil.clearConsole();
            this.printActionTitle();

            System.out.println("¿Cuánto va a comprar de " + product.getCapitalizedName() + "?");
            System.out.println("Stock disponible: " + product.getQuantity() + ". Precio: " + product.getPrice());
            int rawQuantity = scanner.nextInt();

            if (rawQuantity == 0) {
                break;
            }

            if (rawQuantity > 0 && rawQuantity <= product.getQuantity()) {
                quantity = rawQuantity;
                break;
            }

            System.out.println("Ingresa una cantidad válida.");
            ConsoleUtil.pressEnterToContinue(scanner);
        }

        return quantity;
    }
}
