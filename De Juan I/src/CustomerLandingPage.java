import javax.swing.*;
import java.awt.*;

public class CustomerLandingPage extends JFrame {
    private Cart cart;
    private ProductList productList;

    public CustomerLandingPage(String username) {
        super("Customer Landing Page");

        // Initialize cart and productList
        cart = new Cart();
        productList = new ProductList();

        // Create panel to hold products
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        FrameUtilities.stylePanel(panel); // Apply styling to the panel

        // Add products to panel
        for (Product product : productList.getAllProducts()) {
            panel.add(createProductPanel(product));
        }

        // Add panel to scroll pane
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        // Add Cart Button
        JButton cartButton = new JButton(new ImageIcon("cart.jpg"));
        cartButton.setPreferredSize(new Dimension(50, 50));
        cartButton.addActionListener(e -> cart.show());
        JPanel cartButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cartButtonPanel.add(cartButton);
        FrameUtilities.stylePanel(cartButtonPanel); // Apply styling to the cartButtonPanel
        add(cartButtonPanel, BorderLayout.SOUTH);

        // Add Back Button
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            dispose();
            new LoginSignUpSelectionFrame();
        });
        JPanel backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        backButtonPanel.add(backButton);
        FrameUtilities.stylePanel(backButtonPanel); // Apply styling to the backButtonPanel
        add(backButtonPanel, BorderLayout.NORTH);

        // Apply frame styling using FrameUtilities
        FrameUtilities.styleFrame(this);

        // Set frame size and visibility
        setSize(1000, 700);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JPanel createProductPanel(Product product) {
        JPanel productPanel = new JPanel();
        productPanel.setLayout(new BorderLayout());
        productPanel.setOpaque(false);

        JLabel categoryLabel = new JLabel("Category: " + product.getCategory());
        JLabel modelLabel = new JLabel("Model: " + product.getModel());
        JLabel priceLabel = new JLabel("Price: PHP " + product.getPrice()); // Display price in PHP
        JButton addToCartButton = new JButton("Add to Cart");

        JPanel labelsPanel = new JPanel();
        labelsPanel.setLayout(new BoxLayout(labelsPanel, BoxLayout.Y_AXIS));
        labelsPanel.add(categoryLabel);
        labelsPanel.add(modelLabel);
        labelsPanel.add(priceLabel);

        productPanel.add(labelsPanel, BorderLayout.CENTER);
        productPanel.add(addToCartButton, BorderLayout.EAST);

        addToCartButton.addActionListener(e -> {
            cart.addItemToCart(product);
            JOptionPane.showMessageDialog(this, "Added to Cart: " + product.getModel(), "Success", JOptionPane.INFORMATION_MESSAGE);
        });

        return productPanel;
    }
}
