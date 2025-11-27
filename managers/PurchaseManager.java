package managers;
import entities.Comic;
import entities.Order;
import entities.Stock;

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



    public void addOrder(String comicTitle, int quantity) {
        Comic comic;
        comic = comicManager.findByName(comicTitle);
        
      /*   Stock stock;


        if (stock.getQuantity() < quantity) {
        comic = comicManager.findByName(comicTitle);
        stock = */


        
        if (comic == null) {
            System.out.println("Comic not found!");
            return;
        } else { 
            Order newOrder = new Order(comic, quantity);
            cart.add(newOrder);
            System.out.println("Added to cart!");
        }
    } /*else {
        System.out.println("I am sorry but we do not have the sufficient stock for your purchase.");
    }*/
    






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

        cart.removeIf(o-> o.getComic().getTitle().equalsIgnoreCase(c));

        }

        }

    


    public void checkout() {
        double total = 0.0;

        for (Order order : cart) {
            System.out.println("===Receipt===");
            System.out.println(order.toString());
            total += order.getComic().getPrice() * order.getQuantity();
            add(order);
            
             
        }      
        System.out.println("Total comes out to:  " + total);
        cart.clear();

    }



}
