import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

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

    public static ArrayList<String[]> getUsersOrders(String username) {
        ArrayList<String[]> orderTuples = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("select orders.order_num, orders.tracking_num, orders.ord_date, orders.ord_cost, books.isbn, books.title, books.price, in_orders.quantity from orders INNER JOIN in_orders ON orders.order_num = in_orders.order_num INNER JOIN books ON in_orders.isbn = books.isbn WHERE orders.username  = '" + username + "'");

            while (resultSet.next()) {
                orderTuples.add(new String[] {
                        resultSet.getString("order_num"),
                        resultSet.getString("tracking_num"),
                        resultSet.getString("ord_date"),
                        resultSet.getString("ord_cost"),
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("price"),
                        resultSet.getString("quantity")
                });
            }

            return orderTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void displayUsersOrders(ArrayList<String[]> orders) {
        ArrayList<ArrayList<String[]>> ordersList = new ArrayList<>();
        TreeSet<String> orderNumsAdded = new TreeSet<>();
        for(int i = 0; i < orders.size(); i++) {
            ArrayList<String[]> temp = new ArrayList<>();

            if(!orderNumsAdded.contains(orders.get(i)[0])) {
                orderNumsAdded.add(orders.get(i)[0]);
                for (String[] order : orders) {
                    if (order[0].equals(orders.get(i)[0])) {
                        temp.add(order);
                    }
                }
                ordersList.add(temp);
            }
        }

        for(ArrayList<String[]> order : ordersList) {
            System.out.print("\n----------Order: " + order.get(0)[0]);
            System.out.println(", Traking Number: " + order.get(0)[1] + ", Date Shipped: " + order.get(0)[2] + ", Cost: " + order.get(0)[3]);

            System.out.println(String.format("%20s %30s %20s %20s", "ISBN", "Title", "Cost", "Quantity"));
            for(String[] item : order) {
                System.out.println(String.format(
                        "%20s %30s %20s %20s",      //format spacing
                        item[4], item[5], item[6], item[7]
                ));
            }
        }
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

    public static void sendOrder(ArrayList<String[]> myArr) {

        if(myArr.isEmpty()) {
            System.out.println("Must have at least 1 item in basket to checkout");
            return;
        }
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
            System.out.println("We Have processes your Order!");

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
                StoreStats.addToBalance(Double.parseDouble(item[3]), Double.parseDouble(item[4]));
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
