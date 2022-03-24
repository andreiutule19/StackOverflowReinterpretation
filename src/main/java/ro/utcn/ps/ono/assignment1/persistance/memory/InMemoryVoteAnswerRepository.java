package ro.utcn.ps.ono.assignment1.persistance.memory;

import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteAnswerRepository;

import java.util.*;

public class InMemoryVoteAnswerRepository implements VoteAnswerRepository {
    private volatile int currentId = 1;
    private final Map<Integer, VoteAnswer> data = new HashMap<>();
    @Override
    public List<VoteAnswer> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public VoteAnswer save(VoteAnswer voteAnswer) {
        if (voteAnswer.getVoteAnswerId() != null) {
            data.put(voteAnswer.getVoteAnswerId()  , voteAnswer);
        } else {
            voteAnswer.setVoteAnswerId(currentId++);
            data.put(voteAnswer.getVoteAnswerId()  ,voteAnswer);
        }

        return voteAnswer;
    }

    @Override
    public void remove(VoteAnswer voteAnswer) {
        data.remove(voteAnswer.getVoteAnswerId());
    }

    @Override
    public Optional<VoteAnswer> findByVoteAnswerId(Integer userId) {
        return null;
    }

    @Override
    public Optional<VoteAnswer> findByAnswerIdAndUserId(Integer userId, Integer questionId) {
        return Optional.empty();
    }
}
