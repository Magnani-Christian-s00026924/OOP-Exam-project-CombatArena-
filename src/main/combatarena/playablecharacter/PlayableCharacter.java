package main.combatarena.playablecharacter;

import main.combatarena.equipment.Armor;
import main.combatarena.equipment.EquipmentGenerator;
import main.combatarena.equipment.Weapon;
import main.combatarena.itemmanagement.Inventory;
import main.combatarena.itemmanagement.UsableItem;
import main.combatarena.savemanagement.CharacterMemento;

public abstract class PlayableCharacter {
    private String name;
    private int health;
    private int attack;
    private int defense;
    private int speed;
    private double coin;
    private Inventory inventory;
    public Weapon attackEquipment;
    public Armor defenseEquipment;

    public PlayableCharacter(String name, int health, int attack, int defense, int speed, double coin) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.coin = coin;
        this.inventory = new Inventory();  
        
        EquipmentGenerator generator = new EquipmentGenerator();
        this.attackEquipment = generator.getRandomWeapon();
        this.defenseEquipment = generator.getRandomArmor();
    }

    //Restituisce le informazioni del personaggio formattate in modo ordinato. 
    public String getInfo() {
        String separator = "------------------------------------------------------------";
        String header = String.format(" HERO: %-15s | CLASSE: %-10s", name.toUpperCase(), this.getClass().getSimpleName());
        
        // Formattazione a colonne per le statistiche base
        String stats = String.format(" [HP: %-3d] [ATK: %-3d] [DEF: %-3d] [SPD: %-3d] [MONETE: %.1f $]", 
                                      health, attack, defense, speed, coin);
        
        // Formattazione per l'equipaggiamento
        String equip = String.format(" ARMA: %-18s (+%d ATK)\n ARMATURA: %-14s (+%d DEF)", 
                                      attackEquipment.getName(), attackEquipment.getBonus(),
                                      defenseEquipment.getName(), defenseEquipment.getBonus());

        return "\n" + separator + "\n" + header + "\n" + stats + "\n" + equip + "\n" + separator;
    }

    // Metodi getter e setter per le proprietà del personaggio
    public void setDefense(int newDefense) { this.defense = newDefense; }
    public void setAttack(int newAttack) { this.attack = newAttack; }
    public void setName(String newName) { this.name = newName; }
    public String getName() { return name; }
    public int getHealth() { return health; }
    public void setHealth(int health) { this.health = health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public double getCoin() { return coin; }
    public void setCoin(double coin) { this.coin = coin; }
    public Armor getDefenseEquipment() { return defenseEquipment; }
    public void setDefenseEquipment(Armor defenseEquipment) { this.defenseEquipment = defenseEquipment; }
    public Weapon getAttackEquipment() { return attackEquipment; }
    public void setAttackEquipment(Weapon attackEquipment) { this.attackEquipment = attackEquipment; }
    public Inventory getInventory() { return inventory; }

    public void useItemFromInventory(int index) {
        UsableItem item = this.inventory.getItem(index); 
        if (item != null) {
            item.use(this);
            this.inventory.removeItem(index);
        }
    }

    // Crea il memento del personaggio.
    public CharacterMemento saveState(){
        return new CharacterMemento(this.name, this.getClass().getSimpleName(), this.health, this.attack, this.defense, this.speed, this.coin);  
}
    // Ripristina lo stato del personaggio dal memento.
    public void restoreState(CharacterMemento memento){
        this.name = memento.getName();
        this.health = memento.getHealth();
        this.attack = memento.getAttack();
        this.defense = memento.getDefense();
        this.speed = memento.getSpeed();
        this.coin = memento.getCoin();
    }   
}
