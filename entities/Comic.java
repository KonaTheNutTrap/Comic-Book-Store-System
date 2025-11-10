package entities;

public class Comic {
    private int id;
    private String title;
    private String author;
    private double price;

    public Comic(int id, String title, String author, double price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public double getPrice() { return price; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return id + "," + title + "," + author + "," + price;
    }

    public static Comic fromString(String line) {
        String[] parts = line.split(",");
        if (parts.length != 4) return null;
        return new Comic(Integer.parseInt(parts[0]), parts[1], parts[2], Double.parseDouble(parts[3]));
    }

    public String display() {
        return "ID: " + id + " | Title: " + title + " | Author: " + author + " | Price: $" + price;
    }
}
