package frontend.cards;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

import javax.swing.*;
import javax.swing.event.*;

import frontend.Master;
import frontend.tabs.PersonalDetails;
import backend.PostgreSQLAccess;

public class Login extends JPanel implements ActionListener {
    static JPanel panel = new JPanel();
    ImageIcon i = new ImageIcon(Master.class.getResource("/assets/Census-of-India-Recruitment.jpg"));
    JLabel label = new JLabel(i, SwingConstants.CENTER);
    JLabel userLabel = new JLabel("USERNAME");
    JLabel passwordLabel = new JLabel("PASSWORD");
    JTextField userTextField = new JTextField();
    JPasswordField passwordField = new JPasswordField();
    JButton nextButton = new JButton("View Population Statistics");
    JButton LoginButton = new JButton("Login to Dashboard");
    JCheckBox showPassword = new JCheckBox(" Show Password");
    Font font = new Font("Arial", Font.BOLD, 15);
    public static String givenUsername = "";

    public Login() {

        add(Box.createRigidArea(new Dimension(850, 50)));

        setBackground(Color.white);
        nextButton.setFont(font);
        LoginButton.setFont(font);
        userLabel.setBounds(50, 150, 100, 30);
        passwordLabel.setBounds(50, 220, 100, 30);
        userTextField.setBounds(150, 150, 150, 30);
        passwordField.setBounds(150, 220, 150, 30);
        showPassword.setBounds(150, 250, 150, 30);

        userLabel.setPreferredSize(new Dimension(100, 30));
        passwordLabel.setPreferredSize(new Dimension(100, 30));
        userTextField.setPreferredSize(new Dimension(130, 20));
        passwordField.setPreferredSize(new Dimension(130, 20));
        showPassword.setPreferredSize(new Dimension(150, 30));
        passwordField.setEchoChar('\u25cf');

        userLabel.setFont(font);
        passwordLabel.setFont(font);
        add(label, BorderLayout.CENTER);
        showPassword.setBackground(Color.white);
        panel.add(userLabel);
        panel.add(userTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(showPassword);
        panel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.setPreferredSize(new Dimension(250, 100));
        panel.setBackground(Color.white);
        panel.setFont(font);
        add(Box.createRigidArea(new Dimension(800, 2)));
        add(panel, BorderLayout.CENTER);
        add(Box.createRigidArea(new Dimension(800, 1)));
        add(nextButton);
        add(LoginButton);
        showPassword.addActionListener(this);
        nextButton.addActionListener(this);
        LoginButton.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (showPassword.isSelected()) {
            passwordField.setEchoChar((char) 0);
        } else {
            passwordField.setEchoChar('\u25cf');
        }
        if (source == LoginButton) {
            givenUsername = userTextField.getText();
            String givenPassword = passwordField.getText();
            if ("".equals(givenUsername) || "".equals(givenPassword)) {
                JOptionPane.showMessageDialog(panel, "Enter both a Username/Password");
            } else {
                String query = "select password from Authentication where username=" + "\'" + givenUsername + "\';";
                ResultSet rs = PostgreSQLAccess.fetch(query);
                try {
                    if (rs.next() != false) {
                        System.out.println(rs.getString(1));
                        if (!Objects.equals(rs.getString(1), givenPassword)) {
                            JOptionPane.showMessageDialog(panel, "Wrong Username/Password");
                        } else {
                            if (givenUsername.startsWith("CDMSMA")) {
                                Master.goTo("ManagerDashboard");
                                ManagerDashboard.onLogin();
                            } else {
                                Master.goTo("TeacherDashboard");
                                TeacherDashboard.assigning();
                                PersonalDetails.setvalue();
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(panel, "Wrong Username/Password");
                    }
                } catch (SQLException e1) {
                    // TODO Auto-generated catch block
                    // e1.printStackTrace();
                    JOptionPane.showMessageDialog(panel, "Some Error was encountered");
                }
            }
        } else if (source == nextButton) {

            ResultSet rs = PostgreSQLAccess.fetch("select is_published from commissioner;");
            Boolean currentState = false;
            try {
                while (rs.next()) {
                    currentState = rs.getBoolean(1);
                }
            } catch (SQLException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            if (currentState) {
                Master.goTo("Statistics");
            } else {
                JOptionPane.showMessageDialog(panel, "Census has not been published yet");
            }
        }
    }
}