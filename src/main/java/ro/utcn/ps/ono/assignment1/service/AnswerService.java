package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;

import java.util.List;

@Service
@RequiredArgsConstructor
@RequestMapping(value = "/comm")
public class AnswerService {
    private final RepositoryFactory factory;

    @Transactional
    public Answer findById(Integer id) {
        return factory.createAnswerRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Transactional
    public Answer insert(Answer answer) {
        return factory.createAnswerRepository().save(answer);
    }

    @Transactional
    public Answer update(Answer answer) {
        return factory.createAnswerRepository().save(answer);
    }

    @Transactional
    public List<Answer> findAll() {
        return factory.createAnswerRepository().findAll();
    }


    @Transactional
    public void remove(int id) {
        Answer answer= factory.createAnswerRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
        factory.createAnswerRepository().remove(answer);
    }
    @Transactional
    public Answer findByIdAndQuestion(int id, Integer question){
        return factory.createAnswerRepository().findByAnswerIdAndMyQuestion(id,question);
    }

}
