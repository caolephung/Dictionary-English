package commandline;

import java.util.HashMap;

public class DictionaryCommandline {

    /** Hiển thi từ điển. */
    public void showAllWords(Dictionary dictionary){


    }

    public void dictionaryBasic(Dictionary dictionary) {
        DictionaryManagement dic = new DictionaryManagement();
        dic.insertFromCommandline(dictionary);
        showAllWords(dictionary);
    }

    /** Chạy ứng dụng theo yêu cầu. */
    public void dictionaryAdvanced() {

    }
}
