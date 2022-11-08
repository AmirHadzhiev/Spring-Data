import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

 enum Utils {
     ;

     static Connection getSqlConnection() throws SQLException {
         final Properties properties = new Properties();
         properties.setProperty("user", "root");
         properties.setProperty("password", "123456789");
         return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db",properties);
     }
 }
