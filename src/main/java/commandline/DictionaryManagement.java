package commandline;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class DictionaryManagement {

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
    public void insertFromCommandline(Dictionary dictionary){
        System.out.print("Enter the number of word: ");
        Scanner ip = new Scanner(System.in);
        int cnt = ip.nextInt();
        for(int i = 0; i < cnt; i++){
            System.out.println("Word " + i + ":");
            System.out.print("word_target: ");
            ip.nextLine();
            String word = ip.nextLine();
            word = fix(word);
            System.out.print("word_explain: ");
            ip.nextLine();
            String define = ip.nextLine();
            define = fix(define);

            /** Kiểm tra từ đã tồn tại hay chưa
             * Nếu chưa tồn tại thì thêm vào
             * Nếu đã tồn tại thì mở ra nghĩa của từ đó và xác nhận thay đổi
             */
            if(dictionary.getWords().containsKey(word)) {
                System.out.println("The word \" " + word + " \" already exists");
                System.out.println(word + ":" + dictionary.getWords().get(word));
                System.out.print("Do you want to change ? (0: no, 1: yes) : ");
                int option = ip.nextInt();
                if (option == 1) {
                    dictionary.getWords().put(word, define);
                } else {
                    dictionary.getWords().put(word, define);
                }
            }
        }
    }

    /** Thêm từ mới từ file. */
    public void insertFromFile(Dictionary dictionary) throws IOException {
        String path = "dictionaries.txt";
        FileReader fs = new FileReader(path);
        BufferedReader bs = new BufferedReader(fs);
        String line;
        while ((line = bs.readLine()) != null) {
            String[] word_explain = line.split("\\t");
            String word = fix(word_explain[0]);
            String explain = fix(word_explain[1]);
            Word newWord = new Word(word, explain);
            dictionary.addWord(newWord);
        }
        fs.close();
        bs.close();
    }

    /** Tra cứu từ điển. */
    public void dictionaryLookup(Dictionary dictionary) {
        System.out.print("Enter the word to search: ");
        Scanner ip = new Scanner(System.in);
        String SearchWord = ip.nextLine();
        SearchWord = fix(SearchWord);
        if(dictionary.getWords().containsKey(SearchWord)) {
            System.out.println(SearchWord + " : " + dictionary.getWords().get(SearchWord));
        } else {
            System.out.println("Not Found!");
        }
    }

    /** Sửa nghĩa của từ đã có sẵn. */
    public void changeExplain(Dictionary dictionary) {
        System.out.print("Enter the word to be changed: ");
        Scanner ip = new Scanner(System.in);
        String word = ip.nextLine();
        word = fix(word);
        if(dictionary.getWords().containsKey(word)) {
            System.out.println(word + " : " + dictionary.getWords().get(word));
            System.out.print("New explain: ");
            String newExplain = ip.nextLine();
            newExplain = fix(newExplain);
            dictionary.getWords().put(word, newExplain);

        } else {
            System.out.println("Not Found!");
        }
    }

    /** Xóa từ. */
    public void removeWord(Dictionary dictionary) {
        System.out.print("Enter the word to be removed: ");
        Scanner ip = new Scanner(System.in);
        String word = ip.nextLine();
        word = fix(word);
        if(dictionary.getWords().containsKey(word)) {
            dictionary.getWords().remove(word);
        } else {
            System.out.println("Not Found!");
        }
    }

    /**
     * Ví dụ nhập: tra
     * Kết quả trả về: danh sách các từ vựng bắt đầu bằng “tra”: transport, translate,
     * transform, transit, ...
     */
    public void dictionarySearch(Dictionary dictionary) {

    }

    /** Xuất dữ liệu từ điển hiện tại ra tệp. */
    public void dictionaryExportToFile(Dictionary dictionary) {

    }
}
