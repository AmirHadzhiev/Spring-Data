import java.sql.*;
import java.util.Scanner;

public class IncreaseAgeStoredProcedure {
    private static final String GET_PROCEDURE = "{CALL usp_get_older (?)}";
    private static final String GET_MINION_NAME_AND_AGE_BY_ID = "SELECT m.name, m.age FROM `minions` AS m WHERE id = ?";
    private static final String PRINT_MINIONS = "%s %d%n";

    public static void main(String[] args) throws SQLException {

         int minionId = new Scanner(System.in).nextInt();

         Connection connection = Utils.getSqlConnection();

         CallableStatement getOlderStoredProcedure = connection.prepareCall(GET_PROCEDURE);
        getOlderStoredProcedure.setInt(1, minionId);
        getOlderStoredProcedure.execute();

         PreparedStatement minionsStatement = connection.prepareStatement(GET_MINION_NAME_AND_AGE_BY_ID);
        minionsStatement.setInt(1, minionId);

         ResultSet minions = minionsStatement.executeQuery();
        minions.next();

        System.out.printf(PRINT_MINIONS,
                minions.getString("name"),
                minions.getInt("age"));

    }
}