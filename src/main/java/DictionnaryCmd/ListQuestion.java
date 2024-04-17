package DictionnaryCmd;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListQuestion extends ArrayList<Question> {
    public static String path = "/E:/oop/oop-Phung-Hung-Trieu/src/main/java/DataFile/Listquestion.txt";
    public void insertFromCommandline() {
        File file = new File(path);
        try {
            List<String> allText = Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
            for (int i = 0; i < allText.size(); i+=6) {
                String question = allText.get(i);
                String option1 = allText.get(i+1);
                String option2 = allText.get(i+2);
                String option3 = allText.get(i+3);
                String option4 = allText.get(i+4);
                String answer = allText.get(i+5);
                Question Q = new Question(question, answer);
                Q.addChoice(option1, option2, option3, option4);
                this.add(Q);
            }
            Collections.shuffle(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
