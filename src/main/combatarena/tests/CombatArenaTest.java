package main.combatarena.tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.combatarena.playablecharacter.*;
import main.combatarena.savemanagement.CharacterMemento;
import main.combatarena.combatsystem.CombatSystem;

public class CombatArenaTest {

    private PlayableCharacter warrior;
    private PlayableCharacter mage;

    @BeforeEach
    public void inizializzazionePulita() {
        // inizializziamo due personaggi per i test, usando la Factory in modo che siano creati con i parametri corretti
        warrior = PlayableCharacterFactory.createCharacter(1, "Luca");
        mage = PlayableCharacterFactory.createCharacter(2, "Gianni");
    }

    // TEST 1: Verifica la corretta creazione dei personaggi tramite la Factory e gestione eccezioni
    @Test
    public void testFactoryCharacterCreation() {
        assertEquals("Luca", warrior.getName());
        assertTrue(warrior instanceof Warrior, "L'istanza dovrebbe essere di tipo Warrior");
        assertEquals("Gianni", mage.getName());
        assertTrue(mage instanceof Mage, "L'istanza dovrebbe essere di tipo Mage");
    }

    // TEST 2: Verifica la funzionalità del Memento per il salvataggio e il ripristino dello stato del personaggio
    @Test
    public void testMemento() {
        warrior.setHealth(120);
        warrior.setCoin(50.5);

        CharacterMemento memento = warrior.saveState();
        
        warrior.setHealth(20);
        warrior.setCoin(5.0);

        warrior.restoreState(memento);

        assertEquals(120, warrior.getHealth(), "Gli HP non corrispondono allo stato salvato");
        assertEquals(50.5, warrior.getCoin(), "Le monete non corrispondono allo stato salvato");
    }

    // TEST 3: Verifica la risoluzione del sistema di combattimento, assicurandosi che il perdente sia determinato correttamente
    @Test
    public void testCombatSystem() {
        mage.setHealth(1);
        
        CombatSystem combat = new CombatSystem(warrior, mage);
        PlayableCharacter loser = combat.startCombat();
        assertNotNull(loser, "Il combattimento deve decretare un perdente");
        assertEquals(mage.getName(), loser.getName(), "Il mago avrebbe dovuto perdere");
        assertEquals(0, loser.getHealth(), "Il perdente deve terminare il match con 0 HP");
    }
}