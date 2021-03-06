package com.company.presentationLayer;

import com.company.businessLayer.IRestaurantProcessing;
import com.company.businessLayer.Observable;
import com.company.businessLayer.Observer;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ChefGraphicalUserInterface extends JFrame implements Observer {
    private IRestaurantProcessing restaurantProcessing;
    private JTextArea ordersTA;

    public ChefGraphicalUserInterface(IRestaurantProcessing restaurantProcessing) {
        super("Chef");

        ((Observable)restaurantProcessing).addObserver(this);
        this.restaurantProcessing = restaurantProcessing;

        JPanel mainPanel = new JPanel(); //main panel

        ordersTA = new JTextArea(); //text are
        ordersTA.setRows(25);
        ordersTA.setColumns(40);
        ordersTA.setEditable(false);
        ordersTA.setWrapStyleWord(true);
        ordersTA.setLineWrap(true);

        JScrollPane orderSP = new JScrollPane(ordersTA);
        mainPanel.add(orderSP);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                restaurantProcessing.serialize();
            }
        });
        setSize(420,450);
        setResizable(false);
        setContentPane(mainPanel);
        setVisible(true);
    }

    @Override
    public void updateFields() {
        ordersTA.setText(restaurantProcessing.getChefOrders());
    }
}
