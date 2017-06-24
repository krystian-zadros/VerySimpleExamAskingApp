package pl.kzadros.examapp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import pl.kzadros.examapp.analytics.Analytics;
import pl.kzadros.examapp.dto.Answer;
import pl.kzadros.examapp.entities.Question;
import pl.kzadros.examapp.repository.DataStore;
import pl.kzadros.examapp.dto.UserQuestion;

/**
 *
 * @author kzadros
 */
public class QuestionService {
    private final List<Integer> askedQuestionsNumbers = new ArrayList<>();
    private DataStore dataStore = new DataStore();
    private Analytics analytics = new Analytics();

    public QuestionService() {
    }
    
    public UserQuestion createQuestion(int amountOfProposedAnswers) {
        Random rand = new Random();
        int amountOfAllQuestions = dataStore.size();
        int newQuestionId = rand.nextInt(amountOfAllQuestions);
        
        UserQuestion question = new UserQuestion(amountOfProposedAnswers);
        this.setQuestionAndRightAnswer(question, newQuestionId);
        
        int[] wrongAnswersIds = this.generateRandomWrongAnswersIds(newQuestionId, amountOfProposedAnswers, newQuestionId);
        generateRandomWrongAnswers(question, wrongAnswersIds);
        return question;
    }
    
    public boolean checkAnswer(UserQuestion question, int answerPosition) {
        if (question.checkAnswer(answerPosition)) {
            analytics.updateRight();
            return true;
        } else {
            analytics.updateWrong();
            return false;
        }
    }
    
    public Analytics getAnalytics() {
        return analytics;
    }
    
    private void setQuestionAndRightAnswer(UserQuestion question, int newQuestionId) {
        Question askedQuestion = dataStore.get(newQuestionId);
        Answer rightAnswer = new Answer(newQuestionId, askedQuestion.getAnswer());
        question.setQuestion(askedQuestion.getQuestion());
        question.setQuestionId(newQuestionId);
        question.setRightAnswer(rightAnswer);
    }
    
    private void generateRandomWrongAnswers(UserQuestion question, int[] wrongAnswersIds) {
        int size = question.size();
        String wrongAnswer;
        for (int i=0 ; i< size ; i++) {
            wrongAnswer = dataStore.get(wrongAnswersIds[i]).getAnswer();
            Answer answer = new Answer(wrongAnswersIds[i], wrongAnswer);
            question.addWrongAnswer(answer);
        }
    }
    
    private int[] generateRandomWrongAnswersIds(int amountOfAllAnswers, int amountOfNeededAnswers, int rightAnswerNumber) {
        Random rand = new Random();
        int[] wrongAnswers = new int[amountOfNeededAnswers];
        int newNumber;
        boolean wasBefore;
        for (int i=0 ; i< wrongAnswers.length ; i++) {
            do {
                newNumber = rand.nextInt(amountOfAllAnswers);
                wasBefore = false;
                for (int j=0 ; j<i; j++) {
                    if (wrongAnswers[j] == newNumber || rightAnswerNumber == newNumber) {
                        wasBefore = true;
                        break;
                    }
                }
            } while (wasBefore == true);
            wrongAnswers[i] = newNumber;
        }
        return wrongAnswers;
    }
}
