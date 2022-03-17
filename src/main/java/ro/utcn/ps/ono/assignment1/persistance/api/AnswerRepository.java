package ro.utcn.ps.ono.assignment1.persistance.api;

import ro.utcn.ps.ono.assignment1.entity.Answer;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Answer save(Answer answer);
    Optional<Answer> findById(int id);
    void remove(Answer answer);
    List<Answer> findAll();
    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).

}
