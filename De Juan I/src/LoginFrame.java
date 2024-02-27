import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;

    public LoginFrame() {
        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(Color.BLACK);
        setLayout(new BorderLayout());

        // Create a panel to hold the input components
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.setOpaque(false); // Make the panel transparent

        // Username label and text field
        JLabel usernameLabel = new JLabel("Email:");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        inputPanel.add(usernameLabel);

        usernameField = new JTextField(20);
        usernameField.setMaximumSize(new Dimension(300, 30)); // Set maximum size
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        inputPanel.add(usernameField);

        // Password label and text field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        inputPanel.add(passwordLabel);

        passwordField = new JPasswordField(20);
        passwordField.setMaximumSize(new Dimension(300, 30)); // Set maximum size
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        inputPanel.add(passwordField);

        // Create a panel to hold the login and back buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center-aligned layout
        buttonPanel.setOpaque(false); // Make the panel transparent

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            // Return to LoginSignUpSelectionFrame
            dispose(); // Close the current frame
            new LoginSignUpSelectionFrame(); // Open LoginSignUpSelectionFrame
        });
        buttonPanel.add(backButton);

        // Create login button
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));

        // Add ActionListener to loginButton
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Authenticate user once button is clicked
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());

                if (authenticate(username, password)) {
                    // Close the current frame
                    dispose();
                    // Open UserSelectionFrame
                    SwingUtilities.invokeLater(() -> new UserSelectionFrame(username));
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid Username or Password", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Add login button to button panel
        buttonPanel.add(loginButton);

        // Create a panel to hold the input and button panels
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(Color.BLACK);
        contentPanel.setOpaque(false); // Make the panel transparent

        // Add input panel to content panel, centering horizontally and vertically
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        contentPanel.add(inputPanel, gbc);

        // Add button panel to content panel, centering horizontally and vertically
        gbc.gridy = 1;
        contentPanel.add(buttonPanel, gbc);

        // Add content panel to frame
        add(contentPanel, BorderLayout.CENTER);

        // Add welcome heading
        JLabel welcomeLabel = new JLabel("Welcome to Babababa");
        welcomeLabel.setForeground(Color.WHITE);
        welcomeLabel.setFont(new Font("Courier New", Font.PLAIN, 40));
        welcomeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(100, 0, 20, 0)); // Add padding
        add(welcomeLabel, BorderLayout.NORTH);

        setSize(1000, 700);
        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);

        FrameUtilities.styleFrame(this);
    }

    private boolean authenticate(String username, String password) {
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[1].equals(username) && parts[2].equals(password)) {
                    return true;
                }
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
