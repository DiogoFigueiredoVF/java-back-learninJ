package oop;
import java.io.*;
import java.util.*;

public class ScoreManager {

    private static final String FILE = "scores.txt";

    // SAVE SCORE WITH NAME
    public static void saveScore(String name, int score) {

        List<ScoreEntry> list = loadScores();

        list.add(new ScoreEntry(name, score));

        // sort DESC
        list.sort((a, b) -> b.score - a.score);

        // keep TOP 5
        if (list.size() > 5) {
            list = new ArrayList<>(list.subList(0, 5));
        }

        // rewrite file
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE))) {

            for (ScoreEntry s : list) {
                bw.write(s.name + " " + s.score);
                bw.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // LOAD SCORES
    public static List<ScoreEntry> loadScores() {

        List<ScoreEntry> list = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE))) {

            String line;

            while ((line = br.readLine()) != null) {

                String[] parts = line.split(" ");
                list.add(new ScoreEntry(parts[0], Integer.parseInt(parts[1])));
            }

        } catch (Exception ignored) {}

        return list;
    }
}