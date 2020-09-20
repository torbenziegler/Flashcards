package hyperskill;


import java.io.*;
import java.util.Map;

public class CardFactory {

    public void addCards(Map<String, String> cards) {
        System.out.println("The card:");
        Stat.log.add("The card:");
        String word = Main.scanner.nextLine();
        Stat.log.add(word);
        if (cards.containsKey(word)) {
            System.out.println("The card \"" + word + "\" already exists.");
            Stat.log.add("\"The card \"" + word + "\" already exists.");
        } else {
            System.out.println("The definition of the card:");
            Stat.log.add("The definition of the card:");
            String definition = Main.scanner.nextLine();
            Stat.log.add(definition);
            if (cards.containsValue(definition)) {
                System.out.println("The definition \"" + definition + "\" already exists.");
                Stat.log.add("The definition \"" + definition + "\" already exists.");
            } else {
                cards.put(word, definition);
                System.out.println("The pair (\"" + word + "\":\"" + definition + "\") has been added.");
                Stat.log.add("The pair (\"" + word + "\":\"" + definition + "\") has been added.");
            }
        }
    }

    public void askCards(Map<String, String> cards) {
        System.out.println("How many times to ask?");
        Stat.log.add("How many times to ask?");
        int amount = Integer.parseInt(Main.scanner.nextLine());
        Stat.log.add(String.valueOf(amount));
        int counter = 0;

        while (counter < amount) {
            for (Map.Entry<String, String> entry : cards.entrySet()) {
                String key = entry.getKey();
                String value = entry.getValue();

                System.out.println("Print the definition of \"" + key + "\"");
                Stat.log.add("Print the definition of \"" + key + "\"");
                String givenAnswer = Main.scanner.nextLine();
                Stat.log.add(givenAnswer);

                if (value.equals(givenAnswer)) {
                    System.out.println("Correct!");
                    Stat.log.add("Correct!");
                } else {
                    if (cards.containsValue(givenAnswer)) {
                        String usedDef = null;
                        for (Map.Entry<String, String> entry2 : cards.entrySet()) {
                            if (entry2.getValue().equals(givenAnswer)) {
                                usedDef = entry2.getKey();
                                break;
                            }
                        }

                        System.out.println("Wrong. The right answer is \"" + cards.get(key) + "\", but " +
                                "your definition is correct for \"" + usedDef + "\"");
                        Stat.log.add("Wrong. The right answer is \"" + cards.get(key) + "\", but " +
                                "your definition is correct for \"" + usedDef + "\"");

                        Stat.mistakes.merge(key, 1, Integer::sum); // Add 1 mistake count to word
                    } else {
                        System.out.println("Wrong. The right answer is \"" + value + "\"");
                        Stat.mistakes.merge(key, 1, Integer::sum); // Add 1 mistake count to word
                        Stat.log.add("Wrong. The right answer is \"" + value + "\"");
                    }
                }
                counter++;
                if (counter == amount) {
                    break;
                }
            }
        }
    }

    public void removeCard(Map<String, String> cards) {
        System.out.println("The card:");
        Stat.log.add("The card:");
        String card = Main.scanner.nextLine();
        Stat.log.add(card);
        if (cards.containsKey(card)) {
            cards.remove(card);
            Stat.mistakes.remove(card);
            System.out.println("The card has been removed.");
            Stat.log.add("The card has been removed.");
        } else {
            System.out.println("Can't remove \"" + card + "\": there is no such card.");
            Stat.log.add("Can't remove \"" + card + "\": there is no such card.");
        }

    }

    public void importCards(Map<String, String> cards, String filePath) {
        Stat.log.add(filePath);

        try {
            File file = new File(filePath);

            BufferedReader reader = new BufferedReader(new FileReader(file));

            String string;
            String[] pair;
            int counter = 0;

            while ((string = reader.readLine()) != null) {
                pair = string.split(":");
                cards.put(pair[0], pair[1]);
                Stat.mistakes.put(pair[0], Integer.valueOf(pair[2]));
                counter++;
            }
            reader.close();
            System.out.println(counter + " cards have been loaded.");
            Stat.log.add(counter + " cards have been loaded.");
        } catch (Exception e) {
            System.out.println("File not found.");
            Stat.log.add("File not found.");
        }

    }

    public void exportCards(Map<String, String> cards, String fileName) throws FileNotFoundException {
        Stat.log.add(fileName);
        int counter = 0;
        String output;

        int errorCount;

        PrintWriter out = new PrintWriter(fileName);
        for (Map.Entry<String, String> entry : cards.entrySet()) {
            if (Stat.mistakes.get(entry.getKey()) != null) {
                errorCount = Stat.mistakes.get(entry.getKey());
            } else {
                errorCount = 0;
            }
            output = entry.getKey() + ":" + entry.getValue() + ":" + errorCount;
            out.println(output);
            counter++;
        }

        out.close();

        System.out.println(counter + " cards have been saved.");
        Stat.log.add(counter + " cards have been saved.");
    }
}


