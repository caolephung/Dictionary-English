package commandline;

import java.io.IOException;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.addWord("Hello", "Xin chao");
        dictionary.addWord("Rabbit", "Con tho");
        dictionary.addWord("Pig", "Con lon");
        dictionary.addWord("Bird", "Con cu");
        dictionary.addWord("What your name ?", "May ten la gi ?");
        dictionary.addWord("Where are you from ?", "May den tu dau ?");

        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline(dictionary);
        dictionaryCommandline.dictionaryAdvanced();
    }
}

