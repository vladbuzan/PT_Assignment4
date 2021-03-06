package com.company.businessLayer;

import com.company.dataLayer.OrderWriter;
import com.company.dataLayer.RestaurantSerializator;

import javax.swing.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Class used to represent a restaurant.
 * The orderItemMap field is used to store
 * orders alongside the corresponding products.
 * The menuItems list represents all the
 * available product within the restaurant menu.
 * The observers list holds all the objects that
 * need to be updated when certain changes
 * occur within this class
 */
public class Restaurant implements Observable, IRestaurantProcessing, Serializable {
    private Map<Order, Collection<MenuItem>> orderItemMap;
    private List<MenuItem> menuItems;
    private ArrayList<Observer> observers;

    public Restaurant() {
        orderItemMap = new HashMap<Order, Collection<MenuItem>>();
        menuItems = new ArrayList<>();
        observers = new ArrayList<>();
    }

    /**
     * Used to generate a single String instance
     * containing all the information corresponding
     * to the current order passed as parameter.
     * @param order the order
     * @param menuItems the menuItems corresponding to the given order
     * @return String containing information about the order
     * to be displayed to the Chef GUI
     */
    private String generateChefOrder(Order order, Collection<MenuItem> menuItems) {
        StringBuilder sb = new StringBuilder();
        sb.append("Table: " + order.getTableNo() + "\n");
        menuItems.forEach(menuItem -> sb.append(menuItem.getName() + "\n"));
        return sb.toString();
    }
    @Override
    public void insertMenuItem(MenuItem menuItem) {
        assert menuItem != null;
        assert menuItem.getName() != null;
        assert menuItem.getPrice() > 0.0;
        if(!menuItems.contains(menuItem)) {
            menuItems.add(menuItem);
            notifyObservers();
        }
    }

    @Override
    public void deleteMenuItem(MenuItem menuItem) {
        assert menuItem != null;
        assert menuItem.contains(menuItem);
        Iterator<MenuItem> it = menuItems.iterator();
        while(it.hasNext()) {
            MenuItem current = it.next();
            if(current.contains(menuItem)) {
                it.remove();
            }
        }
        notifyObservers();
    }

    @Override
    public void editMenuItem(MenuItem menuItem, String newName, double newPrice) {
        assert menuItems.contains(menuItem);
        assert newName != null;
        assert newPrice >= 0.0;
        menuItem.setName(newName);
        menuItem.setPrice(newPrice);
        notifyObservers();
    }

    @Override
    public void insertOrder(Order order, List<MenuItem> menuItemList) throws IOException {
        assert order != null;
        assert menuItemList.size() > 0;
        if(!orderItemMap.containsKey(order)) {
            orderItemMap.put(order, menuItemList);
            notifyObservers();
        } else throw new IOException("ID already in order list");
    }

    @Override
    public void generateBill(Order order) {
        assert order != null;
        try{
            OrderWriter.writeBill(order, orderItemMap.get(order), getTotalPrice(order));
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Dialog",  JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public List<MenuItem> getMenuItemList() {
        return menuItems;
    }

    @Override
    public Collection<Order> getOrders() {
        return orderItemMap.keySet();
    }

    @Override
    public String getChefOrders() {
        StringBuilder sb = new StringBuilder();
        Collection<Order> orders = getOrders();
        Collection<MenuItem> menuItems;
        for(Order order : orders) {
            menuItems = orderItemMap.get(order);
            sb.append(generateChefOrder(order, menuItems));
        }
        return sb.toString();
    }

    @Override
    public void serialize() {
        observers.clear();
        RestaurantSerializator.serialize(this);
    }

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(Observer::updateFields);
    }

    /**
     * Used to compute the total price
     * of all the menuItems for a specific order.
     * @param order the order whose price one may need
     * @return double representing the total price
     */
    public double getTotalPrice(Order order) {
        double total = 0;
        for(MenuItem menuItem : orderItemMap.get(order)) {
            total += menuItem.getPrice();
        }
        return total;
    }
}
