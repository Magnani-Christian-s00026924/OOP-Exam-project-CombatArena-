package main.combatarena.menuinterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

public class LogManager {
    private static final String LOG_FILE = "errori_sistema.log";

    // Metodo per loggare eccezioni con contesto e stack trace completo
    public static void logger(String contesto, Exception e) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(LOG_FILE))) {
            writer.write("ERRORE IN: " + LocalDateTime.now() + " - " + contesto);
            writer.write("Messaggio: " + e.getMessage() + "\n");
            
            // Scrive lo stack trace completo nel file di log 
            for (StackTraceElement element : e.getStackTrace()) {
                writer.write("    at " + element.toString() + "\n");
            }
            writer.write("-----------------------------------------------------\n");
        } catch (IOException ioException) {
            // Se fallisce persino il log, stampiamo solo un avviso minimo per non far crashare l'app
            System.err.println("Impossibile scrivere nel file di log: " + ioException.getMessage());
        }
    }
}