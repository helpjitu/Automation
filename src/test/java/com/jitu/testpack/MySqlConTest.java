package com.jitu.testpack;

import com.jitu.base.Page;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;

@Listeners(com.jitu.listeners.ListenerTestNG.class)
public class MySqlConTest extends Page {

    static String ConnectionUrl = "jdbc:mysql://localhost:3306/" + loadProp().getProperty("database");
    static String user = "jitu";
    static String password = "jitu1234";
    static String query = loadProp().getProperty("query");
    static String space = "          ";

    @Test
    public static void retrieveData() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            logInfo("Creating connection");
            Connection con = DriverManager.getConnection(ConnectionUrl, user, password);
            // here selenium is database name, jitu is username and jitu1234 is password
            System.out.println("Connection complete");
            logInfo("Connection complete");
            java.sql.Statement stmt = con.createStatement();
            logInfo("Query to be executed is: "+query);
            ResultSet rs = stmt.executeQuery(query);

            ResultSetMetaData rsmd = rs.getMetaData();
            String column1 = rsmd.getColumnName(1);
            String column2 = rsmd.getColumnName(2);
            String column3 = rsmd.getColumnName(3);
            System.out.println(column1 + space + column2 + space + column3);
            writeToFile(column1 + space + column2 + space + column3);
            while (rs.next())
            {
                System.out.println(rs.getString(1) + space + rs.getString(2) + space + rs.getString(3));
                writeToFile(rs.getString(1) + space + rs.getString(2) + space + rs.getString(3));
            }
            con.close();
            logInfo("Query execution completed");

        } catch (Exception e) {
            System.out.println(e);
        }

    }
}
