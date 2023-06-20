package com.vyshniakov;

import java.sql.*;

public class H2jdbcReadDemo {
    // JDBC driver name and database URL
    static final String JDBC_DRIVER = "org.h2.Driver";
    static final String DB_URL = "jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1;INIT=RUNSCRIPT FROM 'classpath:/scripts/init.sql'";

    //  Database credentials
    static final String USER = "sa";
    static final String PASS = "";
    private static final String QUERY = "SELECT player1.name, player2.name, player3.name FROM matches m " +
            "JOIN players player1 ON m.player1 = player1.id " +
            "JOIN players player2 ON m.player2 = player2.id " +
            "JOIN players player3 ON m.winner = player3.id";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // STEP 1: Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // STEP 2: Open a connection
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // STEP 3: Execute a query
            System.out.println("Connected database successfully...");
            stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(QUERY);

            // STEP 4: Extract data from result set
            while (rs.next()) {
                // Retrieve by column name
                String player1 = rs.getString(1);
                String player2 = rs.getString(2);
                String winner = rs.getString(3);

                // Display values
                System.out.print("Player1: " + player1);
                System.out.print(", Player2: " + player2);
                System.out.println(", Winner: " + winner);
            }
            // STEP 5: Clean-up environment
            rs.close();
        } catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // finally block used to close resources
            try {
                if (stmt != null) stmt.close();
            } catch (SQLException se2) {
            } // nothing we can do
            try {
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            } // end finally try
        } // end try
        System.out.println("Goodbye!");
    }
}
