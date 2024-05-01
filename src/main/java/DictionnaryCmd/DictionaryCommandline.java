package DictionnaryCmd;

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
    public void showAllWords(Trie node){

        int cnt = 1;
        System.out.printf("No     | %-" + 20 + "s| %s\n", "English", "Vietnamese");
        dictionaryManagement.print(node, "", 20, 1);
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
                showAllWords(dictionaryManagement.trieDictionary.root);
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
                    showAllWords(dictionaryManagement.trieDictionary.root);
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
                    String pathIN = "src/main/java/DataFile/inputFile.txt";
                    dictionaryManagement.insertFromFile(pathIN);
                    break;
                case 9:
                    String pathOUT = "D:\\oop-Phung-Hung-Trieu\\src\\main\\java\\DataFile\\outputFile.txt";
                    dictionaryManagement.dictionaryExportToFile(pathOUT);
                    break;
                default:
                    System.out.println("Action not supported");
            }
        }
    }
}
