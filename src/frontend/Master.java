package frontend;

import java.awt.*;
import java.awt.event.*;
import java.net.URL;

import javax.swing.*;
import javax.swing.event.*;

import frontend.cards.Login;
import frontend.cards.ManagerDashboard;
import frontend.cards.Statistics;
import frontend.cards.TeacherDashboard;

import backend.*;

public class Master {
    static CardLayout cardLayout = new CardLayout();

    static JPanel cardHolder = new JPanel(cardLayout);

    public static JFrame masterFrame = new JFrame();

    public static void goTo(String name) {
        cardLayout.show(cardHolder, name);
    }

    public static void run() {

        Login login = new Login();
        Statistics stats = new Statistics();
        TeacherDashboard tdashboard = new TeacherDashboard();
        ManagerDashboard mdashboard = new ManagerDashboard();

        cardHolder.add(login, "Login");
        cardHolder.add(tdashboard, "TeacherDashboard");
        cardHolder.add(mdashboard, "ManagerDashboard");
        cardHolder.add(stats, "Statistics");

        masterFrame.getContentPane().add(cardHolder);

        masterFrame.setTitle("Census Duty Management System");
        masterFrame.setSize(850, 580);
        masterFrame.setLocationRelativeTo(null);
        masterFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        masterFrame.setVisible(true);
        masterFrame.setResizable(false);
    }

    // private Constructor to prevent Instantiation
    private Master() {
        throw new IllegalStateException("Utility class Master");
    }
}
