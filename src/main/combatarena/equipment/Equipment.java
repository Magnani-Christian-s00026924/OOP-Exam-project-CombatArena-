package main.combatarena.equipment;

import main.combatarena.itemmanagement.SingleItem;

public abstract class Equipment extends SingleItem {
    protected int bonus;

    public Equipment(String name, double price, int bonus) {
        super(name, price);
        this.bonus = bonus;
    }

    public int getBonus() { return bonus; }
}
