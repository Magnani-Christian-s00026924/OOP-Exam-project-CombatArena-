package main.combatarena.nonplayablecharacter.medium;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.AttackScroll;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class Deserter extends PlayableCharacter{

    public Deserter(String name) {
        super(name, 120, 18, 15, 10, 15.0);
        this.attackEquipment = new Weapon("Alabarda Militare", 45.0, 12);
        this.defenseEquipment = new Armor("Cotta di Maglia Usurata", 35.0, 10);
        this.getInventory().add(new HealthPotion("Razione Militare", 10.0, 25));
        this.getInventory().add(new AttackScroll("Tattica di Guerra", 25.0, 15));
    }
    
}
