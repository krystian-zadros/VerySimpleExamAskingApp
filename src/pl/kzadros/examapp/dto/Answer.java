package pl.kzadros.examapp.dto;

/**
 *
 * @author kzadros
 */
public class Answer {
    private int questionNumber;
    private String text;

    public Answer(int questionNumber, String text) {
        this.questionNumber = questionNumber;
        this.text = text;
    }
    
    public int getQuestionNumber() {
        return questionNumber;
    }

    public String getText() {
        return text;
    }
}
