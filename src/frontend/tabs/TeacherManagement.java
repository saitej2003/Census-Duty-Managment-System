package frontend.tabs;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.zip.ZipError;

import javax.swing.*;
import javax.swing.table.*;

import backend.PostgreSQLAccess;
import frontend.cards.Login;

public class TeacherManagement extends JPanel {
    public static JScrollPane pane;

    public TeacherManagement() {

        /**
         * This method helps to generate Table with buttons like Add, Update, Delete
         */

        // create JFrame and JTable
        final JTable table = new JTable() {
            DefaultTableCellRenderer renderCenter = new DefaultTableCellRenderer();
            { // initializer block
                renderCenter.setHorizontalAlignment(SwingConstants.CENTER);
            }

            @Override
            public TableCellRenderer getCellRenderer(int arg0, int arg1) {
                return renderCenter;
            }
        };

        // create a table model and set a Column Identifiers to this model
        Object[] columns = { "Username", "Password", "Teacher ID", "School ID", "Full Name", "Date of Birth",
                "Assigned Area ID", "Completed?" };
        final DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columns);

        // set the model to the table
        table.setModel(model);

        // Change A JTable Background Color, Font Size, Font Color, Row Height
        table.setBackground(Color.YELLOW.brighter());
        table.setForeground(Color.black);
        Font font = new Font("", 1, 13);
        table.setFont(font);
        table.setRowHeight(20);

        final JLabel username = new JLabel("Username");
        final JLabel password = new JLabel("Password");
        final JLabel teacher_id = new JLabel("Teacher ID");
        final JLabel school_id = new JLabel("School ID");
        final JLabel name = new JLabel("Full Name");
        final JLabel dob = new JLabel("Date of Birth");
        final JLabel assigned = new JLabel("Area ID");

        // create JTextFields to hold the value
        final JTextField textusername = new JTextField();
        final JTextField textpassword = new JTextField();
        final JTextField textteacherid = new JTextField();
        final JTextField textschoolid = new JTextField();
        final JTextField textname = new JTextField();
        final JTextField textdob = new JTextField();
        final JTextField textassigned = new JTextField();

        // create JButtons to add the action
        JButton btnAdd = new JButton("Add");
        JButton btnDelete = new JButton("Delete");
        JButton btnUpdate = new JButton("Update");

        username.setBounds(20, 310, 100, 25);
        teacher_id.setBounds(280, 310, 100, 25);
        name.setBounds(530, 310, 100, 25);
        password.setBounds(20, 340, 100, 25);
        school_id.setBounds(280, 340, 100, 25);
        dob.setBounds(530, 340, 100, 25);
        assigned.setBounds(280, 370, 100, 25);

        textusername.setBounds(120, 310, 125, 25);
        textteacherid.setBounds(380, 310, 125, 25);
        textname.setBounds(630, 310, 125, 25);
        textpassword.setBounds(120, 340, 125, 25);
        textschoolid.setBounds(380, 340, 125, 25);
        textdob.setBounds(630, 340, 125, 25);
        textassigned.setBounds(380, 370, 125, 25);

        btnAdd.setBounds(150, 425, 100, 25);
        btnUpdate.setBounds(350, 425, 100, 25);
        btnDelete.setBounds(550, 425, 100, 25);

        // create JScrollPane
        pane = new JScrollPane(table);
        pane.setBounds(0, 0, 837, 300);

        add(pane);

        setLayout(null);
        // add JLabels to the jframe
        add(username);
        add(password);
        add(teacher_id);
        add(school_id);
        add(name);
        add(dob);
        add(assigned);

        // add JTextFields to the jframe
        add(textusername);
        add(textpassword);
        add(textteacherid);
        add(textschoolid);
        add(textname);
        add(textdob);
        add(textassigned);
        // add JButtons to the jframe
        add(btnAdd);
        add(btnDelete);
        add(btnUpdate);

        String query = "select * from teacher_management";
        ResultSet rsq = PostgreSQLAccess.fetch(query);
        try {
            Object[] fetchedRow = new Object[8];
            System.out.println("Begin Teacher Management");
            while (rsq.next()) {
                fetchedRow[0] = rsq.getString(1);
                fetchedRow[1] = rsq.getString(2);
                fetchedRow[2] = rsq.getString(3);
                fetchedRow[3] = rsq.getString(4);
                fetchedRow[4] = rsq.getString(5);
                fetchedRow[5] = rsq.getString(6);
                fetchedRow[6] = rsq.getString(7);
                fetchedRow[7] = rsq.getInt(8);
                System.out.print(rsq.getString(1));
                System.out.print(" ");
                System.out.print(rsq.getString(2));
                System.out.print(" ");
                System.out.print(rsq.getString(3));
                System.out.print(" ");
                System.out.print(rsq.getString(4));
                System.out.print(" ");
                System.out.print(rsq.getString(5));
                System.out.print(" ");
                System.out.print(rsq.getString(6));
                System.out.print(" ");
                System.out.print(rsq.getString(7));
                System.out.print(" ");
                System.out.print(rsq.getInt(8));
                System.out.printf("%n");
                model.addRow(fetchedRow);
            }
        } catch (SQLException e2) {
            // TODO Auto-generated catch block
            e2.printStackTrace();
        }

