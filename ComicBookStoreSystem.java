import java.util.*;
import managers.*;
import entities.*;
//import java.io.*;

/*
 * Comic Book Store System - Main Application Class
 * 
 * This application provides a console-based interface for managing a comic book store.
 * It supports two types of users: Admin and Customer, with different levels of access.
 * 
 * Features:
 * - Admin: Manage comics and inventory (CRUD operations)
 * - Customer: Browse comics and make purchases
 * - Data persistence using text files
 * 
 * @author Comic Book Store System
 * @version 2.0
 */
public class ComicBookStoreSystem {
    // Scanner for user input throughout the application
    private static Scanner sc = new Scanner(System.in);
    
    // Manager instances for handling comic and inventory data
    private static ComicManager comicManager = new ComicManager("data/comics.txt");
    private static InventoryManager inventoryManager = new InventoryManager("data/stocks.txt");
    private static PurchaseManager purchaseManager = new PurchaseManager("data/orders.txt", comicManager);

    /**
     * Main entry point of the Comic Book Store System application.
     * Displays the main menu and handles user navigation between different modules.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Main application loop - continues until user chooses to exit
        while (true) {
            System.out.println("\n=== Comic Book Store System ===");
            System.out.println("1. Manage Inventory");
            System.out.println("2. Manage Store Purchases");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int choice = sc.nextInt(); sc.nextLine();
            

            // Route to appropriate menu based on user selection
            switch (choice) {
                case 1  : adminMenu(); break;        // Navigate to admin management interface
                case 2  : manageCart(); break;     // Navigate to customer interface
                case 3  :  { 
                    System.out.println("Exiting..."); 
                    return; // Exit the application
                }
                default : System.out.println("Invalid option!"); break; // Handle invalid input
            }
        }
    }

    /**
     * Admin Management Menu - Provides access to administrative functions.
     * Allows admin users to manage comic inventory and customer records.
     */
    private static void adminMenu() {
        // Admin menu loop - continues until user returns to main menu
        while (true) {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Manage Comics");
            System.out.println("2. Manage Stocks");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select option: ");
            int choice = sc.nextInt(); sc.nextLine();
         

            // Route to appropriate management module
            switch (choice) {
                case 1 : manageComics(); break;      // Navigate to comic management
                case 2 : manageInventory(); break;   // Navigate to inventory management
                case 3 : { return; }          // Return to main menu
                default : System.out.println("Invalid option!"); break;
            }
        }
    }

    /**
     * Comic Management Module - Handles all comic-related operations.
     */
    private static void manageComics() {
        // Comic management loop - continues until user returns to admin menu
        while (true) {
            System.out.println("\n--- Manage Comics ---");
            System.out.println("1. Add Comic");
            System.out.println("2. Display Comics");
            System.out.println("3. Update Comic");
            System.out.println("4. Delete Comic");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();
         

            // Execute comic operation based on user selection
            switch (choice) {
                case 1 : comicManager.addComic(sc); break; // Add new comic to inventory
                case 2 : comicManager.displayAll(Comic::display); break; // Display all comics using method reference
                case 3 : {
                    // Update existing comic - first display all, then select by ID
                    comicManager.displayAll(Comic::display);
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    comicManager.update(id, sc);
                    break;
                }
                case 4 : {
                    // Delete comic - first display all, then select by ID
                    comicManager.displayAll(Comic::display);
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt(); sc.nextLine();
                    comicManager.delete(id);
                    System.out.println("Deleted successfully!");
                    break;
                }
                case 5 : { return; } // Return to admin menu
                default : System.out.println("Invalid option!"); break;
            }
        }
    }

    /**
     * Inventory Management Module - Handles all inventory-related operations.
     */
    private static void manageInventory() {
        // Inventory management loop - continues until user returns to admin menu
        while (true) {
            System.out.println("\n--- Manage Inventory ---");
            System.out.println("1. Add Stock Record");
            System.out.println("2. Display All Stock Records");
            System.out.println("3. Update Stock Record");
            System.out.println("4. Delete Stock Record");
            System.out.println("5. Check Stock for Comic");
            System.out.println("6. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch (choice) {
                case 1:
                    inventoryManager.addStock(sc);
                    break;
                case 2:
                    inventoryManager.displayAll(comicManager);
                    break;
                case 3:
                    inventoryManager.displayAll(comicManager);
                    System.out.print("Enter Stock ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    inventoryManager.update(id, sc);
                    break;
                case 4:
                    inventoryManager.displayAll(comicManager);
                    System.out.print("Enter Stock ID to delete: ");
                    int deleteId = sc.nextInt(); sc.nextLine();
                    inventoryManager.delete(deleteId);
                    System.out.println("Deleted successfully!");
                    break;
                case 5:
                    System.out.print("Enter Comic ID to check stock: ");
                    int comicId = sc.nextInt(); sc.nextLine();
                    int quantity = inventoryManager.getStockQuantity(comicId);
                    if (quantity >= 0) {
                        System.out.println("Current stock: " + quantity);
                    } else {
                        System.out.println("No stock record found for comic ID: " + comicId);
                    }
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private static void manageCart() {
        while (true) {
        System.out.println("===Ordering Menu===");
        System.out.println("1. Add to Cart");
        System.out.println("2. View Cart");
        System.out.println("3. Remove Item from Cart");
        System.out.println("4. Checkout");
        System.out.println("5. Exit");
        System.out.print("What would you like to do? ");
        int choice = sc.nextInt();
        sc.nextLine(); 
      

        switch(choice) {
            case 1: 
            System.out.print("Enter Comic Title: ");
            String comicTitle = sc.nextLine();

            System.out.print("Enter the Amount/Quantity: ");

            int qty = sc.nextInt();
            sc.nextLine();

            purchaseManager.addOrder(comicTitle, qty);
            break;

            case 2: purchaseManager.viewOrders();

            break;

            case 3: 
            purchaseManager.viewOrders();
            System.out.print("Enter the ID of the comic you would like to remove: ");
            String c = sc.nextLine();

            purchaseManager.removeOrder(c);
            sc.nextLine();
            break;

            case 4: purchaseManager.checkout();

            case 5: return;

            default : System.out.println("Please pick from one of the choices!");
            
        }
        
    
    }

}
}
