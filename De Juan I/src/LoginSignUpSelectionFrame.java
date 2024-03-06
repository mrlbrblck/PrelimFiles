import javax.swing.*;
import java.awt.*;

public class LoginSignUpSelectionFrame extends JFrame {
    public LoginSignUpSelectionFrame() {
        super("Login or Sign Up");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JLabel asciiLabel = new JLabel(
            "<html>"
            + "<pre>"
            + "    \\ /<br>"
            + "    oVo<br>"
            + "\\___XXX___/<br>"
            + " __XXXXX__<br>"
            + "/__XXXXX__\\<br>"
            + "/   XXX   \\<br>"
            + "     V"
            + "</pre>"
            + "</html>"
        );
        asciiLabel.setForeground(Color.WHITE);
        asciiLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 14));
        asciiLabel.setHorizontalAlignment(SwingConstants.CENTER);
        asciiLabel.setBackground(Color.BLACK);
        asciiLabel.setOpaque(true);
        asciiLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding to top and bottom

        // Heading
        JLabel headingLabel = new JLabel("Babababa");
        headingLabel.setForeground(Color.WHITE);
        headingLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 40));
        headingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        headingLabel.setBackground(Color.BLACK);
        headingLabel.setOpaque(true);
        headingLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding to top and bottom

        // Subheading
        JLabel subheadingLabel = new JLabel("<html><i>Totally not a rip-off of Alibaba</i></html>");
        subheadingLabel.setForeground(Color.WHITE);
        subheadingLabel.setFont(new Font(Font.MONOSPACED, Font.PLAIN, 27));
        subheadingLabel.setHorizontalAlignment(SwingConstants.CENTER);
        subheadingLabel.setBackground(Color.BLACK);
        subheadingLabel.setOpaque(true);
        subheadingLabel.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0)); // Add padding to top and bottom

        // Panel to contain the buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setOpaque(false); // Make the panel transparent
        JButton loginButton = new JButton("Login");
        JButton signUpButton = new JButton("Sign Up");
        loginButton.setPreferredSize(new Dimension(100, 30));
        signUpButton.setPreferredSize(new Dimension(100, 30));

        loginButton.addActionListener(e -> {
            remove(asciiLabel);
            remove(headingLabel);
            remove(subheadingLabel);
            remove(buttonPanel);
            dispose();
            add(new LoginFrame(), BorderLayout.CENTER);
            revalidate();
            repaint();
        });

        signUpButton.addActionListener(e -> {
            remove(asciiLabel);
            remove(headingLabel);
            remove(subheadingLabel);
            remove(buttonPanel);
            dispose();
            add(new SignUpFrame(), BorderLayout.CENTER);
            revalidate();
            repaint();

        });

        buttonPanel.add(loginButton);
        buttonPanel.add(signUpButton);

        JPanel headerPanel = new JPanel(new GridBagLayout());
        headerPanel.setBackground(Color.BLACK);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(50, 0, 10, 0); // Add padding to top and bottom
        headerPanel.add(asciiLabel, gbc);
        gbc.gridy = 1;
        headerPanel.add(headingLabel, gbc);
        gbc.gridy = 2;
        headerPanel.add(subheadingLabel, gbc);

        // Add a filler panel to the east side of the frame
        JPanel fillerPanel = new JPanel();
        fillerPanel.setBackground(Color.BLACK);
        fillerPanel.setOpaque(true);
        fillerPanel.setPreferredSize(new Dimension(100, 100)); // Adjust size as needed
        add(fillerPanel, BorderLayout.EAST);

        setLocationRelativeTo(null);
        setVisible(true);

        FrameUtilities.styleFrame(this);
    }
}