        // create an array of objects to set the row data
        final Object[] row = new Object[9];

        // button add row - Clicked on Add Button
        btnAdd.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                row[0] = textusername.getText();
                row[1] = textpassword.getText();
                row[2] = textteacherid.getText();
                row[3] = textschoolid.getText();
                row[4] = textname.getText();
                row[5] = textdob.getText();
                row[6] = textassigned.getText();
                row[7] = 0;

                if (textusername.getText().startsWith("CDMSMA")) {
                    JOptionPane.showMessageDialog(pane, "You cannot add a teacher with a Manager username");
                } else {

                    // add row to the model
                    String sql1 = "insert into user_ (username, name, date_of_birth, usertype, designation,gender) values (\'"
                            + row[0] + "\',\'" + row[4] + "\',TO_DATE(\'" + row[5] + "\','DD/MM/YYYY') "
                            + ", 'Teacher', 'Teacher','M');";
                    System.out.println(sql1);

                    String sql2 = "insert into Authentication values (\'" + row[0] + "\', \'" + row[1] + "\');";
                    System.out.println(sql2);

                    String managerID = "";
                    ResultSet rsmid = PostgreSQLAccess
                            .fetch("select employee_id from Manager where username =\'" + Login.givenUsername + "\';");
                    try {
                        if (rsmid.next()) {
                            managerID = rsmid.getString(1);
                        }
                    } catch (SQLException e1) {
                        // TODO Auto-generated catch block
                        e1.printStackTrace();
                    }
                    String sql3 = "insert into Teacher (username,teacher_id,school_id,assigned_area_id,district_id,manager_id) values ("
                            + "\'" + row[0] + "\'," + "\'" + row[2] + "\'," + "\'" + row[3] + "\'," + "\'" + row[6]
                            + "\',"
                            + "\'" + RegPart.districtID + "\'," + "\'" + managerID + "\');";
                    System.out.println(sql3);

                    String sql4 = "insert into submission_status (teacher_id) values (\'" + row[2] + "\');";
                    System.out.println(sql4);
                    PostgreSQLAccess.executeUpdate(sql1);
                    PostgreSQLAccess.executeUpdate(sql2);
                    PostgreSQLAccess.executeUpdate(sql3);
                    PostgreSQLAccess.executeUpdate(sql4);

                    model.addRow(row);
                }
            }
        });

        // button remove row - Clicked on Delete Button
        btnDelete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                if (i >= 0) {
                    // remove a row from jtable
                    String tempUsername = model.getValueAt(i, 0).toString();
                    String tempteacherID = model.getValueAt(i, 2).toString();
                    int is_submitted = Integer.parseInt(model.getValueAt(i, 7).toString());
                    if (is_submitted == 1) {
                        JOptionPane.showMessageDialog(pane,
                                "A Teacher who is done with their census cannot be deleted");
                    } else {
                        String dQuery1 = "delete from submission_status where teacher_id=\'" + tempteacherID + "\';";
                        String dQuery2 = "delete from Authentication where username=" + "\'" + tempUsername
                                + "\';";
                        String dQuery3 = "delete from Teacher where username=" + "\'" + tempUsername + "\';";
                        String dQuery4 = "delete from user_ where username=" + "\'" + tempUsername + "\';";

                        PostgreSQLAccess.executeUpdate(dQuery1);
                        PostgreSQLAccess.executeUpdate(dQuery2);
                        PostgreSQLAccess.executeUpdate(dQuery3);
                        PostgreSQLAccess.executeUpdate(dQuery4);
                        model.removeRow(i);
                    }
                } else {
                    System.out
                            .println("There were issues while Deleting the Row(s).");
                }
            }
        });

        // get selected row data From table to textfields
        table.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();
                textusername.setText(model.getValueAt(i, 0).toString());
                textpassword.setText(model.getValueAt(i, 1).toString());
                textteacherid.setText(model.getValueAt(i, 2).toString());
                textschoolid.setText(model.getValueAt(i, 3).toString());
                textname.setText(model.getValueAt(i, 4).toString());
                textdob.setText(model.getValueAt(i, 5).toString());
                textassigned.setText(model.getValueAt(i, 6).toString());
            }
        });

        // button update row - Clicked on Update Button
        btnUpdate.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                // i = the index of the selected row
                int i = table.getSelectedRow();

                if (i >= 0) {
                    model.setValueAt(textusername.getText(), i, 0);
                    model.setValueAt(textpassword.getText(), i, 1);
                    model.setValueAt(textteacherid.getText(), i, 2);
                    model.setValueAt(textschoolid.getText(), i, 3);
                    model.setValueAt(textname.getText(), i, 4);
                    model.setValueAt(textdob.getText(), i, 5);
                    model.setValueAt(textassigned.getText(), i, 6);
                } else {
                    System.out.println("Update Error");
                }
            }
        });

    }
}