package com.mycompany.hotelmanagement;

import javax.swing.*;

public class AdminUI extends JFrame {
    //constructs the UI
    public AdminUI(){
        setTitle("Admin UI");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //center to screen
        
        // Create a tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        // Create tabs for Available Rooms, Shift Management, and Inventory Management
        JPanel availableRoomsPanel = new ViewRoomAvailability().createAvailableRoomsPanel(); //LINE CHANGED
        JPanel shiftManagementPanel = new Shift().getShiftPanel();        

        // Add tabs to the tabbed pane
        tabbedPane.addTab("Available Rooms", availableRoomsPanel);
        tabbedPane.addTab("Shift Management", shiftManagementPanel);
        
        // Add View Shift Assignments button to the Shift Management tab
        JButton viewShiftAssignmentsButton = new ViewShiftAssignments().getViewShiftAssignmentsButton();
        shiftManagementPanel.add(viewShiftAssignmentsButton);

        // Add View Shift Changes button to the Shift Management tab
        JButton viewShiftChangesButton = new ViewShiftAssignments().getViewShiftChangesButton();
        shiftManagementPanel.add(viewShiftChangesButton);


        // Add the tabbed pane to the frame
        add(tabbedPane);

        setVisible(true);    
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminUI();
        });
    }
}
