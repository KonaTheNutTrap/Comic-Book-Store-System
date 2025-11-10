package managers;

import java.util.*;
import java.io.*;
import utils.FileHandler;

public abstract class EntityManager<T> {
    protected List<T> entities = new ArrayList<>();
    protected String filename;

    public EntityManager(String filename) {
        this.filename = filename;
        load();
    }

    protected abstract T parse(String line);
    protected abstract String serialize(T entity);
    protected abstract int getId(T entity);
    protected abstract void updateEntity(T entity, Scanner sc);

    public void add(T entity) {
        entities.add(entity);
        save();
    }

    public List<T> getAll() { return entities; }

    public void delete(int id) {
        entities.removeIf(e -> getId(e) == id);
        save();
    }

    public T findById(int id) {
        for (T e : entities)
            if (getId(e) == id)
                return e;
        return null;
    }

    public void update(int id, Scanner sc) {
        T entity = findById(id);
        if (entity != null) {
            updateEntity(entity, sc);
            save();
            System.out.println("Updated successfully!");
        } else {
            System.out.println("Entity not found.");
        }
    }

    public void displayAll(java.util.function.Function<T, String> displayFunc) {
        if (entities.isEmpty()) {
            System.out.println("No records found.");
            return;
        }
        for (T e : entities)
            System.out.println(displayFunc.apply(e));
    }

    public void load() {
        List<String> lines = FileHandler.readFile(filename);
        for (String line : lines) {
            T entity = parse(line);
            if (entity != null) entities.add(entity);
        }
    }

    public void save() {
        List<String> lines = new ArrayList<>();
        for (T entity : entities)
            lines.add(serialize(entity));
        FileHandler.writeFile(filename, lines);
    }

    public int nextId() {
        if (entities.isEmpty()) return 1;
        return getId(entities.get(entities.size() - 1)) + 1;
    }
}
