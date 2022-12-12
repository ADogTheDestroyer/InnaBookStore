import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

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

    public static ArrayList<String[]> getBooksByAuthor(String fname, String lname) {
        ArrayList<String[]> bookTuples = new ArrayList<>();
        try {
            // TO DEVS: Use your local sql server here

            ResultSet resultSet = statement.executeQuery("SELECT * FROM books INNER JOIN  authored ON books.isbn = authored.isbn INNER JOIN authors ON authored.aid = authors.aid WHERE authors.fname = '"+fname+"' AND authors.lname = '"+lname+"'"

            );

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

    public static ArrayList<String[]> getBooksByPublisher(String publisherId) {
        ArrayList<String[]> bookTuples = new ArrayList<>();
        try {
            ResultSet resultSet = statement.executeQuery("SELECT books.* FROM books INNER JOIN publishers ON books.pid = publishers.pid WHERE publishers.pid = '" + publisherId + "'");

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

    public static ArrayList<String[]> getBooksByISBN(String isbn) {
        ArrayList<String[]> bookTuples = new ArrayList<>();
        try {

            ResultSet resultSet = statement.executeQuery("select * from books where isbn = '" + isbn + "'");

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
        System.out.println(String.format("%20s %40s %10s %10s %10s", "ISBN", "Title", "Pages", "Price", "Stock"));
        System.out.println("---------------------------------------------------------------------------------------------------------------");
        for(String[] book : books) {
            System.out.println(String.format(
                    "%20s %40s %10s %10s %10s",      //format spacing
                    book[0], book[1], book[2], book[3], book[5]
            ));
        }
    }

    public static void insertBook(ArrayList<String> book){
        try {
            for(String ele : book) {
                System.out.println(ele);
            }
            statement.execute("INSERT INTO Books (isbn, title, pages, price, royalty_percent, stock, pid) VALUES ( '"+book.get(0)+"', '"+book.get(1)+"', '"+ book.get(2)+"', '"+book.get(3)+"', '"+book.get(4)+"', '"+book.get(5)+"', '"+book.get(6)+"')");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String generateISBN() {
        String numeric = "0123456789";

        String isbn = "";
        for(int i = 0; i < 9; i++) {
            isbn += numeric.charAt((int) (Math.random() * numeric.length()));
        }

        isbn += ("-" + numeric.charAt((int) (Math.random() * numeric.length())));

        return isbn;
    }

    public static boolean exists(String isbn) {
        try {
            ResultSet resultSet = statement.executeQuery("SELECT COUNT(*) AS count FROM books WHERE isbn = '" + isbn +  "'");
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

    public static void bookSetup() {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> newBook = new ArrayList<>();

        System.out.print("ISBN: ");
        newBook.add(sc.nextLine());
        System.out.print("\nTitle: ");
        newBook.add(sc.nextLine());
        System.out.print("\nPages: ");
        newBook.add(sc.nextLine());
        System.out.print("\nPrice: ");
        newBook.add(sc.nextLine());
        System.out.print("\nPublisher's Royalty Percent: ");
        newBook.add(sc.nextLine());
        System.out.print("\nAmount to add: ");
        newBook.add(sc.nextLine());

        if(Books.exists(newBook.get(0))) {
            System.out.println("Sorry, but this book already exists");
            return;
        }

        System.out.print("\nAuthor, first and lastname separated by spaces: ");
        String[] author = sc.nextLine().split(" ");
        if(author.length != 2) {
            System.out.println("Sorry, author names bust be of format (fname, lname)");
            return;
        }
        System.out.print("\nEnter up to three genres, seperated by spaces: ");
        String[] genres = sc.nextLine().split(" ");
        if(genres.length > 3 || genres.length < 1) {
            System.out.println("Sorry, you must have between 1 - 3 genres");
            return;
        }

        System.out.print("\nDoes your publisher already exist here? (y/n)");
        String ans = sc.nextLine();
        if(ans.equals("y")) {
            System.out.print("\nPID: ");
            String existingPid = sc.nextLine();

            if(Publishers.exists(existingPid)) {
                // insert to books, insert to authors, authored and genres, and then...
                newBook.add(existingPid);
                Authors.insert(author[0], author[1], newBook.get(0));
                Genre.insert(newBook.get(0), genres);
                Books.insertBook(newBook);
                return;
            }
        }
        else {

            String newPid = Publishers.generateNextPid();
            String[] publisherName = sc.nextLine().split(" ");

            System.out.print("\nPublisher's email:");
            String email = sc.nextLine();

            System.out.print("\nPublisher's bank account:");
            String bankAccount = sc.nextLine();

            System.out.print("\nEnter publisher's address information:");
            System.out.print("\nStreet name: ");
            String streetName = sc.nextLine();

            System.out.print("\nStreet number: ");
            String streetNum = sc.nextLine();

            System.out.print("\nUnit (if not applicable type 'none'): ");
            String unit = sc.nextLine();
            unit = unit.equals("none") ? null : unit;

            System.out.print("\nCity: ");
            String city = sc.nextLine();

            System.out.print("\nProvince: ");
            String province = sc.nextLine();

            System.out.print("\nCountry: ");
            String country = sc.nextLine();

            System.out.print("\nPostal Code: ");
            String postal = sc.nextLine();

            System.out.print("\nPublisher's Phone Number: ");
            String phoneNum = sc.nextLine();

            String addressId = Addresses.insert(streetName, streetNum, unit, city, province, postal, country);
            Publishers.insert(newPid, publisherName[0], publisherName[1], email, bankAccount, addressId);
            PhoneNumbers.insert(newPid, phoneNum);

            newBook.add(newPid);
            Authors.insert(author[0], author[1], newBook.get(0));
            Genre.insert(newBook.get(0), genres);
            Books.insertBook(newBook);
        }
    }
}
