package com.company.businessLayer;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CompositeProduct extends MenuItem implements Serializable {
    private List<MenuItem> menuItems;
    private String name;
    private double price;

    public CompositeProduct(List<MenuItem> menuItems, String name, double price) {
        this.menuItems = menuItems;
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
        if(this.equals(menuItem)) return true;
        boolean ret = false;
        for(MenuItem mi : menuItems) ret = ret || mi.contains(menuItem);
        return ret;
    }

    @Override
    public String toString() {
        return buildString();
    }

    @Override
    public boolean equals(Object menuItem) {
        if(menuItem instanceof CompositeProduct) {
            return name.equals(((CompositeProduct)menuItem).getName());
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
