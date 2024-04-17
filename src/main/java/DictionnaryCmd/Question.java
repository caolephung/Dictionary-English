package DictionnaryCmd;

import java.util.ArrayList;
import java.util.List;

public class Question {
    private String question;
    private List<String> option = new ArrayList<>();
    private String answer;

    public String getQuestion() {
        return question;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public List<String> getOption() {
        return option;
    }
    public void setOption(List<String> option) {
        this.option = option;
    }
    public String getAnswer() {
        return answer;
    }
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public Question(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public void addChoice(String option1, String option2, String option3, String option4) {
        option.add(option1);
        option.add(option2);
        option.add(option3);
        option.add(option4);
    }
}
