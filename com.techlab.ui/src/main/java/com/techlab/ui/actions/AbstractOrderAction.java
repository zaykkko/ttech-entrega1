package com.techlab.ui.actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.techlab.business.orders.Order;
import com.techlab.business.products.Product;

public abstract class AbstractOrderAction extends AbstractProductAction {
    protected final ArrayList<Order> orders;

    public AbstractOrderAction(Scanner scanner, ArrayList<Product> products, ArrayList<Order> orders) {
        super(scanner, products);
        this.orders = orders;
    }
}
