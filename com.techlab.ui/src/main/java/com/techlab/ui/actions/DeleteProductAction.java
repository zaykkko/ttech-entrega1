package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.business.products.Product;
import com.techlab.ui.utilities.ConsoleUtil;

public class DeleteProductAction extends AbstractProductAction {
    public DeleteProductAction(Scanner scanner, ArrayList<Product> products) {
        super(scanner, products);
    }

    public void performAction() {
        Product product = this.requestProduct(scanner);
        if (product == null) {
            return;
        }

        System.out.println("Producto: " + product);

        while (true) {
            System.out.println("¿Estás seguro que querés eliminarlo? (S/N) ");
            String response = scanner.nextLine();

            if (response.trim().equalsIgnoreCase("S")) {
                products.remove(product);
                System.out.println("Producto eliminado exitósamente.");
                ConsoleUtil.pressEnterToContinue(scanner);
                break;
            }

            if (response.equalsIgnoreCase("N")) {
                break;
            }
        }
    }

    protected void printActionTitle() {
        System.out.println("=========================");
        System.out.println("Editar producto - TechLab");
        System.out.println("=========================\n");
    }
}
