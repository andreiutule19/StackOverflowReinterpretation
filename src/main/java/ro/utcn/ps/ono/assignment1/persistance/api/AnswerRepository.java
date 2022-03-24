package ro.utcn.ps.ono.assignment1.persistance.api;

import ro.utcn.ps.ono.assignment1.entity.Answer;


import java.util.List;
import java.util.Optional;

public interface AnswerRepository {
    Answer save(Answer answer);
    Optional<Answer> findById(int id);
    void remove(Answer answer);
    List<Answer> findAll();
    Answer findByAnswerIdAndMyQuestion(Integer answerId, Integer question);
   // void increaseDownVote();
  //  void increaseDownVote();

}
