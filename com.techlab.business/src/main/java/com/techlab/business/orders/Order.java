package com.techlab.business.orders;

import com.techlab.business.exceptions.InvalidOrderDataException;
import com.techlab.business.exceptions.UnprocesableOrderException;
import com.techlab.business.products.Product;

import java.util.ArrayList;

public class Order {
    private final int id;
    private final ArrayList<OrderQuantity> orders = new ArrayList<>();

    private static int lastId = 0;

    public Order(int id) {
        if (id < lastId) {
            throw new InvalidOrderDataException("El id de producto " + id + " es menor al id auto-generado. Es muy probable que haya una colisión.");
        }
        if (id > lastId) {
            lastId = id;
        }
        this.id = id;
    }

    public Order() {
        this(Order.getNextId());
    }

    public void addProduct(Product product, int quantity) throws InvalidOrderDataException {
        OrderQuantity orderQuantity = this.findOrderQuantity(product);
        if (orderQuantity == null) {
            orderQuantity = new OrderQuantity(product, quantity);
            orders.add(orderQuantity);
        } else {
            orderQuantity.increaseQuantity(quantity);
        }
    }

    public void confirm() throws UnprocesableOrderException {
        if (!this.isProcessable()) {
            throw new UnprocesableOrderException("No hay suficiente stock de productos para procesar la orden " + id + ".");
        }

        for (OrderQuantity orderQuantity : orders) {
            orderQuantity.saveTotal();
            orderQuantity.getProduct().decreaseQuantity(orderQuantity.getQuantity());
        }
    }

    public boolean isProcessable() {
        for (OrderQuantity orderQuantity : orders) {
            if (orderQuantity.getQuantity() > orderQuantity.getProduct().getQuantity()) {
                return false;
            }
        }

        return true;
    }

    public OrderQuantity findOrderQuantity(Product product) {
        for (OrderQuantity order : orders) {
            if (order.getProduct() == product) {
                return order;
            }
        }

        return null;
    }

    public int getId() {
        return id;
    }

    public double getTotal() {
        double total = 0;

        for (OrderQuantity order : orders) {
            total += order.getTotal();
        }

        return total;
    }

    public int getTotalProducts() {
        return this.orders.size();
    }

    public void printOrder() {
        System.out.println("Detalles de la orden " + id + ":");
        System.out.println("Nombre / Cantidad / Precio");
        for (OrderQuantity orderQuantity : orders) {
            System.out.println(orderQuantity);
        }
        System.out.println("Total a pagar: " + this.getTotal());
    }

    private static int getNextId() {
        return ++lastId;
    }
}
