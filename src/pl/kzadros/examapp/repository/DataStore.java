package pl.kzadros.examapp.repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import pl.kzadros.examapp.entities.Question;

/**
 *
 * @author kzadros
 */
public class DataStore {
    private List<Question> questions;
    
    public DataStore() {
        questions = new ArrayList<>();
        List<String> data = init();
        
        String[] row;
        for (int i=0 ; i<data.size() ; i++) {
            row = data.get(i).split(": ");
            if (row.length != 2) {
                System.out.println("Error: " + i);
                continue;
            }
            try {
                Question question = new Question(i, row[0], row[1]);
                questions.add(question);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
    
    private List<String> init() {
        List<String>data = new ArrayList<>();
        Collections.addAll(data, "Pierwszy król Polski to: Bolesław Chrobry.",
"Język programowania C stworzył: Dennis Ritchie.",
"Za pomocą storna poprawia się błędy, które: nie naruszają zasady podwójnego zapisu.",
"Zapis korygujący na tych samych kontach i po tych samych stronach, na których wystąpiło błędne księgowanie, ale za pomocą liczb ujemnych to: storno czerwone.",
"Czarny charakter w oryginalnej trylogii Gwiezdnych Wojen, który był dobrym jedi, to: Darth Vader.",
"Firma, do której należą programy Access, Excel i Word pakietu Office i system MS Windows, to: Microsoft.",
"Znany polski językoznawca: prof. Jan Miodek.",
"Znany historyczny król, wymieniony w Biblii, słynący z mądrości: Salomon.",
"Pierwszy prezydent USA to: George Washington.",
"Znany filmowy podróżnik: Indiana Jones.",
"Ostatni Piast będący królem Polski: Kazimierz Wielki."
        );
        return data;
    }

    public List<Question> getQuestions() {
        return questions;
    }
    
    public Question get(int number) {
        return this.questions.get(number);
    }
    
    public int size() {
        return questions.size();
    }
}
