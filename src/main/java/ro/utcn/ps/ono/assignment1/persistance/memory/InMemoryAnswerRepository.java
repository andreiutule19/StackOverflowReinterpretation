package ro.utcn.ps.ono.assignment1.persistance.memory;

import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class InMemoryAnswerRepository implements AnswerRepository {
    private volatile int currentId = 1;
    private final Map<Integer, Answer> data = new HashMap<>();

    @Override
    public Answer save(Answer answer) {
        if (answer.getAnswerId() != null) {
            data.put(answer.getAnswerId() , answer);
        } else {
            answer.setAnswerId(currentId++);
            data.put(answer.getAnswerId() ,answer);
        }

        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.empty();
    }

    @Override
    public void remove(Answer answer) {

    }

    @Override
    public List<Answer> findAll() {
        return null;
    }

    @Override
    public Answer findByAnswerIdAndMyQuestion(Integer answerId, Integer question) {
        return null;
    }

}
