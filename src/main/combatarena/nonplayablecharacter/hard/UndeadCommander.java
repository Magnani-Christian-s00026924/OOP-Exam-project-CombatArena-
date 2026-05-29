package main.combatarena.nonplayablecharacter.hard;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.DefenseScroll;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class UndeadCommander extends PlayableCharacter{

    public UndeadCommander(String name) {
        super(name, 250, 35, 30, 12, 50.0);
        this.attackEquipment = new Weapon("Lama del Terrore", 250.0, 25);
        this.defenseEquipment = new Armor("Piastre Spettrali", 200.0, 25);
        this.getInventory().add(new HealthPotion("Elisir di Non-Morte", 25.0, 60));
        this.getInventory().add(new DefenseScroll("Aura Oscura", 75.0, 20));
    }
    
}
