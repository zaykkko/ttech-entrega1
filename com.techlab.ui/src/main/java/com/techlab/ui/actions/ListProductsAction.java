package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.business.products.Product;
import com.techlab.ui.utilities.ConsoleUtil;

public class ListProductsAction extends AbstractProductAction {
    public ListProductsAction(Scanner scanner, ArrayList<Product> products) {
        super(scanner, products);
    }

    public void performAction() {
        ConsoleUtil.clearConsole();
        this.printActionTitle();

        if (this.products.isEmpty()) {
            System.out.println("Todavía no se agregó ningún producto.\n");
        } else {
            for (Product product : this.products) {
                System.out.println(product);
            }
            System.out.println("\n");
        }

        ConsoleUtil.pressEnterToContinue(scanner);
    }

    protected void printActionTitle() {
        System.out.println("==========================");
        System.out.println("Listar productos - TechLab");
        System.out.println("==========================\n");
    }
}
