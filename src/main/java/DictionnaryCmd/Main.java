package DictionnaryCmd;


import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        IOData_SQL dataSql = new IOData_SQL("jdbc:mysql://localhost:3306/dictionarycmd");
        Dictionary dictionary = new Dictionary();
        dictionary.addAll(dataSql.getAllWords());

        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline(dictionary);
        dictionaryCommandline.dictionaryAdvanced();
    }
}