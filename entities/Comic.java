package entities;

/**
 * Comic entity class representing a comic book in the Comic Book Store System.
 * 
 * This class models the core product of the store, containing attributes
 * for identification, description, and pricing. It implements serialization
 * and deserialization methods for file storage.
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public class Comic {
    // Unique identifier for the comic 
    private int id;
    
    // Title of the comic book 
    private String title;
    
    // Author/creator of the comic book 
    private String author;
    
    // Price of the comic book in pesos
    private double price;

    /**
     * Constructor for creating a Comic instance.
     * 
     * @param id The unique identifier for the comic
     * @param title The title of the comic book
     * @param author The author/creator of the comic book
     * @param price The price of the comic book in pesos
     */
    public Comic(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    /**
     * Gets the unique identifier of the comic.
     * 
     * @return The comic's ID
     */
    public int getId() { return id; }
    
    /**
     * Gets the title of the comic.
     * 
     * @return The comic's title
     */
    public String getTitle() { return title; }
    
    /**
     * Gets the author of the comic.
     * 
     * @return The comic's author
     */
    public String getAuthor() { return author; }
    
    /**
     * Gets the price of the comic.
     * 
     * @return The comic's price in pesos
     */
    public double getPrice() { return price; }

    /**
     * Sets the title of the comic.
     * 
     * @param title The new title for the comic
     */
    public void setTitle(String title) { this.title = title; }
    
    /**
     * Sets the author of the comic.
     * 
     * @param author The new author for the comic
     */
    public void setAuthor(String author) { this.author = author; }
    
    /**
     * Sets the price of the comic.
     * 
     * @param price The new price for the comic in pesos
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Converts the comic to a string representation for file storage.
     * Uses comma-separated format: id,title,author,price
     * 
     * @return String representation suitable for file storage
     */
    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + price;
    }

    /**
     * Static factory method to create a Comic instance from a string.
     * This method parses a comma-separated string to reconstruct a Comic object.
     * Used for loading comic data from text files.
     * 
     * @param line A comma-separated string in the format: id,title,author,price
     * @return Comic object if parsing is successful, null otherwise
     */
    public static Comic fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null; // Invalid format
        return new Comic(Integer.parseInt(parts[0]), parts[1], parts[2], Double.parseDouble(parts[3]));
    }

    /**
     * Formats the comic details in a readable string for console output.
     * 
     * @return Formatted string for display purposes
     */
    public String display() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Price: P" + price;
    }
}
