package com.company.businessLayer;

import java.io.Serializable;

public class BaseProduct extends MenuItem implements Serializable {
    private String name;
    private double price;

    public BaseProduct(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    void setName(String newName) {
        name = newName;
    }

    @Override
    void setPrice(double newPrice) {
        price = newPrice;
    }

    @Override
    public boolean contains(MenuItem menuItem) {
        return equals(menuItem);
    }

    @Override
    public String toString() {
        return buildString();
    }

    @Override
    public boolean equals(Object menuItem) {
        if (menuItem instanceof BaseProduct) {
            return name.equals(((BaseProduct) menuItem).getName());
        }
        return false;
    }

    private String buildString() {
        int total = 20 - name.length() - Double.toString(price).length();
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        for (int i = 0; i < total; i++) sb.append(" ");
        sb.append(price);
        return sb.toString();
    }
}


