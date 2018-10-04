package no.hvl.dat108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    
    private List<CartItem> items = Collections.synchronizedList(new ArrayList<>());
    
    public void addItem(CartItem item) {
        items.add(item);
    }

    public void removeItem(CartItem item) {
        items.remove(item);
    }
    
    public List<CartItem> getItems() {
        return items;
    }
}
