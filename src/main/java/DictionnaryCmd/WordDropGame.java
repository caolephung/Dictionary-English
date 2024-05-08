package DictionnaryCmd;

import java.io.*;
import java.util.*;

public class WordDropGame {
    private List<String> words;
    private Random random;

    public WordDropGame(String filename) throws IOException {
        words = new ArrayList<>();
        random = new Random();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                words.add(line.trim());
            }
        }
    }

    public String getRandomWord() {
        int index = random.nextInt(words.size());
        return words.get(index);
    }

    public String scrambleWord(String word) {
        List<Character> characters = new ArrayList<>();
        for (char c : word.toCharArray()) {
            characters.add(c);
        }
        Collections.shuffle(characters);
        StringBuilder result = new StringBuilder(word.length());
        for (char c : characters) {
            result.append(c);
            result.append("/"); // Add a "/" after each character
        }
        return result.toString();
    }
}