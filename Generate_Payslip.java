package PMSProject;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.sql.*;

public class Generate_Payslip extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6;
    JButton btn1, btn2; // Added a new button btn2 for printing
    JTextArea ta;
    Choice ch;
    JPanel p1;
    Font f;
    JTable t;

    Generate_Payslip() {
        super("Generate Pay Slip");
        setLocation(300, 200);
        setSize(780, 580);
        setResizable(false);

        f = new Font("Arial", Font.BOLD, 16);

        // Set background color for the frame
        getContentPane().setBackground(new Color(240, 248, 255)); // Light blue background

        l2 = new JLabel("Employee Id");
        l2.setFont(f);

        // Set background color for panels
        ch = new Choice();
        ch.setBackground(Color.WHITE); // White background for dropdown
        try {
            ConnectionClass obj = new ConnectionClass();
            String q = "select * from new_employee";
            ResultSet rest = obj.st.executeQuery(q);
            while (rest.next()) {
                ch.add(rest.getString("Eid"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // Generate button
        btn1 = new JButton("Generate");
        btn1.setBackground(new Color(70, 130, 180)); // Steel blue button
        btn1.setForeground(Color.WHITE);
        btn1.addActionListener(this);
        btn1.setFont(f);

        // Print button
        btn2 = new JButton("Print");
        btn2.setBackground(new Color(70, 130, 180)); // Steel blue button
        btn2.setForeground(Color.WHITE);
        btn2.addActionListener(this);
        btn2.setFont(f);

        ta = new JTextArea();
        JScrollPane sp = new JScrollPane(ta);
        ta.setEditable(true);
        ta.setFont(f);
        ta.setBackground(new Color(224, 255, 255)); // Light cyan background for text area
        ta.setForeground(Color.BLACK);

        p1 = new JPanel();
        p1.setLayout(new GridLayout(1, 4, 10, 10));
        p1.setBackground(new Color(245, 245, 245)); // Light gray background for panel
        p1.add(l2);
        p1.add(ch);
        p1.add(btn1);
        p1.add(btn2);

        setLayout(new BorderLayout(30, 30));
        add(sp, "Center");
        add(p1, "South");
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btn1) {
            // Generate payslip logic
            ta.setText("-------PaySlip-----");
            try {
                ConnectionClass obj1 = new ConnectionClass();
                int id = Integer.parseInt(ch.getSelectedItem());
                String q1 = "select * from new_employee where Eid='" + id + "'";
                ResultSet rest1 = obj1.st.executeQuery(q1);
                while (rest1.next()) {
                    ta.append("\n\nEmployee Id :" + Integer.parseInt(rest1.getString("Eid")));
                    ta.append("\n\nEmployee Name :" + rest1.getString("name"));
                    ta.append("\n ----------------------\n\n");
                }
                String q2 = "select * from salary where Eid='" + id + "'";
                ResultSet rest2 = obj1.st.executeQuery(q2);
                while (rest2.next()) {
                    ta.append("\nHRA :" + Float.parseFloat(rest2.getString("hra")));
                    ta.append("\nDA :" + Float.parseFloat(rest2.getString("da")));
                    ta.append("\nMID :" + Float.parseFloat(rest2.getString("mid")));
                    ta.append("\nPF :" + Float.parseFloat(rest2.getString("pf")));
                    ta.append("\nBASIC SALARY :" + Float.parseFloat(rest2.getString("basic")));
                    ta.append("\n-------------------------\n\n");

                    float gross_salary = Float.parseFloat(rest2.getString("hra"))
                            + Float.parseFloat(rest2.getString("da"))
                            + Float.parseFloat(rest2.getString("mid"))
                            + Float.parseFloat(rest2.getString("pf"))
                            + Float.parseFloat(rest2.getString("basic"));
                    double tax = (gross_salary * 2.1) / 100;
                    ta.append("\nGross Salary : " + gross_salary);
                    ta.append("\nTotal : " + gross_salary);
                    ta.append("\nTax 2.1% of Salary : " + tax);
                }
            } catch (Exception exx) {
                exx.printStackTrace();
            }
        } else if (e.getSource() == btn2) {
            // Print payslip logic
            try {
                ta.print(); // Print the content of the JTextArea
            } catch (Exception exx) {
                exx.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        new Generate_Payslip().setVisible(true);
    }
}
