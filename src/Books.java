import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;


public class Books {
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
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LookInnaBookDb", "root", "SQLwhaley1*");

            Statement statement = connection.createStatement();

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

        Formatter fmt = new Formatter();
        fmt.format("%20s %20s %20s %15s %20s %20s\n", "ISBN", "Title", "Pages", "Royalty", "Stock", "Genre(s)");
        fmt.format("---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        for(String[] book : books) {
            for(String attribute : book) {

                if(attribute == null) continue;

                if(attribute.equals("Title")) fmt.format("%30s", attribute);
                else fmt.format("%20s", attribute);

            }
            fmt.format("\n");
        }
        System.out.println(fmt);
    }
}
