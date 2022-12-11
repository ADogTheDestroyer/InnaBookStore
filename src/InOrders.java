import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class InOrders {
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

    public static void upsert(String isbn, String orderNum) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM in_orders WHERE isbn = '" + isbn + "' AND order_num = '" + orderNum + "'");
            int size = 0;
            while(resultSet.next()) {
                size = resultSet.getInt(1);
            }

            if(size == 0) {
                statement.execute("""
                            INSERT INTO in_orders (isbn, order_num, quantity)
                            VALUES ( '%s', '%s', '%s' )
                        """.formatted(isbn, orderNum, 1));
            } else {
                statement.executeUpdate("""
                            UPDATE in_orders
                            SET quantity = quantity + 1
                            WHERE isbn = '%s' AND order_num = '%s'
                        """.formatted(isbn, orderNum));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
