package main.combatarena.nonplayablecharacter.easy;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class Slime extends PlayableCharacter{

    public Slime(String name) {
        super(name, 40, 5, 2, 2, 2.0);
        this.attackEquipment = new Weapon("Nucleo Appiccicoso", 5.0,2);
        this.defenseEquipment = new Armor("Pelle Gelatinosa", 2.0, 1);
        this.getInventory().add(new HealthPotion("Resina di Slime", 5.0, 5));
    }
    
}
