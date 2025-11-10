package managers;

import entities.Customer;
import java.util.*;

/**
 * CustomerManager class - Specialized manager for Customer entities.
 * 
 * This class extends EntityManager to provide customer-specific implementations
 * of the abstract methods defined in the parent class. It handles the business
 * logic specific to customer management including user interaction for adding
 * and updating customers.
 * 
 * @author Comic Book Store System
 * @version 1.0
 */
public class CustomerManager extends EntityManager<Customer> {

    /**
     * Constructor for creating a CustomerManager instance.
     * 
     * @param filename The path to the customers data file
     */
    public CustomerManager(String filename) {
        super(filename); // Call parent constructor to initialize with data file
    }

    /**
     * Parses a string line into a Customer object.
     * Uses the Customer class's static factory method for deserialization.
     * 
     * @param line The comma-separated string representation of a customer
     * @return Customer object if parsing is successful, null otherwise
     */
    @Override
    protected Customer parse(String line) { return Customer.fromString(line); }

    /**
     * Serializes a Customer object to a string for file storage.
     * Uses the Customer class's toString method for serialization.
     * 
     * @param entity The Customer entity to serialize
     * @return String representation of the customer
     */
    @Override
    protected String serialize(Customer entity) { return entity.toString(); }

    /**
     * Gets the ID from a Customer entity.
     * 
     * @param entity The Customer entity to get the ID from
     * @return The customer's ID
     */
    @Override
    protected int getId(Customer entity) { return entity.getId(); }

    /**
     * Updates the fields of a Customer entity based on user input.
     * Prompts the user for new values for name and contact information.
     * 
     * @param c The Customer entity to update
     * @param sc Scanner for reading user input
     */
    @Override
    protected void updateEntity(Customer c, Scanner sc) {
        System.out.print("Enter new name: ");
        c.setName(sc.nextLine());
        System.out.print("Enter new contact: ");
        c.setContact(sc.nextLine());
    }

    /**
     * Interactive method to add a new customer to the collection.
     * Prompts the user for customer details and creates a new Customer instance.
     * Automatically generates the next available ID and persists the changes.
     * 
     * @param sc Scanner for reading user input
     */
    public void addCustomer(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter contact: ");
        String contact = sc.nextLine();
        
        // Create new customer with auto-generated ID and add to collection
        add(new Customer(nextId(), name, contact));
        System.out.println("Customer added!");
    }
}
