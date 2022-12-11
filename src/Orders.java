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
        try {
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

            for (int i = 0; i < myArr.size(); i++) {
                int newStock = Integer.parseInt(myArr.get(i)[5]);
                for(int x = i; x < myArr.size(); x++) { //heyo added this forloop so that it reflects if they buy two of the same book
                    newStock--;
                }

                statement.execute("UPDATE Books SET stock= '" + newStock + "' WHERE isbn= '" + myArr.get(i)[0] + "'");
            }
            insertToOrders(myArr, Users.getUsername());


            System.out.println("We Have processes youe Order!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void insertToOrders(ArrayList<String[]> basket, String username) {

        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM Orders");
            int orderId = -1;
            while(resultSet.next()) {
                orderId = resultSet.getInt(1) + 1;
            }

            statement.execute("""
                        INSERT INTO Orders (order_num, tracking_num, ord_date, ord_cost, username)
                        VALUES ( '%s', '%s', '%s', '%s', '%s' )
                    """.formatted(orderId, generateTrackingNum(), LocalDate.now(), computeCost(basket), username));

            for(String[] item : basket) {
                InOrders.upsert(item[0], Integer.toString(orderId));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateTrackingNum() {
        String alphaNumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        String trackingNumber = "";
        for(int i = 0; i < 10; i++) {
            trackingNumber += alphaNumeric.charAt((int) (Math.random() * alphaNumeric.length()));
        }

        return trackingNumber;
    }

    public static double computeCost(ArrayList<String[]> basket) {
        double cost = 0;
        for(String[] item : basket) {
            cost += Double.parseDouble(item[3]);
        }
        return cost;
    }
}
