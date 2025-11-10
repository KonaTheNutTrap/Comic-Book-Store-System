package entities;

/**
 * Abstract base class representing a Person in the Comic Book Store System.
 * 
 * This class serves as the foundation for all person entities in the system,
 * providing common attributes and methods that all person types share.
 * Uses the Template Method pattern by defining abstract methods that must be
 * implemented by subclasses.
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public abstract class Person {
    /** Unique identifier for the person */
    protected int id;
    
    /** Name of the person */
    protected String name;

    /**
     * Constructor for creating a Person instance.
     * 
     * @param id The unique identifier for the person
     * @param name The name of the person
     */
    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Gets the unique identifier of the person.
     * 
     * @return The person's ID
     */
    public int getId() { return id; }
    
    /**
     * Gets the name of the person.
     * 
     * @return The person's name
     */
    public String getName() { return name; }
    
    /**
     * Sets the name of the person.
     * 
     * @param name The new name for the person
     */
    public void setName(String name) { this.name = name; }

    /**
     * Abstract method for converting the person to a string representation.
     * Subclasses must implement this to define their specific string format.
     * 
     * @return String representation of the person
     */
    public abstract String toString();
    
    /**
     * Abstract method for displaying the person's details in a user-friendly format.
     * Subclasses must implement this to define their specific display format.
     * 
     * @return Formatted string for display purposes
     */
    public abstract String display();
}
