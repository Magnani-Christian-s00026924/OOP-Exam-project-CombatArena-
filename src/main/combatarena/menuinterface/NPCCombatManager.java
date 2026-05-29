package main.combatarena.menuinterface;

import java.util.List;
import main.combatarena.combatsystem.CombatSystem;
import main.combatarena.nonplayablecharacter.NPCFactory;
import main.combatarena.nonplayablecharacter.NPCFactory.Difficulty;
import main.combatarena.playablecharacter.PlayableCharacter;

public class NPCCombatManager {

    private List<PlayableCharacter> characters;
    private UserInputInterface userInputInterface = new UserInputInterface();

    public NPCCombatManager(List<PlayableCharacter> characters) {
        this.characters = characters;
    }

    public void manage() {
        if (characters.isEmpty()) {
            System.out.println("Devi avere almeno un personaggio per combattere!");
            pause(1500);
            return;
        }

        System.out.println("\nSeleziona il tuo combattente:");
        displayCharacters();
        System.out.print("Scegli il numero del tuo personaggio: ");
        int index = userInputInterface.getUserChoice() - 1;

        if (index < 0 || index >= characters.size()) {
            System.out.println("Numero di personaggio non valido.");
            pause(1500);
            return;
        }

        PlayableCharacter player = characters.get(index);

        Difficulty difficulty = chooseDifficulty();
        if (difficulty == null) return;

        PlayableCharacter enemy = chooseEnemy(difficulty);
        if (enemy == null) return;

        System.out.println("\n[" + difficulty.name() + "] " + player.getName() + " vs " + enemy.getName() + "!");

        CombatSystem combatSystem = new CombatSystem(player, enemy);
        PlayableCharacter loser = combatSystem.startCombat();
        ConsoleCleaner.clearConsole();

        if (loser == player) {
            characters.remove(player);
            System.out.println("Hai perso! " + player.getName() + " è stato rimosso dalla tua armata.");
        } else {
            System.out.println("Hai sconfitto " + enemy.getName() + "! Ottimo combattimento!");
        }

        pause(2000);
    }

    private Difficulty chooseDifficulty() {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Selezione Difficoltà");
        System.out.println(" [1] FACILE    (Bat, Skeleton, Slime)");
        System.out.println(" [2] MEDIO     (Alpha Wolf, Deserter...)");
        System.out.println(" [3] DIFFICILE (Giant, Assassin...)");
        System.out.println(" [4] Annulla");
        System.out.println("----------------------------------------");
        System.out.print("Scelta > ");

        return switch (userInputInterface.getUserChoice()) {
            case 1 -> Difficulty.EASY;
            case 2 -> Difficulty.MEDIUM;
            case 3 -> Difficulty.HARD;
            default -> null;
        };
    }

    private PlayableCharacter chooseEnemy(Difficulty difficulty) {
        List<PlayableCharacter> enemies = NPCFactory.getEnemiesForDifficulty(difficulty);

        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Scegli il tuo Avversario");
        for (int i = 0; i < enemies.size(); i++) {
            PlayableCharacter e = enemies.get(i);
            System.out.printf("  %d. %-20s HP: %-5d ATK: %-4d DEF: %-4d SPD: %d%n",
                    i + 1, e.getName(), e.getHealth(), e.getAttack(), e.getDefense(), e.getSpeed());
        }
        System.out.println("  " + (enemies.size() + 1) + ". Nemico casuale");
        System.out.println("  " + (enemies.size() + 2) + ". Annulla");
        System.out.print("Scegli un'opzione: ");

        int choice = userInputInterface.getUserChoice() - 1;

        if (choice >= 0 && choice < enemies.size()) {
            return enemies.get(choice);
        } else if (choice == enemies.size()) {
            PlayableCharacter random = NPCFactory.getRandomEnemy(difficulty);
            System.out.println("Nemico casuale selezionato: " + random.getName());
            return random;
        } else {
            return null;
        }
    }

    private void displayCharacters() {
        for (int i = 0; i < characters.size(); i++) {
            System.out.println((i + 1) + ". " + characters.get(i).getInfo());
        }
    }

    private void pause(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}