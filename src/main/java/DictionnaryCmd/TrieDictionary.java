package DictionnaryCmd;

import java.util.Map;
import java.util.TreeMap;

public class TrieDictionary {
    protected Trie root;

    public TrieDictionary() {
        root = new Trie();
    }

    public Trie getRoot() {
        return root;
    }

    public void addWord(String word, String meaning) {
        Trie current = root;
        for (char c : word.toCharArray()) {
            current.getChildren().putIfAbsent(c, new Trie());
            current = current.getChildren().get(c);
        }
        current.setEndOfWord(true);
        current.setMeaning(meaning);
    }

    public void removeWord(String word) {
        Trie currentNode = root;
        Trie parentNode = null;

        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (currentNode.getChildren().containsKey(ch)) {
                parentNode = currentNode;
                currentNode = currentNode.getChildren().get(ch);
            } else {
                System.out.println("Not Found!");
                return;
            }
        }

        // Kiểm tra nếu từ đó tồn tại trong từ điển
        if (currentNode.isEndOfWord()) {
            if (currentNode.getChildren().isEmpty()) {
                parentNode.getChildren().remove(word.charAt(word.length() - 1));
            } else {
                currentNode.setMeaning("");
                currentNode.setEndOfWord(false);
            }
        }
    }


    public String searchWord(String word) {
        Trie tmp = root;

        for(int i = 0; i < word.length(); i++) {
            TreeMap<Character, Trie> children = tmp.getChildren();
            if (children.containsKey(word.charAt(i))) {
                tmp = children.get(word.charAt(i));
            }
        }

        if(tmp.isEndOfWord()) {
            return tmp.getMeaning();
        }
        return "";
    }
}
