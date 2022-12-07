import java.util.Scanner;
public class InnaBookStore {
    public static void main(String [] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("--------------------------------");
        System.out.println("|  Welcome to InnaBook Store   |");
        System.out.println("--------------------------------");

        Users.checkCredentials();

        String textInput = "";
        while(!textInput.equals("exit")) {
            CommonOutput.menu();
            System.out.print("> ");
            textInput = sc.nextLine();
        }
    }
}
