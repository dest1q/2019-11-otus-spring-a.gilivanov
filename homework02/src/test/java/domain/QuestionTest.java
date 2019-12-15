package domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homework.domain.Question;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс QuestionTest")
public class QuestionTest {
    @DisplayName("Корректно создается конструктором")
    @Test
    void shouldHaveCorrectConstructor(){
        Question question = new Question("7x7?", "49");
        assertEquals("7x7?", question.getQuestion());
        assertEquals("49", question.getAnswer());
    }
}
