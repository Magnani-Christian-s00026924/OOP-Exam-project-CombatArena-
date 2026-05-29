package main.combatarena.itemmanagement.usableitem;

import main.combatarena.itemmanagement.UsableItem;
import main.combatarena.playablecharacter.PlayableCharacter;

public class DefenseScroll extends UsableItem {
    private int defenseIncrement;

    public DefenseScroll(String name, double price, int defenseIncrement) {
        super(name, price);
        this.defenseIncrement = defenseIncrement;
    }

    @Override
    public void use(PlayableCharacter character) {
        int oldDef = character.getDefense();
        character.setDefense(oldDef + defenseIncrement);
    
        System.out.println("\n[+] DEFENSE-UP: " + getName());
        System.out.println("    [DEF] " + oldDef + " -> " + character.getDefense() + " (+" + defenseIncrement + ")");
}
}