import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class Authors {
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

    public static ArrayList<String[]> getAllAuthors() {

        ArrayList<String[]> authorTuples = new ArrayList<>();
        try {

            ResultSet resultSet = statement.executeQuery("select * from authors");

            while (resultSet.next()) {
                authorTuples.add(new String[] {
                        resultSet.getString("aid"),
                        resultSet.getString("fname"),
                        resultSet.getString("lname"),
                });
            }

            return authorTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
