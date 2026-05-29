package main.combatarena.menuinterface;

import java.util.Scanner;

public class UserInputInterface {
    private Scanner scanner = new Scanner(System.in);

    public int getUserChoice() {
        while (!scanner.hasNextInt()) {
            System.out.println("Errore: Inserisci un numero valido.");
            scanner.next(); // Pulisce la stringa.
        }
        int choice = scanner.nextInt();
        scanner.nextLine(); // FONDAMENTALE: Consuma il "Invio" rimasto nel buffer. Così si evita che il prossimo getStringInput() legga una stringa vuota.
        return choice;
    }

    public String getStringInput() {
        String input = scanner.nextLine();
        return (input == null) ? "" : input.trim();
    }
}