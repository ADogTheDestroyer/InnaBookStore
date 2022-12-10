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
        try {
            // TO DEVS: Use your local sql server here


            int count = 0;
            for (int i = 0; i < myArr.size(); i++) {
                for (int x = 0; x < myArr.size(); x++) {
                    if (myArr.get(i) == myArr.get(x)) {
                        count++;
                    }
                }
                if (Integer.parseInt(myArr.get(i)[5]) <= count) {
                    System.out.println("We only have " + myArr.get(i)[5] + " of " + myArr.get(i)[1] + " left please remove some from your basket");
                    return;
                }
                count = 0;
            }
            System.out.println("We Have processes youe Order!");
            for (int i = 0; i < myArr.size(); i++) {
                int newStock=Integer.parseInt(myArr.get(i)[5]);
                newStock--;

                statement.execute("UPDATE Books SET stock= '"+newStock+"' WHERE isbn= '"+myArr.get(i)[0]+"'");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
