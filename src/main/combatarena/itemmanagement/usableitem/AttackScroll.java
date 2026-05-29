package main.combatarena.itemmanagement.usableitem;

import main.combatarena.itemmanagement.UsableItem;
import main.combatarena.playablecharacter.PlayableCharacter;

public class AttackScroll extends UsableItem {
    private int attackIncrement;

    public AttackScroll(String name, double price, int attackIncrement) {
        super(name, price);
        this.attackIncrement = attackIncrement;
    }

    @Override
    public void use(PlayableCharacter character) {
        int oldAtk = character.getAttack();
        character.setAttack(oldAtk + attackIncrement);
    
        System.out.println("\n[+] POWER-UP: " + getName());
        System.out.println("    [ATK] " + oldAtk + " -> " + character.getAttack() + " (+" + attackIncrement + ")");
}
}