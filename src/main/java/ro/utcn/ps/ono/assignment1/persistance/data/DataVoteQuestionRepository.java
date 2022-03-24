package ro.utcn.ps.ono.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteQuestionRepository;

public interface DataVoteQuestionRepository extends VoteQuestionRepository, Repository<VoteQuestion, Integer> {

    void delete(VoteQuestion voteQuestion);

    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
    @Override
    default void remove(VoteQuestion voteQuestion) {
        delete(voteQuestion);
    }
}
