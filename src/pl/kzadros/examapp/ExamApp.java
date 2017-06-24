package pl.kzadros.examapp;

import pl.kzadros.examapp.controller.ConsoleController;

/**
 *
 * @author kzadros
 */
public class ExamApp {

    public static void main(String[] args) {
        ConsoleController controller = new ConsoleController();
        controller.startLoop();
    }
    
}
