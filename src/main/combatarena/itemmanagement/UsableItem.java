package main.combatarena.itemmanagement;

import main.combatarena.playablecharacter.PlayableCharacter;

public class UsableItem extends SingleItem {
    public UsableItem(String name, double price) {
        super(name, price);
    }

    public void use(PlayableCharacter character) {
        System.out.println("\n[AZIONE] " + character.getName() + " sta usando: " + getName().toUpperCase() + "...");
    }
}
