import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DB {
    public static Connection connect() throws SQLException {
        try {
            // Get database credentials from DatabaseConfig class
            var jdbcUrl = DatabaseConfig.getDbUrl();
            var user = DatabaseConfig.getDbUsername();
            var password = DatabaseConfig.getDbPassword();
            // Open a connection
            return DriverManager.getConnection(jdbcUrl, user, password);
        } catch (SQLException  e) {
            System.err.println(e.getMessage());
            return null;
        }
    }
}
