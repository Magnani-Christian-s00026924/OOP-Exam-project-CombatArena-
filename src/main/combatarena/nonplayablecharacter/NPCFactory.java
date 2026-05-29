package main.combatarena.nonplayablecharacter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import main.combatarena.nonplayablecharacter.easy.Bat;
import main.combatarena.nonplayablecharacter.easy.Skeleton;
import main.combatarena.nonplayablecharacter.easy.Slime;
import main.combatarena.nonplayablecharacter.hard.MountainGiant;
import main.combatarena.nonplayablecharacter.hard.ShadowAssasin;
import main.combatarena.nonplayablecharacter.hard.UndeadCommander;
import main.combatarena.nonplayablecharacter.medium.AlphaWolf;
import main.combatarena.nonplayablecharacter.medium.Deserter;
import main.combatarena.nonplayablecharacter.medium.StoneGolem;
import main.combatarena.playablecharacter.PlayableCharacter;

public class NPCFactory {

    public enum Difficulty {
        EASY, MEDIUM, HARD;

        public String getDescription() {
            return switch (this) {
                case EASY   -> "[FACILE]    (Bat, Skeleton, Slime)";
                case MEDIUM -> "[MEDIO]     (Alpha Wolf, Deserter, Golem)";
                case HARD   -> "[DIFFICILE] (Giant, Assassin, Commander)";
            };
        }
    }

    public static List<PlayableCharacter> getEnemiesForDifficulty(Difficulty difficulty) {
        List<PlayableCharacter> enemies = new ArrayList<>();
        switch (difficulty) {
            case EASY -> {
                enemies.add(new Bat("Bat"));
                enemies.add(new Skeleton("Skeleton"));
                enemies.add(new Slime("Slime"));
            }
            case MEDIUM -> {
                enemies.add(new AlphaWolf("Alpha Wolf"));
                enemies.add(new Deserter("Deserter"));
                enemies.add(new StoneGolem("Stone Golem"));
            }
            case HARD -> {
                enemies.add(new MountainGiant("Mountain Giant"));
                enemies.add(new ShadowAssasin("Shadow Assassin"));
                enemies.add(new UndeadCommander("Undead Commander"));
            }
        }
        return enemies;
    }

    public static PlayableCharacter getRandomEnemy(Difficulty difficulty) {
        List<PlayableCharacter> enemies = getEnemiesForDifficulty(difficulty);
        return enemies.get(new Random().nextInt(enemies.size()));
    }
}