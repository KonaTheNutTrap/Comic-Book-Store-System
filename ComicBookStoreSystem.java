import java.util.*;
import managers.*;
import entities.*;
//import java.io.*;

/**
 * Comic Book Store System - Main Application Class
 * 
 * This application provides a console-based interface for managing a comic book store.
 * It supports two types of users: Admin and Customer, with different levels of access.
 * 
 * Features:
 * - Admin: Manage comics and customer records (CRUD operations)
 * - Customer: Browse comics and make purchases (placeholder)
 * - Data persistence using text files
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public class ComicBookStoreSystem {
    // Scanner for user input throughout the application
    private static Scanner sc = new Scanner(System.in);
    
    // Manager instances for handling comic and customer data
    private static ComicManager comicManager = new ComicManager("data/comics.txt");
    private static CustomerManager customerManager = new CustomerManager("data/customers.txt");

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
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Login");
            System.out.println("3. Exit");
            System.out.print("Select option: ");
            int choice = sc.nextInt(); sc.nextLine(); 

            // Route to appropriate menu based on user selection
            switch (choice) {
                case 1  : adminMenu(); break;        // Navigate to admin management interface
                case 2  : customerMenu(); break;     // Navigate to customer interface
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
            System.out.println("2. Manage Customers");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select option: ");
            int choice = sc.nextInt(); sc.nextLine();

            // Route to appropriate management module
            switch (choice) {
                case 1 : manageComics(); break;      // Navigate to comic management
                case 2 : manageCustomers(); break;   // Navigate to customer management
                case 3 : { return; }          // Return to main menu
                default : System.out.println("Invalid option!"); break;
            }
        }
    }

    /**
     * Customer Interface Menu - Placeholder for customer functionality.
     * Currently displays planned features and returns to main menu.
     * Future implementation will include browsing, purchasing, and order history.
     */
    private static void customerMenu() {
        System.out.println("\n=== Customer Menu ===");
        System.out.println("Customer features");
        System.out.println("Browse available comics");
        System.out.println("Make purchases");
        System.out.println("View order history");
        System.out.println("Press Enter to return to main menu...");
        sc.nextLine(); // Wait for user input before returning
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
     * Customer Management Module - Handles all customer-related operations.
     */
    private static void manageCustomers() {
        // Customer management loop - continues until user returns to admin menu
        while (true) {
            System.out.println("\n--- Manage Customers ---");
            System.out.println("1. Add Customer");
            System.out.println("2. Display Customers");
            System.out.println("3. Update Customer");
            System.out.println("4. Delete Customer");
            System.out.println("5. Back");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            // Execute customer operation based on user selection
            switch (choice) {
                case 1 : customerManager.addCustomer(sc); break; // Add new customer to database
                case 2 : customerManager.displayAll(Customer::display); break; // Display all customers using method reference
                case 3 : {
                    // Update existing customer - first display all, then select by ID
                    customerManager.displayAll(Customer::display);
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt(); sc.nextLine();
                    customerManager.update(id, sc);
                    break;
                }
                case 4 : {
                    // Delete customer - first display all, then select by ID
                    customerManager.displayAll(Customer::display);
                    System.out.print("Enter ID to delete: ");
                    int id = sc.nextInt(); sc.nextLine();
                    customerManager.delete(id);
                    System.out.println("Deleted successfully!");
                    break;
                }
                case 5 : { return; } // Return to admin menu
                default : System.out.println("Invalid option!"); break;
            }
        }
    }
}
