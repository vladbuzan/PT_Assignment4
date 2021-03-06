package com.company.presentationLayer;

import com.company.businessLayer.*;
import com.company.businessLayer.MenuItem;
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AddItemWindow extends JFrame implements Observer{
    private JButton addButton;
    private JList<MenuItem> menuItemList;
    private IRestaurantProcessing restaurantProcessing;
    private JTextField nameTF;
    private JTextField priceTF;
    private DefaultListModel<MenuItem> listModel;

    public AddItemWindow (IRestaurantProcessing restaurantProcessing) {
        super("Add");

        listModel = new DefaultListModel<MenuItem>();
        this.restaurantProcessing = restaurantProcessing;
        listModel.addAll(restaurantProcessing.getMenuItemList());
        menuItemList = new JList<>(listModel);

        JPanel mainPanel = new JPanel(); //main panels
        JPanel namePanel = new JPanel();
        JPanel pricePanel = new JPanel();
        JPanel listPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel infoPanel = new JPanel();

        JLabel nameLabel = new JLabel("Name:"); //labels
        JLabel priceLabel = new JLabel("Price:");
        JLabel infoLabel = new JLabel("Hold ctrl to select multiple products");

        nameTF = new JTextField(15); //text fields
        priceTF = new JTextField(15);

        namePanel.setLayout(new FlowLayout()); //name panel
        namePanel.add(nameLabel);
        namePanel.add(nameTF);

        pricePanel.setLayout(new FlowLayout()); //price panel
        pricePanel.add(priceLabel);
        pricePanel.add(priceTF);

        infoPanel.add(namePanel); //info panel
        infoPanel.add(pricePanel);
        infoPanel.add(infoLabel);

        menuItemList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);//list panel
        JScrollPane menuItemSP = new JScrollPane(menuItemList);
        listPanel.setLayout(null);
        listPanel.add(menuItemSP);
        menuItemSP.setSize(230,100);

        addButton = new JButton("Add"); //button panel
        buttonPanel.add(addButton);
        addButton.addActionListener(event -> { //add listener as lambda expression
            List<MenuItem> list = menuItemList.getSelectedValuesList();
            try {
                if(list.size() == 0) {
                    BaseProduct baseProduct = new BaseProduct(nameTF.getText(), Double.parseDouble(priceTF.getText()));
                    if(baseProduct.getPrice() <= 0) {
                        JOptionPane.showMessageDialog(null, "Negative price", "Dialog",  JOptionPane.INFORMATION_MESSAGE);
                    }
                    restaurantProcessing.insertMenuItem(baseProduct);
                } else {
                    CompositeProduct compositeProduct = new CompositeProduct(list, nameTF.getText(), Double.parseDouble(priceTF.getText()));
                    restaurantProcessing.insertMenuItem(compositeProduct);
                }
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Dialog",  JOptionPane.INFORMATION_MESSAGE);
            }

        } );

        mainPanel.setLayout(new GridLayout(3,1)); //main panel
        mainPanel.add(infoPanel);
        mainPanel.add(listPanel);
        mainPanel.add(buttonPanel);

        setContentPane(mainPanel);
        setResizable(false);
        setSize(250,350);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void updateFields() {
        listModel.clear();
        listModel.addAll(restaurantProcessing.getMenuItemList());
    }
}
