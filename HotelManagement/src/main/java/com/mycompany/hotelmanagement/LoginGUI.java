package com.mycompany.hotelmanagement;

import javax.swing.*; //for GUI
import java.awt.*; //for GUI
import java.awt.event.*; //event handling, like button clicks, key presses
import java.io.*; //for file reading and writing

public class LoginGUI extends JFrame{
    private JTextField usernameField; //to enter username
    private JPasswordField passwordField; //to enter password ****
    private JButton loginButton; //for triggering an action when clicked
    
    public LoginGUI(){
        setTitle("Hotel Management Login");
        setSize(350, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); //centers GUI to screen
        
        //will add 4 components to panel
        JPanel panel = new JPanel();

        JLabel welcomeLabel = new JLabel("Welcome to Our Hotel! Please Login.");
        panel.add(welcomeLabel);
        
        JPanel usernamePanel = new JPanel(new FlowLayout()); // left to right
        usernamePanel.add(new JLabel("Username:"));
        usernameField = new JTextField(20); 
        usernamePanel.add(usernameField);
        panel.add(usernamePanel); //for enter username
        
        JPanel passwordPanel = new JPanel(new FlowLayout());
        passwordPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField(20);
        passwordPanel.add(passwordField);
        panel.add(passwordPanel);
        
        loginButton = new JButton("Login");
        panel.add(loginButton);

        //adds action listener to loginbutton to perform actions
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { //triggered when button clicked
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                try {
                    int userType = validateLogin(username, password);
                    if (userType != -1) {
                        JOptionPane.showMessageDialog(LoginGUI.this, "Login successful!");
                        // Open the appropriate user interface based on user type
                        openUserInterface(userType);
                        // Close the login window after login
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(LoginGUI.this, "Invalid username or password.");
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(LoginGUI.this, "Error reading user data. Please try again later.");
                }
            }
        });
        
        
        add(panel);
        setVisible(true);
    }

    private int validateLogin(String username, String password) throws IOException {
        String filePath = "users.txt";
        System.out.println("File path: " + new File(filePath).getAbsolutePath());
        
        BufferedReader reader = new BufferedReader(new FileReader("users.txt"));
        String line;
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split(" ");
            if (parts.length == 3 && parts[0].equals(username) && parts[1].equals(password)) {
                reader.close();
                return Integer.parseInt(parts[2]); // Return user type
            }
        }
        reader.close();
        return -1; // No match found
    }

    private void openUserInterface(int userType) {
        // Open the appropriate user interface based on user type
        switch (userType) {
            case 0:
                // Guest UI
                System.out.println("Opening Guest UI");
                //open GuestUI
                SwingUtilities.invokeLater(() -> {
                    new GuestUI();
                });
                dispose(); // Close the login window
                break;
            case 1:
                // Staff UI
                System.out.println("Opening Staff UI");
                // Create and display the StaffUI
                SwingUtilities.invokeLater(() -> {
                    new StaffUI();
                });   
                dispose(); // Close the login window
                break;
            case 2:
                // Admin UI
                System.out.println("Opening Admin UI");
                // Create and display the AdminUI
                SwingUtilities.invokeLater(() -> {
                    new AdminUI();
                });   
                dispose();
                break;
            default:
                System.out.println("Unknown user type");
                break;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginGUI();
            }
        });
        
    }
}


