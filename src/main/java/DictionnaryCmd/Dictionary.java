package DictionnaryCmd;

import java.util.ArrayList;
import java.util.List;

public class Dictionary extends ArrayList<Word> {
    private static Dictionary dictionary;

    public static Dictionary getDictionary() {
        if(dictionary == null) {
            dictionary = new Dictionary();
        }
        return dictionary;
    }

}
