package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;

@Service
@RequiredArgsConstructor
public class AnswerService {
    private final RepositoryFactory factory;

    @Transactional
    public Answer findById(Integer id) {
        return factory.createAnswerRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }

}
