import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
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

    public static ArrayList<String[]> getAllOrders() {
        ArrayList<String[]> orderTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here

            ResultSet resultSet = statement.executeQuery("select * from orders");

            while (resultSet.next()) {
                orderTuples.add(new String[] {
                        resultSet.getString("order_num"),
                        resultSet.getString("tracking_num"),
                        resultSet.getString("ord_date"),
                        resultSet.getString("ord_cost"),
                        resultSet.getString("username"),
                });
            }

            return orderTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String[]> getLastMonth() {
        ArrayList<String[]> orderTuples = new ArrayList<>();
        try {
            LocalDate startOfMonth = LocalDate.now().minusMonths(1).withDayOfMonth(1);
            LocalDate endOfMonth = LocalDate.now().withDayOfMonth(1);

            ResultSet resultSet = statement.executeQuery("select * from orders where ord_date >= '" + startOfMonth + "' AND ord_date < '" + endOfMonth + "'");

            while (resultSet.next()) {
                orderTuples.add(new String[] {
                        resultSet.getString("order_num"),
                        resultSet.getString("tracking_num"),
                        resultSet.getString("ord_date"),
                        resultSet.getString("ord_cost"),
                        resultSet.getString("username"),
                });
            }

            return orderTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void displayOrders(ArrayList<String[]> orders) {
        System.out.println(String.format("%20s %20s %20s %20s %20s", "Order Number", "Tracking Number", "Date Shipped", "Order Cost", "Buyer"));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for(String[] order : orders) {
            System.out.println(String.format(
                    "%20s %20s %20s %20s %20s",      //format spacing
                    order[0], order[1], order[2], "$"+order[3], order[4]
            ));
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
