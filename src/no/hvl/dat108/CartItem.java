package no.hvl.dat108;

public class CartItem {
    
    private String name;

    
    public CartItem(String name) {
        this.name = name;

    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
