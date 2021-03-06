package com.company.dataLayer;

import com.company.businessLayer.MenuItem;
import com.company.businessLayer.Order;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collection;


public class OrderWriter {

    public static void writeBill(Order order, Collection<MenuItem> menuItemList, double price) throws IOException {
        File billFile = new File("Order" + order.getOrderID() +"_" + order.getTableNo() + ".txt");
        if(billFile.createNewFile()) {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(billFile));
            for(MenuItem menuItem : menuItemList) {
                bufferedWriter.write(menuItem.toString() + '\n');
            }
            bufferedWriter.write("Total:    " + price);
            bufferedWriter.close();
        } else throw new IOException("Bill already created");
    }
}
