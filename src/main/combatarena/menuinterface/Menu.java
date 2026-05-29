package main.combatarena.menuinterface;

import main.combatarena.savemanagement.SaveManager;

public class Menu {
    private UserInputInterface userInputInterface = new UserInputInterface();
    private CharacterManager characterManager = new CharacterManager();
    private CombatManager combatManager;

    public void run() {
        while (true) {
            ConsoleCleaner.clearConsole();
            ConsoleCleaner.printHeader("Combat Arena - Main Menu");
            System.out.println(" [1] Crea personaggio");
            System.out.println(" [2] Gestisci personaggi");
            System.out.println(" [3] Inizia combattimento");
            System.out.println(" [5] Salva Armata"); 
            System.out.println(" [6] Carica Armata");
            System.out.println(" [4] Esci");
            System.out.println("--------------------------------");
            System.out.print("Seleziona un'opzione > ");

            int choice = userInputInterface.getUserChoice();

            switch (choice) {
                case 1:
                    characterManager.createCharacter();
                    break;
                case 2:
                    characterManager.manageCharacters();
                    break;
                case 3:
                    combatManager = new CombatManager(characterManager.getCharacters());
                    combatManager.manageCombat();
                    break;
                case 4:
                    System.out.println("Grazie per aver giocato!");
                    try { Thread.sleep(2000); } catch (InterruptedException e) {}
                    return;
                case 5:
                    SaveManager.saveCharacters(characterManager.getCharacters());
                    break;
                case 6:
                    var loaded = SaveManager.loadCharacters();
                    if (!loaded.isEmpty()) {
                     characterManager.getCharacters().clear();      // Svuota la lista attuale
                    characterManager.getCharacters().addAll(loaded); // Inserisce quelli caricati
                    }
                    try { Thread.sleep(1500); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
                    break;
                default:
                    System.out.println("Opzione non valida.");
                    try { Thread.sleep(1500); } catch (InterruptedException e) {}
            }
        }
    }

    public static void main(String[] args) {
        new Menu().run();
    }
}
