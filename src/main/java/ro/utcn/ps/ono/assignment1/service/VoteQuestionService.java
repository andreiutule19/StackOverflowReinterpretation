package ro.utcn.ps.ono.assignment1.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class VoteQuestionService {
    private final RepositoryFactory factory;


    @Transactional
    public VoteQuestion insert(VoteQuestion voteQuestion) {
        return factory.createVoteQuestionRepository().save(voteQuestion);
    }

    @Transactional
    public List<VoteQuestion> findAll() {
        return factory.createVoteQuestionRepository().findAll();
    }



    @Transactional
    public VoteQuestion update(VoteQuestion voteQuestion) {
        return factory.createVoteQuestionRepository().save(voteQuestion);
    }

    @Transactional
    public void remove(int id) {
        VoteQuestion voteQuestion = factory.createVoteQuestionRepository().findByVoteQuestionId(id).orElseThrow(QuestionNotFoundException::new);
        factory.createVoteQuestionRepository().remove(voteQuestion);
    }
    @Transactional
    public VoteQuestion findByQuestionIdAndUserId(Integer userId, Integer questionId) {
        return factory.createVoteQuestionRepository().findByQuestionIdAndUserId(userId,questionId).orElse(null);
    }
}
