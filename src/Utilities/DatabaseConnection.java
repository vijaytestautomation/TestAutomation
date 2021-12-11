package Utilities;

import java.sql.*;

import static java.lang.Thread.sleep;


public class DatabaseConnection {

    public static Connection connection;
    public static Statement statement;
    public static ResultSet rs;
    public static String databaseURL;


    public void setUp() throws Exception {
        if(Utils.getConfigParser().getPropertyValue("TestEnvironment").equalsIgnoreCase("Test")){
            databaseURL="";
        }

        connection = null;

        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            System.out.println("Connecting to Database...");
            connection = DriverManager.getConnection(databaseURL);
            if (connection != null) {
                System.out.println("Connected to the Database...");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public void tearDown() {
        if (connection != null) {
            try {
                System.out.println("Closing Database Connection...");
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }




}
