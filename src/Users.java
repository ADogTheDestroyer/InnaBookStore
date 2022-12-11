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
    private static String username;
    public static String getUsername() {
        return username;
    }

    public static boolean checkCredentials() {
        System.out.println();
        Scanner sc = new Scanner(System.in);

        for(int i = 2; i >=0; i--) {
            System.out.print("Username: ");
            String username = sc.nextLine();

            System.out.print("Password: ");
            String password = sc.nextLine();

            try {
                ResultSet resultSet = statement.executeQuery("select * from users WHERE username = '" + username + "' AND pword = '" + password + "'");

                String fetchedUsername = "", fetchedPassword = "";
                boolean fetchedIsOwner = false;
                while (resultSet.next()) {
                    fetchedUsername = resultSet.getString("username");
                    fetchedPassword = resultSet.getString("pword");
                    fetchedIsOwner = resultSet.getBoolean("isowner");
                }

                if(fetchedUsername.equals(username) && fetchedPassword.equals(password)) {
                    System.out.println("Welcome back " + fetchedUsername);
                    Users.username = username;
                    return fetchedIsOwner;
                } else {
                    System.out.println("Incorrect credentials, " + i + " attempts remaining\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("Sorry, too many tries. :(");
        System.exit(0);
        return false;
    }
    public static boolean loginPage() {
        Scanner sc = new Scanner(System.in);
        String LInput = "";
        while(!LInput.equals("1") && !LInput.equals("2")) {
            System.out.println("1.Login ");
            System.out.println("2.Register");
            LInput=sc.nextLine();
        }
        if(LInput.equals("1")){
            return true;
        }
        else{
            try {
                boolean ok=true;
                String UInput="";
                String PInput="";
                String FNInput="";
                String LNInput="";
                while(ok=true){
                    System.out.print("Please Enter a Unique Username:");
                    UInput = sc.nextLine();
                    ResultSet resultSet = statement.executeQuery("select * from users");
                    String getUsername;
                    while (resultSet.next()) {
                        getUsername = resultSet.getString("username");
                        if (UInput.equals(getUsername)) {
                            System.out.println("This Username already exist please choose another one");
                            ok=false;
                        }
                    }
                    if(ok=true){
                        if(UInput.length()==0){
                            System.out.println("You have to Type Something for UserName");
                        }
                        else{
                            break;

                        }
                    }
                }
                while(true){
                    System.out.print("Please Enter a Password:");
                    PInput=sc.nextLine();
                    if(PInput.length()==0){
                        System.out.println("You have to Type Something for Password");
                    }
                    else{
                        break;

                    }
                }
                while(true) {
                    System.out.print("Enter Your First Name:");
                    FNInput=sc.nextLine();
                    if(FNInput.length()==0){
                        System.out.println("You have to Type Something for First Name");
                    }
                    else{
                        break;

                    }
                }
                while(true) {
                    System.out.print("Enter Your Last Name:");
                    LNInput = sc.nextLine();
                    if(LNInput.length()==0){
                        System.out.println("You have to Type Something for Last Name");
                    }
                    else{
                        break;

                    }
                }
                statement.execute("INSERT INTO users(username,pword,fname,lname,isowner) VALUES('"+UInput+"','"+PInput+"','"+FNInput+"','"+LNInput+"','"+false+"')");
                return true;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return true;
    }


}
