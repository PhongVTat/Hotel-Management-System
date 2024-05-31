package com.mycompany.hotelmanagement;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ViewShiftAssignments {
    public JButton getViewShiftAssignmentsButton() {
        JButton viewShiftAssignmentsButton = new JButton("View Shift Assignments");
        viewShiftAssignmentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayShiftAssignments();
            }
        });
        return viewShiftAssignmentsButton;
    }

    private void displayShiftAssignments() {
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        try (BufferedReader reader = new BufferedReader(new FileReader("shift_assignments.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading shift assignments: " + e.getMessage());
        }

        JOptionPane.showMessageDialog(null, scrollPane, "Shift Assignments", JOptionPane.PLAIN_MESSAGE);
    }

    public JButton getRequestShiftChangesButton() {
        JButton requestShiftChangesButton = new JButton("Request Shift Changes");
        requestShiftChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = JOptionPane.showInputDialog("Enter your username:");
                if (username != null && !username.isEmpty()) {
                    requestShiftChanges(username);
                } else {
                    JOptionPane.showMessageDialog(null, "Username cannot be empty.");
                }
            }
        });
        return requestShiftChangesButton;
    }

    private void requestShiftChanges(String username) {
        String shiftChanges = JOptionPane.showInputDialog("Request any shift changes:");
        if (shiftChanges != null && !shiftChanges.isEmpty()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter("shift_changes.txt", true))) {
                writer.write("Username: " + username + "\n");
                writer.write("Shift Changes: " + shiftChanges + "\n\n");
                JOptionPane.showMessageDialog(null, "Shift changes requested and saved.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(null, "Error writing shift changes: " + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "No shift changes requested.");
        }
    }

    public JButton getViewShiftChangesButton() {
        JButton viewShiftChangesButton = new JButton("View Request Shift Changes");
        viewShiftChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayShiftChanges();
            }
        });
        return viewShiftChangesButton;
    }

    private void displayShiftChanges() {
        JTextArea textArea = new JTextArea(20, 40);
        JScrollPane scrollPane = new JScrollPane(textArea);
        textArea.setEditable(false);

        try (BufferedReader reader = new BufferedReader(new FileReader("shift_changes.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                textArea.append(line + "\n");
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error reading shift changes: " + ex.getMessage());
        }

        JOptionPane.showMessageDialog(null, scrollPane, "View Request Shift Changes", JOptionPane.PLAIN_MESSAGE);
    }
}

