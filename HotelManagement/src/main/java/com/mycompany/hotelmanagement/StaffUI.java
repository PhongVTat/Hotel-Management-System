package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StaffUI extends JFrame {
    private final HotelInventory inventory;

    // Constructs the UI
    public StaffUI() {
        setTitle("Staff UI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center to screen

        inventory = new HotelInventory(); // Create an instance of HotelInventory

        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        // Create tabs for Available Rooms, Shift Management, and Inventory Management
        JPanel availableRoomsPanel = new ViewRoomAvailability().createAvailableRoomsPanel();
        JPanel shiftManagementPanel = new JPanel(); // No need to create Shift panel
        JPanel inventoryManagementPanel = createInventoryManagementPanel(); // Create Inventory Management panel

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Available Rooms", availableRoomsPanel);
        tabbedPane.addTab("Shift Management", shiftManagementPanel);
        tabbedPane.addTab("Inventory Management", inventoryManagementPanel);

        // Add View Shift Assignments button to the Shift Management tab
        JButton viewShiftAssignmentsButton = new ViewShiftAssignments().getViewShiftAssignmentsButton();
        shiftManagementPanel.add(viewShiftAssignmentsButton);

        // Add Request Shift Changes button to the Shift Management tab
        JButton requestShiftChangesButton = new ViewShiftAssignments().getRequestShiftChangesButton();
        shiftManagementPanel.add(requestShiftChangesButton);

        // Add View Shift Changes button to the Shift Management tab
        JButton viewShiftChangesButton = new ViewShiftAssignments().getViewShiftChangesButton();
        shiftManagementPanel.add(viewShiftChangesButton);

        // Add the tabbed pane to the frame
        add(tabbedPane);

        setVisible(true);
    }

    // Create Inventory Management panel with buttons
    private JPanel createInventoryManagementPanel() {
        JPanel panel = new JPanel();

        JButton addItemButton = new JButton("Add Item");
        addItemButton.addActionListener((ActionEvent e) -> {
            String itemName = JOptionPane.showInputDialog("Enter the name of the item:");
            int quantity = Integer.parseInt(JOptionPane.showInputDialog("Enter the quantity:"));
            double price = Double.parseDouble(JOptionPane.showInputDialog("Enter the price:"));
            inventory.addItemToInventory(itemName, quantity, price); // Add item to inventory
        });

        JButton removeItemButton = new JButton("Remove Item");
        removeItemButton.addActionListener((ActionEvent e) -> {
            String itemName = JOptionPane.showInputDialog("Enter the name of the item to remove:");
            inventory.removeItemFromInventory(itemName); // Remove item from inventory
        });

        JButton displayInventoryButton = new JButton("Display Inventory");
        displayInventoryButton.addActionListener((ActionEvent e) -> {
            inventory.displayInventory(); // Display inventory
        });

        panel.add(addItemButton);
        panel.add(removeItemButton);
        panel.add(displayInventoryButton);

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new StaffUI();
        });
    }
}

