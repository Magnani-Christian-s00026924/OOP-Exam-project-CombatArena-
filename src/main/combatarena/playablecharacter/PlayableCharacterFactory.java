package main.combatarena.playablecharacter;

//PlayableCharacterFactory: Implementazione del pattern Factory, gestisce la creazione centralizzata di istanze di PlayableCharacter.
public class PlayableCharacterFactory {

    // Crea personaggio in base al tipo e al nome forniti.
    public static PlayableCharacter createCharacter(int type, String name) {
        if (type > 6 || type <= 0 || name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Invalid character type or name.");
        }
        
        switch (type) {
            case 1:
                return new Warrior(name);
                
            case 2:
                return new Mage(name);
                
            case 3:
                return new Rogue(name);
            case 4 :
                return new Knight(name);
            case 5:
                return new Orc(name);
            case 6:
                return new Slave(name); 
                
            default:
                throw new IllegalArgumentException("Type of character not recognized: " + type);
        }
    }
}
