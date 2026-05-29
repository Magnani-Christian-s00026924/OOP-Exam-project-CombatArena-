package main.combatarena.combatsystem;

import java.util.ArrayList;
import java.util.List;
import main.combatarena.itemmanagement.Item;
import main.combatarena.menuinterface.ConsoleCleaner;
import main.combatarena.menuinterface.LogManager;
import main.combatarena.playablecharacter.PlayableCharacter;

public class CombatSystem {
    private PlayableCharacter player1;
    private PlayableCharacter player2;

    public CombatSystem(PlayableCharacter player1, PlayableCharacter player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public PlayableCharacter startCombat() {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("INIZIO COMBATTIMENTO");
        System.out.println(" Sfidante 1: " + player1.getName() + " (SPD: " + player1.getSpeed() + ")");
        System.out.println(" Sfidante 2: " + player2.getName() + " (SPD: " + player2.getSpeed() + ")");
        System.out.println("----------------------------------------");
        
        int roundNumber = 1;
        boolean isPlayer1Turn = player1.getSpeed() > player2.getSpeed();
        try{
            while (player1.getHealth() > 0 && player2.getHealth() > 0) {
                try {
                    Thread.sleep(1200); // Tempo per leggere chi ha colpito chi
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    }
                System.out.println("\n>>> ROUND " + roundNumber + " <<<");
            
                if (isPlayer1Turn) {
                    combatTurn(player1, player2);
                } else {
                    combatTurn(player2, player1);
                }
            
                isPlayer1Turn = !isPlayer1Turn;
                roundNumber++;
            
                try {
                    Thread.sleep(1200); 
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            LogManager.logger("Errore durante il combattimento tra " + player1.getName() + " e " + player2.getName(), e);
            System.out.println("\n [!] ALTOPARLANTE DELL'ARENA: Un turbine magico ha interrotto bruscamente l'incontro.");
            System.out.println("     L'incontro è terminato in parità per motivi di sicurezza planetaria.");
            return null;
        }
    

        // Gestione fine combattimento
        PlayableCharacter winner = (player1.getHealth() > 0) ? player1 : player2;
        PlayableCharacter loser = (winner == player1) ? player2 : player1;

        ConsoleCleaner.printHeader("RISULTATO FINALE");
        System.out.println(" VINCITORE: " + winner.getName().toUpperCase());
        System.out.println(" " + loser.getName() + " è caduto in battaglia!");
        System.out.println("----------------------------------------");

        //  Gestione del bottino
        int droppedGold = (int) loser.getCoin();
        winner.setCoin(winner.getCoin() + droppedGold);
        System.out.println(" [GOLD] Recuperate " + droppedGold + " monete.");

        List<Item> loot = new ArrayList<>(loser.getInventory().getItems());
        if (!loot.isEmpty()) {
            System.out.println(" [LOOT] Oggetti recuperati:");
            for (Item item : loot) {
                winner.getInventory().addItem(item);
                System.out.println("   - " + item.getName());
            }
        }
        
        System.out.println("----------------------------------------");
        return loser;
    }

    private void combatTurn(PlayableCharacter attacker, PlayableCharacter defender) {
        int atkTotal = attacker.getAttack() + attacker.attackEquipment.getBonus();
        int defTotal = defender.getDefense() + defender.defenseEquipment.getBonus();
        
        int damage = atkTotal - defTotal;
        if (damage <= 0) {
            damage = 1;
            System.out.println(" [!] L'attacco di " + attacker.getName() + " è debole, ma infligge 1 danno.");
        }

        defender.setHealth(Math.max(0, defender.getHealth() - damage));
        
        System.out.printf(" [%s] attacca! -> Infligge %d danni.%n", attacker.getName(), damage);
        System.out.printf(" [%s] HP rimanenti: %d%n", defender.getName(), defender.getHealth());

        if (defender.getHealth() <= 0) {
            System.out.println("\n [X] COLPO DI GRAZIA! " + defender.getName() + " è stato sconfitto.");
        }
    }
}