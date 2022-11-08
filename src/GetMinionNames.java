import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GetMinionNames {
    public static void main(String[] args) throws SQLException {
         Scanner scanner = new Scanner(System.in);
        Connection connection = Utils.getSqlConnection();
        PreparedStatement minionsStatement = connection.prepareStatement("select m.name, m.age  from minions as m\n" +
                "join minions_villains as mv on m.id=mv.minion_id\n" +
                "where ? = mv.villain_id;\n");

        PreparedStatement villainStatement = connection.prepareStatement("select v.name from villains as v\n" +
                "join minions_villains as mv on mv.villain_id= v.id\n" +
                "where ? = mv.villain_id\n" +
                "limit 1;\n");
        int villainId = Integer.parseInt(scanner.nextLine());
        minionsStatement.setInt(1,villainId);
        villainStatement.setInt(1,villainId);
        ResultSet miniontSet = minionsStatement.executeQuery();
        ResultSet villainSet = villainStatement.executeQuery();
        int startNumber = 1;
        if (villainSet.next()){
            String villainName = villainSet.getString("v.name");
        System.out.printf("Villain: %s%n",villainName);
            while (miniontSet.next()){
                String name = miniontSet.getString("m.name");
                int years =  miniontSet.getInt("m.age");
                System.out.printf("%d. %s %d%n",startNumber,name,years);
                startNumber +=1;
            }
        } else {
            System.out.printf("No villain with ID %d exists in the database.",villainId);
        }



    }
}
