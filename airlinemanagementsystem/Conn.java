package airlinemanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Conn {
    Connection c;
    Statement s;
    
    public Conn() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Load database configuration from environment variables
            // Set these before running the application:
            // Windows: set DB_HOST=localhost && set DB_PORT=3306 && set DB_USER=root && set DB_PASSWORD=your_password
            // Linux/Mac: export DB_HOST=localhost; export DB_PORT=3306; export DB_USER=root; export DB_PASSWORD=your_password
            
            String dbHost = System.getenv("DB_HOST");
            String dbPort = System.getenv("DB_PORT");
            String dbUser = System.getenv("DB_USER");
            String dbPassword = System.getenv("DB_PASSWORD");
            
            // Use defaults if environment variables are not set (for development only)
            if (dbHost == null) dbHost = "localhost";
            if (dbPort == null) dbPort = "3306";
            if (dbUser == null) dbUser = "YOUR_USERNAME";
            if (dbPassword == null) dbPassword = "YOUR_PASSWORD";
            
            String url = "jdbc:mysql://" + dbHost + ":" + dbPort + "/airlinemanagementsystem";
            
            c = DriverManager.getConnection(url, dbUser, dbPassword);
            s = c.createStatement();
        } catch (Exception e) {
            System.err.println("Database Connection Error: Check DB_HOST, DB_PORT, DB_USER, and DB_PASSWORD environment variables");
            e.printStackTrace();
        }
    }
}