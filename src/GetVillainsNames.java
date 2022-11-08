import java.sql.*;
import java.util.Properties;

public class GetVillainsNames {
    public static void main(String[] args) throws SQLException {
       final Connection connection = Utils.getSqlConnection();
       final PreparedStatement statement = connection.prepareStatement("select v.name , count(distinct mv.minion_id)minions_count" +
               " from villains as v\n" +
               "join minions_villains as mv on v.id = mv.villain_id\n" +
               "group by mv.villain_id\n" +
               "having minions_count > 15\n" +
               "order by minions_count desc ;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()){
            String valainName = resultSet.getString("v.name");
            int countMinions = resultSet.getInt("minions_count");
            System.out.println(valainName+" " +countMinions);
        }
    }

}
