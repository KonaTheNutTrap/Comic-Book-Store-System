package entities;

public class Customer extends Person {
    private String contact;

    public Customer(int id, String name, String contact) {
        super(id, name);
        this.contact = contact;
    }

    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }

    @Override
    public String toString() {
        return id + "," + name + "," + contact;
    }

    public static Customer fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 3) return null;
        return new Customer(Integer.parseInt(parts[0]), parts[1], parts[2]);
    }

    @Override
    public String display() {
        return "ID: " + id + " | Name: " + name + " | Contact: " + contact;
    }
}
