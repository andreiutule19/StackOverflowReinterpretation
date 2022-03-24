package ro.utcn.ps.ono.assignment1.persistance.data;

import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;
import org.springframework.data.repository.Repository;

public interface DataAnswerRepository extends AnswerRepository, Repository<Answer, Integer> {

    void delete(Answer answer);

    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
    @Override
    default void remove(Answer answer) {
        delete(answer);
    }



}
