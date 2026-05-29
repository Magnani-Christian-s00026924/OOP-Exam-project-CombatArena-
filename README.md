# Combat Arena - Progetto Finale Java SE

## 1. Panoramica del Progetto
Il progetto consiste in un'applicazione sviluppata in Java che simula un'arena di combattimento fantasy gestionale. L'utente può creare un'armata di eroi (fino a un massimo di 5 personaggi scelti tra varie classi come Guerriero, Mago, Ladro, Cavaliere, Orco, Schiavo), potenziarne le statistiche spendendo le monete guadagnate, acquistare o vendere oggetti consumabili ed equipaggiamenti presso un mercante, e farli combattere.
Il sistema supporta due modalità di gioco:
* **PvP (Player vs Player):** Combattimento simulato a turni tra due personaggi della propria armata.
* **PvE (Player vs Environment):** Combattimento contro creature ed NPC gestiti dal computer, divisi per tre livelli di difficoltà (Facile, Medio, Difficile).

Il software è stato strutturato separando la logica di gestione dei dati dei personaggi, formule di combattimento, inventario dall'interfaccia utente interazione a console tramite la classe `Menu` e `UserInputInterface`.

---

## 2. Architettura del Software e Design Patterns

Per soddisfare i requisiti richiesti dalle specifiche d'esame, sono stati integrati i seguenti design patterns:

### Factory Pattern (`PlayableCharacterFactory` & `NPCFactory`)
La creazione dei personaggi (sia giocabili che nemici) è centralizzata. La classe `PlayableCharacterFactory` espone il metodo statico `createCharacter(int type, String name)` che incapsula lo switch-case per istanziare le sottoclassi specifiche (`Warrior`, `Mage`, `Rogue`, ecc.).
* **Perché è stato usato:** Evita di disperdere l'operatore `new` nel codice dei menu, disaccoppiando l'interfaccia utente dalle classi concrete. Se in futuro volessi aggiungere una classe "Elf", mi basterà modificare la Factory senza toccare la logica del `CharacterManager`.
* **NPCFactory:** Svolge lo stesso compito per i mostri del PvE, raggruppandoli per enumerazione di difficoltà (`Difficulty.EASY`, `MEDIUM`, `HARD`), automatizzando la selezione dei nemici casuali.

### Memento Pattern (`CharacterMemento`)
Utilizzato per gestire i salvataggi temporanei dello stato di un personaggio prima di eventi critici o per tracciare le modifiche strutturali.
* **Componenti:**
    * *Originator (`PlayableCharacter`):* Crea il memento contenente i suoi dati correnti (HP e monete) tramite `saveState()` e ripristina lo stato tramite `restoreState(CharacterMemento m)`.
    * *Memento (`CharacterMemento`):* Oggetto immutabile che memorizza lo stato dei dati.
* **Perché è stato usato:** Permette di salvare lo stato interno di un oggetto senza violare il suo incapsulament.

### Composite Pattern (`Inventory` e `Item`)
L'architettura degli oggetti di gioco sfrutta un pattern strutturale per trattare singoli elementi e contenitori in modo uniforme.
* **Componenti:**
    * Component (`Item`): Interfaccia che definisce le operazioni comuni `getName()` e `getPrice()`.
    * Leaf (`SingleItem`, `UsableItem`, `Equipment`): Rappresentano i singoli elementi acquistabili (pozioni, pergamene, armi, armature).
    * Composite (`Inventory`): Implementa `Item` ma contiene al suo interno una lista di altri `Item` (`List<Item> content`).
* **Perché è stato usato:** Il metodo `getPrice()` invocato sull'inventario calcola dinamicamente la somma dei prezzi di tutti gli oggetti contenuti al suo interno. Questo permette al sistema del Mercante di valutare il patrimonio complessivo di un eroe trattando l'intero zaino come se fosse un singolo oggetto.

