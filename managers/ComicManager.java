package managers;

import entities.Comic;
import java.util.*;

public class ComicManager extends EntityManager<Comic> {

    public ComicManager(String filename) {
        super(filename);
    }

    @Override
    protected Comic parse(String line) { return Comic.fromString(line); }

    @Override
    protected String serialize(Comic entity) { return entity.toString(); }

    @Override
    protected int getId(Comic entity) { return entity.getId(); }

    @Override
    protected void updateEntity(Comic c, Scanner sc) {
        System.out.print("Enter new title: ");
        c.setTitle(sc.nextLine());
        System.out.print("Enter new author: ");
        c.setAuthor(sc.nextLine());
        System.out.print("Enter new price: ");
        c.setPrice(sc.nextDouble());
        sc.nextLine();
    }

    public void addComic(Scanner sc) {
        System.out.print("Enter title: ");
        String title = sc.nextLine();
        System.out.print("Enter author: ");
        String author = sc.nextLine();
        System.out.print("Enter price: ");
        double price = sc.nextDouble();
        sc.nextLine();
        add(new Comic(nextId(), title, author, price));
        System.out.println("Comic added!");
    }
}
