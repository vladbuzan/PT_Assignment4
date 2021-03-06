package com.company.businessLayer;

import java.io.Serializable;

public abstract class MenuItem implements Serializable {

    public abstract String getName();
    public abstract double getPrice();
    abstract void setName(String newName);
    abstract void setPrice(double newPrice);
    public abstract boolean contains(MenuItem menuItem);


}
