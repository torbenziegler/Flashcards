package hyperskill;


import java.io.PrintWriter;
import java.util.*;

public class Stat {

    public static HashMap<String, Integer> mistakes = new HashMap<>();

    public static List<String> log = new ArrayList<>();


    public void logSession(List<String> log) {
        try {
            System.out.println("File name:");
            Stat.log.add("File name:");
            String fileName = Main.scanner.nextLine();
            Stat.log.add(fileName);

            PrintWriter out = new PrintWriter(fileName);

            for (String s : log) {
                out.println(s);
            }

            out.close();

            System.out.println("The log has been saved.");
            Stat.log.add("The log has been saved.");
        } catch (Exception e) {
            System.out.println("Log couldn't be saved.");
            Stat.log.add("Log couldn't be saved.");
        }
    }

    public void resetStats() {
        mistakes.clear();
        System.out.println("Card statistics has been reset.");
        Stat.log.add("Card statistics has been reset.");
    }

    public void getHardestCards() {
        StringBuilder builder = new StringBuilder();
        builder.append("The hardest cards are ");

        String key = "";
        int maxValueInMap = 0;
        int counter = 0;

        if (mistakes.isEmpty()) {
            System.out.println("There are no cards with errors");
            Stat.log.add("There are no cards with errors");
        } else {
            maxValueInMap = (Collections.max(mistakes.values()));
            for (Map.Entry<String, Integer> entry : mistakes.entrySet()) {
                if (entry.getValue() == maxValueInMap) {
                    key = entry.getKey();
                    builder.append("\"").append(entry.getKey()).append("\", ");
                    counter++;
                }
            }
        }

        if (counter == 1) {
            System.out.println("The hardest card is \"" + key + "\". You have " + maxValueInMap + " errors answering it.");
            Stat.log.add("The hardest card is \"" + key + "\". You have " + maxValueInMap + " errors answering it.");
        }

        if (counter > 1) {
            String build = builder.toString();
            build = build.substring(0, build.length() - 2);
            System.out.println(build + " You have " + maxValueInMap + " errors answering them.");
            Stat.log.add(build + " You have " + maxValueInMap + " errors answering them.");
        }


    }


}
