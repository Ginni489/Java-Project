package PMSProject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Salary_View extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7;
    JTextField t1, t2, t3, t4, t5, t6;
    Choice ch1;
    JButton btn2;
    Font f;
    JPanel p1, p2;

    Salary_View() {
        super("Salary");
        setLocation(100, 100);
        setSize(880, 557);
        setResizable(false);
        f = new Font("Arial", Font.BOLD, 19);

        l1 = new JLabel("Select ID");
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

        ch1 = new Choice();
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "Select Eid from new_employee";
            ResultSet rest = obj.st.executeQuery(q);
            while (rest.next()) {
                ch1.add(rest.getString("Eid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        ch1.setFont(f);
        ch1.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                // Fetch salary when an Eid is selected
                fetchSalaryDetails();
            }
        });

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

        ImageIcon img = new ImageIcon(ClassLoader.getSystemResource("PMSProject/icons/Salary1.png"));
        Image image = img.getImage().getScaledInstance(440, 580, Image.SCALE_DEFAULT);
        ImageIcon img2 = new ImageIcon(image);
        l7 = new JLabel(img2);

        btn2 = new JButton("Close");
        btn2.setFont(f);
        btn2.setBackground(Color.BLACK);
        btn2.setForeground(Color.WHITE);
        btn2.addActionListener(this);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(7, 2, 10, 10));
        p1.add(l1);
        p1.add(ch1);
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
        p1.add(btn2);  // Only Close button

        p2 = new JPanel();
        p2.setLayout(new GridLayout(1, 1, 10, 10));
        p2.add(l7);

        setLayout(new BorderLayout(30, 30));

        add(p1, "Center");
        add(p2, "West");
    }

    // Method to fetch salary details based on selected Eid
    private void fetchSalaryDetails() {
        String selectedEid = ch1.getSelectedItem();
        try {
            ConnectionClass obj = new ConnectionClass();
            String query = "SELECT * FROM salary WHERE Eid = '" + selectedEid + "'";
            ResultSet rs = obj.st.executeQuery(query);

            if (rs.next()) {
                t1.setText(rs.getString("HRA"));
                t2.setText(rs.getString("DA"));
                t3.setText(rs.getString("MID"));
                t4.setText(rs.getString("PF"));
                t5.setText(rs.getString("Basic"));
            } else {
                JOptionPane.showMessageDialog(this, "Salary details not found for the selected ID");
                // Clear the fields if no data is found
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn2) {
            JOptionPane.showMessageDialog(null, "Are you sure?");
            setVisible(false);
        }
    }

    public static void main(String args[]) {
        new Salary_View().setVisible(true);
    }
}
