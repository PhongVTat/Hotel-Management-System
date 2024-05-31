package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GuestUI extends JFrame {
    private RoomAvailability roomAvailability; // Reference to RoomAvailability object

    public GuestUI() {
        this.roomAvailability = new RoomAvailability();
        initUI(); // Initialize the UI
    }

    public void initUI() {
        setTitle("Welcome to Our Hotel!");
        setSize(900, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create main panel
        JPanel mainPanel = new JPanel();

        // Add prices panel
        JPanel pricesPanel = createPricesPanel();
        mainPanel.add(pricesPanel);

        // Add reserve room panel
        JPanel reserveRoomPanel = createReserveRoomPanel();
        mainPanel.add(reserveRoomPanel);

        add(mainPanel);

        setVisible(true);
    }

    private JPanel createPricesPanel() {
        JPanel pricesPanel = new JPanel();
        pricesPanel.setBorder(BorderFactory.createTitledBorder("Room Prices"));
        pricesPanel.setLayout(new GridLayout());

        // Add labels for room prices
        pricesPanel.add(new JLabel("Single Bed:"));
        pricesPanel.add(new JLabel("$100"));
        pricesPanel.add(new JLabel("Double Bed:"));
        pricesPanel.add(new JLabel("$150"));
        pricesPanel.add(new JLabel("Studio:"));
        pricesPanel.add(new JLabel("$200"));
        pricesPanel.add(new JLabel("Suite:"));
        pricesPanel.add(new JLabel("$250"));
        pricesPanel.add(new JLabel("Presidential:"));
        pricesPanel.add(new JLabel("$500"));

        return pricesPanel;
    }

    private JPanel createReserveRoomPanel() {
        JPanel reserveRoomPanel = new JPanel(new FlowLayout());
        JButton reserveButton = new JButton("Reserve a Room");
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Open the ReserveRoom class when the button is clicked
                new ReserveRoom();
                dispose();
            }
        });
        reserveRoomPanel.add(reserveButton);
        return reserveRoomPanel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new GuestUI();
        });
    }
}

