package commandline;

import java.util.HashMap;


public class Dictionary {
    private HashMap<String, String> words = new HashMap<>();
    public Dictionary(){
        this.words = new HashMap<>();
    }
    public Dictionary(HashMap<String, String> words){
        this.words = words;
    }

    public HashMap<String, String> getWords() {
        return this.words;
    }
    public void addWord(Word word){
        words.put(word.getWord_target(), word.getWord_explain());
    }

    public void addWord(String word, String explain){
        words.put(word, explain);
    }
}


