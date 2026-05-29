package main.combatarena.savemanagement;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import main.combatarena.playablecharacter.PlayableCharacter;
import main.combatarena.playablecharacter.PlayableCharacterFactory;

public class SaveManager {
    private static final String FILE_NAME = "salvataggi.txt";

    //SALVATAGGIO: Prende la lista dei personaggi, ne estrae il Memento e scrive su File.
    public static void saveCharacters(List<PlayableCharacter> characters) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (PlayableCharacter character : characters) {
                CharacterMemento memento = character.saveState();
                
                // Scriviamo i dati su file separati da una virgola.
                writer.write(String.format("%s,%s,%d,%d,%d,%d,%.2f",
                        memento.getName(),
                        memento.getCharacterClass(),
                        memento.getHealth(),
                        memento.getAttack(),
                        memento.getDefense(),
                        memento.getSpeed(),
                        memento.getCoin()));
                writer.newLine();
            }
            System.out.println("\n[IO] Salvataggio completato con successo nel file: " + FILE_NAME);
            try { Thread.sleep(2000); } catch (InterruptedException e) {}
        } catch (IOException e) {
            System.err.println("\n[ERRORE IO] Impossibile salvare i personaggi: " + e.getMessage());
                try { Thread.sleep(2000); } catch (InterruptedException ex) { Thread.currentThread().interrupt(); }
        }
    }

    // CARICAMENTO: Legge il file, ricrea i personaggi usando la Factory e ripristina le loro statistiche usando il Memento.
    public static List<PlayableCharacter> loadCharacters() {
        List<PlayableCharacter> loadedCharacters = new ArrayList<>();
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            System.out.println("\n[!] Nessun file di salvataggio trovato. Inizia una nuova partita.");
            return loadedCharacters;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) continue;
                
                // Splittiamo i dati salvati
                String[] arrayCharacter = line.split(",");
                String name = arrayCharacter[0];
                String className = arrayCharacter[1];
                int health = Integer.parseInt(arrayCharacter[2]);
                int attack = Integer.parseInt(arrayCharacter[3]);
                int defense = Integer.parseInt(arrayCharacter[4]);
                int speed = Integer.parseInt(arrayCharacter[5]);
                double coin = Double.parseDouble(arrayCharacter[6].replace(",", "."));

                // Mappiamo la stringa della classe sul numero intero atteso dalla tua Factory
                int typeId = switch (className.toLowerCase()) {
                    case "warrior" -> 1;
                    case "mage" -> 2;
                    case "rogue" -> 3;
                    case "knight" -> 4;
                    case "orc" -> 5;
                    case "slave" -> 6;
                    default -> -1;
                };

                if (typeId != -1) {
                    PlayableCharacter character = PlayableCharacterFactory.createCharacter(typeId, name);
                    CharacterMemento memento = new CharacterMemento(name, className, health, attack, defense, speed, coin);
                    character.restoreState(memento);
                    loadedCharacters.add(character);
                }
            }
            System.out.println("\n[IO] Caricati con successo " + loadedCharacters.size() + " personaggi dal file!");
        } catch (IOException | IllegalArgumentException e) {
            System.err.println("\n[ERRORE IO] Errore durante il caricamento del file: " + e.getMessage());
        }

        return loadedCharacters;
    }
}