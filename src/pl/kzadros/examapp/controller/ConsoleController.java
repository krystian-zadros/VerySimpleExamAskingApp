package pl.kzadros.examapp.controller;

import java.util.Scanner;
import pl.kzadros.examapp.analytics.Analytics;
import pl.kzadros.examapp.dto.Answer;
import pl.kzadros.examapp.dto.UserQuestion;
import pl.kzadros.examapp.service.QuestionService;

/**
 *
 * @author kzadros
 */
public class ConsoleController {
    private final QuestionService qService = new QuestionService();

    public void startLoop() {
        boolean willContinue;
        do {
            this.performAsking(3);
            willContinue = this.askIfContinue();
        } while (willContinue == true);
        this.printStatistics();
    }
    
    public void  performAsking(int amountOfProposedAnswers) {
        UserQuestion question = qService.createQuestion(amountOfProposedAnswers);
        this.printQuestion(question);
        System.out.print("> Numer odp.: ");
        Scanner sc = new Scanner(System.in);
        Integer answer = sc.nextInt();
        
        if (qService.checkAnswer(question, answer)) {
            System.out.println("OK");
        } else {
            System.out.println("Błąd: " + question.getRightAnswerPosition());
        }
    }
    
    public boolean askIfContinue() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Kontynuować? 1/0: ");
        Integer answer = sc.nextInt();
        return (answer == 1);
    }
    
    public void printStatistics() {
        Analytics analytics = qService.getAnalytics();
        float accurrantionPercent = 100 * analytics.getAmountOfRightAnsweredAQuestions() / analytics.getAmountOfAskedQuestions();
        System.out.format("Poprawnych odpowiedzi: %d/%d%n", analytics.getAmountOfRightAnsweredAQuestions(), analytics.getAmountOfAskedQuestions());
        System.out.format("Wynik procentowo: %.2f%%%n", accurrantionPercent);
    }
    
    private void printQuestion(UserQuestion question) {
        System.out.println(question.getQuestion());
        int i=0;
        for (Answer answer : question.getAnswers()) {
            System.out.println(i + ". " + answer.getText());
            ++i;
        }
    }
}
