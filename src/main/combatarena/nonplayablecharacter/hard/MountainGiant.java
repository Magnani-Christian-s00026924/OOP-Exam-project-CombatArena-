package main.combatarena.nonplayablecharacter.hard;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.AttackScroll;
import main.combatarena.itemmanagement.usableitem.DefenseScroll;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class MountainGiant extends PlayableCharacter{

    public MountainGiant(String name) {
        super("Gigante di montagna", 400, 50, 20, 5, 100.0);
        this.attackEquipment = new Weapon("Schiantapietre Gigante", 550, 45);
        this.defenseEquipment = new Armor("Pelle di Drago Antico", 450, 30);
        this.getInventory().add(new HealthPotion("Nettare dei Giganti", 50.0, 150));
        this.getInventory().add(new DefenseScroll("Volontà della Montagna", 100.0, 25));
        this.getInventory().add(new AttackScroll("Furia Terrestre", 120.0, 30));
    }
    
}
