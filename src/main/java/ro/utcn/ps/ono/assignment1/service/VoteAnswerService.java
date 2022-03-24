package ro.utcn.ps.ono.assignment1.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Getter
@Service
@RequiredArgsConstructor
public class VoteAnswerService {
    private final RepositoryFactory factory;

    @Transactional
    public VoteAnswer insert(VoteAnswer voteQuestion) {
        return factory.createVoteAnswerRepository().save(voteQuestion);
    }

    @Transactional
    public List<VoteAnswer> findAll() {
        return factory.createVoteAnswerRepository().findAll();
    }



    @Transactional
    public VoteAnswer update(VoteAnswer voteQuestion) {
        return factory.createVoteAnswerRepository().save(voteQuestion);
    }

    @Transactional
    public void remove(int id) {
        VoteAnswer voteQuestion = factory.createVoteAnswerRepository().findByVoteAnswerId(id).orElseThrow(QuestionNotFoundException::new);
        factory.createVoteAnswerRepository().remove(voteQuestion);
    }
    @Transactional
    public VoteAnswer findByAnswerIdAndUserId(Integer userId, Integer answerId) {
        return factory.createVoteAnswerRepository().findByAnswerIdAndUserId(userId,answerId).orElse(null);
    }
}
