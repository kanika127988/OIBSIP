import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ignored) {}

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Online Examination System - Login");
            frame.setSize(420, 280);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationRelativeTo(null);
            frame.setLayout(new BorderLayout());

            // Header Banner
            JPanel headerPanel = new JPanel();
            headerPanel.setBackground(new Color(26, 35, 126));
            headerPanel.setPreferredSize(new Dimension(420, 50));
            JLabel titleLabel = new JLabel("Candidate Login Portal");
            titleLabel.setForeground(Color.WHITE);
            titleLabel.setFont(new Font("Times New Roman", Font.BOLD, 17));
            headerPanel.add(titleLabel);
            frame.add(headerPanel, BorderLayout.NORTH);

            // Form Panel (Centered)
            JPanel formPanel = new JPanel(new GridBagLayout());
            formPanel.setBackground(new Color(245, 247, 250));
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10);
            gbc.anchor = GridBagConstraints.WEST;

            JLabel userLabel = new JLabel("Username:");
            userLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
            gbc.gridx = 0; gbc.gridy = 0;
            formPanel.add(userLabel, gbc);

            JTextField userText = new JTextField(15);
            userText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            gbc.gridx = 1; gbc.gridy = 0;
            formPanel.add(userText, gbc);

            JLabel passwordLabel = new JLabel("Password:");
            passwordLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));
            gbc.gridx = 0; gbc.gridy = 1;
            formPanel.add(passwordLabel, gbc);

            JPasswordField passwordText = new JPasswordField(15);
            passwordText.setFont(new Font("Times New Roman", Font.PLAIN, 14));
            gbc.gridx = 1; gbc.gridy = 1;
            formPanel.add(passwordText, gbc);

            frame.add(formPanel, BorderLayout.CENTER);

            // Footer Panel with Blue Button and Black Text
            JPanel footerPanel = new JPanel();
            footerPanel.setBackground(new Color(245, 247, 250));
            footerPanel.setPreferredSize(new Dimension(420, 60));
            
            JButton loginButton = new JButton("Login");
            loginButton.setBackground(new Color(30, 144, 255)); // Blue
            loginButton.setForeground(Color.BLACK); // Black text
            loginButton.setFont(new Font("Times New Roman", Font.BOLD, 15));
            loginButton.setFocusPainted(false);
            loginButton.setPreferredSize(new Dimension(110, 35));
            loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
            
            footerPanel.add(loginButton);
            frame.add(footerPanel, BorderLayout.SOUTH);

            loginButton.addActionListener(e -> {
                String user = userText.getText();
                String password = new String(passwordText.getPassword());

                if (user.equals("student") && password.equals("123")) {
                    JOptionPane.showMessageDialog(frame, "Login Successful! Loading Instructions...");
                    frame.dispose();
                    new InstructionFrame().setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid Username or Password!", "Error", JOptionPane.ERROR_MESSAGE);
                }
            });

            frame.setVisible(true);
        });
    }
}