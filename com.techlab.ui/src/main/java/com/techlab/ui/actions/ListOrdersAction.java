package com.techlab.ui.actions;

import com.techlab.business.orders.Order;
import com.techlab.business.products.Product;
import com.techlab.ui.utilities.ConsoleUtil;

import java.util.ArrayList;
import java.util.Scanner;

public class ListOrdersAction extends AbstractOrderAction {
    public ListOrdersAction(Scanner scanner, ArrayList<Product> products, ArrayList<Order> orders) {
        super(scanner, products, orders);
    }

    public void performAction() {
        ConsoleUtil.clearConsole();
        this.printActionTitle();

        for (Order order : this.orders) {
            order.printOrder();
            System.out.print("\n");
        }

        ConsoleUtil.pressEnterToContinue(scanner);
    }

    protected void printActionTitle() {
        System.out.println("======================\n");
        System.out.println("Listar pedidos - TechLab");
        System.out.println("======================\n");
    }
}
