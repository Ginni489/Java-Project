package PMSProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Update_Salary extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JButton btn1, btn2;
    JTextField t1, t2, t3, t4, t5;
    JComboBox<String> empIdComboBox; // ComboBox for employee ID selection
    JPanel p1, p2;
    Font f;

    public Update_Salary() {
        super("Update Salary");
        setLocation(100, 100);
        setSize(880, 557);
        setResizable(false);

        f = new Font("Arial", Font.BOLD, 18);

        // Initialize labels
        l1 = new JLabel("Select Employee ID");
        l2 = new JLabel("HRA");
        l3 = new JLabel("DA");
        l4 = new JLabel("MID");
        l5 = new JLabel("PF");
        l6 = new JLabel("Basic Salary");

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);

        // Initialize ComboBox for employee ID selection
        empIdComboBox = new JComboBox<>();
        empIdComboBox.setFont(f);
        empIdComboBox.addActionListener(this); // Listen for selection changes

        // Initialize text fields for salary components
        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();

        t1.setFont(f);
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        t5.setFont(f);

        // Load an image and set it for display
        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("PMSProject/icons/Salary1.png"));
        Image image = img.getImage().getScaledInstance(440, 580, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(image);
        l7 = new JLabel(img2);

        // Initialize buttons
        btn1 = new JButton("Submit");
        btn2 = new JButton("Close");
        btn1.setFont(f);
        btn2.setFont(f);
        btn1.setBackground(Color.BLACK);
        btn1.setForeground(Color.WHITE);
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.WHITE);

        // Add action listeners
        btn1.addActionListener(this);
        btn2.addActionListener(this);

        // Layout for the form
        p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 2, 10, 10));
        p1.add(l1);
        p1.add(empIdComboBox); // Add ComboBox in the form
        p1.add(l2);
        p1.add(t1);
        p1.add(l3);
        p1.add(t2);
        p1.add(l4);
        p1.add(t3);
        p1.add(l5);
        p1.add(t4);
        p1.add(l6);
        p1.add(t5);
        p1.add(btn1);
        p1.add(btn2);

        // Layout for the image display
        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.add(l7);

        // Main layout configuration
        setLayout(new BorderLayout(30, 30));
        add(p1, "Center");
        add(p2, "West");

        // Load employee IDs into the ComboBox
        loadEmployeeIds();
    }

    // Load employee IDs into the ComboBox
    private void loadEmployeeIds() {
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT Eid FROM new_employee";
            ResultSet resultSet = obj.st.executeQuery(query);
            while (resultSet.next()) {
                empIdComboBox.addItem(resultSet.getString("Eid"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Load salary data based on selected employee ID
    private void loadSalaryData(String selectedEid) {
        try {
            ConnectionClass obj1 = new ConnectionClass();
            String query = "SELECT * FROM salary WHERE Eid='" + selectedEid + "'";
            ResultSet resultSet = obj1.st.executeQuery(query);

            if (resultSet.next()) {
                t1.setText(resultSet.getString("hra"));
                t2.setText(resultSet.getString("da"));
                t3.setText(resultSet.getString("mid"));
                t4.setText(resultSet.getString("pf"));
                t5.setText(resultSet.getString("basic"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Handle button actions (Submit and Close)
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            updateSalaryData();
        } else if (e.getSource() == btn2) {
            closeWindow();
        } else if (e.getSource() == empIdComboBox) {
            // Load salary details when a new employee ID is selected
            String selectedEid = (String) empIdComboBox.getSelectedItem();
            loadSalaryData(selectedEid);
        }
    }

    // Update the salary data in the database
    private void updateSalaryData() {
        try {
            String selectedEid = (String) empIdComboBox.getSelectedItem();
            if (selectedEid == null) {
                JOptionPane.showMessageDialog(null, "Please select an employee ID");
                return;
            }

            float hra = Float.parseFloat(t1.getText());
            float da = Float.parseFloat(t2.getText());
            float mid = Float.parseFloat(t3.getText());
            float pf = Float.parseFloat(t4.getText());
            float basic = Float.parseFloat(t5.getText());

            ConnectionClass obj = new ConnectionClass();
            String query = "UPDATE salary SET hra='" + hra + "', da='" + da + "', mid='" + mid + "', pf='" + pf + "', basic='" + basic + "' WHERE Eid='" + selectedEid + "'";
            obj.st.executeUpdate(query);

            JOptionPane.showMessageDialog(null, "Data Updated Successfully");
            setVisible(false);
            setVisible(true); // Refresh the window
        } catch (NumberFormatException nfe) {
            JOptionPane.showMessageDialog(null, "Please fill all fields with valid numbers.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    // Close the window with confirmation
    private void closeWindow() {
        int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to close?", "Confirm", JOptionPane.YES_NO_OPTION);
        if (response == JOptionPane.YES_OPTION) {
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new Update_Salary().setVisible(true);
    }
}
