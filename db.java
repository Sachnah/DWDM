import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class db {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("User Management");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(500, 400);
            frame.setLocationRelativeTo(null); // Center on screen

            JTabbedPane tabbedPane = new JTabbedPane();
            tabbedPane.addTab("Register User", createRegisterPanel());
            tabbedPane.addTab("Read User", createReadPanel());
            tabbedPane.addTab("Delete User", createDeletePanel());
            tabbedPane.addTab("Update User", createUpdatePanel());

            frame.add(tabbedPane);
            frame.setVisible(true);
        });
    }

    private static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/userdb";
        String user = "root";
        String password = ""; // Default XAMPP password
        return DriverManager.getConnection(url, user, password);
    }

    private static JPanel createRegisterPanel() {
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextField nameField = new JTextField();
        JTextField emailField = new JTextField();
        JButton registerButton = new JButton("Register");

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Email:"));
        panel.add(emailField);
        panel.add(new JLabel());
        panel.add(registerButton);

        registerButton.addActionListener(e -> {
            try (Connection conn = getConnection()) {
                String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, nameField.getText());
                stmt.setString(2, emailField.getText());
                stmt.executeUpdate();
                JOptionPane.showMessageDialog(panel, "User Registered!");
                nameField.setText("");
                emailField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }

    private static JPanel createReadPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextArea userDisplay = new JTextArea();
        userDisplay.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(userDisplay);
        JButton loadButton = new JButton("Load Users");

        loadButton.addActionListener(e -> {
            try (Connection conn = getConnection()) {
                String sql = "SELECT * FROM users";
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                StringBuilder sb = new StringBuilder();
                while (rs.next()) {
                    sb.append("ID: ").append(rs.getInt("id"))
                            .append(", Name: ").append(rs.getString("name"))
                            .append(", Email: ").append(rs.getString("email")).append("\n");
                }
                userDisplay.setText(sb.toString());
            } catch (SQLException ex) {
                ex.printStackTrace();
                userDisplay.setText("Error: " + ex.getMessage());
            }
        });

        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(loadButton, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel createDeletePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextField emailField = new JTextField();
        JButton deleteButton = new JButton("Delete User");

        deleteButton.addActionListener(e -> {
            try (Connection conn = getConnection()) {
                String sql = "DELETE FROM users WHERE email = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, emailField.getText());
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(panel, "User deleted.");
                } else {
                    JOptionPane.showMessageDialog(panel, "No user found.");
                }
                emailField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage());
            }
        });

        panel.add(new JLabel("Enter Email to Delete:"), BorderLayout.NORTH);
        panel.add(emailField, BorderLayout.CENTER);
        panel.add(deleteButton, BorderLayout.SOUTH);
        return panel;
    }

    private static JPanel createUpdatePanel() {
        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        JTextField oldEmailField = new JTextField();
        JTextField newNameField = new JTextField();
        JTextField newEmailField = new JTextField();
        JButton updateButton = new JButton("Update User");

        panel.add(new JLabel("Email to Update:"));
        panel.add(oldEmailField);
        panel.add(new JLabel("New Name:"));
        panel.add(newNameField);
        panel.add(new JLabel("New Email:"));
        panel.add(newEmailField);
        panel.add(new JLabel());
        panel.add(updateButton);

        updateButton.addActionListener(e -> {
            try (Connection conn = getConnection()) {
                String sql = "UPDATE users SET name = ?, email = ? WHERE email = ?";
                PreparedStatement stmt = conn.prepareStatement(sql);
                stmt.setString(1, newNameField.getText());
                stmt.setString(2, newEmailField.getText());
                stmt.setString(3, oldEmailField.getText());
                int rows = stmt.executeUpdate();
                if (rows > 0) {
                    JOptionPane.showMessageDialog(panel, "User updated.");
                } else {
                    JOptionPane.showMessageDialog(panel, "No user found.");
                }
                oldEmailField.setText("");
                newNameField.setText("");
                newEmailField.setText("");
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(panel, "Error: " + ex.getMessage());
            }
        });

        return panel;
    }
}