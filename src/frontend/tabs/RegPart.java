package frontend.tabs;

import backend.PostgreSQLAccess;
import frontend.cards.*;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;

public class RegPart extends JPanel {
    public static String query = "";
    public static JScrollPane sp = new JScrollPane();
    public static String districtID;
    static TableModel model;
    static JTextField jtf;
    static TableRowSorter sorter;
    static JTable jt;

    public RegPart() {
        setLayout(new FlowLayout());
        JLabel jl = new JLabel("Search: ");
        jtf = new JTextField(30);

        add(jl);
        add(jtf);
        add(sp);
        sp.setPreferredSize(new Dimension(835, 500));

        jtf.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(jtf.getText());
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(jtf.getText());
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(jtf.getText());
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter("(?i)" + str));
                }
            }
        });
    }

    public static void onManagerLogin() {
        query = "select form_id, teacher_id, district_id, firstname, phone_no, aadhaar_no, gender, education, occupation, nationality from Census";
        if (ManagerDashboard.managingActor.getDesignation().equals("Head Master")) {
            ResultSet rst = PostgreSQLAccess
                    .fetch("select district_id from headmaster where username=\'" + Login.givenUsername + "\';");
            districtID = "";
            try {
                while (rst.next()) {
                    districtID = rst.getString(1);
                }
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            query = query.concat(" where district_id = \'" + districtID + "\';");
        } // else if (ManagerDashboard.managingActor.getDesignation().equals("Director"))
          // {
          // ResultSet rst = PostgreSQLAccess
          // .fetch("select state_id from director where username=\'" +
          // Login.givenUsername + "\';");
          // String stateID = "";
          // try {
          // while (rst.next()) {
          // stateID = rst.getString(1);
          // }
          // } catch (SQLException e) {
          // // TODO Auto-generated catch block
          // e.printStackTrace();
          // }
          // query = query.concat(" where state_id = \'" + stateID + "\';");
          // }
        ResultSet rs;
        ResultSetMetaData rsmd;
        ArrayList<ArrayList<String>> regpart = new ArrayList<>();
        try {
            rs = PostgreSQLAccess.fetch(query);
            rsmd = rs.getMetaData();
            int column_no = rsmd.getColumnCount();
            while (rs.next()) {
                ArrayList<String> row = new ArrayList<>();
                for (int x = 1; x <= column_no; x++) {
                    row.add((String) rs.getString(x));
                    // System.out.println(rs.getString(x));
                }
                regpart.add(row);
                System.out.println("Row completed.");
            }
            for (int x = 0; x < regpart.size(); x++) {
                System.out.printf("Row %d: ", x + 1);
                for (int y = 0; y < regpart.get(x).size(); y++) {
                    System.out.printf("%s ", regpart.get(x).get(y));
                }
                System.out.println();
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[][] data = regpart.stream().map(u -> u.toArray(new String[0])).toArray(String[][]::new);
        String[] column = { "Form ID", "T-ID", "D-ID", "NAME", "PHONE NO.", "AADHAR NO.", "GENDER",
                "EDUCATION", "OCCUPATION", "NATIONALITY" };

        model = new DefaultTableModel(data, column);
        sorter = new TableRowSorter<>(model);

        jt = new JTable(data, column);
        jt.setRowSorter(sorter);

        sp.setViewportView(jt);
    }
}