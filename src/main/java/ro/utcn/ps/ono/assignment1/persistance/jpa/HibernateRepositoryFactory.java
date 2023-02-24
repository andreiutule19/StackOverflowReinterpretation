package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.persistance.api.*;



import javax.persistence.EntityManager;

@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "repository-type", havingValue = "JPA")
public class HibernateRepositoryFactory implements RepositoryFactory {
    private final EntityManager entityManager;

    @Override
    public QuestionRepository createQuestionRepository() {
        return new HibernateQuestionRepository(entityManager);
    }

    @Override
    public UserRepository createUserRepository() {
        return new HibernateUserRepository(entityManager);
    }

    @Override
    public TagRepository createTagRepository() {
        return new HibernateTagRepository(entityManager);
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return new HibernateAnswerRepository(entityManager);
    }

    @Override
    public VoteAnswerRepository createVoteAnswerRepository() {
        return new HibernateVoteAnswerRepository(entityManager);
    }

    @Override
    public VoteQuestionRepository createVoteQuestionRepository() {
        return new HibernateVoteQuestionRepository(entityManager);
    }
}
