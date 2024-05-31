package com.mycompany.hotelmanagement;

import java.io.*;
import java.util.Scanner;

public class RoomAvailability {
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
    
    // Constructor to initialize the availability of rooms
    public RoomAvailability() {
        availability = new int[5][4]; // 4 types of rooms, 20 rooms total
        // Initialize all rooms as available
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                availability[i][j] = AVAILABLE;
            }
        }
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
     
    public void toggleAvailability(int roomType, int roomNumber) {
        // Toggle the availability status, if true -> false, if false -> true
        availability[roomType][roomNumber] = (availability[roomType][roomNumber] == AVAILABLE) ? TAKEN : AVAILABLE;
        try {
            writeAvailabilityToFile(); // Update the file after availability change
        } catch (IOException e) {
            System.out.println("An error occurred while updating availability in file.");
            e.printStackTrace();
        }
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
}
