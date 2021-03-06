package com.company.presentationLayer;

import com.company.businessLayer.IRestaurantProcessing;
import com.company.businessLayer.MenuItem;
import com.company.businessLayer.Observable;
import com.company.businessLayer.Observer;

import javax.swing.*;
import java.awt.*;


public class ModifyWindow extends JFrame{
    private JButton modifyButton;
    private JTextField nameTF;
    private JTextField priceTF;


    public ModifyWindow(MenuItem menuItem, IRestaurantProcessing restaurantProcessing) {
        super("Modify");


        JPanel mainPanel = new JPanel(); //mainly used panels
        JPanel namePanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel buttonPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name: "); //labels
        JLabel priceLabel = new JLabel("Price: ");

        nameTF = new JTextField(menuItem.getName()); //text fields
        priceTF = new JTextField(Double.toString(menuItem.getPrice()));
        nameTF.setColumns(10);
        priceTF.setColumns(10);

        namePanel.setLayout(new FlowLayout()); //name panel
        namePanel.add(nameLabel);
        namePanel.add(nameTF);

        modifyButton = new JButton("Modify"); //button panel
        buttonPanel.setSize(50,20);
        modifyButton.addActionListener(event -> {
            restaurantProcessing.editMenuItem(menuItem, nameTF.getText(), Double.parseDouble(priceTF.getText()));
            dispose();
        });
        buttonPanel.add(modifyButton);

        pricePanel.setLayout(new FlowLayout()); //price panel
        pricePanel.add(priceLabel);
        pricePanel.add(priceTF);

        mainPanel.setLayout(new GridLayout(3, 1)); //main panel
        mainPanel.add(namePanel);
        mainPanel.add(pricePanel);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
        setResizable(false);
        setSize(200,150);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
