package ro.utcn.ps.ono.assignment1.persistance.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;
import ro.utcn.ps.ono.assignment1.persistance.api.TagRepository;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;


@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "repository-type", havingValue = "DATA")
public class DataRepositoryFactory implements RepositoryFactory {
	private final DataQuestionRepository dataQuestionRepository;

	@Override
	public DataQuestionRepository createQuestionRepository() {
		return dataQuestionRepository;
	}

	@Override
	public UserRepository createUserRepository() {
		return null;
	}

	@Override
	public TagRepository createTagRepository() {
		return null;
	}

	@Override
	public AnswerRepository createAnswerRepository() {
		return null;
	}
}
