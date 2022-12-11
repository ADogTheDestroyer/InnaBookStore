import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;

public class StoreStats {
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

    public static double balance = 50000;
    public static int numSales = 0;

    public static void addToBalance(double cost, double royalty) {
        balance += cost - (cost * (royalty/100));
    }

    public static double getMonthlyRevenue() {
        ArrayList<String[]> ordersThisMonth = Orders.getLastMonth();
        double revenu = 0;
        for(String[] order : ordersThisMonth) {
            revenu += Double.parseDouble(order[3]);
        }
        return revenu;
    }

    public static void showLastMonthOrders() {
        ArrayList<String[]> ordersThisMonth = Orders.getLastMonth();
        Orders.displayOrders(ordersThisMonth);
    }
}
