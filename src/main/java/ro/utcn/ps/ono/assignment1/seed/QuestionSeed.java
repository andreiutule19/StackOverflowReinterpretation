package ro.utcn.ps.ono.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.persistance.api.QuestionRepository;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;


@Component
@RequiredArgsConstructor
// The Order ensures that this CommandLineRunner is ran first (before the ConsoleController if you implement that one too)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class QuestionSeed implements CommandLineRunner {

    private final RepositoryFactory factory;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        QuestionRepository repository = factory.createQuestionRepository();

        int COUNT = 110;

//        if (repository.findAll().isEmpty()) {
//            for (int i = 100; i < COUNT; i++) {
//                Question question = new Question("Title" + i, "Body" + i);
//                repository.save(question);
//            }
//        }
    }
}

