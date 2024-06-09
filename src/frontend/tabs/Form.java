package frontend.tabs;

import javax.swing.*;
import javax.swing.table.*;

import backend.PostgreSQLAccess;
import backend.actors.*;
import frontend.cards.TeacherDashboard;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Form extends JPanel implements ActionListener {
    public static int number_of_form;
    // public static Form form;
    public static ArrayList<String> inp;

    public static void census(ArrayList<String> inp) {
        // form = new Form();

        String query1 = "insert into Census  values ('" + inp.get(0) + "','" + inp.get(1) + "'," + inp.get(2) + ","
                + inp.get(3) + "," + inp.get(4) + ",'" + inp.get(5) + "','" + inp.get(6) + "','" + inp.get(7) + "','"
                + inp.get(8) + "','" + inp.get(9) + "','" + inp.get(10) + "','" + inp.get(11) + "','" + inp.get(12)
                + "','" + inp.get(13) + "','" + inp.get(14) + "'," + inp.get(15) + ",'" + inp.get(16) + "',TO_DATE('"
                + inp.get(17) + "', 'DD/MM/YYYY'),'" + inp.get(18) + "','" + inp.get(19) + "','" + inp.get(20) + "','"
                + inp.get(21) + "'); ";
        PostgreSQLAccess.executeUpdate(query1);
    }

    public static void function(Teacher teacher) {
        String query1 = "select count(*) from Census;";
        ResultSet rs1 = PostgreSQLAccess.fetch(query1);
        try {
            while (rs1.next()) {

                number_of_form = rs1.getInt(1);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }

    String dates[] = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17",
            "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
    String months[] = { "Jan", "feb", "Mar", "Apr", "May", "Jun", "July", "Aug", "Sup", "Oct", "Nov", "Dec" };
    String years[] = { "1995", "1996", "1997", "1998", "1999", "2000", "2001", "2002", "2003", "2004", "2005", "2006",
            "2007", "2008", "2009", "2010", "2011", "2012", "2013", "2014", "2015", "2016", "2017", "2018", "2019",
            "2020", "2021" };
    String column[] = { "Name", "Date of Birth", "Aadhar No.", "Gender" };
    String rel[] = { "Mother", "Father", "Brother", "Sister", "Wife", "Husband", "Mother in law", "Father in Law",
            "Son", "Daughter", "Son in Law", "Daughter in Law", "Relatives", "Others" };
    String nati[] = { "Indian", "Afghan", "Albanian", "Algerian", "American",
            "Andorran", "Angolan", "Anguillan", "Citizen of Antigua and Barbuda",
            "Argentine", "Armenian", "Australian", "Austrian",
            "Azerbaijani", "Bahamian", "Bahraini", "Bangladeshi", "Barbadian",
            "Belarusian", "Belgian", "Belizean", "Beninese",
            "Bermudian", "Bhutanese", "Bolivian", "Citizen of Bosnia and Herzegovina",
            "Botswanan", "Brazilian", "British", "British Virgin Islander",
            "Bruneian", "Bulgarian", "Burkinan", "Burmese",
            "Burundian", "Cambodian", "Cameroonian", "Canadian", "Cape Verdean",
            "Cayman Islander", "Central African", "Chadian", "Chilean",
            "Chinese", "Colombian", "Comoran", "Congolese (Congo)",
            "Congolese (DRC)", "Cook Islander", "Costa Rican", "Croatian",
            "Cuban", "Cymraes", "Cymro", "Cypriot",
            "Czech", "Danish", "Djiboutian", "Dominican", "Citizen of the Dominican Republic",
            "Dutch", "East Timorese", "Ecuadorean", "Egyptian", "Emirati",
            "English", "Equatorial Guinean", "Eritrean", "Estonian",
            "Ethiopian", "Faroese", "Fijian", "Filipino", "Finnish",
            "French", "Gabonese", "Gambian", "Georgian", "German",
            "Ghanaian", "Gibraltarian", "Greek", "Greenlandic",
            "Grenadian", "Guamanian", "Guatemalan", "Citizen of Guinea-Bissau",
            "Guinean", "Guyanese", "Haitian", "Honduran", "Hong Konger", "Hungarian",
            "Icelandic", "Indonesian", "Iranian", "Iraqi", "Irish", "Israeli", "Italian",
            "Ivorian", "Jamaican", "Japanese", "Jordanian", "Kazakh", "Kenyan", "Kittitian", "Citizen of Kiribati",
            "Kosovan", "Kuwaiti", "Kyrgyz", "Lao", "Latvian", "Lebanese", "Liberian",
            "Libyan", "Liechtenstein citizen", "Lithuanian", "Luxembourger",
            "Macanese", "Macedonian", "Malagasy", "Malawian",
            "Malaysian", "Maldivian", "Malian", "Maltese",
            "Marshallese", "Martiniquais", "Mauritanian", "Mauritian",
            "Mexican", "Micronesian", "Moldovan", "Monegasque",
            "Mongolian", "Montenegrin", "Montserratian", "Moroccan",
            "Mosotho", "Mozambican", "Namibian", "Nauruan", "Nepalese", "New Zealander",
            "Nicaraguan", "Nigerian", "Nigerien", "Niuean",
            "North Korean", "Northern Irish", "Norwegian", "Omani", "Pakistani", "Palauan", "Palestinian", "Panamanian",
            "Papua New Guinean", "Paraguayan", "Peruvian", "Pitcairn Islander",
            "Polish", "Portuguese", "Prydeinig", "Puerto Rican", "Qatari", "Romanian", "Russian", "Rwandan",
            "Salvadorean", "Sammarinese", "Samoan", "Sao Tomean",
            "Saudi Arabian", "Scottish", "Senegalese", "Serbian",
            "Citizen of Seychelles", "Sierra Leonean", "Singaporean", "Slovak",
            "Slovenian", "Solomon Islander", "Somali", "South African",
            "South Korean", "South Sudanese", "Spanish", "Sri Lankan",
            "St Helenian", "St Lucian", "Stateless", "Sudanese",
            "Surinamese", "Swazi", "Swedish", "Swiss",
            "Syrian", "Taiwanese", "Tajik", "Tanzanian", "Thai",
            "Togolese", "Tongan", "Trinidadian", "Tristanian",
            "Tunisian", "Turkish", "Turkmen", "Turks and Caicos Islander",
            "Tuvaluan", "Ugandan", "Ukrainian", "Uruguayan", "Uzbek",
            "Vatican citizen", "Citizen of Vanuatu", "Venezuelan", "Vietnamese",
            "Vincentian", "Wallisian", "Welsh", "Yemeni", "Zambian", "Zimbabwean" };
    JPanel panel = new JPanel();
    JPanel Image = new JPanel();
    JPanel gbutton = new JPanel();
    ImageIcon i = new ImageIcon(frontend.Master.class.getResource("/assets/OIP.jpeg"));
    JLabel label = new JLabel(i, SwingConstants.CENTER);
    JPanel wind = new JPanel();
    JPanel panel1 = new JPanel(new BorderLayout());
    JLabel title = new JLabel("Census Registration Form");
    JLabel member = new JLabel("Personal Details :  ");
    JButton next = new JButton("Next");
    JButton Go = new JButton("Go Back");
    JButton ad = new JButton("Add");
    JButton clear = new JButton("Clear");
    JButton submit = new JButton("Submit");
    // JLabel houseID = new JLabel("HouseID : ");
    JLabel Address = new JLabel("Address : ");
    JLabel No_of_Members = new JLabel("Number of House Members : ");
    JLabel street = new JLabel("Street No. : ");
    JLabel sname = new JLabel("Street Name : ");
    JLabel aptno = new JLabel("Apt No./ Bunglow No. : ");
    JLabel city = new JLabel("District ID : ");
    JLabel state = new JLabel("State ID: ");
    JLabel zip = new JLabel("Pincode : ");
    // JLabel Land = new JLabel("LandLine No. : ");
    JLabel fname = new JLabel("First Name : ");
    JLabel mname = new JLabel("Middle Name : ");
    JLabel lname = new JLabel("Last Name : ");
    JLabel mother = new JLabel("Mother Name : ");
    JLabel father = new JLabel("Father Name : ");
    JLabel DOB = new JLabel("DOB:");
    JLabel aadhar = new JLabel("Aadhar No. : ");
    JLabel gender = new JLabel("Gender : ");
    JLabel Relation = new JLabel("Relation to Head : ");
    JLabel owner = new JLabel("House Owner : ");
    JLabel phone = new JLabel("Contact No. : ");
    JLabel Edu = new JLabel("Qualification : ");
    JLabel Occ = new JLabel("Occupation : ");
    JLabel nat = new JLabel("Nationality : ");
    // JLabel blood = new JLabel("Blood :");
    JRadioButton male = new JRadioButton("M");
    JRadioButton female = new JRadioButton("F");
    JRadioButton others = new JRadioButton("Others");
    ButtonGroup gengrp = new ButtonGroup();
    JRadioButton yes = new JRadioButton("Yes");
    JRadioButton no = new JRadioButton("No");
    ButtonGroup owngrp = new ButtonGroup();
    // JTextField ID = new JTextField();
    JTextField TotalMembers = new JTextField();
    JTextField AadNo = new JTextField();
    JTextField fn = new JTextField();
    JTextField mn = new JTextField();
    JTextField ln = new JTextField();
    JTextField mom = new JTextField();
    JTextField dad = new JTextField();
    JTextField con = new JTextField();
    JTextField qua = new JTextField();
    JTextField work = new JTextField();
    JTextField sno = new JTextField();
    JTextField sna = new JTextField();
    JTextField num = new JTextField();
    JTextField ci = new JTextField();
    JTextField st = new JTextField();
    JTextField pin = new JTextField();
    // JTextField line = new JTextField();
    JComboBox date = new JComboBox(dates);
    JComboBox month = new JComboBox(months);
    JComboBox year = new JComboBox(years);
    JComboBox rela = new JComboBox(rel);
    // JComboBox Blood = new JComboBox(bgrp);
    JComboBox nation = new JComboBox(nati);
    DefaultTableModel model = new DefaultTableModel(column, 0);
    JTable table = new JTable(model);
    Font font = new Font("Arial", Font.BOLD, 15);

    public Form() {
        inp = new ArrayList<>(21);
        // census();
        title.setFont(new Font("Arial", Font.PLAIN, 30));
        title.setSize(100, 15);
        add(title);
        add(Box.createRigidArea(new Dimension(850, 10)));

        // houseID.setFont(font);
        // houseID.setPreferredSize(new Dimension(150, 20));
        // panel.add(houseID);
        // ID.setPreferredSize(new Dimension(150, 20));
        // panel.add(ID);

        No_of_Members.setPreferredSize(new Dimension(250, 20));
        No_of_Members.setFont(font);
        panel.add(No_of_Members);
        TotalMembers.setPreferredSize(new Dimension(50, 20));
        panel.add(TotalMembers);

        panel.add(Box.createRigidArea(new Dimension(400, 5)));

        Image.add(Box.createRigidArea(new Dimension(400, 50)));

        Address.setFont(new Font("Arial", Font.ITALIC, 20));
        Address.setPreferredSize(new Dimension(300, 25));
        panel.add(Address);

        aptno.setPreferredSize(new Dimension(300, 20));
        aptno.setFont(font);
        panel.add(aptno);
        num.setPreferredSize(new Dimension(300, 20));
        panel.add(num);

        street.setPreferredSize(new Dimension(150, 20));
        street.setFont(font);
        panel.add(street);
        sno.setPreferredSize(new Dimension(150, 20));
        panel.add(sno);

        sname.setPreferredSize(new Dimension(150, 20));
        sname.setFont(font);
        panel.add(sname);
        sna.setPreferredSize(new Dimension(150, 20));
        panel.add(sna);

        city.setPreferredSize(new Dimension(150, 20));
        city.setFont(font);
        panel.add(city);
        ci.setPreferredSize(new Dimension(150, 20));
        panel.add(ci);

        state.setPreferredSize(new Dimension(150, 20));
        state.setFont(font);
        panel.add(state);
        st.setPreferredSize(new Dimension(150, 20));
        panel.add(st);

        zip.setPreferredSize(new Dimension(150, 20));
        zip.setFont(font);
        panel.add(zip);
        pin.setPreferredSize(new Dimension(150, 20));
        panel.add(pin);

        // Land.setPreferredSize(new Dimension(150, 20));
        // Land.setFont(font);
        // panel.add(Land);
        // line.setPreferredSize(new Dimension(150, 20));
        // panel.add(line);

        add(new JSeparator(SwingConstants.HORIZONTAL));
        member.setFont(new Font("Arial", Font.ITALIC, 18));
        member.setPreferredSize(new Dimension(250, 17));
        wind.add(member);
        ad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Dob = date.getSelectedItem().toString() + "/" + month.getSelectedItem().toString() + "/"
                        + year.getSelectedItem().toString();
                model.addRow(
                        new Object[] {
                                fn.getText(),
                                Dob,
                                AadNo.getText(),
                                gengrp.getSelection().getActionCommand()
                        });
                inp.add(8, fn.getText());
                inp.add(9, mn.getText());
                inp.add(10, ln.getText());
                inp.add(11, mom.getText());
                inp.add(12, dad.getText());
                inp.add(13, con.getText());
                inp.add(14, AadNo.getText());
                inp.add(15, String.valueOf(yes.isSelected()));
                inp.add(16, rela.getSelectedItem().toString());
                String Dob1 = date.getSelectedItem().toString() + "/" + (month.getSelectedIndex() + 1) + "/"
                        + year.getSelectedItem().toString();
                inp.add(17, Dob1);
                inp.add(18, gengrp.getSelection().getActionCommand());
                inp.add(19, qua.getText());
                inp.add(20, work.getText());
                inp.add(21, nation.getSelectedItem().toString());
                // System.out.println(inp);
                census(inp);
            }
        });
        clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fn.setText("");
                ln.setText("");
                mn.setText("");
                mom.setText("");
                dad.setText("");
                // Blood.setSelectedIndex(0);
                rela.setSelectedIndex(0);
                owngrp.clearSelection();
                qua.setText("");
                work.setText("");
                con.setText("");
                nation.setSelectedIndex(0);
                AadNo.setText("");
                gengrp.clearSelection();
                date.setSelectedIndex(0);
                month.setSelectedIndex(0);
                year.setSelectedIndex(0);
                owngrp.clearSelection();
                rela.setSelectedIndex(0);

            }
        });
        wind.add(clear);
        wind.add(ad);

        fname.setPreferredSize(new Dimension(155, 15));
        fname.setFont(font);
        wind.add(fname);
        fn.setPreferredSize(new Dimension(140, 18));
        wind.add(fn);

        mname.setPreferredSize(new Dimension(155, 15));
        mname.setFont(font);
        wind.add(mname);
        mn.setPreferredSize(new Dimension(140, 18));
        wind.add(mn);

        lname.setPreferredSize(new Dimension(155, 15));
        lname.setFont(font);
        wind.add(lname);
        ln.setPreferredSize(new Dimension(140, 18));
        wind.add(ln);

        mother.setPreferredSize(new Dimension(155, 15));
        mother.setFont(font);
        wind.add(mother);
        mom.setPreferredSize(new Dimension(140, 18));
        wind.add(mom);

        father.setPreferredSize(new Dimension(155, 15));
        father.setFont(font);
        wind.add(father);
        dad.setPreferredSize(new Dimension(140, 18));
        wind.add(dad);

        phone.setPreferredSize(new Dimension(155, 15));
        phone.setFont(font);
        wind.add(phone);
        con.setPreferredSize(new Dimension(140, 18));
        wind.add(con);

        aadhar.setPreferredSize(new Dimension(155, 15));
        aadhar.setFont(font);
        wind.add(aadhar);
        AadNo.setPreferredSize(new Dimension(140, 18));
        wind.add(AadNo);
        panel.add(Box.createRigidArea(new Dimension(400, 5)));

        owner.setFont(font);
        owner.setPreferredSize(new Dimension(200, 15));
        wind.add(owner);
        yes.setPreferredSize(new Dimension(60, 18));
        yes.setActionCommand("Y");
        wind.add(yes);
        no.setPreferredSize(new Dimension(60, 18));
        no.setActionCommand("N");
        wind.add(no);
        owngrp.add(yes);
        owngrp.add(no);

        Relation.setFont(font);
        Relation.setPreferredSize(new Dimension(180, 15));
        wind.add(Relation);
        rela.setPreferredSize(new Dimension(150, 20));
        wind.add(rela);

        // blood.setFont(font);
        // blood.setPreferredSize(new Dimension(60, 15));
        // wind.add(blood);
        // Blood.setPreferredSize(new Dimension(80, 18));
        // wind.add(Blood);

        DOB.setFont(font);
        DOB.setPreferredSize(new Dimension(80, 15));
        wind.add(DOB);
        date.setPreferredSize(new Dimension(50, 18));
        wind.add(date);
        month.setPreferredSize(new Dimension(50, 18));
        wind.add(month);
        year.setPreferredSize(new Dimension(60, 18));
        wind.add(year);

        gender.setFont(font);
        gender.setPreferredSize(new Dimension(150, 15));
        wind.add(gender);
        male.setPreferredSize(new Dimension(50, 18));
        male.setActionCommand("M");
        wind.add(male);
        female.setPreferredSize(new Dimension(50, 18));
        female.setActionCommand("F");
        wind.add(female);
        others.setPreferredSize(new Dimension(80, 18));
        others.setActionCommand("O");
        wind.add(others);
        gengrp.add(male);
        gengrp.add(female);
        gengrp.add(others);

        Edu.setPreferredSize(new Dimension(155, 15));
        Edu.setFont(font);
        wind.add(Edu);
        qua.setPreferredSize(new Dimension(140, 18));
        wind.add(qua);

        Occ.setPreferredSize(new Dimension(155, 15));
        Occ.setFont(font);
        wind.add(Occ);
        work.setPreferredSize(new Dimension(140, 18));
        wind.add(work);

        nat.setPreferredSize(new Dimension(155, 15));
        nat.setFont(font);
        wind.add(nat);
        nation.setPreferredSize(new Dimension(140, 20));
        wind.add(nation);

        panel.add(Box.createRigidArea(new Dimension(250, 5)));

        panel.add(next);
        Image.add(label);
        add(Image);
        Image.setPreferredSize(new Dimension(400, 365));
        add(panel);
        panel.setPreferredSize(new Dimension(400, 365));

        add(new JSeparator(SwingConstants.VERTICAL));
        panel1.add(new JScrollPane(table));
        panel1.setPreferredSize(new Dimension(400, 365));
        wind.setPreferredSize(new Dimension(400, 365));

        gbutton.add(Go);
        gbutton.add(submit);
        gbutton.setPreferredSize(new Dimension(800, 50));

        submit.addActionListener(this);
        next.addActionListener(this);
        Go.addActionListener(this);
    }

    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == next) {
            panel.setVisible(false);
            Image.setVisible(false);
            add(wind);
            add(panel1);
            add(gbutton);
            wind.setVisible(true);
            panel1.setVisible(true);
            gbutton.setVisible(true);
            System.out.println(number_of_form);
            function(TeacherDashboard.teacher);
            int temp = number_of_form + 1;
            String FormId = "F" + String.format("%05d", temp);
            inp.add(0, FormId);
            inp.add(1, TeacherDashboard.teacher.getTeacherID());
            inp.add(2, TotalMembers.getText());
            inp.add(3, num.getText());
            inp.add(4, sno.getText());
            inp.add(5, sna.getText());
            inp.add(6, ci.getText());
            inp.add(7, st.getText());

        }
        if (source == Go) {
            wind.setVisible(false);
            panel1.setVisible(false);
            gbutton.setVisible(false);
            Image.setVisible(true);
            panel.setVisible(true);

            // form.setSchema()

        }
        if (source == submit) {
            JOptionPane.showMessageDialog(submit, "Submitted Successfully");
            fn.setText("");
            ln.setText("");
            mn.setText("");
            mom.setText("");
            dad.setText("");
            // Blood.setSelectedIndex(0);
            rela.setSelectedIndex(0);
            owngrp.clearSelection();
            qua.setText("");
            work.setText("");
            con.setText("");
            nation.setSelectedIndex(0);
            AadNo.setText("");
            gengrp.clearSelection();
            date.setSelectedIndex(0);
            month.setSelectedIndex(0);
            year.setSelectedIndex(0);
            // ID.setText("");
            TotalMembers.setText("");
            model.setRowCount(0);
            sno.setText("");
            sna.setText("");
            num.setText("");
            ci.setText("");
            st.setText("");
            pin.setText("");
            // line.setText("");
            wind.setVisible(false);
            panel1.setVisible(false);
            gbutton.setVisible(false);
            Image.setVisible(true);
            panel.setVisible(true);

        }
    }
}