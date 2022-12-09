import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
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

    public static void checkCredentials() {

        Scanner sc = new Scanner(System.in);

        for(int i = 2; i >=0; i--) {
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            try {
                ResultSet resultSet = statement.executeQuery("select * from users WHERE username = '" + username + "' AND pword = '" + password + "'");

                String fetchedUsername = "", fetchedPassword = "";
                while (resultSet.next()) {
                    fetchedUsername = resultSet.getString("username");
                    fetchedPassword = resultSet.getString("pword");
                }

                if(fetchedUsername.equals(username) && fetchedPassword.equals(password)) {
                    System.out.println("Welcome back " + fetchedUsername);
                    return;
                } else {
                    System.out.println("Incorrect credentials, " + i + " attempts remaining\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sorry, too many tries. :(");
        System.exit(0);
    }
}
