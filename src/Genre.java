import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Genre {
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

    public static ArrayList<String[]> getAllGenre() {

        ArrayList<String[]> GenreTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here
            Connection connection = DriverManager.getConnection(Config.connectionUrl, Config.username, Config.password);

            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("select * from Genres");


            while (resultSet.next()) {
                GenreTuples.add(new String[]{
                        resultSet.getString("isbn"),
                        resultSet.getString("gname"),
                });
            }

            return GenreTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String[]> getGenre(String genre) {
        ArrayList<String[]> GenreTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here

            ResultSet resultSet = statement.executeQuery("select * from Genres where gname = '" + genre + "'");

            while (resultSet.next()) {
                GenreTuples.add(new String[]{
                        resultSet.getString("isbn"),
                        resultSet.getString("gname"),

                });
            }

            return GenreTuples;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void insert(String isbn, String[] genres) {
        try {
            for(String genre : genres) {
                statement.execute("INSERT INTO genres (isbn, gname) VALUES ( '" + isbn + "', '" + genre + "')");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void displayGenre(ArrayList<String[]> Genre) {
        System.out.println(String.format("%10s %30s ", "ISBN", "Genre Name"));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for(String[] Genres : Genre) {
            System.out.println(String.format(
                    "%10s %30s",      //format spacing

                    Genres[0], Genres[1]
            ));
        }
    }


}