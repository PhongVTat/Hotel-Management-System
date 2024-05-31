package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Shift {
    public JPanel getShiftPanel() {
        JPanel shiftPanel = new JPanel();

        // Create labels and text fields for shift details
        JLabel shiftLabel = new JLabel("Select a shift:");
        JLabel timeLabel = new JLabel("Enter times (e.g., 9:00 AM - 5:00 PM):");
        JTextField timeField = new JTextField(20);

        // Create a dropdown menu for shifts
        String[] shifts = {"Monday Shift", "Tuesday Shift", "Wednesday Shift", "Thursday Shift", "Friday Shift", "Saturday Shift", "Sunday Shift"};
        JComboBox<String> shiftComboBox = new JComboBox<>(shifts);

        // Create a button to select and save the shift
        JButton selectShiftButton = new JButton("Select Shift");
        selectShiftButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedShift = (String) shiftComboBox.getSelectedItem();
                String times = timeField.getText();
                saveShift(selectedShift, times);
            }
        });

        // Add components to the shift panel
        shiftPanel.add(shiftLabel);
        shiftPanel.add(shiftComboBox);
        shiftPanel.add(timeLabel);
        shiftPanel.add(timeField);
        shiftPanel.add(selectShiftButton);

        return shiftPanel;
    }

    private void saveShift(String selectedShift, String times) {
        // Prompt for the Staff User's name
        String staffUserName = JOptionPane.showInputDialog("Enter Staff User for " + selectedShift + ":");
        if (staffUserName != null && !staffUserName.isEmpty()) {
            // Save to file
            try {
                BufferedWriter writer = new BufferedWriter(new FileWriter("shift_assignments.txt", true));
                writer.write(selectedShift + " - Times: " + times + " - Staff User: " + staffUserName);
                writer.newLine();
                writer.close();
                JOptionPane.showMessageDialog(null, "Shift assigned and saved successfully!");
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error saving shift: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Staff User name is required!");
        }
    }

    
}
