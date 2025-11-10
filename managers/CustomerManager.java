package managers;

import entities.Customer;
import java.util.*;

public class CustomerManager extends EntityManager<Customer> {

    public CustomerManager(String filename) {
        super(filename);
    }

    @Override
    protected Customer parse(String line) { return Customer.fromString(line); }

    @Override
    protected String serialize(Customer entity) { return entity.toString(); }

    @Override
    protected int getId(Customer entity) { return entity.getId(); }

    @Override
    protected void updateEntity(Customer c, Scanner sc) {
        System.out.print("Enter new name: ");
        c.setName(sc.nextLine());
        System.out.print("Enter new contact: ");
        c.setContact(sc.nextLine());
    }

    public void addCustomer(Scanner sc) {
        System.out.print("Enter name: ");
        String name = sc.nextLine();
        System.out.print("Enter contact: ");
        String contact = sc.nextLine();
        add(new Customer(nextId(), name, contact));
        System.out.println("Customer added!");
    }
}
