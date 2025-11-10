import java.util.*;
import managers.*;
import entities.*;

public class ComicBookStoreSystem {
    private static Scanner sc = new Scanner(System.in);
    private static ComicManager comicManager = new ComicManager("data/comics.txt");
    private static CustomerManager customerManager = new CustomerManager("data/customers.txt");

    public static void main(String[] args) {
        while (true) {
            System.out.println("\n=== Comic Book Store System ===");
            System.out.println("1. Manage Comics");
            System.out.println("2. Manage Customers");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> manageComics();
                case 2 -> manageCustomers();
                case 3 -> { System.out.println("Exiting..."); return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void manageComics() {
        while (true) {
            System.out.println("\n--- Manage Comics ---");
            System.out.println("1. Add Comic");
            System.out.println("2. Display Comics");
            System.out.println("3. Update Comic");
            System.out.println("4. Delete Comic");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> comicManager.addComic(sc);
                case 2 -> comicManager.displayAll(Comic::display);
                case 3 -> {
                    comicManager.displayAll(Comic::display);
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    comicManager.update(id, sc);
                }
                case 4 -> {
                    comicManager.displayAll(Comic::display);
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt(); sc.nextLine();
                    comicManager.delete(id);
                    System.out.println("Deleted successfully!");
                }
                case 5 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }

    private static void manageCustomers() {
        while (true) {
            System.out.println("\n--- Manage Customers ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Display Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1 -> customerManager.addCustomer(sc);
                case 2 -> customerManager.displayAll(Customer::display);
                case 3 -> {
                    customerManager.displayAll(Customer::display);
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    customerManager.update(id, sc);
                }
                case 4 -> {
                    customerManager.displayAll(Customer::display);
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt(); sc.nextLine();
                    customerManager.delete(id);
                    System.out.println("Deleted successfully!");
                }
                case 5 -> { return; }
                default -> System.out.println("Invalid option!");
            }
        }
    }
}
