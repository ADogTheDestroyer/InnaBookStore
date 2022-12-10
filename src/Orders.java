import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Orders {
    //establishes connection to your local server
    //-> Go to Config.java and change those values to your own connection stuff
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
    public static void getOrder(ArrayList<String[]> myArr) {
        for (String[]items:myArr){
            if(Integer.parseInt(items[5])>3){
                        break;
            }
        }
    }
}
