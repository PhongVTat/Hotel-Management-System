/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Payment extends JFrame {
    private double price; // Price of the room
    private int roomnumber;
    private int roomtype;
    private String paymentMethod;
    private String roomTypeName;

    public Payment(int roomnumber, double price, int roomtype) {
        this.price = price;
        this.roomnumber = roomnumber;
        this.roomtype = roomtype;

        switch (roomtype) {
            case 0:
                roomTypeName = "Single Bed";
                break;
            case 1:
                roomTypeName = "Double Bed";
                break;
            case 2:
                roomTypeName = "Studio";
                break;
            case 3:
                roomTypeName = "Suite";
                break;
            case 4:
                roomTypeName = "Presidential";
                break;
            default:
                roomTypeName = "Unknown"; // If roomType doesn't match any case
        }
        
        initUI(); // Initialize the UI
    }

    public void initUI() {
        setTitle("Payment");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Label to display price
        JLabel priceLabel = new JLabel("Price: $" + price);
        mainPanel.add(priceLabel, BorderLayout.CENTER);

        // Panel for payment options
        JPanel paymentOptionsPanel = new JPanel(new FlowLayout());
        paymentOptionsPanel.add(new JLabel("Select Payment Method:"));
        JComboBox<String> paymentMethodComboBox = new JComboBox<>(new String[]{"Cash", "Credit", "Debit"});
        paymentOptionsPanel.add(paymentMethodComboBox);
        mainPanel.add(paymentOptionsPanel, BorderLayout.NORTH);

        // Button to proceed with payment
        JButton payButton = new JButton("Pay");
        payButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Process payment here
                paymentMethod = (String) paymentMethodComboBox.getSelectedItem();
                int confirmationNumber = Integer.parseInt(generateConfirmationNumber());
                
                //print receipt
                System.out.println("Receipt:");
                System.out.println("Room Type: " + roomTypeName);
                System.out.println("Room Number: " + roomnumber);
                System.out.println("Price: $" + price);
                System.out.println("Payment Method: " + paymentMethod);
                System.out.println("Confirmation Number: " + confirmationNumber);
                
                JOptionPane.showMessageDialog(Payment.this, "Payment Successful! Your confirmation number is: " + confirmationNumber);
                dispose(); // Close the Payment window
            }
        });
        mainPanel.add(payButton, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);

        setVisible(true);
    }

    // Helper method to generate a random confirmation number
    private String generateConfirmationNumber() {
        return String.valueOf((int) (Math.random() * 1000000));
    }
}

