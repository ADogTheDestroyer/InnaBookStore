import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;

public class Publishers {
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

    public static ArrayList<String[]> getAll() {

        ArrayList<String[]> publisherTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here
            Connection connection = DriverManager.getConnection(Config.connectionUrl, Config.username, Config.password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from publishers");

            while (resultSet.next()) {
                publisherTuples.add(new String[] {
                        resultSet.getString("pid"),
                        resultSet.getString("addr_id"),
                        resultSet.getString("bank_acc"),
                        resultSet.getString("email"),
                        resultSet.getString("fname"),
                        resultSet.getString("lname")
                });
            }

            return publisherTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void displayBooks(ArrayList<String[]> publishers) {
        System.out.println(String.format("%10s %10s %15s %35s %10s %10s", "PID", "AddressID", "Bank Accnt", "Email", "Name", "Surname"));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for(String[] publisher : publishers) {
            System.out.println(String.format(
                    "%10s %10s %15s %35s %10s %10s",      //format spacing
                    publisher[0], publisher[1], publisher[2], publisher[3], publisher[4], publisher[5]
            ));
        }
    }

    public static String generateNextPid() {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM books");
            int newPid = -1;
            while(resultSet.next()) {
                newPid = resultSet.getInt(1) + 1;
            }
            return String.valueOf(newPid);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean exists(String fname, String lname) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM publishers WHERE fname = '" + fname +"' AND lname = '" + lname + "'");
            int counter = -1;
            while(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
            return counter > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static boolean exists(String pid) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM publishers WHERE fname = '" + pid + "'");
            int counter = -1;
            while(resultSet.next()) {
                counter = resultSet.getInt(1);
            }
            return counter > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public static void insert(String pid, String fname, String lname, String email, String bank_acc, String addr_id) {
        try {
            statement.execute("INSERT INTO publishers ( pid, fname, lname, email, bank_acc, addr_id) VALUES ( '"+pid+"', '"+fname+"', '"+ lname+"', '"+email+"', '"+bank_acc+"', '"+ addr_id+"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
