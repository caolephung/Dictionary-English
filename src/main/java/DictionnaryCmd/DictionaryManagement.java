package DictionnaryCmd;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DictionaryManagement {
    protected Dictionary dictionary = new Dictionary();

    private IOData_SQL IO = new IOData_SQL("jdbc:mysql://localhost:3306/dictionarycmd");

    protected TrieDictionary trieDictionary = new TrieDictionary();

    public DictionaryManagement(Dictionary dictionary) {
        this.dictionary.addAll(dictionary);
        for(Word word : dictionary) {
            trieDictionary.addWord(word.getWord_target(), word.getWord_explain());
        }
    }

    public DictionaryManagement() {
        this.dictionary.addAll(IO.getAllWords());
        for(Word word : dictionary) {
            trieDictionary.addWord(word.getWord_target(), word.getWord_explain());
        }
    }


//    /** Chuyển dictionary từ arraylist sang trie. */
//    private void dictionaryToTriedictionary() {
//        for(Word word : dictionary.getDictionary()) {
//            trieDictionary.addWord(word.getWord_target(), word.getWord_explain());
//        }
//    }

    /**
     * Chỉnh sửa lại từ cho chuẩn
     * Ví dụ: "enGliSh" -> "English"
     */
    public String fix(String word) {
        word = word.trim();
        word.toLowerCase();
        word = word.substring(0,1).toUpperCase() + word.substring(1);
        return word;
    }

    /** Thêm từ mới từ bàn phím. */
    public void insertFromCommandline(){
        System.out.print("Enter the number of word: ");
        Scanner ip = new Scanner(System.in);
        int cnt = ip.nextInt(); ip.nextLine();
        for(int i = 0; i < cnt; i++){
            System.out.println("Word " + i + ":");
            System.out.print("word_target: ");
            String word = ip.nextLine();
            word = fix(word);
            System.out.print("word_explain: ");
            String define = ip.nextLine();
            define = fix(define);

            Word newWord = new Word(word, define);

            /** Kiểm tra từ đã tồn tại hay chưa
             * Nếu chưa tồn tại thì thêm vào
             * Nếu đã tồn tại thì mở ra nghĩa của từ đó và xác nhận thay đổi
             */
            if(!trieDictionary.searchWord(word).isEmpty()) {
                System.out.println("The word \" " + word + " \" already exists");
                System.out.println(word + ":" + trieDictionary.searchWord(word));
                System.out.print("Do you want to change ? (0: no, 1: yes) : ");
                int option = ip.nextInt();
                if (option == 1) {
                    dictionary.add(newWord);
                    trieDictionary.addWord(word, define);
                    IO.updateWord(word, define);
                }
            } else {
                dictionary.add(newWord);
                trieDictionary.addWord(word, define);
                IO.addWord(word, define);
            }
        }
    }

    /** Thêm từ mới từ file. */
    public void insertFromFile(String path) throws IOException {
        FileReader fs = new FileReader(path);
        BufferedReader bs = new BufferedReader(fs);
        String line;
        while ((line = bs.readLine()) != null) {
            String[] word_explain = line.split(" : ");
            String word = fix(word_explain[0]);
            String explain = fix(word_explain[1]);
            Word newWord = new Word(word, explain);
            dictionary.add(newWord);
            trieDictionary.addWord(word, explain);
            IO.addWord(word, explain);
        }
        fs.close();
        bs.close();
    }

    /** Tra cứu từ điển. */
    public void dictionaryLookup() {
        System.out.print("Enter the word to search: ");
        Scanner ip = new Scanner(System.in);
        String SearchWord = ip.nextLine();
        SearchWord = fix(SearchWord);
        if(!trieDictionary.searchWord(SearchWord).isEmpty()) {
            System.out.println("Explain: " + trieDictionary.searchWord(SearchWord));
        } else {
            System.out.println("Not Found!");
        }
    }

    /** Sửa nghĩa của từ đã có sẵn. */
    public void changeExplain() {
        System.out.print("Enter the word to be changed: ");
        Scanner ip = new Scanner(System.in);
        String word = ip.nextLine();
        word = fix(word);
        if(!trieDictionary.searchWord(word).isEmpty()) {
            System.out.println(word + " : " + trieDictionary.searchWord(word));
            System.out.print("New explain: ");
            String newExplain = ip.nextLine();
            newExplain = fix(newExplain);
            dictionary.add(new DictionnaryCmd.Word(word, newExplain));
            trieDictionary.addWord(word, newExplain);
            IO.updateWord(word, newExplain);
        } else {
            System.out.println("Not Found!");
        }
    }

    /** Xóa từ. */
    public void removeWord() {
        System.out.print("Enter the word to be removed: ");
        Scanner ip = new Scanner(System.in);
        String word = ip.nextLine();
        word = fix(word);
        if(!trieDictionary.searchWord(word).isEmpty()) {
            dictionary.remove(word);
            trieDictionary.removeWord(word);
            IO.removeWord(word);
        } else {
            System.out.println("Not Found!");
        }
    }

    /**
     * Ví dụ nhập: tra
     * Kết quả trả về: danh sách các từ vựng bắt đầu bằng “tra”: transport, translate,
     * transform, transit, ...
     */
    public void dictionarySearch() {
        System.out.print("Enter the start word: ");
        Scanner ip = new Scanner(System.in);
        String search = ip.nextLine();
        search = fix(search);

        Trie currentNode = trieDictionary.root;

        for (int i = 0; i < search.length(); i++) {
            char ch = search.charAt(i);
            if (currentNode.getChildren().containsKey(ch)) {
                currentNode = currentNode.getChildren().get(ch);
            }
        }

        if(currentNode == trieDictionary.root) {
            System.out.println("Not Found!");
            return;
        }

        print(currentNode, search, 20, 1);
    }

    /** Xuất dữ liệu từ điển hiện tại ra tệp. */
    public void dictionaryExportToFile(String path) throws IOException {
        // Kiểm tra xem đường dẫn có hợp lệ không
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Đường dẫn không hợp lệ.");
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
            List<Word> tmp = new ArrayList<>(IO.getAllWords());

            for (Word word : tmp) {
                bw.write(word.getWord_target() + " : " + word.getWord_explain());
                bw.newLine();
            }

            System.out.println("Dữ liệu đã được xuất thành công vào file: src/main/java/DataFile/outputFile.txt" );
        } catch (IOException e) {
            System.out.println("Đã xảy ra lỗi khi ghi vào file: " + e.getMessage());
            throw e;
        }
    }

    /** Game. */
    public void dictionaryGame() {
        System.out.print("[1] Game 1\n" +
                "[2] Game 2\n");
        while(true) {
            System.out.print("Your action: ");
            Scanner ip = new Scanner(System.in);
            int action = ip.nextInt();
            if(action == 1) {
                System.out.println("Start game!");
                ListQuestion listQuestion = new ListQuestion();
                listQuestion.insertFromCommandline();
                for (int i = 0; i < listQuestion.size(); i++) {
                    Question q = listQuestion.get(i);
                    System.out.println(q.getQuestion());
                    System.out.println(q.getOption().get(0));
                    System.out.println(q.getOption().get(1));
                    System.out.println(q.getOption().get(2));
                    System.out.println(q.getOption().get(3));
                    Scanner a0 = new Scanner(System.in);
                    String o = a0.next();
                    if (q.getAnswer().equals("A")) {
                        if (o.equals("A")) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    } else if (q.getAnswer().equals("B")) {
                        if (o.equals("B")) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    }else if (q.getAnswer().equals("C")) {
                        if (o.equals("C")) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    } else if (q.getAnswer().equals("D")) {
                        if (o.equals("D")) {
                            System.out.println("Correct!");
                        } else {
                            System.out.println("Wrong!");
                        }
                    }
                    Scanner a1 = new Scanner(System.in);
                    System.out.println("[1] Exit");
                    System.out.println("[2] Do again");
                    System.out.println("[3] Next question");
                    System.out.print("Your action: ");
                    int c = a1.nextInt();
                    if (c == 1) return;
                    else if (c == 2) i--;
                }
            }
            else if(action == 2) {
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
    }


    public void print(Trie node, String prefix, int maxlength, int cnt) {
        if (node.isEndOfWord()) {
            System.out.printf("%-7d| %-" + maxlength + "s| %s\n", cnt, prefix, node.getMeaning());
        }
        for (char ch : node.getChildren().keySet()) {
            Trie childNode = node.getChildren().get(ch);
            print(childNode, prefix + ch, maxlength, cnt);
        }
    }
}