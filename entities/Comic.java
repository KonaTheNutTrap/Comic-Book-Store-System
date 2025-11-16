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
    
    // Genre of the comic book (e.g., Superhero, Fantasy, Romance)
    private String genre;
    
    // Publication year of the comic book
    private int year;
    
    // Number of copies available in stock
    private int stocks;

    /**
     * Constructor for creating a Comic instance.
     * 
     * @param id The unique identifier for the comic
     * @param title The title of the comic book
     * @param author The author/creator of the comic book
     * @param price The price of the comic book in pesos
     * @param genre The genre of the comic book
     * @param year The publication year of the comic book
     * @param stocks The number of copies available in stock
     */
    public Comic(int id, String title, String author, double price, String genre, int year, int stocks) {
        this.id = id;
        this.title = title;
        this.author = author;
        setPrice(price); // Use setter for validation
        this.genre = genre;
        this.year = year;
        this.stocks = stocks;
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
     * Gets the genre of the comic.
     * 
     * @return The comic's genre
     */
    public String getGenre() { return genre; }
    
    /**
     * Gets the publication year of the comic.
     * 
     * @return The comic's publication year
     */
    public int getYear() { return year; }
    
    /**
     * Gets the stock quantity of the comic.
     * 
     * @return The comic's stock quantity
     */
    public int getStocks() { return stocks; }

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
     * Sets the price of the comic with validation.
     * 
     * @param price The new price for the comic in pesos
     * @throws IllegalArgumentException if price is less than or equal to 0
     */
    public void setPrice(double price) { 
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        this.price = price; 
    }
    
    /**
     * Sets the genre of the comic.
     * 
     * @param genre The new genre for the comic
     */
    public void setGenre(String genre) { this.genre = genre; }
    
    /**
     * Sets the publication year of the comic.
     * 
     * @param year The new publication year for the comic
     */
    public void setYear(int year) { this.year = year; }
    
    /**
     * Sets the stock quantity of the comic.
     * 
     * @param stocks The new stock quantity for the comic
     */
    public void setStocks(int stocks) { this.stocks = stocks; }

    /**
     * Converts the comic to a string representation for file storage.
     * Uses comma-separated format: id,title,author,price,genre,year,stocks
     * 
     * @return String representation suitable for file storage
     */
    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + price + "," + genre + "," + year + "," + stocks;
    }

    /**
     * Static factory method to create a Comic instance from a string.
     * This method parses a comma-separated string to reconstruct a Comic object.
     * Used for loading comic data from text files.
     * 
     * @param line A comma-separated string in the format: id,title,author,price,genre,year,stocks
     * @return Comic object if parsing is successful, null otherwise
     */
    public static Comic fromString(String line) {
        String[] parts = line.split(",");
        // Handle both old format (4 fields) and new format (7 fields)
        if (parts.length == 4) {
            // Old format - set default values for new fields
            return new Comic(Integer.parseInt(parts[0]), parts[1], parts[2], 
                           Double.parseDouble(parts[3]), "Unknown", 2000, 1);
        } else if (parts.length == 7) {
            // New format
            return new Comic(Integer.parseInt(parts[0]), parts[1], parts[2], 
                           Double.parseDouble(parts[3]), parts[4], 
                           Integer.parseInt(parts[5]), Integer.parseInt(parts[6]));
        } else {
            return null; // Invalid format
        }
    }

    /**
     * Formats the comic details in a readable string for console output.
     * 
     * @return Formatted string for display purposes
     */
    public String display() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + 
               " | Price: P" + price + " | Genre: " + genre + 
               " | Year: " + year + " | Stocks: " + stocks;
    }
}
