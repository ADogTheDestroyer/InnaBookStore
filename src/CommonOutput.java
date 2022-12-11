public class CommonOutput {
    public static void menu(boolean isOwner) {
        System.out.println("\nMain Menu");
        System.out.println("1) Browse books");
        System.out.println("2) Checkout Basket");
        System.out.println("3) Add to Order");
        System.out.println("4) Remove from Order");
        if(isOwner) System.out.println("5) Store Stats (Owner Only)");

    }

    public static void searchMenu() {
        System.out.println("\nSearch By...");
        System.out.println("1) Title");
        System.out.println("2) Author");
        System.out.println("3) Genre");
        System.out.println("4) All");
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
