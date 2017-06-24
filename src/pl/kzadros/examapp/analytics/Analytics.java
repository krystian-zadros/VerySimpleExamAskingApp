package pl.kzadros.examapp.analytics;

/**
 *
 * @author kzadros
 */
public class Analytics {
    private int amountOfAskedQuestions = 0;
    private int amountOfRightAnsweredAQuestions = 0;
    
    public void updateRight() {
        ++amountOfRightAnsweredAQuestions;
        ++amountOfAskedQuestions;
    }
    
    public void updateWrong() {
        ++amountOfAskedQuestions;
    }

    public int getAmountOfAskedQuestions() {
        return amountOfAskedQuestions;
    }

    public int getAmountOfRightAnsweredAQuestions() {
        return amountOfRightAnsweredAQuestions;
    }
    
    
}
