package main.combatarena.menuinterface;

public class ConsoleCleaner {
    public static void clearConsole() {
        try {
            // Verifica il sistema operativo
            String os = System.getProperty("os.name").toLowerCase();

            if (os.contains("win")) {
                // Comando specifico per il Prompt dei Comandi di Windows
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Codice ANSI per sistemi Unix-like (Linux, macOS)
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            // Se fallisce, stampiamo molte righe vuote come "pulizia" della console
            for (int i = 0; i < 50; i++) System.out.println();
        }
    }

    public static void printHeader(String title) {
        String separator = "========================================";
        System.out.println("\n" + separator);
        System.out.println("      " + title.toUpperCase());
        System.out.println(separator);
    }
    
}