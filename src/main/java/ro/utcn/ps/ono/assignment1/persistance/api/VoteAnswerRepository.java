package ro.utcn.ps.ono.assignment1.persistance.api;

import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;

import java.util.List;
import java.util.Optional;

public interface VoteAnswerRepository {
    List<VoteAnswer> findAll();
    VoteAnswer save(VoteAnswer voteAnswer);
    void remove(VoteAnswer voteAnswer);
    Optional<VoteAnswer> findByVoteAnswerId(Integer userId);
    Optional<VoteAnswer> findByAnswerIdAndUserId(Integer userId,Integer questionId);
}
