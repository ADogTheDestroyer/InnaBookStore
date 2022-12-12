import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.Scanner;

public class Users {
    static String theUserName="";
    public static boolean setTheUserName(String a){
            theUserName=a;
            return true;
    }
    public static String getTheUserName(){
        return theUserName;
    }

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
                    setTheUserName(username);
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
                    boolean itsUnnique=true;
                    System.out.print("Please Enter a Unique Username:");
                    UInput = sc.nextLine();
                    ResultSet resultSet = statement.executeQuery("select * from users");
                    String getUsername;
                    while (resultSet.next()) {
                        getUsername = resultSet.getString("username");
                        if (UInput.equals(getUsername)) {
                            System.out.println("This Username already exist please choose another one");
                            itsUnnique=false;
                        }
                    }
                    if(itsUnnique==true){
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
                AddAddress(UInput);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
        return true;
    }
    public static boolean AddAddress(String userN) {
        System.out.println();
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Time to add an address!");
            String input1="";
            int input2=2;
            int input3=3;
            String input4="";
            String input5="";
            String input6="";
            String input7="";
            while(true) {
                System.out.print("Enter Street Name:");
                input1 = sc.nextLine();
                if(input1.length()==0){
                    System.out.println("You have to Type Something for Street Name");
                }
                else{
                    break;

                }
            }
            while(true) {
                System.out.print("Enter Street Number:");
                String Tempin = sc.nextLine();
                if(Tempin.length()==0){
                    System.out.println("You have to Type Something for Street Number");
                }
                else{
                    input2=Integer.parseInt(Tempin);
                    break;

                }
            }
            while(true) {
                System.out.print("Enter Unit Number:");
                String Tempin = sc.nextLine();
                if(Tempin.length()==0){
                    System.out.println("You have to Type Something for Unit Number");
                }
                else{
                    input3=Integer.parseInt(Tempin);
                    break;

                }
            }
            while(true) {
                System.out.print("Enter City:");
                input4 = sc.nextLine();
                if(input4.length()==0){
                    System.out.println("You have to Type Something for City");
                }
                else{
                    break;

                }
            }
            while(true) {
                System.out.print("Enter Province:");
                input5 = sc.nextLine();
                if(input5.length()==0){
                    System.out.println("You have to Type Something for Province");
                }
                else{
                    break;

                }
            }
            while(true) {
                System.out.print("Enter Postal Code:");
                input6 = sc.nextLine();
                if(input6.length()==0){
                    System.out.println("You have to Type Something for Postal Code");
                }
                else{
                    break;

                }
            }
            while(true) {
                System.out.print("Enter Country:");
                input7 = sc.nextLine();
                if(input7.length()==0){
                    System.out.println("You have to Type Something for Country");
                }
                else{
                    break;

                }
            }
            ResultSet myResult = statement.executeQuery("select * from addresses");
            String getid="";
            while (myResult.next()) {
                getid = myResult.getString("addr_id");
            }
            int newid=Integer.parseInt(getid);
            newid++;
            statement.execute("INSERT INTO addresses(addr_id,street_name,street_num,unit,city,province,postal,country) VALUES('"+newid+"','"+input1+"','"+input2+"','"+input3+"','"+input4+"','"+input5+"','"+input6+"','"+input7+"')");
            boolean ship=true;
            boolean bill=true;
            while(true){
                System.out.print("Is This a shipping address(Y or N)");
                String tempVal= sc.nextLine();
                if(tempVal.equals("N")){
                    ship=false;
                    System.out.print("Is This a billing address(Y or N)");
                    String tempVal2= sc.nextLine();
                    if(tempVal2.equals("Y")){break;}
                    if(tempVal2.equals("N")){bill=false;break;}
                }
                else if(tempVal.equals("Y")){
                    System.out.print("Is This a billing address(Y or N)");
                    String tempVal2= sc.nextLine();
                    if(tempVal2.equals("Y")){break;}
                    if(tempVal2.equals("N")){bill=false;break;}
                }
            }

            statement.execute("INSERT INTO user_addrs(username,addr_id,isshipping,isbilling) VALUES('"+userN+"','"+newid+"','"+ship+"','"+bill+"')");
            System.out.print("The Address is now added to your Account!!!");
            return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        return true;
    }


}
