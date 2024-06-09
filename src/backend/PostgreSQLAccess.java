package backend;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PostgreSQLAccess {
    private static final String URI = "jdbc:postgresql://ec2-44-193-111-218.compute-1.amazonaws.com:5432/d567gabu130h2q";
    private static final String user = "kabdtcrsfpnwop";
    private static final String password = "da9ecc63fbf7472d503ce16634492ca7ba50dcc14ace050c1d86eee940f2f01c";
    private static Connection c;
    private static Statement statement;

    // Useless Private Constructor to hide Implicit One
    private PostgreSQLAccess() {
        int i = 0;
    }

    // insert, update, delete
    public static void executeUpdate(String updateQuery) {
        try {
            statement.executeUpdate(updateQuery);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // create, alter, drop, rename, truncate, comment
    public static void execute(String updateQuery) {
        try {
            statement.execute(updateQuery);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    // select
    public static ResultSet fetch(String fetchQuery) {
        ResultSet rs = null;
        try {
            rs = statement.executeQuery(fetchQuery);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return rs;
    }

    public static void run() {
        try {
            c = DriverManager.getConnection(URI, user, password);
            statement = c.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        System.out.println("Opened database successfully");

        try {
            Statement stmt = c.createStatement();
            // String sql1 = "create table student(rollno int, name varchar(100), age int,
            // state varchar(100));";
            // String sql2 = "insert into student values (1,'Ashish',18,'Telangana'), (2,
            // 'Venkat', 12, 'Madhya Pradesh'), (3, 'Ravi', 56, 'New Delhi'),
            // (4,'Chakki',77, 'Haryana');";
            // stmt.executeUpdate(sql1);
            // stmt.executeUpdate(sql2);
            // ResultSet rs = fetch("select * from student;");
            // while (rs.next()) {
            // System.out.println(rs.getString(1)); // First Column
            // System.out.println(rs.getString(2)); // Second Column
            // System.out.println(rs.getString(3)); // Third Column
            // System.out.println(rs.getString(4)); // Fourth Column
            // }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        // System.out.println("Created and inserted values successfully");

    }

    public void close() {
        try {
            c.close();
            statement.close();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
