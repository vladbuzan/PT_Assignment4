package com.company.dataLayer;

import com.company.businessLayer.Restaurant;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class RestaurantSerializator {
    public static void serialize(Restaurant restaurant) {
        try {
            FileOutputStream fs = new FileOutputStream("restaurant.ser");
            ObjectOutputStream os = new ObjectOutputStream(fs);
            os.writeObject(restaurant);
            os.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
