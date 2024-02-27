import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class SignUpFrame extends JFrame {
    private JTextField nameField;
    private JTextField usernameField;
    private JPasswordField passwordField;

    public SignUpFrame() {
        setTitle("Sign Up");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());
        
        // Set frame size
        setSize(1000, 700);

        // Panel to hold input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setBackground(Color.BLACK);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setForeground(Color.WHITE);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        nameField = new JTextField();
        nameField.setMaximumSize(new Dimension(300, 30)); // Set maximum size
        nameField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        usernameField = new JTextField();
        usernameField.setMaximumSize(new Dimension(300, 30)); // Set maximum size
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        passwordField = new JPasswordField();
        passwordField.setMaximumSize(new Dimension(300, 30)); // Set maximum size
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        inputPanel.add(Box.createVerticalGlue());
        inputPanel.add(nameLabel);
        inputPanel.add(nameField);
        inputPanel.add(usernameLabel);
        inputPanel.add(usernameField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(Box.createVerticalGlue());

        // Panel to hold the sign up button
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Center-aligned layout
        buttonPanel.setBackground(Color.BLACK);

        JButton signUpButton = new JButton("Sign Up");
        signUpButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally

        JButton backButton = new JButton("Back");

        buttonPanel.add(backButton);
        buttonPanel.add(signUpButton);

        // Add action listener to the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginSignUpSelectionFrame();
            }
        });

        // Add action listener to the sign-up button
        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the input values
                String name = nameField.getText();
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                
                // Save account details to a text file
                saveAccountDetails(name, username, password);
                
                // Proceed to UserSelectionFrame
                dispose();
                new UserSelectionFrame(username);
            }
        });

        // Add panels to the main frame
        add(inputPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }
    
    private void saveAccountDetails(String name, String username, String password) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("users.txt", true))) {
            writer.println(name + "," + username + "," + password);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
