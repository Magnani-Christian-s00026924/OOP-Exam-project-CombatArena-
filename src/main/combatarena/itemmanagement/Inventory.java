package main.combatarena.itemmanagement;

import java.util.ArrayList;
import java.util.List;

public class Inventory implements Item {
    private String name;    
    private List<Item> content = new ArrayList<>();

        public void showItems() {
        System.out.println("\n----------------------------------------");
        System.out.println("       ZAINO - OGGETTI POSSEDUTI");
        System.out.println("----------------------------------------");
    
    if (content.isEmpty()) {
        System.out.println("  [!] L'inventario è vuoto.");
    } else {
        System.out.printf("    %-20s | %-10s%n", "NOME OGGETTO", "VALORE");
        System.out.println("----------------------------------------");
        for (int i = 0; i < content.size(); i++) {
            Item item = content.get(i);
            System.out.printf("[%d] %-20s | %.1f $%n", (i + 1), item.getName(), item.getPrice());
        }
    }
        System.out.println("----------------------------------------");
    }   

    public void add(Item item) {
        this.content.add(item);
    }

    public void remove(Item item) {
        content.remove(item);
    }


    @Override
    public double getPrice() {
        return content.stream()
                      .mapToDouble(Item::getPrice)
                      .sum();
    }

    @Override
    public String getName() {
        return name;
    }

    public void removeItem(int index) {
        if (index >= 0 && index < content.size()) {
            content.remove(index);
        } else {
            System.out.println("Indice non valido. Impossibile rimuovere l'oggetto.");
        }
    }

    public UsableItem getItem(int index) {
        if (index >= 0 && index < content.size()) {
            Item item = content.get(index);
            if (item instanceof UsableItem) {
                return (UsableItem) item;
            } else {
                System.out.println("L'oggetto all'indice " + index + " non è un UsableItem.");
                return null;
            }
        } else {
            System.out.println("Indice non valido. Impossibile ottenere l'oggetto.");
            return null;
        }
    }

    public List<Item> getItems() {
    return content;
    }

    public void addItem(Item item) {
    this.content.add(item);
    }

    public int size() {
        return content.size();
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }
} 
