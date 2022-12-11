public class CommonOutput {
    public static void menu(boolean isOwner) {
        System.out.println("\nMain Menu");
        System.out.println("1) Search for books by...");
        System.out.println("2) View Checkout Basket");
        System.out.println("3) Add to Order");
        System.out.println("4) Remove Item From Order");
        System.out.println("5) Go To Checkout Basket");
        if(isOwner) System.out.println("6) Show store stats");

    }

    public static void searchMenu() {
        System.out.println("\nSearch By...");
        System.out.println("1) Title");
        System.out.println("2) Author");
        System.out.println("3) Genre");
        System.out.println("4) Publisher ID");
    }

    public static void statsMenu() {
        System.out.println("1) Show orders of previous month");
        System.out.println("2) Show stores balance");
        System.out.println("3) Show stores expenditures of previous month");
        System.out.println("4) Add to Book Collection");
        System.out.println("5) Remove from Book Collection");
        System.out.println("6) Publisher Contact Information");
    }
}
