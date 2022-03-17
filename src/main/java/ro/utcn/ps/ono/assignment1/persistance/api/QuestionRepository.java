package ro.utcn.ps.ono.assignment1.persistance.api;

import ro.utcn.ps.ono.assignment1.entity.Question;


import java.util.List;
import java.util.Optional;

public interface QuestionRepository {

    Question save(Question question);
    Optional<Question> findById(int id);
    void remove(Question question);
    Optional<Question> findByTitle(String title);
    List<Question> findAll();
}
