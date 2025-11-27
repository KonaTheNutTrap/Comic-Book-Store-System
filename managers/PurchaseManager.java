package managers;
import entities.Comic;
import entities.Order;

import java.util.*;




public class PurchaseManager extends EntityManager<Order> {
    ArrayList<Order> cart = new ArrayList<>();
    ComicManager comicManager;

    //Abstract methods that must be implemented because of inheriting from a abstract class
    @Override
    protected Order parse(String line) { return null; }

    @Override
    protected String serialize(Order entity) { return entity.toString(); }

    @Override
    protected int getId(Order entity) { return entity.getComic().getId(); }

    @Override
    protected void updateEntity(Order entity, Scanner sc) { }

    @Override
    public void add(Order entity) {
        entities.add(entity);
        save(); // Persist changes to file
    }
    

    public PurchaseManager(String filename, ComicManager comicManager) {
         
        super(filename);
        this.comicManager = comicManager; 
    }



    public void addOrder(String comicInput, int quantity) {
        Comic comic;

        comic = comicManager.findByIdOrName(comicInput);

        if (comic == null) {
            System.out.println("Comic not found!");
            return;
        }

        try {
            Order newOrder = new Order(comic, quantity);
            cart.add(newOrder);
            System.out.println("Added to cart!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }





    public void viewOrders() {
        System.out.println("==Your Shopping Cart==");
        if (cart.isEmpty()) {
            System.out.println("Cart is currently empty");
            return;
        }

        for (Order order : cart) {
            System.out.println(order.toString());
            System.out.println("================================");
        }


    }

    public void removeOrder(String c) {

        if (cart.isEmpty()) {
            System.out.println("There's nothing to remove because the cart is empty.");
            return;
        } else {

        // Try to parse as ID, else remove by title
        boolean removed = false;
        try {
            int id = Integer.parseInt(c.trim());
            removed = cart.removeIf(o -> o.getComic().getId() == id);
        } catch (NumberFormatException e) {
            removed = cart.removeIf(o -> o.getComic().getTitle().equalsIgnoreCase(c.trim()));
        }

        if (removed) {
            System.out.println("Item removed from cart!");
        } else {
            System.out.println("Item not found in cart!");
        }

        }

        }


    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Nothing to checkout.");
            return;
        }

        for (Order order : cart) {
            System.out.println("===Receipt===");
            System.out.println(order.toString());
            add(order);
        }
    }
}
