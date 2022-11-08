import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;

public class IncreaseMinionsAge {
    final static String NAME_AND_AGE_FOR_PRINT_BY_ID = "select name , age from minions";
    final static String UPDATE_NAME_AND_AGE_BY_ID = "UPDATE minions SET name=lower(name) ,age= age+1 where  id = ? ";


    public static void main(String[] args) throws SQLException {
         Scanner scanner = new Scanner(System.in);
        int[] ids = Arrays.stream(scanner.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        Connection conection = Utils.getSqlConnection();
        PreparedStatement statement = conection.prepareStatement(UPDATE_NAME_AND_AGE_BY_ID);

        for (int i = 0; i < ids.length; i++) {
            statement.setInt(1,ids[i]);
               statement.executeUpdate();
        }

        PreparedStatement minionsToPrint = conection.prepareStatement(NAME_AND_AGE_FOR_PRINT_BY_ID);
        ResultSet minionsSet = minionsToPrint.executeQuery();

        while (minionsSet.next()){
            String name = minionsSet.getString("name");
            int age = minionsSet.getInt("age");
            System.out.printf("%s% d%n",name,age);
        }

    }
}
