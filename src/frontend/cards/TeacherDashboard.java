package frontend.cards;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.lang.model.util.ElementScanner6;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.text.View;

import backend.PostgreSQLAccess;
import backend.actors.Teacher;
import frontend.Master;
import frontend.tabs.Form;
import frontend.tabs.PersonalDetails;
import frontend.tabs.ViewForms;
import frontend.custom.RoundedBorder;
import frontend.custom.Logout;

public class TeacherDashboard extends JPanel implements Logout {
    public static Teacher teacher;
    public static String givenUsername;
    public static String teacher_id;
    public static JTabbedPane tp;

    public static void assigning() {
        givenUsername = Login.givenUsername;
        String query1 = "select * from user_ where username=" + "\'" + givenUsername + "\';";
        ResultSet rs1 = PostgreSQLAccess.fetch(query1);
        try {
            while (rs1.next()) {

                teacher.setUsername(rs1.getString("username"));
                teacher.setName(rs1.getString("name"));
                teacher.setDateOfBirth(rs1.getDate("date_of_birth"));
                if (rs1.getString("gender") != null)
                    teacher.setGender(rs1.getString("gender").charAt(0));
                else
                    teacher.setGender('N');
                teacher.setAddress(rs1.getString("address"));
                teacher.setUsertype(rs1.getString("usertype"));
                teacher.setDesignation(rs1.getString("designation"));
                teacher.setEmail(rs1.getString("email_id"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String query2 = "select * from phone_nos where username=" + "\'" + givenUsername + "\';";
        ResultSet rs2 = PostgreSQLAccess.fetch(query2);
        try {
            while (rs2.next()) {
                teacher.setPhoneNo(rs2.getString("phone_no"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String query3 = "select * from teacher where username=" + "\'" + givenUsername + "\';";
        ResultSet rs3 = PostgreSQLAccess.fetch(query3);
        try {
            while (rs3.next()) {
                System.out.println(rs3.getString(1));
                teacher.setAreaCode(Integer.valueOf(rs3.getString("assigned_area_id")));
                teacher_id = rs3.getString("teacher_id");
                teacher.setTeacherID(teacher_id);
                teacher.setSchoolID(rs3.getString("school_id"));
                teacher.setSchoolName(rs3.getString("school_name"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String query4 = "select password from Authentication where username=" + "\'" + givenUsername + "\';";
        ResultSet rs4 = PostgreSQLAccess.fetch(query4);
        try {
            while (rs4.next()) {
                teacher.setPassword(rs4.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        ViewForms.onTeacherLogin();

        String q = "select is_submitted from submission_status where teacher_id='" + teacher_id + "';";
        ResultSet rsq = PostgreSQLAccess.fetch(q);
        try {
            while (rsq.next()) {
                int t = rsq.getInt(1);
                if (t == 1) {
                    tp.setEnabledAt(0, false);
                    tp.setSelectedIndex(1);
                }
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void setValues(Teacher teacher) {
        String query1 = "Update user_ set email_id =" + "\'" + teacher.getEmail() + "\' , address =" + "\'"
                + teacher.getAddress() + "\', gender =" + "\'" + teacher.getGender() + "\'" + " where username=" + "\'"
                + givenUsername + "\';";
        String query3 = "Update Authentication set password=" + "\'" + teacher.getPassword() + "\' where username="
                + "\'" + givenUsername + "\';";
        PostgreSQLAccess.executeUpdate(query1);
        PostgreSQLAccess.executeUpdate(query3);
    }

    public static void setPhoneNo(Teacher teacher) {
        String query1 = "select * from phone_nos where username = '" + givenUsername + "' AND phone_no = '"
                + teacher.getPhoneNo() + "';";
        ResultSet rs1 = PostgreSQLAccess.fetch(query1);
        try {
            if (rs1.next() == false) {
                String query2 = "Insert into phone_nos values ('" + teacher.getUsername() + "','" + teacher.getPhoneNo()
                        + "');";
                PostgreSQLAccess.executeUpdate(query2);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    public TeacherDashboard() {
        teacher = new Teacher();
        setLayout(new BorderLayout(0, 10));

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        JLabel l1 = new JLabel("Teacher Dashboard ");
        topPanel.add(l1, BorderLayout.WEST);
        l1.setPreferredSize(new Dimension(550, 30));

        JButton logoutButton = new JButton("Log Out");
        logoutButton.setBorder(new RoundedBorder(10));
        logoutButton.setPreferredSize(new Dimension(80, 3));
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                causeLogOut();
            }
        });

        topPanel.add(logoutButton, BorderLayout.EAST);

        tp = new JTabbedPane();

        JButton submissionButton = new JButton("Area Completed");
        submissionButton.setBorder(new RoundedBorder(10));
        submissionButton.setPreferredSize(new Dimension(10, 3));

        submissionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(tp, "Thank You for contributing to the Indian Census");
                String queryK = "update submission_status set is_submitted = 1 where teacher_id=\'" + teacher_id
                        + "\';";
                System.out.println(queryK);
                PostgreSQLAccess.executeUpdate(queryK);
                causeLogOut();
            }
        });

        topPanel.add(submissionButton, BorderLayout.CENTER);

        topPanel.setBorder(BorderFactory.createLineBorder(Color.black));

        add(topPanel, BorderLayout.NORTH);

        tp.setBounds(0, 0, 850, 450);

        Form form = new Form();
        ViewForms viewForms = new ViewForms();
        PersonalDetails personalDetails = new PersonalDetails();

        tp.addTab("Submit Form", form);
        tp.addTab("View Submitted Forms", viewForms);
        tp.addTab("Personal Details", personalDetails);

        add(tp);
    }

    public void causeLogOut() {
        Login.givenUsername = "";
        Master.goTo("Login");
    }
}