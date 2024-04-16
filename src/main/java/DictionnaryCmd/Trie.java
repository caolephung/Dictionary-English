package DictionnaryCmd;

import java.util.HashMap;
import java.util.TreeMap;

public class Trie {
    private boolean endOfWord;
    private String meaning;
    private TreeMap<Character,Trie> children;

    public Trie() {
        this.endOfWord = false;
        this.meaning = "";
        this.children = new TreeMap<>();
    }

    public boolean isEndOfWord() {
        return endOfWord;
    }

    public void setEndOfWord(boolean endOfWord) {
        this.endOfWord = endOfWord;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public TreeMap<Character, Trie> getChildren() {
        return children;
    }

    public void setChildren(TreeMap<Character, Trie> children) {
        this.children = children;
    }
}
