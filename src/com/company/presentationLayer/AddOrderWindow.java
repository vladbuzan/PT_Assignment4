package com.company.presentationLayer;

import com.company.businessLayer.*;
import com.company.businessLayer.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddOrderWindow extends JFrame implements Observer{
    private JButton orderButton;
    private JTextField tableTF;
    private JTextField idTF;
    private ArrayList<Observer> observers;
    private IRestaurantProcessing restaurantProcessing;
    private JList<MenuItem> menuItemList;
    private DefaultListModel<MenuItem> listModel;

    public AddOrderWindow(IRestaurantProcessing restaurantProcessing) {
        super("Order");

        ((Observable)restaurantProcessing).addObserver(this);
        observers = new ArrayList<>();
        listModel = new DefaultListModel<>();
        this.restaurantProcessing = restaurantProcessing;
        listModel.addAll(restaurantProcessing.getMenuItemList());
        menuItemList = new JList<>(listModel);

        JPanel mainPanel = new JPanel(); //main panels
        JPanel tablePanel = new JPanel();
        JPanel idPanel = new JPanel();
        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel infoPanel = new JPanel();

        JLabel tableLabel = new JLabel("table:"); //labels
        JLabel idLabel = new JLabel("id:");
        JLabel infoLabel = new JLabel("Hold ctrl to select multiple products");

        tableTF = new JTextField(10); //text fields
        idTF = new JTextField(10);

        tablePanel.setLayout(new FlowLayout()); //table panel
        tablePanel.add(tableLabel);
        tablePanel.add(tableTF);

        idPanel.setLayout(new FlowLayout()); //id panel
        idPanel.add(idLabel);
        idPanel.add(idTF);

        infoPanel.add(tablePanel); //info panel
        infoPanel.add(idPanel);
        infoPanel.add(infoLabel);

        menuItemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//list panel
        JScrollPane menuItemSP = new JScrollPane(menuItemList);
        listPanel.setLayout(null);
        listPanel.add(menuItemSP);
        menuItemSP.setSize(230,100);

        orderButton = new JButton("Order"); //button panel
        buttonPanel.setSize(50,20);
        buttonPanel.add(orderButton);
        orderButton.addActionListener(event -> {
            List<MenuItem> list = menuItemList.getSelectedValuesList();
            if(list.size() > 0) {
                try {
                    Order order = new Order(Integer.parseInt(idTF.getText()), Integer.parseInt(tableTF.getText()));
                    restaurantProcessing.insertOrder(order, list);
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "Dialog",  JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        mainPanel.setLayout(new GridLayout(3,1));
        mainPanel.add(infoPanel);
        mainPanel.add(listPanel);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
        setSize(250,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    @Override
    public void updateFields() {
        listModel.clear();
        listModel.addAll(restaurantProcessing.getMenuItemList());
    }
}
