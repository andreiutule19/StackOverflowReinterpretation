package ro.utcn.ps.ono.assignment1.persistance.data;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.persistance.api.*;



@RequiredArgsConstructor
@Component
@ConditionalOnProperty(name = "repository-type", havingValue = "DATA")
public class DataRepositoryFactory implements RepositoryFactory {
//	private final DataQuestionRepository dataQuestionRepository;
	private final DataAnswerRepository dataAnswerRepository;
	private final DataUserRepository dataUserRepository;
	private final DataVoteAnswerRepository dataVoteAnswerRepository;
	private final DataVoteQuestionRepository dataVoteQuestionRepository;
//	@Override
//	public DataQuestionRepository createQuestionRepository() {
//		return dataQuestionRepository;
//	}

	@Override
	public QuestionRepository createQuestionRepository() {
		return null;
	}

	@Override
	public UserRepository createUserRepository() {
		return dataUserRepository;
	}

	@Override
	public TagRepository createTagRepository() {
		return null;
	}

	@Override
	public AnswerRepository createAnswerRepository() {
		return null;
	}

	@Override
	public VoteAnswerRepository createVoteAnswerRepository() {
		return dataVoteAnswerRepository;
	}

	@Override
	public VoteQuestionRepository createVoteQuestionRepository() {
		return dataVoteQuestionRepository;
	}
}
