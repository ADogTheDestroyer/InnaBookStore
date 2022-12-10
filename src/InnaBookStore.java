
import java.util.ArrayList;

import java.util.Scanner;
public class InnaBookStore {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String[]> checkoutBasket = new ArrayList<>();
        System.out.println("--------------------------------");
        System.out.println("|  Welcome to InnaBook Store   |");
        System.out.println("--------------------------------");

        Users.checkCredentials();

        String textInput = "";
        while(!textInput.equals("exit")) {
            CommonOutput.menu();
            System.out.print("> ");
            textInput = sc.nextLine();

            // if they choose to search for a book by...
            if(textInput.equals("1")) {
                while(true) {
                    CommonOutput.searchMenu();
                    System.out.print("> ");
                    textInput = sc.nextLine();

                    // search by interfaces
                    if(textInput.equals("1")) {
                        while(true) {
                            System.out.print("Search for Title: ");
                            textInput = sc.nextLine();

                            if(textInput.equals("-1")) break;

                            Books.displayBooks(Books.getBooksByTitle(textInput));
                        }
                    }
                    else if(textInput.equals("2")) {
                        while(true) {
                            System.out.print("Search for author (firstname, lastname): ");
                            textInput = sc.nextLine();

                            if(textInput.equals("-1")) break;

                            String[] queryArgs = textInput.split(" ");

                            if(queryArgs.length != 2) {

                                System.out.println("Must enter the authors first, and last name, separated by a space");
                                continue;
                            }

                            Books.displayBooks(Books.getBooksByAuthor(queryArgs[0], queryArgs[1]));
                        }
                    }
                    else if(textInput.equals("3")) {
                        while(true) {
                            System.out.print("Search for Genre: ");
                            textInput = sc.nextLine();

                            if(textInput.equals("-1")) break;

                            Books.displayBooks(Books.getBooksByGenre(textInput));
                        }
                    }
                    else if(textInput.equals("4")) {
                        while(true) {
                            System.out.print("Search for Publisher ID: ");
                            textInput = sc.nextLine();

                            if(textInput.equals("-1")) break;

                            Books.displayBooks(Books.getBooksByPublisher(textInput));
                        }
                    }
                    else if(textInput.equals("-1")) {
                        break;
                    }
                }
            }
            else if(textInput.equals("2")) {
                System.out.println("This Is What Your basket Looks Like:");
                Books.displayBooks(checkoutBasket);
            }
            else if(textInput.equals("3")) {
                System.out.println("Printing All Books:");
                Books.displayBooks(Books.getAllBooks());
                String Ainput="Sully";
                while (!Ainput.equals("quit")){
                    System.out.println("Write quit to leave this page!");
                    System.out.println("Please write the title of the book you want to buy:");
                    Ainput = sc.nextLine();

                    ArrayList<String[]> temp=Books.getBooksByTitle(Ainput);
                    for (String[]items:temp){
                        checkoutBasket.add(items);
                    }

                    Books.displayBooks(Books.getBooksByTitle(Ainput));
                }
            }
            else if(textInput.equals("4")) {
                System.out.println("Printing All Items in Checkout Basket:");
                Books.displayBooks(checkoutBasket);
                String Rinput="Sully";
                while (!Rinput.equals("quit")){
                    System.out.println("Choose Title You Want to Remove::");
                    Rinput = sc.nextLine();
                    for(int i=0;i<checkoutBasket.size();i++){
                        if(checkoutBasket.get(i)[1].equals(Rinput)){
                            checkoutBasket.remove(i);
                            break;
                        }
                        if(checkoutBasket.size()==0){
                            Rinput="quit";
                        }

                    }
                    System.out.println("Here is the new Checkout Basket:");
                    Books.displayBooks(checkoutBasket);
                }

            }
            else if(textInput.equals("5")) {
                String Cinput;
                System.out.println("Here is your Basket:");
                Books.displayBooks(checkoutBasket);
                System.out.println("Type Yes to Place Order:");
                Cinput = sc.nextLine();
                if(Cinput.equals("Yes")){
                    Orders.getOrder(checkoutBasket);
                }
            }
        }
    }
}
