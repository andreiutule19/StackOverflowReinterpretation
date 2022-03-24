package ro.utcn.ps.ono.assignment1.persistance.memory;

import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteQuestionRepository;

import java.util.*;

public class InMemoryVoteQuestionRepository implements VoteQuestionRepository {
    private volatile int currentId = 1;
    private final Map<Integer, VoteQuestion> data = new HashMap<>();
    @Override
    public List<VoteQuestion> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public VoteQuestion save(VoteQuestion voteQuestion) {
        if (voteQuestion.getVoteQuestionId() != null) {
            data.put(voteQuestion.getVoteQuestionId()  , voteQuestion);
        } else {
            voteQuestion.setVoteQuestionId(currentId++);
            data.put(voteQuestion.getVoteQuestionId()  ,voteQuestion);
        }

        return voteQuestion;
    }

    @Override
    public void remove(VoteQuestion voteQuestion) {
        data.remove(voteQuestion.getVoteQuestionId());

    }

    @Override
    public Optional<VoteQuestion> findByVoteQuestionId(Integer userId) {
        return Optional.empty();
    }

    @Override
    public Optional<VoteQuestion> findByQuestionIdAndUserId(Integer userId, Integer questionId) {
        return Optional.empty();
    }

}
