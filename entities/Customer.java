package entities;

/**
 * Customer entity class representing a customer in the Comic Book Store System.
 * 
 * This class extends the abstract Person class and adds customer-specific
 * attributes and behaviors.
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public class Customer extends Person {
    /** Contact information for the customer (email or phone) */
    private String contact;

    /**
     * Constructor for creating a Customer instance.
     * 
     * @param id The unique identifier for the customer
     * @param name The name of the customer
     * @param contact Contact information (email or phone)
     */
    public Customer(int id, String name, String contact) {
        super(id, name); // Call parent class constructor
        this.contact = contact;
    }

    /**
     * Gets the contact information of the customer.
     * 
     * @return The customer's contact information
     */
    public String getContact() { return contact; }
    
    /**
     * Sets the contact information of the customer.
     * 
     * @param contact The new contact information
     */
    public void setContact(String contact) { this.contact = contact; }

    /**
     * Converts the customer to a string representation for file storage.
     * Uses comma-separated format: id,name,contact
     * 
     * @return String representation suitable for file storage
     */
    @Override
    public String toString() {
        return id + "," + name + "," + contact;
    }

    /**
     * Static factory method to create a Customer instance from a string.
     * This method parses a comma-separated string to reconstruct a Customer object.
     * Used for loading customer data from text files.
     * 
     * @param line A comma-separated string in the format: id,name,contact
     * @return Customer object if parsing is successful, null otherwise
     */
    public static Customer fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) return null; // Invalid format
        return new Customer(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }

    /**
     * Provides a user-friendly display format for the customer.
     * Implements the abstract method from the Person class.
     * 
     * @return Formatted string for display purposes
     */
    @Override
    public String display() {
        return "ID: " + id + " | Name: " + name + " | Contact: " + contact;
    }
}
