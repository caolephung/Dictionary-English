package DictionnaryCmd;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        Dictionary dictionary = new Dictionary();
        dictionary.add(new Word("Hello", "Xin chao"));
        dictionary.add(new Word("Rabbit", "tho"));
        dictionary.add(new Word("Pig", "lon"));
        dictionary.add(new Word("Bird", "chim"));
        dictionary.add(new Word("Where are you from ?", "Ban den tu dau ?"));
        dictionary.add(new Word("What your name ?", "Ban ten la gi ?"));

        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline(dictionary);
        dictionaryCommandline.dictionaryAdvanced();
    }
}



