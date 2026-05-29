package main.combatarena.upgradeandmerchant;

import main.combatarena.menuinterface.ConsoleCleaner;
import main.combatarena.menuinterface.UserInputInterface;
import main.combatarena.playablecharacter.PlayableCharacter;

public class UpgradeCharacter {
    private PlayableCharacter character;
    private UserInputInterface input = new UserInputInterface();

    public UpgradeCharacter(PlayableCharacter character) {
        this.character = character;
    }

    public void displayUpgradeMenu() {
        while (true) {
            ConsoleCleaner.clearConsole();
            ConsoleCleaner.printHeader("Potenziamento Personaggio");
            
            // Visualizzazione rapida statistiche
            System.out.println(" Eroe: " + character.getName());
            System.out.println(" Statistiche: [ATK: " + character.getAttack() + "] [DEF: " + character.getDefense() + "] [HP: " + character.getHealth() + "]");
            System.out.println(" Monete disponibili: " + character.getCoin() + " $");
            System.out.println("----------------------------------------");
            System.out.println(" [1] Potenzia Attacco (+1)  | Costo: 20 $");
            System.out.println(" [2] Potenzia Difesa  (+1)  | Costo: 20 $");
            System.out.println(" [3] Aggiungi Salute (+20)  | Costo: 10 $");
            System.out.println(" [4] Torna indietro");
            System.out.println("----------------------------------------");
            System.out.print("Scegli l'upgrade > ");
            
            int choice = input.getUserChoice();
            switch (choice) {
                case 1 -> upgradeAttack();
                case 2 -> upgradeDefense();
                case 3 -> addHealth();
                case 4 -> { return; }
                default -> System.out.println("\n[!] Scelta non valida.");
            }
            pause();
        }
    }

    private void upgradeAttack() {
        if (character.getCoin() >= 20) {
            character.setAttack(character.getAttack() + 1);
            character.setCoin(character.getCoin() - 20);
            System.out.println("\n[+] Attacco aumentato! (Ora: " + character.getAttack() + ")");
        } else {
            System.out.println("\n[!] Monete insufficienti per l'attacco.");
        }
    }

    private void upgradeDefense() {
        if (character.getCoin() >= 20) {
            character.setDefense(character.getDefense() + 1);
            character.setCoin(character.getCoin() - 20);
            System.out.println("\n[+] Difesa aumentata! (Ora: " + character.getDefense() + ")");
        } else {
            System.out.println("\n[!] Monete insufficienti per la difesa.");
        }
    }

    private void addHealth() {
        if (character.getCoin() >= 10) {
            character.setHealth(character.getHealth() + 20);
            character.setCoin(character.getCoin() - 10);
            System.out.println("\n[+] Salute aumentata! (Ora: " + character.getHealth() + ")");
        } else {
            System.out.println("\n[!] Monete insufficienti per la salute.");
        }
    }
    private void pause() {
        try { Thread.sleep(1200); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }
}