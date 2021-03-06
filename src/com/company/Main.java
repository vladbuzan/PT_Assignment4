package com.company;

import com.company.businessLayer.BaseProduct;
import com.company.businessLayer.MenuItem;
import com.company.businessLayer.Order;
import com.company.businessLayer.Restaurant;
import com.company.presentationLayer.AdministratorGraphicalUserInterface;
import com.company.presentationLayer.ChefGraphicalUserInterface;
import com.company.presentationLayer.WaiterGraphicalUserInterface;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;


public class Main {

    public static void main(String[] args) {
		Restaurant restaurant = null;
		try {
			ObjectInputStream is = new ObjectInputStream(new FileInputStream("restaurant.ser"));
			restaurant = (Restaurant) is.readObject();
			is.close();
			System.out.println("read serial");
		} catch (Exception ex) {
			System.out.println("new instance");
			restaurant = new Restaurant();
		}finally {
			AdministratorGraphicalUserInterface adminGUI = new AdministratorGraphicalUserInterface(restaurant);
			WaiterGraphicalUserInterface waiterGUI = new WaiterGraphicalUserInterface(restaurant);
			ChefGraphicalUserInterface chefGUI = new ChefGraphicalUserInterface(restaurant);
			restaurant.notifyObservers();
		}

    }
}
