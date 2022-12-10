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

    public static ArrayList<String[]> getOrder() {
        ArrayList<String[]> TheOrders = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here
            Connection connection = DriverManager.getConnection(Config.connectionUrl, Config.username, Config.password);

            Statement statement = connection.createStatement();

            // ResultSet resultSet = statement.executeQuery("select * from books");
            Scanner sc = new Scanner(System.in);

            System.out.println("Printing All Books:");
            Books.displayBooks(Books.getAllBooks());
            String textInput = "bob";
            while (!textInput.equals("quit")){
                System.out.println("Write quit to leave this page!");
                System.out.println("Please write the title of the book you want to buy:");
                textInput = sc.nextLine();
                ResultSet resultSet = statement.executeQuery("select * from Books where title = '" + textInput + "'");
                while (resultSet.next()) {
                    TheOrders.add(new String[]{
                            resultSet.getString("isbn"),
                            resultSet.getString("title"),
                            resultSet.getString("pages"),
                            resultSet.getString("royalty_percent"),
                            resultSet.getString("stock"),
                    });
                }
            }
            Books.displayBooks(TheOrders);
            return TheOrders;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }



}
