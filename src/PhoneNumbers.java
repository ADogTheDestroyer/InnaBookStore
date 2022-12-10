import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class PhoneNumbers {
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

    public static ArrayList<String[]> getAllPhoneNumbers() {

        ArrayList<String[]> phoneNumberTuples = new ArrayList<>();
        try {

            ResultSet resultSet = statement.executeQuery("select * from phone_numbers");

            while (resultSet.next()) {
                phoneNumberTuples.add(new String[] {
                        resultSet.getString("pid"),
                        resultSet.getString("phone_num"),
                });
            }

            return phoneNumberTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
