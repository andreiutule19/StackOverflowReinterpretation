package ro.utcn.ps.ono.assignment1.persistance.memory;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.persistance.api.*;
@Component
@ConditionalOnProperty(name = "repository-type", havingValue = "MEMORY")
public class InMemoryRepositoryFactory implements RepositoryFactory {

    private final InMemoryQuestionRepository inMemoryQuestionRepository = new InMemoryQuestionRepository();
    private final InMemoryUserRepository inMemoryUserRepository = new InMemoryUserRepository();
    private final InMemoryTagRepository inMemoryTagRepository = new InMemoryTagRepository();
    @Override
    public QuestionRepository createQuestionRepository() {
        return inMemoryQuestionRepository;
    }
    @Override
    public UserRepository createUserRepository() {
        return inMemoryUserRepository;
    }

    @Override
    public TagRepository createTagRepository() {
        return inMemoryTagRepository;
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return null;
    }


}
