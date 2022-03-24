package ro.utcn.ps.ono.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteAnswerRepository;

public interface DataVoteAnswerRepository extends VoteAnswerRepository, Repository<VoteAnswer, Integer> {
    void delete(VoteAnswer voteAnswer);

    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
    @Override
    default void remove(VoteAnswer voteAnswer) {
        delete(voteAnswer);
    }
}
