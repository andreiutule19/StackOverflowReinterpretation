package ro.utcn.ps.ono.assignment1.persistance.memory;

import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.persistance.api.QuestionRepository;


import java.util.*;

public class InMemoryQuestionRepository implements QuestionRepository {
    // we want to be thread-safe, because this class will be a singleton (just one instance)
    // in order to not use synchronized methods, we can use thread-safe classes (ConcurrentHashMap and AtomicInteger)
    private volatile int currentId = 1;
    private final Map<Integer, Question> data = new HashMap<>();

    @Override
    public synchronized Question save(Question question) {
        if (question.getQuestionId() != null) {
            data.put(question.getQuestionId(), question);
        } else {
            question.setQuestionId(currentId++);
            data.put(question.getQuestionId(), question);
        }

        return question;
    }


    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(data.get(id));
    }


    @Override
    public synchronized void remove(Question question) {
        data.remove(question.getQuestionId());
    }

    @Override
    public Optional<Question> findByTitle(String title) {
       return Optional.ofNullable(data.get(title));
    }

    @Override
    public List<Question> findAll() {
        return new ArrayList<>(data.values());
    }
}
