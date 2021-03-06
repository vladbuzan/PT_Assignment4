package com.company.presentationLayer;

import com.company.businessLayer.IRestaurantProcessing;
import com.company.businessLayer.Observable;
import com.company.businessLayer.Observer;
import com.company.businessLayer.Order;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class WaiterGraphicalUserInterface extends JFrame implements Observer {
    private JButton orderButton;
    private JButton billButton;
    private JList<Order> orders;
    private DefaultListModel<Order> listModel;
    IRestaurantProcessing restaurantProcessing;

    public WaiterGraphicalUserInterface(IRestaurantProcessing restaurantProcessing) {
        super("Waiter");
        ((Observable)restaurantProcessing).addObserver(this);
        this.restaurantProcessing = restaurantProcessing;
        JPanel mainPanel = new JPanel(); //instantiating the mainly used panels for
        JPanel listPanel = new JPanel(); // grouping the buttons and the list
        JPanel buttonPanel = new JPanel();

        listModel = new DefaultListModel<>(); //list panel
        listModel.addAll(restaurantProcessing.getOrders());
        orders = new JList<>(listModel);
        JScrollPane ordersSP = new JScrollPane(this.orders);
        this.orders.setFixedCellWidth(330);
        listPanel.add(ordersSP);
        listPanel.setBounds(10, 20,340,200);

        orderButton = new JButton("Order");  //handling the button panel
        billButton = new JButton("Bill");
        buttonPanel.add(orderButton);
        buttonPanel.add(billButton);
        buttonPanel.setBounds(370,50,100,200);

        mainPanel.setLayout(null); //main panel
        mainPanel.add(listPanel);
        mainPanel.add(buttonPanel);

        initListeners();
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                restaurantProcessing.serialize();
            }
        });
        setContentPane(mainPanel);
        setSize(500, 250);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setVisible(true);
    }

    private void initListeners() {
        orderButton.addActionListener(event -> new AddOrderWindow(restaurantProcessing));
        billButton.addActionListener(event -> {
            if(orders.getSelectedValue() != null) restaurantProcessing.generateBill(orders.getSelectedValue());
        });
    }

    @Override
    public void updateFields() {
        listModel.clear();
        listModel.addAll(restaurantProcessing.getOrders());
    }
}
