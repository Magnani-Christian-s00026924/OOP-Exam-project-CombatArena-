package main.combatarena.itemmanagement.usableitem;

import main.combatarena.itemmanagement.UsableItem;
import main.combatarena.playablecharacter.PlayableCharacter;

public class HealthPotion extends UsableItem {
    private int healingAmount;

    public HealthPotion(String name, double price, int healingAmount) {
        super(name, price);
        this.healingAmount = healingAmount;
    }

    @Override
    public void use(PlayableCharacter character) {
        int oldHealth = character.getHealth();
        int newHealth = oldHealth + healingAmount;
        character.setHealth(newHealth);
        System.out.println("\n[+] RECOVERY: " + getName());
        System.out.println("    [HP] " + oldHealth + " -> " + newHealth + " (+" + healingAmount + ")");
}
}
