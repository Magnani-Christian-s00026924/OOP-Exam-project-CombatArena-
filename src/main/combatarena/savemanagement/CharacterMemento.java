package main.combatarena.savemanagement;

// Memento Pattern.
public class CharacterMemento {
    private final String name;
    private final String characterClass;
    private final int health;
    private final int attack;
    private final int defense;
    private final int speed;
    private final double coin;

    public CharacterMemento(String name, String characterClass, int health, int attack, int defense, int speed, double coin) {
        this.name = name;
        this.characterClass = characterClass;
        this.health = health;
        this.attack = attack;
        this.defense = defense;
        this.speed = speed;
        this.coin = coin;
    }

    // Solo getter, nessun setter per mantenere l'immutabilità del Memento
    public String getName() { return name; }
    public String getCharacterClass() { return characterClass; }
    public int getHealth() { return health; }
    public int getAttack() { return attack; }
    public int getDefense() { return defense; }
    public int getSpeed() { return speed; }
    public double getCoin() { return coin; }
}