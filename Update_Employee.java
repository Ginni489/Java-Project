package PMSProject;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Update_Employee extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8, l9;
    JButton btn1, btn2;
    JTextField t1, t2, t3, t4, t5, t6;
    Choice ch1, ch2;
    JPanel p1, p2;
    Font f;

    Update_Employee() {
        super("Update Employee");
        setSize(840, 547);
        setLocation(200, 70);
        setResizable(false);
        f = new Font("Arial", Font.BOLD, 18);

        l1 = new JLabel("Name");
        l2 = new JLabel("Gender");
        l3 = new JLabel("Address");
        l4 = new JLabel("State");
        l5 = new JLabel("City");
        l6 = new JLabel("Email");
        l7 = new JLabel("Phone");
        l9 = new JLabel("Employee Id");

        ImageIcon img1 = new ImageIcon(ClassLoader.getSystemResource("PMSProject/icons/NewEmployee.jpg"));
        Image image1 = img1.getImage().getScaledInstance(320, 480, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(image1);
        l8 = new JLabel(img2);

        l1.setFont(f);
        l2.setFont(f);
        l3.setFont(f);
        l4.setFont(f);
        l5.setFont(f);
        l6.setFont(f);
        l7.setFont(f);
        l9.setFont(f);

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();
        t4 = new JTextField();
        t5 = new JTextField();
        t6 = new JTextField();

        t1.setFont(f);
        t2.setFont(f);
        t3.setFont(f);
        t4.setFont(f);
        t5.setFont(f);
        t6.setFont(f);

        ch1 = new Choice();
        ch1.add("Male");
        ch1.add("Female");
        ch1.setFont(f);

        ch2 = new Choice();
        ch2.setFont(f);
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select Eid from new_employee";
            ResultSet rest = obj.st.executeQuery(q);
            while (rest.next()) {
                ch2.add(rest.getString("Eid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Buttons
        btn1 = new JButton("Submit");
        btn2 = new JButton("Close");

        btn1.setFont(f);
        btn2.setFont(f);

        // Change button colors
        btn1.setBackground(Color.GREEN);
        btn1.setForeground(Color.WHITE);
        btn2.setBackground(Color.RED);
        btn2.setForeground(Color.WHITE);

        btn1.addActionListener(this);
        btn2.addActionListener(this);

        // Panel for Form
        p1 = new JPanel();
        p1.setLayout(new GridLayout(9, 2, 10, 10));
        p1.setBackground(new Color(220, 220, 255)); // Light blue background for the form
        p1.add(l1);
        p1.add(t1);
        p1.add(l2);
        p1.add(ch1);
        p1.add(l3);
        p1.add(t2);
        p1.add(l4);
        p1.add(t3);
        p1.add(l5);
        p1.add(t4);
        p1.add(l6);
        p1.add(t5);
        p1.add(l7);
        p1.add(t6);
        p1.add(l9);
        p1.add(ch2);
        p1.add(btn1);
        p1.add(btn2);

        // Panel for Image
        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.setBackground(new Color(255, 228, 225)); // Light pink background for the image panel
        p2.add(l8);

        setLayout(new BorderLayout(30, 30));
        add(p1, "Center");
        add(p2, "West");

        // Fetch and set data based on selected Employee ID
        ch2.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                try {
                    int Eid = Integer.parseInt(ch2.getSelectedItem());
                    ConnectionClass obj1 = new ConnectionClass();
                    String q1 = "select * from new_employee where Eid='" + Eid + "'";
                    ResultSet rest1 = obj1.st.executeQuery(q1);
                    if (rest1.next()) {
                        t1.setText(rest1.getString("name"));
                        ch1.select(rest1.getString("gender"));
                        t2.setText(rest1.getString("address"));
                        t3.setText(rest1.getString("state"));
                        t4.setText(rest1.getString("city"));
                        t5.setText(rest1.getString("email"));
                        t6.setText(rest1.getString("phone"));
                    }
                } catch (Exception exx) {
                    exx.printStackTrace();
                }
            }
        });
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            int Eid = Integer.parseInt(ch2.getSelectedItem());
            String name = t1.getText();
            String gender = ch1.getSelectedItem();
            String address = t2.getText();
            String state = t3.getText();
            String city = t4.getText();
            String email = t5.getText();
            String phone = t6.getText();

            try {
                ConnectionClass obj = new ConnectionClass();
                String q = "UPDATE new_employee SET name='" + name + "',gender='" + gender + "',address='" + address + "',state='" + state + "',city='" + city + "',email='" + email + "',phone='" + phone + "' WHERE Eid='" + Eid + "'";
                obj.st.executeUpdate(q);
                JOptionPane.showMessageDialog(null, "Record Updated");
                setVisible(false);
                setVisible(true);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        if (e.getSource() == btn2) {
            JOptionPane.showMessageDialog(null, "Are You sure?");
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new Update_Employee().setVisible(true);
    }
}
