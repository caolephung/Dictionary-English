package commandline;

import java.io.IOException;
import java.util.Scanner;

public class DictionaryCommandline {
    private DictionaryManagement dictionaryManagement;

    public DictionaryCommandline() {
        dictionaryManagement = new DictionaryManagement();
    }

    public DictionaryCommandline(Dictionary dictionary) {
        dictionaryManagement = new DictionaryManagement(dictionary);
    }

    /** Hiển thi từ điển. */
    public void showAllWords(){
        // Lấy độ dài của từ dài nhất
        int maxlength = 0;
        for (String word : dictionaryManagement.dictionary.getWords().keySet()) {
            maxlength = Math.max(maxlength, word.length());
        }

        int cnt = 1;
        System.out.printf("No     | %-" + maxlength + "s| %s\n", "English", "Vietnamese");
        for(String key : dictionaryManagement.dictionary.getWords().keySet()) {
            System.out.printf("%-7d| %-" + maxlength + "s| %s\n", cnt, key, dictionaryManagement.dictionary.getWords().get(key));
            cnt++;
        }

    }

    public void dictionaryBasic() {
        System.out.print("[1] Insert form commandline\n" +
                "[2] Show all words\n" +
                "Your action: "
        );
        Scanner ip = new Scanner(System.in);
        int action = ip.nextInt();
        switch (action) {
            case 1:
                dictionaryManagement.insertFromCommandline();
                break;
            case 2:
                showAllWords();
                break;
            case 0:
                System.out.println("Goodbye!");
                return;
            default:
                System.out.println("Action not supported");
                break;
        }
    }

    /** Chạy ứng dụng theo yêu cầu. */
    public void dictionaryAdvanced() throws IOException {
        System.out.print("Welcome to My Application!\n" +
                "[0] Exit\n" +
                "[1] Add\n" +
                "[2] Remove\n" +
                "[3] Update\n" +
                "[4] Display\n" +
                "[5] Lookup\n" +
                "[6] Search\n" +
                "[7] Game\n" +
                "[8] Import from file\n" +
                "[9] Export to file\n");
        while(true) {
            System.out.print("Your action: ");
            Scanner ip = new Scanner(System.in);
            int action = ip.nextInt();
            switch (action) {
                case 0:
                    System.out.println("END.");
                    return;
                case 1:
                    dictionaryManagement.insertFromCommandline();
                    break;
                case 2:
                    dictionaryManagement.removeWord();
                    break;
                case 3:
                    dictionaryManagement.changeExplain();
                    break;
                case 4:
                    showAllWords();
                    break;
                case 5:
                    dictionaryManagement.dictionaryLookup();
                    break;
                case 6:
                    dictionaryManagement.dictionarySearch();
                    break;
                case 7:
                    dictionaryManagement.dictionaryGame();
                    break;
                case 8:
                    String pathIN = "src/main/java/commandline/inputFile.txt";
                    dictionaryManagement.insertFromFile(pathIN);
                    break;
                case 9:
                    String pathOUT = "src/main/java/commandline/outputFile.txt";
                    dictionaryManagement.dictionaryExportToFile(pathOUT);
                    break;
                default:
                    System.out.println("Action not supported");
            }
        }
    }
}
