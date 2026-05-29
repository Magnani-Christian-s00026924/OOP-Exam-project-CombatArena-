package main.combatarena.equipment;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EquipmentGenerator {
    private Random random = new Random();
    private List<Weapon> weapons;
    private List<Armor> armors;

    public EquipmentGenerator() {
        this.weapons = new ArrayList<>();
        this.armors = new ArrayList<>();

        
        weapons.add(new Weapon("Staff of Fire", 10.0, 5));
        weapons.add(new Weapon("Dagger of Shadows", 8.0, 7));
        weapons.add(new Weapon("Sword of Light", 12.0, 6));

        
        armors.add(new Armor("Robe of Power", 15.0, 4));
        armors.add(new Armor("Cloak of Stealth", 13.0, 3));
        armors.add(new Armor("Plate Mail", 20.0, 6));
    }

    public Weapon getRandomWeapon() {
        return weapons.get(random.nextInt(weapons.size()));
    }

    public Armor getRandomArmor() {
        return armors.get(random.nextInt(armors.size()));
    }
}
