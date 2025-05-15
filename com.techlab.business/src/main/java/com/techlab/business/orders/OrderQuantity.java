package com.techlab.business.orders;

import com.techlab.business.products.Product;
import com.techlab.business.exceptions.InvalidOrderDataException;

public class OrderQuantity {
    private final Product product;
    private int quantity;
    private double savedTotal = 0;

    public OrderQuantity(Product product, int quantity) throws InvalidOrderDataException {
        this.product = product;
        this.setQuantity(quantity);
    }

    public void increaseQuantity(int increaseBy) throws InvalidOrderDataException {
        this.setQuantity(quantity + increaseBy);
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) throws InvalidOrderDataException {
        if (this.product.getQuantity() < quantity) {
            throw new InvalidOrderDataException("La cantidad solicitada supera al stock disponible.");
        }
        this.quantity = quantity;
    }

    public void saveTotal() {
        this.savedTotal = this.getTotal();
    }

    public double getTotal() {
        if (this.savedTotal > 0) {
            return this.savedTotal;
        }

        return this.product.getPrice() * this.quantity;
    }

    @Override
    public String toString() {
        return String.format("%s / %d / %.2f", this.getProduct().getCapitalizedName(), this.getQuantity(), this.getTotal());
    }
}
