import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Addresses {
    public static Connection connection;
    public static Statement statement;
    static {
        try {
            connection = DriverManager.getConnection(Config.connectionUrl, Config.username, Config.password);

            statement = connection.createStatement();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String[]> getAllAddresses() {

        ArrayList<String[]> addressTuples = new ArrayList<>();
        try {

            ResultSet resultSet = statement.executeQuery("select * from addresses");

            while (resultSet.next()) {
                addressTuples.add(new String[] {
                        resultSet.getString("addr_id"),
                        resultSet.getString("street_name"),
                        resultSet.getString("street_num"),
                        resultSet.getString("unit"),
                        resultSet.getString("city"),
                        resultSet.getString("province"),
                        resultSet.getString("postal"),
                        resultSet.getString("country"),
                });
            }

            return addressTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
