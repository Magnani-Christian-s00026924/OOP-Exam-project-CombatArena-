package main.combatarena.nonplayablecharacter.medium;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class AlphaWolf extends PlayableCharacter{

    public AlphaWolf(String name) {
        super(name, 90, 22, 8, 18, 12.0);
        this.attackEquipment = new Weapon("Zanne del Branco", 30.0, 15);
        this.defenseEquipment = new Armor("Pelliccia Folta", 20.0, 5);
        this.getInventory().add(new HealthPotion("Carne Fresca", 8.0, 20));
    }
    
}
