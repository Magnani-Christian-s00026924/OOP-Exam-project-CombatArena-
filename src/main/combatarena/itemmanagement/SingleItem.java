package main.combatarena.itemmanagement;

public class SingleItem implements Item {
    protected String name;
    protected double price;

    public SingleItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    @Override public String getName() { 
        return name; 
    }
    @Override public double getPrice() { 
        return price; 
    }
}