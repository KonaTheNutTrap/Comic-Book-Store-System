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
     * Prompts the user for new values for title, author, and price.
     * 
     * @param c The Comic entity to update
     * @param sc Scanner for reading user input
     */
    @Override
    protected void updateEntity(Comic c, Scanner sc) {
        System.out.print("Enter new title: ");
        c.setTitle(sc.nextLine());
        System.out.print("Enter new author: ");
        c.setAuthor(sc.nextLine());
        System.out.print("Enter new price: ");
        c.setPrice(sc.nextDouble());
        sc.nextLine(); // Consume the newline after double input
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
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine(); // Consume the newline after double input
        
        // Create new comic with auto-generated ID and add to collection
        add(new Comic(nextId(), title, author, price));
        System.out.println("Comic added!");
    }
}
