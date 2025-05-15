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

        for (Product product : this.products) {
            System.out.println(product);
        }

        ConsoleUtil.pressEnterToContinue(scanner);
    }

    protected void printActionTitle() {
        System.out.println("==============================\n");
        System.out.println("Eliminar un producto - TechLab");
        System.out.println("==============================\n");
    }
}
