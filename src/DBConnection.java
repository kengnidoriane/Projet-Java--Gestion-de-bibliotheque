import  java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBConnection {
       private static final String URL = "jdbc:postgresql://localhost:5432/Bibliotheque";
       private static final String USER = "postgres";
       private static final String PASSWORD = " ";

        public static Connection getConnection() throws SQLException {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        }
    
}