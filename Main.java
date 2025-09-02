import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        DbHelper dbhelper = new DbHelper();

        JFrame frame = new JFrame("JDBC");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        // TabPane
        JTabbedPane tabbedpane = new JTabbedPane();

        // Register Panel
        JPanel registerPanel = new JPanel();
        JPanel npanel = new JPanel();
        registerPanel.add(npanel);

        JLabel name = new JLabel("Name");
        JTextField nameField = new JTextField(10);
        npanel.add(name);
        npanel.add(nameField);

        JLabel email = new JLabel("Email");
        JTextField emailField = new JTextField(10);
        npanel.add(email);
        npanel.add(emailField);

        JLabel psd = new JLabel("Password");
        JPasswordField psdField = new JPasswordField(10);
        npanel.add(psd);
        npanel.add(psdField);

        JButton btn = new JButton("Add/Create");
        npanel.add(btn);

        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String email = emailField.getText();
                String psd = psdField.getText();
                dbhelper.saveUser(name, email, psd);
                JOptionPane.showMessageDialog(registerPanel, "User Registered!");
            }
        });

        npanel.setLayout(new BoxLayout(npanel, BoxLayout.Y_AXIS));

        // Read Panel
        JPanel readPanel = new JPanel();
        JPanel mpanel = new JPanel();
        readPanel.add(mpanel);
        JButton btn1 = new JButton("Read");
        btn1.setAlignmentX(Component.CENTER_ALIGNMENT);
        mpanel.add(btn1);

        JTextArea area = new JTextArea(10, 30);
        JScrollPane scrollPane = new JScrollPane(area);
        mpanel.add(scrollPane);

        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ResultSet result = dbhelper.fetchAllData();
                StringBuilder output = new StringBuilder();
                try {
                    while (result.next()) {
                        output.append("ID: ").append(result.getInt("id"))
                                .append(", Name: ").append(result.getString("name"))
                                .append(", Email: ").append(result.getString("email"))
                                .append("\n");
                    }
                    area.setText(output.toString());
                } catch (SQLException ex) {
                    System.out.println(ex.getMessage());
                }
            }
        });

        mpanel.setLayout(new BoxLayout(mpanel, BoxLayout.Y_AXIS));

        // Delete Panel
        JPanel deletePanel = new JPanel();
        JPanel opanel = new JPanel();
        deletePanel.add(opanel);

        JLabel id = new JLabel("Enter ID");
        JTextField idField = new JTextField(10);
        opanel.add(id);
        opanel.add(idField);

        JButton btn2 = new JButton("Delete");
        opanel.add(btn2);

        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                dbhelper.deleteUser(id);
                JOptionPane.showMessageDialog(deletePanel, "User Deleted!");
            }
        });

        opanel.setLayout(new BoxLayout(opanel, BoxLayout.Y_AXIS));

        // Update Panel
        JPanel updatePanel = new JPanel();
        JPanel ppanel = new JPanel();
        updatePanel.add(ppanel);

        JLabel newid = new JLabel("Enter ID to update");
        JTextField newidField = new JTextField(10);
        ppanel.add(newid);
        ppanel.add(newidField);

        JLabel newname = new JLabel("Enter new Name");
        JTextField newnameField = new JTextField(10);
        ppanel.add(newname);
        ppanel.add(newnameField);

        JButton btn3 = new JButton("Update");
        ppanel.add(btn3);

        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = newnameField.getText();
                String id = newidField.getText();
                dbhelper.update(name, id);
                JOptionPane.showMessageDialog(updatePanel, "Updated Successfully!");
            }
        });

        ppanel.setLayout(new BoxLayout(ppanel, BoxLayout.Y_AXIS));

        // Add Panels to Tabs
        tabbedpane.addTab("Register Users", registerPanel);
        tabbedpane.addTab("Read Users", readPanel);
        tabbedpane.addTab("Delete Users", deletePanel);
        tabbedpane.addTab("Update Users", updatePanel);

        frame.add(tabbedpane);
        frame.setVisible(true);
    }
}
