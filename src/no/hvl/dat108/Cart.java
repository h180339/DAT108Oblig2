package no.hvl.dat108;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    
    private List<CartItem> items = new ArrayList<>();
    
    public void addItem(CartItem item) {
        synchronized (this) {
            items.add(item);
        }
    }

    public void removeItem(CartItem item) {
        synchronized (this) {
            items.remove(item);
        }
    }
    
    public List<CartItem> getItems() {
        return items;
    }
}
