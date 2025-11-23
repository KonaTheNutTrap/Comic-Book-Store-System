package managers;
import entities.Comic;
import entities.Order;
import java.util.*;



public class PurchaseManager extends EntityManager<Order> {
    ArrayList<Order> cart = new ArrayList<>();
    ComicManager comMan;


    public PurchaseManager(String filename, ComicManager comMan) {
        super(filename);
        this.comMan = comMan;
    }

    public void addOrder(int ComicId, int quantity) {
        Comic comic = comMan.findById(ComicId);
        if (comic == null) {
            System.out.println("Comic not found!");
            return;
        } else { 
            Order newOrder = new Order(comic, quantity);
            cart.add(newOrder);
            System.out.println("Added to cart!");
        }
    }

    public void viewOrders() {
        System.out.println("==Your Shopping Cart==");
        if (cart.isEmpty()) {
            System.out.println("Im sorry, cart is currently empty!");
            return;
        }

        for (Order order : cart) {
            System.out.println(order.toString());
        }
    }

    public void removeOrder() {

    }

    public void checkout() {

    }




    @Override
    protected Order parse(String line) { return null; }

    @Override
    protected String serialize(Order entity) { return entity.toString(); }

    @Override
    protected int getId(Order entity) { return entity.getComic().getId(); }

    @Override
    protected void updateEntity(Order entity, Scanner sc) { }
    

 
}
