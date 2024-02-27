import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class UserSelectionFrame extends JFrame {
    private String name;

    public UserSelectionFrame(String username) {
        name = getNameFromFile(username);

        // Header label
        JLabel headerLabel = new JLabel("Welcome " + name);
        headerLabel.setBounds(375, 200, 245, 30);
        headerLabel.setFont(new Font("Courier New", Font.BOLD, 20));

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(10, 10, 75, 30);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new LoginSignUpSelectionFrame();
            }
        });

        // Customer button
        JButton customerButton = new JButton("Customer");
        customerButton.setBounds(375, 350, 250, 30);
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Dispose of the current frame
                new CustomerLandingPage(name); // Open CustomerLandingPage with the given name
            }
        });

        // Seller button
        JButton sellerButton = new JButton("Seller");
        sellerButton.setBounds(375, 425, 250, 30);
        sellerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new SellerLandingPage(name);
            }
        });

        // Frame setup
        setLayout(null); // Using null layout for manual positioning
        add(headerLabel);
        add(backButton);
        add(customerButton);
        add(sellerButton);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setVisible(true);

        FrameUtilities.styleFrame(this);
    }

    private String getNameFromFile(String username) {
        try (BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[1].equals(username)) {
                    return parts[0]; // Return the name from the file
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ""; // Return empty string if username is not found in the file
    }
}
