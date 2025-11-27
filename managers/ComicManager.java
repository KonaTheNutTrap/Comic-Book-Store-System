package managers;

import entities.Comic;
import java.util.*;

/**
 * ComicManager class - Specialized manager for Comic entities.
 * 
 * This class extends EntityManager to provide comic-specific implementations
 * of the abstract methods defined in the parent class. It handles the business
 * logic specific to comic management including user interaction for adding
 * and updating comics.
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public class ComicManager extends EntityManager<Comic> {

    /**
     * Constructor for creating a ComicManager instance.
     * 
     * @param filename The path to the comics data file
     */
    public ComicManager(String filename) {
        super(filename); // Call parent constructor to initialize with data file
    }

    /**
     * Parses a string line into a Comic object.
     * Uses the Comic class's static factory method for deserialization.
     * 
     * @param line The comma-separated string representation of a comic
     * @return Comic object if parsing is successful, null otherwise
     */
    @Override
    protected Comic parse(String line) { return Comic.fromString(line); }

    /**
     * Serializes a Comic object to a string for file storage.
     * Uses the Comic class's toString method for serialization.
     * 
     * @param entity The Comic entity to serialize
     * @return String representation of the comic
     */
    @Override
    protected String serialize(Comic entity) { return entity.toString(); }

    /**
     * Gets the ID from a Comic entity.
     * 
     * @param entity The Comic entity to get the ID from
     * @return The comic's ID
     */
    @Override
    protected int getId(Comic entity) { return entity.getId(); }

    /**
     * Updates the fields of a Comic entity based on user input.
     * Provides specific options for which field to update.
     * 
     * @param c The Comic entity to update
     * @param sc Scanner for reading user input
     */
    @Override
    protected void updateEntity(Comic c, Scanner sc) {
        boolean updating = true;
        
        while (updating) {
            System.out.println("\n--- Update Comic: " + c.getTitle() + " ---");
            System.out.println("Select field to update:");
            System.out.println("1. Title");
            System.out.println("2. Author");
            System.out.println("3. Price");
            System.out.println("4. Genre");
            System.out.println("5. Year");
            System.out.println("6. Finish updating");
            System.out.print("Enter choice: ");
            
            int choice = sc.nextInt();
            sc.nextLine(); 
            
            switch (choice) {
                case 1:
                    System.out.print("Enter new title: ");
                    c.setTitle(sc.nextLine());
                    System.out.println("Title updated!");
                    break;
                    
                case 2:
                    System.out.print("Enter new author: ");
                    c.setAuthor(sc.nextLine());
                    System.out.println("Author updated!");
                    break;
                    
                case 3:
                    boolean validPrice = false;
                    while (!validPrice) {
                        try {
                            System.out.print("Enter new price: ");
                            double newPrice = sc.nextDouble();
                            sc.nextLine(); 
                            c.setPrice(newPrice);
                            System.out.println("Price updated!");
                            validPrice = true;
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                    }
                    break;
                    
                case 4:
                    System.out.print("Enter new genre: ");
                    c.setGenre(sc.nextLine());
                    System.out.println("Genre updated!");
                    break;
                    
                case 5:
                    System.out.print("Enter new publication year: ");
                    int newYear = sc.nextInt();
                    sc.nextLine(); 
                    c.setYear(newYear);
                    System.out.println("Year updated!");
                    break;
                    
                case 6:
                    updating = false;
                    System.out.println("Update completed!");
                    break;
                    
                default:
                    System.out.println("Invalid option! Please try again.");
                    break;
            }
        }
    }

    //Find an entity by its name
    public Comic findByName(String Title) {
        for (Comic e : entities)
            if (e.getTitle().equals(Title))
                return e;

        return null;
    }

    //Find a comic by ID or title
    public Comic findByIdOrName(String input) {
        input = input.trim();
        try {
            int id = Integer.parseInt(input);
            return findById(id);
        } catch (NumberFormatException e) {
            return findByName(input);
        }
    }

    /**
     * Gets the price of a comic by its title.
     *
     * @param title The title of the comic to search for
     * @return The price of the comic if found, -1.0 if not found
     */
    public double getPriceByTitle(String title) {
        Comic comic = findByName(title);
        return comic != null ? comic.getPrice() : -1.0;
    }

    /**
     * Interactive method to add a new comic to the collection.
     * Prompts the user for comic details and creates a new Comic instance.
     * Automatically generates the next available ID and persists the changes.
     * 
     * @param sc Scanner for reading user input
     */
    public void addComic(Scanner sc) {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        
        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            try {
                System.out.print("Enter price: ");
                price = sc.nextDouble();
                sc.nextLine(); // Consume the newline after double input
                if (price <= 0) {
                    throw new IllegalArgumentException("Price must be greater than 0");
                }
                validPrice = true;
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
                sc.nextLine(); // Clear invalid input
            }
        }
        
        System.out.print("Enter genre: ");
        String genre = sc.nextLine();
        
        System.out.print("Enter publication year: ");
        int year = sc.nextInt();
        sc.nextLine(); 
        
        // Create new comic with auto-generated ID and add to collection
        add(new Comic(nextId(), title, author, price, genre, year));
        System.out.println("Comic added!");
    }
}
