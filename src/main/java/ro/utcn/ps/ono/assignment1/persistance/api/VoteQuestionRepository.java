package ro.utcn.ps.ono.assignment1.persistance.api;

import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;

import java.util.List;
import java.util.Optional;

public interface VoteQuestionRepository {
    List<VoteQuestion> findAll();
    VoteQuestion save(VoteQuestion voteQuestion);
    void remove(VoteQuestion voteQuestion);
    Optional<VoteQuestion> findByVoteQuestionId(Integer voteQuestionId);
    List<VoteQuestion> findByQuestionId(Integer questionId);
    Optional<VoteQuestion> findByQuestionIdAndUserId(Integer userId,Integer questionId);
}

