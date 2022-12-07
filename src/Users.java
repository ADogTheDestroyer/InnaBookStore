import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {

    public static void checkCredentials() {

        Scanner sc = new Scanner(System.in);

        for(int i = 2; i >=0; i--) {
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            try {
                // TO DEVS: Use your local sql server here
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/LookInnaBookDb", "root", "SQLwhaley1*");

                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("select * from users WHERE uname = '" + username + "' AND pword = '" + password + "'");

                String fetchedUsername = "", fetchedPassword = "";
                while (resultSet.next()) {
                    fetchedUsername = resultSet.getString("uname");
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
