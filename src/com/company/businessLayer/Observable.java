package com.company.businessLayer;

public interface Observable {
    void addObserver(Observer observer);
    void notifyObservers();
}
