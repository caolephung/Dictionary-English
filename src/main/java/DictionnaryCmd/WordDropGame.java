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

    public static void main(String[] args) {
        String filePath = "src/main/java/DataFile/words.txt"; // Default file path

        try {
            WordDropGame game = new WordDropGame(filePath);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String word = game.getRandomWord();
                String scrambled = game.scrambleWord(word);
                System.out.println("Guess the word: " + scrambled);
                String guess = scanner.nextLine();
                if (guess.equalsIgnoreCase(word)) {
                    System.out.println("Correct!");
                } else {
                    System.out.println("Incorrect, the word was: " + word);
                }
                Scanner a1 = new Scanner(System.in);
                System.out.println("[1] Exit");
                System.out.println("[2] Next question");
                System.out.print("Your action: ");
                int c = a1.nextInt();
                if (c == 1) return;
            }
        } catch (IOException e) {
            System.out.println("Error reading word file: " + e.getMessage());
        }
    }
}