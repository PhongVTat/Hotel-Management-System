package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ViewRoomAvailability {
    private RoomAvailability roomAvailability; // Reference to RoomAvailability object

    public ViewRoomAvailability() {
        this.roomAvailability = new RoomAvailability();
    }

    public JPanel createAvailableRoomsPanel() {
        JPanel availableRoomsPanel = new JPanel(new BorderLayout());

        // Create labels and buttons to display and change room availability
        JLabel titleLabel = new JLabel("Available Rooms");
        JPanel roomsPanel = new JPanel(new GridLayout(0, 4)); // Grid layout for room buttons

        // Add room buttons dynamically based on availability
        for (int roomType = 0; roomType < 5; roomType++) {
        int[] availableRooms = roomAvailability.getAvailableRooms(roomType);
        for (int roomNumber : availableRooms) {
            final int FinalRoomType = roomType;
            int adjustedRoomNumber = (roomNumber % 100) - 1; // Adjust room number
            JButton roomButton = new JButton("Room " + roomNumber);

            if (roomAvailability.isTaken(FinalRoomType, adjustedRoomNumber)) {
                roomButton.setText("<html><center>Room " + roomNumber + "<br>(Taken)</center></html>");
                roomButton.setBackground(new Color(184, 207, 229));
            }

            roomButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    // Change room availability status when button clicked
                    roomAvailability.toggleAvailability(FinalRoomType, adjustedRoomNumber);
                    // Update GUI to reflect changes

                    if (roomAvailability.isTaken(FinalRoomType, adjustedRoomNumber)) {
                        roomButton.setText("<html><center>Room " + roomNumber + "<br>(Taken)</center></html>");
                        roomButton.setBackground(new Color(184, 207, 229));
                    } else {
                        roomButton.setText("Room " + roomNumber);
                        roomButton.setBackground(UIManager.getColor("Button.background"));
                    }
                }
            });
            roomsPanel.add(roomButton);
        }
    }

        availableRoomsPanel.add(titleLabel, BorderLayout.NORTH);
        availableRoomsPanel.add(roomsPanel, BorderLayout.CENTER);

        return availableRoomsPanel;
    }
    
}

