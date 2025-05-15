package com.techlab.business.products;

import com.techlab.business.utilities.StringUtil;
import com.techlab.business.exceptions.InvalidProductDataException;

public class Product {
    private final int id;
    private int quantity;
    private String name;
    private double price;

    private static int lastId = 0;

    public Product(int id, String name, int quantity, double price) throws InvalidProductDataException {
        if (id < lastId) {
            throw new InvalidProductDataException("El id de producto " + id + " es menor al id auto-generado. Es muy probable que haya una colisión.");
        }
        if (id > lastId) {
            lastId = id;
        }
        this.id = id;

        this.setName(name.trim());
        this.setQuantity(quantity);
        this.setPrice(price);
    }

    public Product(String name, int quantity, double price) throws InvalidProductDataException {
        this(Product.getNextId(), name, quantity, price);
    }

    public void increaseQuantity(int quantity) {
        this.setQuantity(this.quantity + quantity);
    }

    public void decreaseQuantity(int quantity) throws InvalidProductDataException {
        if (quantity <= this.quantity) {
            throw new InvalidProductDataException("No se puede reducir el stock ya que la cantidad solicitada supera al stock disponible.");
        }

        this.setQuantity(this.quantity - quantity);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws InvalidProductDataException {
        if (price < 0) {
            throw new InvalidProductDataException("El precio del producto es inválido. Id de producto: " + id + ".");
        }
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getCapitalizedName() {
        return StringUtil.toTitleCase(name);
    }

    public void setName(String name) throws InvalidProductDataException {
        if (name == null || name.isEmpty()) {
            throw new InvalidProductDataException("El nombre del producto es inválido. Id de producto: " + id + ".");
        }
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws InvalidProductDataException {
        if (quantity < 0) {
            throw new InvalidProductDataException("La cantidad del producto es inválida. Id de producto: " + id + ".");
        }
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("%d / %s / %.2f / %d", id, this.getCapitalizedName(), price, quantity);
    }

    private static int getNextId() {
        return ++lastId;
    }
}
