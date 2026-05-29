package main.combatarena.nonplayablecharacter.hard;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.usableitem.AttackScroll;
import main.combatarena.itemmanagement.usableitem.HealthPotion;
import main.combatarena.playablecharacter.PlayableCharacter;

public class ShadowAssasin extends PlayableCharacter{

    public ShadowAssasin(String name) {
        super("Shadow Assassin", 180, 45, 15, 40, 80.0);
        this.attackEquipment = new Weapon("Daghe del Vuoto", 350.0, 35);
        this.defenseEquipment = new Armor("Veste delle Ombre", 180.0, 12);
        this.getInventory().add(new AttackScroll("Colpo Critico", 90.0,  30));
        this.getInventory().add(new HealthPotion("Veleno Curativo", 15.0, 40));
    }
}

