import javax.swing.*;
import java.awt.*;

public class SellerLandingPage extends JFrame {
    private ProductList productList;
    private JPanel panel;

    public SellerLandingPage(String username) {
        super("Seller Landing Page");
        productList = new ProductList(); // Initialize the product list

        panel = new JPanel(new BorderLayout());
        add(panel);

        // Display seller's available listings
        JLabel listingsLabel = new JLabel("Your Available Listings:");
        listingsLabel.setForeground(Color.WHITE); // Set text color to white
        panel.add(listingsLabel, BorderLayout.NORTH);

        JPanel listingsPanel = new JPanel(new GridLayout(0, 1));
        listingsPanel.setBackground(Color.BLACK); // Set background color to black
        panel.add(listingsPanel, BorderLayout.CENTER);

        for (Product product : productList.getAllProducts()) {
            JLabel productLabel = new JLabel("- " + product.getModel());
            productLabel.setForeground(Color.WHITE); // Set text color to white
            listingsPanel.add(productLabel);
        }

        // Add listing button
        JButton addListingButton = new JButton("Add Listing");
        addListingButton.setForeground(Color.BLACK); // Set text color to black
        addListingButton.addActionListener(e -> {
            // Prompt user to add a new product
            String type = JOptionPane.showInputDialog(this, "Enter type of product:");
            String name = JOptionPane.showInputDialog(this, "Enter product name:");
            double price = Double.parseDouble(JOptionPane.showInputDialog(this, "Enter product price:"));
            // Create the new product
            Product newProduct = new Product(type, name, price);
            // Add the new product to the product list
            productList.addProduct(newProduct);
            // Update the listings panel
            JLabel productLabel = new JLabel("- " + newProduct.getModel());
            productLabel.setForeground(Color.WHITE); // Set text color to white
            listingsPanel.add(productLabel);
            panel.revalidate();
            panel.repaint();
            
            // Prompt user to continue or not
            int choice = JOptionPane.showConfirmDialog(this, "Listing added successfully! Do you want to continue?", "Continue?", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.NO_OPTION) {
                // Go back to UserSelectionFrame
                dispose(); // Close the current frame
                UserSelectionFrame userSelectionFrame = new UserSelectionFrame(username);
                userSelectionFrame.setVisible(true); // Display the UserSelectionFrame
                
            }
        });
        addListingButton.setBackground(Color.BLACK); // Set button background color to black
        addListingButton.setForeground(Color.WHITE);
        panel.add(addListingButton, BorderLayout.SOUTH);

        FrameUtilities.styleFrame(this); // Apply styling to the frame

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700); // Set frame size
        setLocationRelativeTo(null);
        setVisible(true); // Set frame to be visible
    }
}
