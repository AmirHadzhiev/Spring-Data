import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ChangeTownNamesCasing {
    private static String TAKE_TOWN_NAMES_BY_COUNTRY = "select t.name from towns t where country = ? ";
    private static String TOWN_NAMES_UPPER_CASE_BY_COUNTRY  = "UPDATE towns SET name = upper(name) WHERE country = ? ";
    public static void main(String[] args) throws SQLException {
         Scanner scanner = new Scanner(System.in);
        final String givenCountry = scanner.nextLine();

        Connection connection = Utils.getSqlConnection();
        PreparedStatement townStatement = connection.prepareStatement(TAKE_TOWN_NAMES_BY_COUNTRY);
        townStatement.setString(1,givenCountry);

        PreparedStatement townUpdateStament = connection.prepareStatement(TOWN_NAMES_UPPER_CASE_BY_COUNTRY);
        townUpdateStament.setString(1,givenCountry);

        int townsCountChange = townUpdateStament.executeUpdate();

        if (townsCountChange==0){
            System.out.println("Italy No town names were affected.");
        } else {
            System.out.println(townsCountChange+" town names were affected.");
            ResultSet resultSet = townStatement.executeQuery();
            System.out.print("[");
            while (resultSet.next()){

                if (resultSet.isLast()) {
                    System.out.printf("%s]",resultSet.getString("name"));
                } else {
                    System.out.printf("%s, ",resultSet.getString("name"));
                }
            }


        }


    }

}
