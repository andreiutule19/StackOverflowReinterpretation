package ro.utcn.ps.ono.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.persistance.api.*;



@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "repository-type", havingValue = "JDBC")
public class JdbcRepositoryFactory implements RepositoryFactory {

    private final JdbcTemplate template;

    @Override
    public QuestionRepository createQuestionRepository() {
        return new JdbcQuestionRepository(template);
    }

    @Override
    public UserRepository createUserRepository() {
        return new JdbcUserRepository(template);
    }

    @Override
    public TagRepository createTagRepository() {
        return new JdbcTagRepository(template);
    }

    @Override
    public AnswerRepository createAnswerRepository() {
        return new JdbcAnswerRepository(template);
    }

    @Override
    public VoteAnswerRepository createVoteAnswerRepository() {
        return null;
    }

    @Override
    public VoteQuestionRepository createVoteQuestionRepository() {
        return null;
    }


}
