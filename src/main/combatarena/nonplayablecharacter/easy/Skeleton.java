package main.combatarena.nonplayablecharacter.easy;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class Skeleton extends PlayableCharacter {

    public Skeleton(String name) {
        super("Skeleton", 50, 10, 5, 5, 5.0);
        this.attackEquipment = new Weapon("Spada Arrugginita", 10, 5);
        this.defenseEquipment = new Armor("Scudo Scheggiato", 8, 3);
        this.getInventory().add(new HealthPotion("Frammento d'Osso", 4.0, 10));
    }
    
}
