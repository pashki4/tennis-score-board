package vyshniakov.po;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyClass {
    public static void main(String[] args) throws SQLException {
        try {
            System.out.println("hello, world!");
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");
            System.out.println("connected");
            // add application code here
        } catch (ClassNotFoundException ex) {
            System.out.println("ERROR: Class not found: " + ex.getMessage());
        }
        System.exit(0);

    }
}
