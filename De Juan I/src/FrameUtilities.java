import javax.swing.*;
import java.awt.*;

public class FrameUtilities {
    public static void styleFrame(JFrame frame) {
        frame.getContentPane().setBackground(Color.BLACK);
        for (Component comp : frame.getContentPane().getComponents()) {
            if (comp instanceof JLabel) {
                ((JLabel) comp).setForeground(Color.WHITE);
            } else if (comp instanceof JTextField) {
                ((JTextField) comp).setForeground(Color.WHITE);
            } else if (comp instanceof JPasswordField) {
                ((JPasswordField) comp).setForeground(Color.WHITE);
            } else if (comp instanceof JButton) {
                ((JButton) comp).setForeground(Color.WHITE);
                ((JButton) comp).setBackground(Color.BLACK);
            }
        }
    }
            public static void stylePanel(JPanel panel) {
                panel.setBackground(Color.BLACK);
        }
    }

