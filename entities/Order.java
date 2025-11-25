package entities;



public class Order {
    private Comic comic;
    private int quantity;
    private double itemTotal;


    public Order (Comic comic, int quantity) {
        this.comic = comic;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public Comic getComic() {
        return comic;
    }

    public double getItemTotal() {
        return itemTotal;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setComic(Comic comic) {
        this.comic = comic;
    }

    @Override
    public String toString() {
        return "Order{" + "comic=" + comic + ", quantity=" + quantity + ",}";
    }

}