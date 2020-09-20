package hyperskill;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        HashMap<String, String> arguments = new HashMap<>();
        for (int i = 0; i < args.length - 1; i++) {
            arguments.put(args[i], args[i + 1]);
        }

        String importFile = arguments.getOrDefault("-import", null);
        String exportFile = arguments.getOrDefault("-export", null);

        Map<String, String> cards = new LinkedHashMap<>();

        CardFactory cardFactory = new CardFactory();
        Stat statistics = new Stat();

        String input;

        if (importFile != null) {
            cardFactory.importCards(cards, importFile);
        }

        do {
            System.out.println();
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            Stat.log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            input = scanner.nextLine();
            Stat.log.add(input);
            switch (input) {
                case "add": // Add card to deck
                    cardFactory.addCards(cards);
                    break;
                case "remove": // Remove card from deck
                    cardFactory.removeCard(cards);
                    break;
                case "import": // Import cards from file
                    System.out.println("File name:");
                    Stat.log.add("File name:");
                    cardFactory.importCards(cards, scanner.nextLine());
                    break;
                case "export": // Export cards to file
                    System.out.println("File name:");
                    Stat.log.add("File name:");
                    cardFactory.exportCards(cards, scanner.nextLine());
                    break;
                case "ask": // Test cards
                    cardFactory.askCards(cards);
                    break;
                case "exit": // Terminate application
                    System.out.println("Bye bye!");
                    Stat.log.add("Bye bye!");
                    if (exportFile != null) {
                        cardFactory.exportCards(cards, exportFile);
                    }
                    break;
                case "log": // Log current session
                    statistics.logSession(Stat.log);
                    break;
                case "hardest card": // Show hardest card(s)
                    statistics.getHardestCards();
                    break;
                case "reset stats": // Reset mistake statistics
                    statistics.resetStats();
                    break;
            }
        } while (!input.equals("exit"));


    }


}

