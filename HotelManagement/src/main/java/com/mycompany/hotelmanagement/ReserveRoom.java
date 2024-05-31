/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ReserveRoom extends JFrame {
    private JComboBox<String> roomTypeComboBox;
    private JComboBox<String> roomComboBox;

    // Define constants for room types
    public static final int SINGLE_BED = 0;
    public static final int DOUBLE_BED = 1;
    public static final int STUDIO = 2;
    public static final int SUITE = 3;
    public static final int PRESIDENTIAL = 4;

    // Define constants for room availability status
    public static final int AVAILABLE = 0;
    public static final int TAKEN = 1;

    // Define a 2D array to store room availability
    private int[][] availability;
    private static final String FILE_NAME = "Availability.txt";
    
    public ReserveRoom() {
        availability = new int[5][4]; // 4 types of rooms, 20 rooms total
        // Initialize all rooms as available
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                availability[i][j] = AVAILABLE;
            }
        }
        initUI(); // Initialize the UI
    }

    private void readAvailabilityFromFile() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME));
        for (int i = 0; i < 5; i++) {
            String line = reader.readLine();
            String[] parts = line.split(" ");
            for (int j = 0; j < 4; j++) {
                availability[i][j] = Integer.parseInt(parts[j]);
            }
        }
        reader.close();
    }

    // Method to write availability data to file
    private void writeAvailabilityToFile() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME));
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                writer.write(availability[i][j] + " ");
            }
            writer.newLine();
        }
        writer.close();
    }
    
    
    // Method to update room availability status
    public void updateAvailability(int roomType, int roomNumber, int status) {
        availability[roomType][roomNumber] = status;
    }
    
    public boolean isTaken(int roomType, int roomNumber) {
        if (availability[roomType][roomNumber] == AVAILABLE) {
            return false;
        }
        else
            return true;
    }
    
    // Method to get available rooms of a specific type
    public int[] getAvailableRooms(int roomType) {
        // Load availability from file if not already loaded
        if (availability[0][0] == AVAILABLE) {
            try {
                readAvailabilityFromFile();
            } catch (IOException e) {
                System.out.println("An error occurred while reading availability from file.");
                e.printStackTrace();
            }
        }
        
        // Create an array to store all room numbers
        int[] availableRooms = new int[4];
        
        int index = 0;
        for (int i = 0; i < 4; i++) {
            availableRooms[index++] = i + 101 + (roomType * 100); // Add 101 to get room numbers from 101~104, 201~204, etc til 501~504
        }
        return availableRooms;
    } 
    
    public void initUI() {
        setTitle("Reserve a Room");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Create room type combo box
        roomTypeComboBox = new JComboBox<>(new String[]{"Single Bed", "Double Bed", "Studio", "Suite", "Presidential"});
        roomTypeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When a room type is selected, update the available rooms combo box
                int roomTypeIndex = roomTypeComboBox.getSelectedIndex();
                int[] availableRooms = getAvailableRooms(roomTypeIndex);
                roomComboBox.removeAllItems(); // Clear previous items

                // Add only available rooms to the combo box
                for (int room : availableRooms) {
                    // Check if the room is available before adding it
                    if (!isTaken(roomTypeIndex, room - 101 - (roomTypeIndex * 100))) {
                        roomComboBox.addItem("Room " + room);
                    }
                }
                roomComboBox.setEnabled(true); // Enable the combo box
            }
        });

        // Create available rooms combo box
        roomComboBox = new JComboBox<>();
        roomComboBox.setEnabled(false); // Disable initially until room type is selected

        // Create reserve button
        JButton reserveButton = new JButton("Reserve");
        reserveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // When reserve button is clicked
                int roomTypeIndex = roomTypeComboBox.getSelectedIndex();
                int selectedRoomNumber = Integer.parseInt(roomComboBox.getSelectedItem().toString().split(" ")[1]);

                // Mark the selected room as taken
                updateAvailability(roomTypeIndex, selectedRoomNumber - 101 - (roomTypeIndex * 100), TAKEN);
                JOptionPane.showMessageDialog(ReserveRoom.this, "Room reserved successfully!");

                // Write the updated availability to file
                try {
                    writeAvailabilityToFile();
                } catch (IOException ex) {
                    System.out.println("An error occurred while writing availability to file.");
                    ex.printStackTrace();
                }

                // Proceed to payment process
                double roomPrice = getPrice(roomTypeIndex);
                Payment payment = new Payment(selectedRoomNumber, roomPrice, roomTypeIndex);
                payment.setVisible(true);
                dispose(); // Close the ReserveRoom window
            }
        });

        // Add components to main panel
        mainPanel.add(new JLabel("Select Room Type:"), BorderLayout.NORTH);
        mainPanel.add(roomTypeComboBox, BorderLayout.NORTH);
        mainPanel.add(new JLabel("Available Rooms:"), BorderLayout.CENTER);
        mainPanel.add(roomComboBox, BorderLayout.CENTER);
        mainPanel.add(reserveButton, BorderLayout.SOUTH);

        // Add main panel to the frame
        add(mainPanel);

        setVisible(true);
    }


    // Method to get the price based on the selected room type
    private double getPrice(int roomTypeIndex) {
        switch (roomTypeIndex) {
            case SINGLE_BED:
                return 100.0;
            case DOUBLE_BED:
                return 150.0;
            case STUDIO:
                return 200.0;
            case SUITE:
                return 250.0;
            case PRESIDENTIAL:
                return 500.0;
            default:
                return 0.0;
        }
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ReserveRoom();
        });
    }
}
