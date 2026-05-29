package main.combatarena.nonplayablecharacter.easy;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class Bat extends PlayableCharacter{

    public Bat(String name) {
        super("Bat", 30, 8, 1, 20, 3.0);
        this.attackEquipment = new Weapon("Zanne Piccole", 2, 1);
        this.defenseEquipment = new Armor("Membrana Leggera", 2, 0);
        this.getInventory().add(new HealthPotion("Sangue di Insetto", 1.0, 5));
    }
    
}
