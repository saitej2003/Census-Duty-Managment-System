package frontend.tabs;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;

import backend.PostgreSQLAccess;
import frontend.Master;
import frontend.cards.TeacherDashboard;

public class PersonalDetails extends JPanel {

    public static void detail() {
        String query1 = "select count(*) from Census where teacher_id =" + "\'"
                + TeacherDashboard.teacher.getTeacherID() + "\';";
        ResultSet rs1 = PostgreSQLAccess.fetch(query1);
        try {
            while (rs1.next()) {
                tnumberofforms.setText(rs1.getString(1));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        String query2 = "select district_id,manager_id from teacher where teacher_id =" + "\'"
                + TeacherDashboard.teacher.getTeacherID() + "\';";
        ResultSet rs2 = PostgreSQLAccess.fetch(query2);
        try {
            while (rs2.next()) {
                tdistrictid.setText(rs2.getString("district_id"));
                tmanager.setText(rs2.getString("manager_id"));
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }

    }

    public static void setvalue() {
        male.setSelected(TeacherDashboard.teacher.getGender() == 'M');
        female.setSelected(TeacherDashboard.teacher.getGender() == 'F');
        tusername.setText(TeacherDashboard.teacher.getUsername());
        tname.setText(TeacherDashboard.teacher.getName());
        tid.setText(TeacherDashboard.teacher.getTeacherID());
        tmobile.setText(TeacherDashboard.teacher.getPhoneNo());
        tdob.setText(String.valueOf(TeacherDashboard.teacher.getDateOfBirth()));
        tpassword.setText(TeacherDashboard.teacher.getPassword());
        tschoolid.setText(TeacherDashboard.teacher.getSchoolID());
        tarea.setText(String.valueOf(TeacherDashboard.teacher.getAreaCode()));
        taddress.setText(TeacherDashboard.teacher.getAddress());
        temail.setText(TeacherDashboard.teacher.getEmail());
        detail();
    }

    private JLabel username;
    private static JTextField tusername;
    private JLabel password;
    private static JTextField tpassword;
    private JLabel name;
    private static JTextField tname;
    private JLabel id;
    private static JTextField tid;
    private JLabel mobile;
    private static JTextField tmobile;
    private JLabel email;
    private static JTextField temail;
    private JLabel gender;
    private static JRadioButton male;
    private static JRadioButton female;
    private ButtonGroup gendergrp;
    private JLabel dob;
    private static JTextField tdob;
    private JLabel address;
    private static JTextArea taddress;
    private JLabel heading;
    private JLabel numberofforms;
    private JLabel areas;
    private JLabel manager;
    private static JTextField tnumberofforms;
    private JLabel schoolid;
    private static JTextField tschoolid;
    private JLabel districtid;
    private static JTextField tdistrictid;
    private static JTextField tmanager;
    private static JTextField tarea;

    public PersonalDetails() {

        // Create new JLabel
        heading = new JLabel("Edit Profile");
        username = new JLabel("Username");
        password = new JLabel("Password");
        name = new JLabel("Name");
        id = new JLabel("ID");
        mobile = new JLabel("Mobile");
        gender = new JLabel("Gender");
        email = new JLabel("E-mail");
        dob = new JLabel("Date of Birth");
        address = new JLabel("Address");
        numberofforms = new JLabel("Submitted");
        schoolid = new JLabel("School ID");
        districtid = new JLabel("District ID");
        manager = new JLabel("Manager");
        areas = new JLabel("Area");

        // Create new JTextfield and JTextArea
        tusername = new JTextField();
        tname = new JTextField();
        tid = new JTextField();
        tmobile = new JTextField();
        temail = new JTextField();
        tdob = new JTextField();
        tpassword = new JTextField();
        tnumberofforms = new JTextField();
        tschoolid = new JTextField();
        tdistrictid = new JTextField();
        tmanager = new JTextField();
        tarea = new JTextField();
        taddress = new JTextArea();

        detail();
        // setEditable(false) to non editable text fields
        tusername.setEditable(false);
        tnumberofforms.setEditable(false);
        tmanager.setEditable(false);
        tarea.setEditable(false);
        tschoolid.setEditable(false);
        tdistrictid.setEditable(false);
        tid.setEditable(false);
        tname.setEditable(false);
        tdob.setEditable(false);

        // Create ButtonGroup and JRadioButton for gender.
        gendergrp = new ButtonGroup();
        male = new JRadioButton("M");
        female = new JRadioButton("F");

        // Create JButton for back and save
        JButton backButton = new JButton("Back");
        JButton saveButton = new JButton("Save");

        // Set font and size of JLabel
        heading.setFont(new Font("Serif", Font.PLAIN, 20));
        heading.setSize(300, 40);
        username.setSize(100, 20);
        password.setSize(100, 20);
        name.setSize(100, 20);
        id.setSize(100, 20);
        mobile.setSize(100, 20);
        gender.setSize(100, 20);
        male.setSize(75, 20);
        female.setSize(80, 20);
        email.setSize(100, 20);
        dob.setSize(100, 20);
        address.setSize(100, 20);
        schoolid.setSize(100, 20);
        districtid.setSize(100, 20);
        manager.setSize(100, 20);
        numberofforms.setSize(100, 20);
        areas.setSize(100, 20);

        // Set locations of JLabel
        heading.setLocation(350, 0);
        username.setLocation(50, 50);
        password.setLocation(50, 80);
        name.setLocation(50, 110);
        id.setLocation(50, 140);
        mobile.setLocation(50, 170);
        gender.setLocation(50, 200);
        male.setLocation(150, 200);
        female.setLocation(260, 200);
        email.setLocation(50, 230);
        dob.setLocation(50, 260);
        address.setLocation(50, 290);
        schoolid.setLocation(450, 50);
        districtid.setLocation(450, 80);
        manager.setLocation(450, 110);
        numberofforms.setLocation(450, 140);
        areas.setLocation(450, 170);

        // setbounds of JTextField and JTextArea =>(position-x,position-y,width,height)
        tusername.setBounds(150, 50, 200, 20);
        tpassword.setBounds(150, 80, 200, 20);
        tname.setBounds(150, 110, 200, 20);
        tid.setBounds(150, 140, 200, 20);
        tmobile.setBounds(150, 170, 200, 20);
        temail.setBounds(150, 230, 200, 20);
        taddress.setBounds(150, 290, 200, 50);
        tdob.setBounds(150, 260, 200, 20);
        tschoolid.setBounds(600, 50, 200, 20);
        tdistrictid.setBounds(600, 80, 200, 20);
        tmanager.setBounds(600, 110, 200, 20);
        tnumberofforms.setBounds(600, 140, 200, 20);
        tarea.setBounds(600, 170, 200, 20);
        backButton.setBounds(250, 380, 100, 25);
        saveButton.setBounds(450, 380, 100, 25);

        // assign gender

        // Make the cursor move to next line when it hits the end of width
        taddress.setLineWrap(true);

        // add all JLabel,JTextField,JButton,JTextArea
        add(heading);
        add(username);
        add(tusername);
        add(password);
        add(tpassword);
        add(name);
        add(tname);
        add(id);
        add(tid);
        add(mobile);
        add(tmobile);
        add(gender);
        add(male);
        add(female);
        gendergrp.add(male);
        gendergrp.add(female);
        add(email);
        add(temail);
        add(dob);
        add(address);
        add(taddress);
        add(tdob);
        add(schoolid);
        add(tschoolid);
        add(districtid);
        add(tdistrictid);
        add(manager);
        add(tmanager);
        add(numberofforms);
        add(tnumberofforms);
        add(areas);
        add(tarea);
        add(taddress);
        add(backButton);
        add(saveButton);

        // null layout
        setLayout(null);

        // button to save
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TeacherDashboard.teacher.setEmail(temail.getText());
                TeacherDashboard.teacher.setPhoneNo(tmobile.getText());
                TeacherDashboard.teacher.setAddress(taddress.getText());
                TeacherDashboard.teacher.setPassword(tpassword.getText());
                if (male.isSelected())
                    TeacherDashboard.teacher.setGender('M');
                else if (female.isSelected())
                    TeacherDashboard.teacher.setGender('F');
                TeacherDashboard.setValues(TeacherDashboard.teacher);
                TeacherDashboard.setPhoneNo(TeacherDashboard.teacher);
                frontend.cards.ManagerDashboard.displaySaveSuccess();
            }
        });

        // button to go back
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Master.goTo("TeacherDashboard");
            }
        });

    }
}