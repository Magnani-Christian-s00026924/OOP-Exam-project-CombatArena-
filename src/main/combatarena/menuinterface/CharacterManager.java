package main.combatarena.menuinterface;

import java.util.ArrayList;
import java.util.List;
import main.combatarena.playablecharacter.PlayableCharacter;
import main.combatarena.playablecharacter.PlayableCharacterFactory;
import main.combatarena.upgradeandmerchant.MerchantCharacter;
import main.combatarena.upgradeandmerchant.UpgradeCharacter;

public class CharacterManager {
    private List<PlayableCharacter> characters = new ArrayList<>();
    private UserInputInterface userInputInterface = new UserInputInterface();

    public void createCharacter() {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Creazione Personaggio");

        if (characters.size() >= 5) {
            System.out.println(" [!] ERRORE: Hai raggiunto il limite massimo (5/5).");
            pause(2000);
            return;
        }

        System.out.println(" Classi disponibili:");
        System.out.println(" [1] Guerriero   [4] Cavaliere");
        System.out.println(" [2] Mago        [5] Orco");
        System.out.println(" [3] Ladro       [6] Schiavo");
        System.out.println("----------------------------------------");
        System.out.print(" Seleziona classe > ");
        int classChoice = userInputInterface.getUserChoice();

        System.out.print(" Inserisci il nome dell'eroe > ");
        String name = userInputInterface.getStringInput().trim();

        if (name.isEmpty()) {
            System.out.println("\n [!] ERRORE: Il nome non può essere vuoto.");
            pause(1500);
            return;
        }

        PlayableCharacter character = PlayableCharacterFactory.createCharacter(classChoice, name);
        if (character != null) {
            characters.add(character);
            System.out.println("\n [+] Personaggio '" + name + "' creato con successo!");
        } else {
            System.out.println("\n [!] ERRORE: Selezione classe non valida.");
        }
        pause(1500);
    }

    public void manageCharacters() {
        while (true) {
            ConsoleCleaner.clearConsole();
            ConsoleCleaner.printHeader("Gestione Armata");
            System.out.println(" [1] Visualizza Personaggi");
            System.out.println(" [2] Modifica Personaggio");
            System.out.println(" [3] Rimuovi Personaggio");
            System.out.println(" [4] Torna al Menu Principale");
            System.out.println("----------------------------------------");
            System.out.print(" Scegli un'opzione > ");

            int choice = userInputInterface.getUserChoice();

            switch (choice) {
                case 1 -> viewCharacters();
                case 2 -> modifyCharacter();
                case 3 -> removeCharacter();
                case 4 -> { return; }
                default -> {
                    System.out.println("\n [!] Opzione non valida.");
                    pause(1000);
                }
            }
        }
    }

    private void viewCharacters() {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("I Tuoi Eroi");
        if (characters.isEmpty()) {
            System.out.println(" [!] L'armata è vuota. Crea un personaggio!");
        } else {
            for (int i = 0; i < characters.size(); i++) {
                System.out.println(" EROE #" + (i + 1) + characters.get(i).getInfo());
            }
        }
        System.out.println("\n Premi INVIO per tornare indietro...");
        userInputInterface.getStringInput();
    }

    private void modifyCharacter() {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Modifica Eroe");
        if (characters.isEmpty()) {
            System.out.println(" [!] Nessun personaggio da modificare.");
            pause(1500);
            return;
        }

        displayCharacterListShort();
        System.out.print(" Quale eroe vuoi gestire? > ");
        int index = userInputInterface.getUserChoice() - 1;

        if (index >= 0 && index < characters.size()) {
            PlayableCharacter selected = characters.get(index);
            showEditMenu(selected);
        } else {
            System.out.println("\n [!] Indice non valido.");
            pause(1000);
        }
    }

    private void showEditMenu(PlayableCharacter character) {
        while (true) {
            ConsoleCleaner.clearConsole();
            ConsoleCleaner.printHeader("Editor: " + character.getName());
            System.out.println(" [1] Cambia Nome");
            System.out.println(" [2] Gestisci Inventario");
            System.out.println(" [3] Potenzia Statistiche");
            System.out.println(" [4] Mercante di Strada");
            System.out.println(" [5] Torna Indietro");
            System.out.println("----------------------------------------");
            System.out.print(" Azione > ");

            int choice = userInputInterface.getUserChoice();
            switch (choice) {
                case 1 -> {
                    System.out.print(" Nuovo nome > ");
                    String newName = userInputInterface.getStringInput();
                    character.setName(newName);
                    System.out.println("\n [+] Nome aggiornato in: " + newName);
                    pause(1200);
                }
                case 2 -> handleItemUsage(character);
                case 3 -> new UpgradeCharacter(character).displayUpgradeMenu();
                case 4 -> new MerchantCharacter(character).displayMerchantMenu();
                case 5 -> { return; }
                default -> System.out.println("\n [!] Scelta errata.");
            }
        }
    }

    private void removeCharacter() {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Elimina Personaggio");
        if (characters.isEmpty()) {
            System.out.println(" [!] Non ci sono personaggi da rimuovere.");
            pause(1500);
            return;
        }

        displayCharacterListShort();
        System.out.print(" Numero del personaggio da congedare > ");
        int index = userInputInterface.getUserChoice() - 1;

        if (index >= 0 && index < characters.size()) {
            String removedName = characters.get(index).getName();
            characters.remove(index);
            System.out.println("\n [-] " + removedName + " ha lasciato l'armata.");
        } else {
            System.out.println("\n [!] Selezione non valida.");
        }
        pause(1500);
    }

    private void handleItemUsage(PlayableCharacter character) {
        ConsoleCleaner.clearConsole();
        ConsoleCleaner.printHeader("Zaino di " + character.getName());
        character.getInventory().showItems();
        
        System.out.print(" Inserisci numero oggetto (0 per uscire) > ");
        int itemIndex = userInputInterface.getUserChoice() - 1;

        if (itemIndex == -1) return;

        character.useItemFromInventory(itemIndex);
        pause(2000);
    }

    private void displayCharacterListShort() {
        for (int i = 0; i < characters.size(); i++) {
            System.out.println(" [" + (i + 1) + "] " + characters.get(i).getName() + " (" + characters.get(i).getClass().getSimpleName() + ")");
        }
        System.out.println("----------------------------------------");
    }

    private void pause(int ms) {
        try { Thread.sleep(ms); } catch (InterruptedException e) { Thread.currentThread().interrupt(); }
    }

    public List<PlayableCharacter> getCharacters() {
        return characters;
    }
}