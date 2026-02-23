import java.util.*;

public class MenuCatalog {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();

    public void add(MenuItem i) { menu.put(i.id, i); }
    public MenuItem getItem(String id) { return menu.get(id); }
}