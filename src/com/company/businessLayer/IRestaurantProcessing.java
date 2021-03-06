package com.company.businessLayer;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Interface used to describe the interaction
 * between the Restaurant class and the GUI classes.
 */
public interface IRestaurantProcessing {
    /**
     * Inserts the given menuItem in the
     * Restaurant's menu
     * @param menuItem menuItem to be inserted
     */
    public void insertMenuItem(MenuItem menuItem);

    /**
     * Removes a menuItem from the
     * Restaurant's menu
     * @param menuItem menuItem to be deleted
     */
    public void deleteMenuItem(MenuItem menuItem);

    /**
     * Used to edit the properties of a
     * menuItem
     * @param menuItem menuItem whose properties are updated
     * @param newName String representing the new name of the product
     * @param newPrice double representing the new price of the product
     */
    public void editMenuItem(MenuItem menuItem, String newName, double newPrice);

    /**
     * Used to add a new order
     * @param order order to be added
     * @param menuItemList list of menuItems corresponding
     *                     to the given order
     * @throws IOException
     */
    public void insertOrder(Order order, List<MenuItem> menuItemList) throws IOException;

    /**
     * Used to generate a txt file containing
     * billing information corresponding
     * to the order passed as parameter
     * @param order
     */
    public void generateBill(Order order);

    /**
     * Used to obtain the menuItems
     * within the Restaurant's menu
     * @return list containing MenuItems
     */
    public List<MenuItem> getMenuItemList();

    /**
     * Used to obtain orders
     * @return Collection containing orders
     */
    public Collection<Order> getOrders();

    /**
     * Used to obtain orders and menuItems corresponding
     * to them in a String format
     * @return String containing orders for the chef
     */
    public String getChefOrders();

    /**
     * Used to serialize the
     * Restaurant object
     */
    public void serialize();
}
