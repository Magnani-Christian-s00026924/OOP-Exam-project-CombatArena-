package main.combatarena.menuinterface;

import java.util.List;
import main.combatarena.combatsystem.CombatSystem;
import main.combatarena.playablecharacter.PlayableCharacter;

public class CombatManager {
    private List<PlayableCharacter> characters;
    private UserInputInterface userInputInterface = new UserInputInterface();

    public CombatManager(List<PlayableCharacter> characters) {
        this.characters = characters;
    }

    public void manageCombat() {

        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Arena di Combattimento");
        System.out.println(" [1] Combattimento tra giocatori (PvP)");
        System.out.println(" [2] Combattimento contro NPC (PvE)");
        System.out.println(" [3] Torna al menu principale");
        System.out.println("----------------------------------------");
        System.out.print("Seleziona modalità > ");
 
        int mode = userInputInterface.getUserChoice();
        if (mode == 2) {
            new NPCCombatManager(characters).manage();
            return;
        }
        if (mode == 3) return;

        if (characters.size() < 2) {
            System.out.println("\n [!] Errore: Servono almeno 2 personaggi per il PvP.");
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
            return;
        }

        ConsoleCleaner.printHeader("Selezione Primo Sfidante");
        displayCharacters();
        System.out.print("Scegli il numero > ");
        int index1 = userInputInterface.getUserChoice() - 1;

        if (index1 < 0 || index1 >= characters.size()) {
            System.out.println("Numero di personaggio non valido.");
            return;
        }

        ConsoleCleaner.printHeader("Selezione Secondo Sfidante");
        displayCharacters();
        System.out.print("Scegli il numero del secondo combattente: ");
        int index2 = userInputInterface.getUserChoice() - 1;

        if (index2 < 0 || index2 >= characters.size() || index2 == index1) {
            System.out.println("Numero di personaggio non valido.");
            return;
        }

        CombatSystem combatSystem = new CombatSystem(characters.get(index1), characters.get(index2));
        PlayableCharacter loser = combatSystem.startCombat();
        ConsoleCleaner.clearConsole(); // Pulisce lo schermo all'inizio di ogni ciclo del combattimento
        if (loser != null) {
        characters.remove(loser); // Rimuove l'oggetto dalla lista condivisa
        System.out.println("Il personaggio è stato rimosso dalla tua armata.");
        
        // Pausa per permettere all'utente di leggere
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    }

    private void displayCharacters() {
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getInfo());
        }
    }
}
