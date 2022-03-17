package ro.utcn.ps.ono.assignment1.service;


import org.junit.jupiter.api.Test;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;
import ro.utcn.ps.ono.assignment1.persistance.memory.InMemoryRepositoryFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;


public class AnswerServiceTests {

    private static RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        User user = new User("Ana", "Maria");
        Date date = new Date();

        factory.createQuestionRepository().save(new Question(1, "Title1", "Body1", 1, date));
        factory.createQuestionRepository().save(new Question(2, "Title2", "Body2", 1, date));
        return factory;
    }

    @Test
    public void addQuestionTest() {
        RepositoryFactory factory = createMockedFactory();
        Date date = new Date();
        factory.createQuestionRepository().save(new Question(3, "Title3", "Body3", 1, date));
        Question question = new Question(3, "Title3", "Body3", 1, date);
        Question foundQuestion = factory.createQuestionRepository().findById(3).get();
        assertEquals(foundQuestion, question);
    }

    @Test
    public void findAllTest() {
        RepositoryFactory factory = createMockedFactory();

        assertEquals(factory.createQuestionRepository().findAll().size(), 2);
    }


    @Test
    public void findByTitleTest() {
        RepositoryFactory factory = createMockedFactory();
        Date date = new Date();
        factory.createQuestionRepository().save(new Question(4, "Title4", "Body3", 1, date));
        Question question = new Question(4, "Title4", "Body3", 1, date);
        Question foundQuestion = factory.createQuestionRepository().findByTitle("Title4").get();
        assertEquals(foundQuestion, question);
    }
}