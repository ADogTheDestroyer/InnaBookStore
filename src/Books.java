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

            ResultSet resultSet = statement.executeQuery("select * from books");


            while (resultSet.next()) {
                bookTuples.add(new String[] {
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("pages"),
                        resultSet.getString("price"),
                        resultSet.getString("royalty_percent"),
                        resultSet.getString("stock"),
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
                        resultSet.getString("royalty_percent"),
                        resultSet.getString("stock"),

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
            ResultSet resultSet = statement.executeQuery("SELECT books.* FROM books INNER JOIN genres ON books.isbn = genres.isbn WHERE genres.gname = '" + genre + "'");

            while (resultSet.next()) {
                bookTuples.add(new String[] {
                        resultSet.getString("isbn"),
                        resultSet.getString("title"),
                        resultSet.getString("pages"),
                        resultSet.getString("price"),
                        resultSet.getString("royalty_percent"),
                        resultSet.getString("stock"),
                });
            }

            return bookTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void displayBooks(ArrayList<String[]> books) {
        System.out.println(String.format("%20s %40s %10s %10s %10s %10s", "ISBN", "Title", "Pages", "Price", "Royalty", "Stock"));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for(String[] book : books) {
            System.out.println(String.format(
                    "%20s %40s %10s %10s %10s %10s",      //format spacing
                    book[0], book[1], book[2], book[3], book[4], book[5]
            ));
        }
    }
}
