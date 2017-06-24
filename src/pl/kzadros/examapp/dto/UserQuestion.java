package pl.kzadros.examapp.dto;

import java.util.Random;
import pl.kzadros.examapp.dto.Answer;

/**
 *
 * @author kzadros
 */
public class UserQuestion {
    private String question;
    private int questionId;
    private int rightAnswerPosition;
    private Answer[] answers;
    private final Random rand = new Random();
    
    public UserQuestion(int amountOfAnswers) {
        answers = new Answer[amountOfAnswers];
        for (int i=0 ; i< answers.length ; i++) {
            answers[i] = null;
        }
    }
    
    public void setRightAnswer(Answer answer) {
       rightAnswerPosition = rand.nextInt(answers.length);
       answers[rightAnswerPosition] = answer;
       
    }
    
    public void addWrongAnswer(Answer answer) {
        for (int i=0 ; i<answers.length ; i++) {
            if (answers[i] == null) {
                answers[i] = answer;
                break;
            }
        }
    }

    public String getQuestion() {
        return question;
    }

    public int getRightAnswerPosition() {
        return rightAnswerPosition;
    }

    public Answer[] getAnswers() {
        return answers;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }
    
    public boolean checkAnswer(int outputAnswerPosition) {
        return (outputAnswerPosition == rightAnswerPosition);
    }
    
    public int size() {
        return answers.length;
    }
}
