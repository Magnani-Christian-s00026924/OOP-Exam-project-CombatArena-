package main.combatarena.nonplayablecharacter.medium;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class StoneGolem extends PlayableCharacter{
    
    public StoneGolem(String name) {
        super(name, 150, 12, 25, 3, 20.0);
        this.attackEquipment = new Weapon("Pugno di Granito", 50.0,10);
        this.defenseEquipment = new Armor("Corazza Rocciosa", 60.0,20);
        this.getInventory().add(new HealthPotion("Essenza di Pietra", 15.0, 30));
    }

}
