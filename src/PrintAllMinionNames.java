import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrintAllMinionNames {
    public static void main(String[] args) throws SQLException {
        Connection connection = Utils.getSqlConnection();
        PreparedStatement minionsNames = connection.prepareStatement("SELECT m.name FROM `minions` AS m");
        ResultSet setNames = minionsNames.executeQuery();




        ArrayList<String> arrayMinions = new ArrayList<>();
        while (setNames.next()){
            arrayMinions.add( setNames.getString("name"));
        }

        for (int i = 0; i <arrayMinions.size() ; i++) {
            System.out.println(arrayMinions.get(0+i));
            System.out.println(arrayMinions.get(arrayMinions.size()-(i+1)));
        }


    }
}
