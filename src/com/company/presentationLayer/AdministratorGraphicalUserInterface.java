package com.company.presentationLayer;

import com.company.businessLayer.IRestaurantProcessing;
import com.company.businessLayer.MenuItem;
import com.company.businessLayer.Observable;
import com.company.businessLayer.Observer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.List;

public class AdministratorGraphicalUserInterface extends JFrame implements Observer {
    private JButton addButton;
    private JButton deleteButton;
    private JButton modifyButton;
    private JList<MenuItem> menuItemList;
    private DefaultListModel<MenuItem> listModel;
    private IRestaurantProcessing restaurantProcessing;

    public AdministratorGraphicalUserInterface(IRestaurantProcessing restaurantProcessing) {
        super("Administrator");

        ((Observable)restaurantProcessing).addObserver(this);
        listModel = new DefaultListModel<>();
        listModel.addAll(restaurantProcessing.getMenuItemList());
        this.restaurantProcessing = restaurantProcessing;
        menuItemList = new JList(listModel);

        JPanel mainPanel = new JPanel(); //instantiating the mainly used panels for grouping
        JPanel listPanel = new JPanel(); //the buttons and the list
        JPanel buttonPanel = new JPanel();

        JScrollPane menuItemSP = new JScrollPane(this.menuItemList); //handling the list panel
        this.menuItemList.setFixedCellWidth(130);
        this.menuItemList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listPanel.add(menuItemSP);

        addButton = new JButton("Add"); //handling the button panel
        deleteButton = new JButton("Delete");
        modifyButton = new JButton("Modify");
        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(modifyButton);

        mainPanel.setLayout(new GridLayout(1,2)); //main panel
        mainPanel.add(listPanel);
        mainPanel.add(buttonPanel);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                restaurantProcessing.serialize();
            }
        });
        setContentPane(mainPanel);
        setResizable(false);
        setSize(300,250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        initListeners();
        setVisible(true);
    }

    private MenuItem getSelectedMenuItem() {
        return menuItemList.getSelectedValue();
    }

    private void initListeners() {
        modifyButton.addActionListener(event -> {
            if(menuItemList.getSelectedValue() != null) {
                ModifyWindow mw = new ModifyWindow(getSelectedMenuItem(), restaurantProcessing);
            }
        });
        deleteButton.addActionListener(event -> {
            restaurantProcessing.deleteMenuItem(getSelectedMenuItem());
            listModel.removeElement(getSelectedMenuItem());
        });
        addButton.addActionListener(event -> {
            AddItemWindow addItemWindow = new AddItemWindow(restaurantProcessing);
            ((Observable)restaurantProcessing).addObserver(addItemWindow);
        });
    }


    @Override
    public void updateFields() {
       listModel.clear();
       listModel.addAll(restaurantProcessing.getMenuItemList());
    }

}
