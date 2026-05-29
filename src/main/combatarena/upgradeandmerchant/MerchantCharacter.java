package main.combatarena.upgradeandmerchant;

import java.util.List;
import main.combatarena.equipment.EquipmentGenerator;
import main.combatarena.itemmanagement.Item;
import main.combatarena.itemmanagement.usableitem.AttackScroll;
import main.combatarena.itemmanagement.usableitem.DefenseScroll;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.menuinterface.ConsoleCleaner;
import main.combatarena.menuinterface.UserInputInterface;
import main.combatarena.playablecharacter.PlayableCharacter;

public class MerchantCharacter {
    private PlayableCharacter character;
    private UserInputInterface input = new UserInputInterface();
    private EquipmentGenerator equipmentGenerator = new EquipmentGenerator();

    public MerchantCharacter(PlayableCharacter character) {
        this.character = character;
    }

    public void displayMerchantMenu() {
        while (true) {
            ConsoleCleaner.clearConsole();
            ConsoleCleaner.printHeader("Mercante di Strada");
            System.out.println(" Monete disponibili: " + character.getCoin() + " $");
            System.out.println("----------------------------------------");
            System.out.println(" [1] Acquista oggetti");
            System.out.println(" [2] Vendi oggetti");
            System.out.println(" [3] Torna indietro");
            System.out.println("----------------------------------------");
            System.out.print("Scegli un'opzione > ");
            
            int choice = input.getUserChoice();
            switch (choice) {
                case 1 -> buyItems();
                case 2 -> sellItems();
                case 3 -> { return; }
                default -> System.out.println("\n[!] Opzione non valida.");
            }
        }
    }

    private void buyItems() {
        while (true) {
            ConsoleCleaner.clearConsole();
            ConsoleCleaner.printHeader("Emporio - Acquista");
            System.out.println(" Monete: " + character.getCoin() + " $");
            System.out.println("----------------------------------------");
            System.out.printf(" [1] %-20s | %6s%n", "Pozione di Salute", "10.0 $");
            System.out.printf(" [2] %-20s | %6s%n", "Pergamena Attacco", "15.0 $");
            System.out.printf(" [3] %-20s | %6s%n", "Pergamena Difesa", "15.0 $");
            System.out.printf(" [4] %-20s | %6s%n", "Arma Casuale", "20.0 $");
            System.out.printf(" [5] %-20s | %6s%n", "Armatura Casuale", "20.0 $");
            System.out.println(" [6] Torna indietro");
            System.out.println("----------------------------------------");
            System.out.print("Cosa vuoi comprare? > ");

            int choice = input.getUserChoice();
            switch (choice) {
                case 1 -> buyItem(new HealthPotion("Pozione di Salute", 10.0, 10), 10.0);
                case 2 -> buyItem(new AttackScroll("Pergamena d'Attacco", 15.0, 15), 15.0);
                case 3 -> buyItem(new DefenseScroll("Pergamena di Difesa", 15.0, 15), 15.0);
                case 4 -> buyItem(equipmentGenerator.getRandomWeapon(), 20.0);
                case 5 -> buyItem(equipmentGenerator.getRandomArmor(), 20.0);
                case 6 -> { return; }
                default -> System.out.println("\n[!] Oggetto non disponibile.");
            }
            pause();
        }
    }

    private void sellItems() {
        while (true) {
            ConsoleCleaner.clearConsole();
            List<Item> inventory = character.getInventory().getItems();
            ConsoleCleaner.printHeader("Emporio - Vendi");
            System.out.println(" Guadagno: 50% del valore originale");
            System.out.println("----------------------------------------");

            if (inventory.isEmpty()) {
                System.out.println(" [!] Il tuo inventario è vuoto.");
                pause();
                return;
            }

            for (int i = 0; i < inventory.size(); i++) {
                double sellPrice = inventory.get(i).getPrice() / 2;
                System.out.printf(" [%d] %-20s | Ricavi: %.1f $%n", (i + 1), inventory.get(i).getName(), sellPrice);
            }
            System.out.println(" [" + (inventory.size() + 1) + "] Torna indietro");
            System.out.println("----------------------------------------");
            System.out.print("Cosa vuoi vendere? > ");

            int choice = input.getUserChoice();
            if (choice > 0 && choice <= inventory.size()) {
                Item itemToSell = inventory.get(choice - 1);
                double price = itemToSell.getPrice() / 2;
                character.setCoin(character.getCoin() + price);
                character.getInventory().removeItem(choice - 1);
                System.out.println("\n[+] Venduto " + itemToSell.getName() + " per " + price + " monete.");
            } else if (choice == inventory.size() + 1) {
                return;
            } else {
                System.out.println("\n[!] Scelta non valida.");
            }
            pause();
        }
    }

    private void buyItem(Item item, double price) {
        if (character.getCoin() >= price) {
            character.setCoin(character.getCoin() - price);
            character.getInventory().addItem(item);
            System.out.println("\n[+] Acquistato: " + item.getName());
        } else {
            System.out.println("\n[!] Monete insufficienti!");
        }
    }

    private void pause() {
        try { Thread.sleep(1200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}