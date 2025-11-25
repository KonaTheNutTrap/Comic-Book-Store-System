package managers;
import entities.Stock;
import java.util.*;

/**
 * InventoryManager class - Specialized manager for Stock entities.
 * 
 * This class extends EntityManager to provide inventory-specific implementations
 * for managing stock levels and inventory operations separately from comic product details.
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public class InventoryManager extends EntityManager<Stock> {

    /**
     * Constructor for creating an InventoryManager instance.
     * 
     * @param filename The path to the stocks data file
     */
    public InventoryManager(String filename) {
        super(filename); // Call parent constructor to initialize with data file
    }

    /**
     * Parses a string line into a Stock object.
     * Uses the Stock class's static factory method for deserialization.
     * 
     * @param line The comma-separated string representation of a stock
     * @return Stock object if parsing is successful, null otherwise
     */
    @Override
    protected Stock parse(String line) { return Stock.fromString(line); }

    /**
     * Serializes a Stock object to a string for file storage.
     * Uses the Stock class's toString method for serialization.
     * 
     * @param entity The Stock entity to serialize
     * @return String representation of the stock
     */
    @Override
    protected String serialize(Stock entity) { return entity.toString(); }

    /**
     * Gets the ID from a Stock entity.
     * 
     * @param entity The Stock entity to get the ID from
     * @return The stock's ID
     */
    @Override
    protected int getId(Stock entity) { return entity.getId(); }

    /**
     * Updates the fields of a Stock entity based on user input.
     * Provides specific options for which field to update.
     * Note: Comic ID cannot be changed to maintain 1:1 relationship with stock ID.
     *
     * @param s The Stock entity to update
     * @param sc Scanner for reading user input
     */
    @Override
    protected void updateEntity(Stock s, Scanner sc) {
        boolean updating = true;

        while (updating) {
            System.out.println("\n--- Update Stock: Comic ID " + s.getId() + " ---");
            System.out.println("Select field to update:");
            System.out.println("1. Quantity");
            System.out.println("2. Add Stock");
            System.out.println("3. Remove Stock");
            System.out.println("4. Finish updating");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); 
            switch (choice) {
                case 1:
                    boolean validQuantity = false;
                    while (!validQuantity) {
                        try {
                            System.out.print("Enter new quantity: ");
                            int newQuantity = sc.nextInt();
                            sc.nextLine(); 
                            s.setQuantity(newQuantity);
                            System.out.println("Quantity updated!");
                            validQuantity = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 2:
                    boolean validAdd = false;
                    while (!validAdd) {
                        try {
                            System.out.print("Enter amount to add: ");
                            int addAmount = sc.nextInt();
                            sc.nextLine(); 
                            s.addStock(addAmount);
                            System.out.println("Stock added! New quantity: " + s.getQuantity());
                            validAdd = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 3:
                    boolean validRemove = false;
                    while (!validRemove) {
                        try {
                            System.out.print("Enter amount to remove: ");
                            int removeAmount = sc.nextInt();
                            sc.nextLine(); 
                            s.removeStock(removeAmount);
                            System.out.println("Stock removed! New quantity: " + s.getQuantity());
                            validRemove = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;

                case 4:
                    updating = false;
                    System.out.println("Update completed!");
                    break;

                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }
    }

    /**
     * Interactive method to add a new stock record to the collection.
     * Prompts the user for stock details and creates a new Stock instance.
     * Stock ID must equal comic ID 
     *
     * @param sc Scanner for reading user input
     */
    public void addStock(Scanner sc) {
        System.out.print("Enter comic ID: ");
        int comicId = sc.nextInt();
        sc.nextLine(); 

        // Check if stock already exists for this comic
        if (findByComicId(comicId) != null) {
            System.out.println("Error: Stock record already exists for comic ID " + comicId +
                             ". Each comic can have only one stock record.");
            return;
        }

        boolean validQuantity = false;
        int quantity = 0;
        while (!validQuantity) {
            try {
                System.out.print("Enter initial quantity: ");
                quantity = sc.nextInt();
                sc.nextLine(); 
                if (quantity < 0) {
                    throw new IllegalArgumentException("Quantity cannot be negative");
                }
                validQuantity = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // Clear invalid input
            }
        }

        // Create new stock with comicId as stockId and add to collection
        add(new Stock(comicId, quantity));
        System.out.println("Stock record added!");
    }

    /**
     * Displays all stock records with comic names.
     * This method requires access to a ComicManager to look up comic names.
     * 
     * @param comicManager The ComicManager instance to look up comic details
     */
    public void displayAll(ComicManager comicManager) {
        if (getAll().isEmpty()) {
            System.out.println("No stock records found.");
            return;
        }
        
        System.out.println("\n--- All Stock Records ---");
        for (Stock stock : getAll()) {
            String comicName = "Unknown Comic";
            // Look up comic name using comic ID
            entities.Comic comic = comicManager.findById(stock.getComicId());
            if (comic != null) {
                comicName = comic.getTitle();
            }
            System.out.println("Stock ID: " + stock.getId() + 
                             " | Comic: " + comicName + 
                             " (ID: " + stock.getComicId() + ")" +
                             " | Quantity: " + stock.getQuantity());
        }
    }

    /**
     * Finds a stock record by comic ID.
     * 
     * @param comicId The comic ID to search for
     * @return The stock record with the matching comic ID, or null if not found
     */
    public Stock findByComicId(int comicId) {
        for (Stock s : getAll()) {
            if (s.getComicId() == comicId) {
                return s;
            }
        }
        return null; // Stock record not found
    }

    /**
     * Updates the stock quantity for a specific comic.
     * 
     * @param comicId The comic ID to update stock for
     * @param newQuantity The new stock quantity
     * @return true if update was successful, false if stock record not found
     */
    public boolean updateStockQuantity(int comicId, int newQuantity) {
        Stock stock = findByComicId(comicId);
        if (stock != null) {
            try {
                stock.setQuantity(newQuantity);
                save(); // Persist changes to file
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error updating stock: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     * Adds stock to a specific comic.
     * 
     * @param comicId The comic ID to add stock to
     * @param amount The amount to add
     * @return true if addition was successful, false if stock record not found
     */
    public boolean addStockToComic(int comicId, int amount) {
        Stock stock = findByComicId(comicId);
        if (stock != null) {
            try {
                stock.addStock(amount);
                save(); // Persist changes to file
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error adding stock: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     * Removes stock from a specific comic.
     * 
     * @param comicId The comic ID to remove stock from
     * @param amount The amount to remove
     * @return true if removal was successful, false if stock record not found or insufficient stock
     */
    public boolean removeStockFromComic(int comicId, int amount) {
        Stock stock = findByComicId(comicId);
        if (stock != null) {
            try {
                stock.removeStock(amount);
                save(); // Persist changes to file
                return true;
            } catch (IllegalArgumentException e) {
                System.out.println("Error removing stock: " + e.getMessage());
                return false;
            }
        }
        return false;
    }

    /**
     * Gets the current stock quantity for a specific comic.
     * 
     * @param comicId The comic ID to check
     * @return The current stock quantity, or -1 if stock record not found
     */
    public int getStockQuantity(int comicId) {
        Stock stock = findByComicId(comicId);
        return stock != null ? stock.getQuantity() : -1;
    }

    /**
     * Checks if there is sufficient stock for a specific comic.
     * 
     * @param comicId The comic ID to check
     * @param requiredQuantity The quantity needed
     * @return true if sufficient stock exists, false otherwise
     */
    public boolean hasSufficientStock(int comicId, int requiredQuantity) {
        int currentStock = getStockQuantity(comicId);
        return currentStock >= requiredQuantity;
    }
}