### Exception Shielding (Gestione Sicura delle Eccezioni)
Implementato per impedire crash applicativi e la fuga di informazioni sensibili verso l'utente finale.
* **Applicazione pratica:** Nella classe `UserInputInterface`, i metodi di cattura dell'input gestiscono esplicitamente il fallimento della lettura (es. inserimento di caratteri alfabetici dove è richiesto un intero) pulendo il buffer dello scanner ed evitando il sollevamento di un'eccezione non intercettata (`InputMismatchException`).
* Inoltre, nella classe `ConsoleCleaner`, l'interazione con il processo di sistema per la pulizia del terminale (`ProcessBuilder`) è racchiusa in un blocco `try-catch (Exception e)` generico: in caso di fallimento su sistemi operativi non standard, l'errore viene schermato e mitigato stampando semplici righe vuote, impedendo la visualizzazione dello stack trace a schermo.

---

## 3. Tecnologie Core Java Utilizzate

* **Java I/O (BufferedReader / BufferedWriter):** Utilizzato per implementare la persistenza dei dati sul disco fisso tramite il file `salvataggi.txt`. Il sistema scrive i dati dei memento in un formato CSV personalizzato. L'uso del costrutto *try-with-resources* garantisce la chiusura automatica dei flussi ed evita memory leak. Inoltre, in fase di lettura viene effettuato un controllo preventivo con `file.exists()` per evitare eccezioni nel caso di primo avvio.
* **Collections Framework & Generics:** Uso di `ArrayList<T>` accoppiato ai Generics per garantire la type-safety a tempo di compilazione. `CharacterManager` utilizza una `List<PlayableCharacter>` per limitare la dimensione massima del party a 5 elementi, mentre `Inventory` usa una lista polimorfica di `Item`.
* **Java Stream API & Lambdas:** Utilizzate nella logica di filtraggio dei dati. Ad esempio, nel metodo `getPrice()` di `Inventory` (che calcola il valore totale degli oggetti), le stream e le funzioni lambda sono state preferite ai cicli for tradizionali per rendere le operazioni di aggregazione più dichiarative e leggibili.
* **Controllo dei Flussi (Thread.sleep):** All'interno di `CombatSystem`, è stato inserito un delay controllato nel ciclo dei round. Questa temporizzazione serve a livello di interfaccia console per consentire all'utente di leggere l'andamento dei danni inflitti e degli HP rimanenti prima del round successivo.

---

## 4. Test Automatizzati (JUnit 5)
La suite di test è implementata nella classe `CombatArenaTest` (pacchetto `main.combatarena.tests`) ed è strutturata per validare le componenti critiche dell'applicazione in completo isolamento:

1.  **`testFactoryCharacterCreation`:** Verifica che la factory istanzi correttamente le classi concrete figlie (`Warrior`, `Mage`) in base all'ID numerico passato e che i nomi vengano assegnati correttamente.
2.  **`testMemento`:** Valida il corretto funzionamento del pattern Memento, accertandosi che dopo il salvataggio dello stato, una modifica ai parametri (HP e monete) venga annullata con successo dal ripristino.
3.  **`testCombatSystem`:** Simula un match forzando la sconfitta del secondo sfidante per verificare la correttezza della determinazione del perdente e l'azzeramento corretto degli HP.

---

## 5. Limitazioni e Sviluppi Futuri
Durante lo sviluppo sono state identificate alcune limitazioni strutturali legate al contesto console e alla gestione semplice dei dati:
1.  **Sicurezza del File di Salvataggio:** Il file `salvataggi.txt` viene memorizzato in formato testo CSV in chiaro. Questo significa che un utente esterno potrebbe aprire il file con un qualsiasi blocco note e alterare manualmente le monete o i punti vita dei personaggi. Un'evoluzione futura prevede l'introduzione della cifratura dei dati o il passaggio a un Database per blindare i salvataggi.
2.  **Interfaccia Utente Sincrona:** L'uso della console rende l'interazione bloccante (l'applicazione attende costantemente l'input dello scanner). L'introduzione di una GUI permetterebbe una gestione degli eventi asincrona e una resa visiva nettamente superiore per i turni di combattimento.