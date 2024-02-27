import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<Product> cartItems;
    private JFrame frame;
    private JPanel cartPanel;
    private JLabel costLabel;

    public Cart() {
        cartItems = new ArrayList<>();
        initializeFrame();
    }

    private void initializeFrame() {
        frame = new JFrame("Cart");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
        cartPanel = new JPanel();
        cartPanel.setForeground(Color.WHITE);
        cartPanel.setBackground(Color.BLACK);
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        frame.add(cartPanel, BorderLayout.CENTER);
        cartPanel.setOpaque(false);
    
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setForeground(Color.WHITE);
        bottomPanel.setBackground(Color.BLACK);
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setPreferredSize(new Dimension(150, 50)); // Increase button size
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCheckout();
            }
        });
        bottomPanel.add(checkoutButton, BorderLayout.WEST); // Move checkout button to the left
    
        costLabel = new JLabel("Total Cost: PHP 0.00");
        costLabel.setForeground(Color.WHITE);
        costLabel.setBackground(Color.BLACK);
        costLabel.setFont(costLabel.getFont().deriveFont(Font.BOLD, 16)); // Increase font size
        bottomPanel.add(costLabel, BorderLayout.EAST); // Move total cost label to the right
    
        FrameUtilities.stylePanel(bottomPanel); // Apply styling to the bottomPanel
        frame.add(bottomPanel, BorderLayout.SOUTH);
    
        FrameUtilities.stylePanel(cartPanel); // Apply styling to the cartPanel
        FrameUtilities.styleFrame(frame); // Apply styling to the frame
    }
    
    

    public void addItemToCart(Product product) {
        cartItems.add(product);
        displayCartItems();
    }

    void displayCartItems() {
        cartPanel.removeAll();
        for (Product item : cartItems) {
            JLabel itemLabel = new JLabel(item.getModel() + " - PHP " + String.format("%.2f", item.getPrice()));
            itemLabel.setForeground(Color.WHITE);
            itemLabel.setBackground(Color.BLACK);
            cartPanel.add(itemLabel);
        }
        updateCostLabel(); // Update the total cost label
        frame.revalidate();
        frame.repaint();
    }

    private void updateCostLabel() {
        double totalCost = calculateTotalCost();
        costLabel.setText("Total Cost: PHP " + String.format("%.2f", totalCost)); // Remove "PHP" prefix
    }

    private double calculateTotalCost() {
        double totalCost = 0;
        for (Product item : cartItems) {
            totalCost += item.getPrice();
        }
        return totalCost;
    }

    private void performCheckout() {
        String address = JOptionPane.showInputDialog(frame, "Enter delivery address:");
        if (address == null || address.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Address is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
    
        String[] paymentMethods = {"Gcash", "COD", "Maya", "Bank Transfer", "BabaLoan"};
        String paymentMethod = (String) JOptionPane.showInputDialog(frame, "Select payment method:", "Payment Method", JOptionPane.QUESTION_MESSAGE, null, paymentMethods, paymentMethods[0]);
        if (paymentMethod == null) {
            return; // User canceled payment selection
        }
    
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");
        for (Product item : cartItems) {
            orderSummary.append("- ").append(item.getModel()).append("\n");
        }
        orderSummary.append("\nTotal Cost: PHP ").append(String.format("%.2f", calculateTotalCost())).append("\n"); // Update to PHP currency
    
        int response = JOptionPane.showConfirmDialog(frame, orderSummary.toString() + "\nOrder placed!\nShipping duration: Manila 2 - 3 days; Other: 5 - 7 days\n\nContinue shopping?", "Order Confirmation", JOptionPane.YES_NO_OPTION);
    
        if (response == JOptionPane.YES_OPTION) {
            // Clear the cart after checkout
            cartItems.clear();
            new UserSelectionFrame(paymentMethod); 
            frame.dispose();
        } else {
            // End the program
            System.exit(0);
        }
    }public void show() {
        frame.setVisible(true);
    }
}
    