import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Books {
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

    public static ArrayList<String[]> getAllBooks() {

        ArrayList<String[]> bookTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LookInnaBookDb", "root", "SQLwhaley1*");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from books");


            while (resultSet.next()) {
                bookTuples.add(new String[] {
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("pages"),
                        resultSet.getString("royalty"),
                        resultSet.getString("stock"),
                        resultSet.getString("genre1"),
                        resultSet.getString("genre2"),
                        resultSet.getString("genre3"),
                });
            }

            return bookTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String[]> getBooksByTitle(String title) {
        ArrayList<String[]> bookTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here

            ResultSet resultSet = statement.executeQuery("select * from books where title = '" + title + "'");

            while (resultSet.next()) {
                bookTuples.add(new String[] {
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("pages"),
                        resultSet.getString("royalty"),
                        resultSet.getString("stock"),
                        resultSet.getString("genre1"),
                        resultSet.getString("genre2"),
                        resultSet.getString("genre3"),
                });
            }

            return bookTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String[]> getBooksByGenre(String genre) {
        ArrayList<String[]> bookTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LookInnaBookDb", "root", "SQLwhaley1*");

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from books where genre1 = '" + genre + "' OR genre2 = '" + genre + "' OR genre3 = '" + genre + "'");

            while (resultSet.next()) {
                bookTuples.add(new String[] {
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("pages"),
                        resultSet.getString("royalty"),
                        resultSet.getString("stock"),
                        resultSet.getString("genre1"),
                        resultSet.getString("genre2"),
                        resultSet.getString("genre3"),
                });
            }

            return bookTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void displayBooks(ArrayList<String[]> books) {
        System.out.println(String.format("%10s %30s %10s %10s %10s %10s", "ISBN", "Title", "Pages", "Royalty", "Stock", "Genre(s)"));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for(String[] book : books) {
            System.out.println(String.format(
                    "%10s %30s %10s %10s %10s %10s %10s %10s",      //format spacing

                    book[0], book[1], book[2], book[3], book[4],    //These are the first 5 attributes

                    book[5] == null ? "" : book[5],                 //These are the genres, they can be null so we need to check first
                    book[6] == null ? "" : book[6],
                    book[7] == null ? "" : book[7]
            ));
        }
    }
}
