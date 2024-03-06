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
    private List<Voucher> vouchers;
    private Voucher chosenVoucher;

    public Cart() {
        cartItems = new ArrayList<>();
        initializeFrame();
        initializeVouchers();
    }

    private void initializeFrame() {
        frame = new JFrame("Cart");
        frame.setSize(1000, 700);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        cartPanel = new JPanel();
        cartPanel.setForeground(Color.WHITE);
        cartPanel.setBackground(Color.BLACK);
        cartPanel.setLayout(new BoxLayout(cartPanel, BoxLayout.Y_AXIS));
        frame.add(cartPanel, BorderLayout.CENTER);
        cartPanel.setOpaque(true);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setForeground(Color.WHITE);
        bottomPanel.setBackground(Color.BLACK);

        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setBackground(Color.BLACK);
        checkoutButton.setForeground(Color.WHITE);
        checkoutButton.setPreferredSize(new Dimension(150, 50));
        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performCheckout();
            }
        });
        bottomPanel.add(checkoutButton, BorderLayout.WEST);

        costLabel = new JLabel("Total Cost: PHP 0.00");
        costLabel.setForeground(Color.WHITE);
        costLabel.setBackground(Color.BLACK);
        costLabel.setFont(costLabel.getFont().deriveFont(Font.BOLD, 22));
        bottomPanel.add(costLabel, BorderLayout.EAST);

        frame.add(bottomPanel, BorderLayout.SOUTH);

        // Add a button to choose vouchers
        JButton chooseVoucherButton = new JButton("Choose Voucher");
        chooseVoucherButton.setBackground(Color.BLACK);
        chooseVoucherButton.setForeground(Color.WHITE);
        chooseVoucherButton.setPreferredSize(new Dimension(150, 50));
        chooseVoucherButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseVoucher();
            }
        });
        bottomPanel.add(chooseVoucherButton, BorderLayout.CENTER);

        styleComponents();

        frame.setVisible(true);
    }

    private void styleComponents() {
        FrameUtilities.stylePanel(cartPanel);
    }

    public void addItemToCart(Product product) {
        cartItems.add(product);
        displayCartItems();
    }

    private void displayCartItems() {
        cartPanel.removeAll();
        for (Product item : cartItems) {
            JLabel itemLabel = new JLabel(item.getName() + " - PHP " + String.format("%.2f", item.getPrice()));
            itemLabel.setFont(new Font("Courier New", Font.BOLD, 22));
            itemLabel.setForeground(Color.WHITE);
            itemLabel.setBackground(Color.BLACK);
            cartPanel.add(itemLabel);
        }
        updateCostLabel();
        frame.revalidate();
        frame.repaint();
    }

    private void updateCostLabel() {
        double totalCost = calculateTotalCost();
        costLabel.setText("Total Cost: PHP " + String.format("%.2f", totalCost));
    }

    private double calculateTotalCost() {
        double totalCost = 0;
        for (Product item : cartItems) {
            totalCost += item.getPrice();
        }
        // Apply voucher discount if a voucher is chosen
        if (chosenVoucher != null) {
            double discount = chosenVoucher.getDiscount(); // Assuming this method returns the discount percentage directly
            totalCost *= (1 - discount / 100.0); // Apply discount
        }
        return totalCost;
    }
    

    private void initializeVouchers() {
        // Initialize vouchers
        vouchers = new ArrayList<>();
        vouchers.add(new Voucher("NEWUSERDISCOUNT", 20.0, 1));
        vouchers.add(new Voucher("LOYALTYDISCOUNT", 10.0, 5));
    }

    private void chooseVoucher() {
        // Create a dialog to choose a voucher
        String[] voucherNames = vouchers.stream().map(Voucher::getName).toArray(String[]::new);
        String chosenVoucherName = (String) JOptionPane.showInputDialog(frame, "Choose a voucher:", "Choose Voucher", JOptionPane.QUESTION_MESSAGE, null, voucherNames, null);

        // Find the chosen voucher by name
        for (Voucher voucher : vouchers) {
            if (voucher.getName().equals(chosenVoucherName)) {
                chosenVoucher = voucher;
                updateCostLabel(); // Update total cost after choosing voucher
                return;
            }
        }
        // If no voucher is chosen, set chosenVoucher to null
        chosenVoucher = null;
        updateCostLabel(); // Update total cost after removing voucher
    }

    private void performCheckout() {
        // Calculate total cost
        double totalCost = calculateTotalCost();

        // Prompt for delivery address
        String address = JOptionPane.showInputDialog(frame, "Enter delivery address:");
        if (address == null || address.trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Address is required.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prompt for payment method
        String[] paymentMethods = {"Gcash", "COD", "Maya", "Bank Transfer", "BabaLoan"};
        String paymentMethod = (String) JOptionPane.showInputDialog(frame, "Select payment method:", "Payment Method", JOptionPane.QUESTION_MESSAGE, null, paymentMethods, paymentMethods[0]);
        if (paymentMethod == null) {
            return; // User canceled payment selection
        }

        // Build order summary
        StringBuilder orderSummary = new StringBuilder("Order Summary:\n");
        for (Product item : cartItems) {
            orderSummary.append("- ").append(item.getName()).append("\n");
        }
        orderSummary.append("\nTotal Cost: PHP ").append(String.format("%.2f", totalCost)).append("\n"); // Update to PHP currency

        // Show order confirmation dialog
        int response = JOptionPane.showConfirmDialog(frame, orderSummary.toString() + "\nOrder placed!\nShipping duration: Manila 2 - 3 days; Other: 5 - 7 days\n\nContinue shopping?", "Order Confirmation", JOptionPane.YES_NO_OPTION);

        // Handle user response
        if (response == JOptionPane.YES_OPTION) {
            // Clear the cart after checkout
            cartItems.clear();
            new UserSelectionFrame(paymentMethod);
            frame.dispose();
        } else {
            // End the program
            System.exit(0);
        }
    }

    public void show() {
        frame.setVisible(true);
    }
}
