package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;


import java.util.List;

// The @Service is a specialized @Component (https://www.baeldung.com/spring-component-repository-service)
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RepositoryFactory factory;

    /*
        Transactional methods ensure that the code contained inside is run in a transaction,
        which is committed when the methods returns and is rolled-back when an exception is thrown
        see http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
    */
    @Transactional
    public Question findById(Integer id) {
        return factory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }


    @Transactional
    public Question findByTitle(String title) {
        return factory.createQuestionRepository().findByTitle(title).orElseThrow(QuestionNotFoundException::new);
    }

    @Transactional
    public List<Question> findAll() {
        return factory.createQuestionRepository().findAll();
    }

    @Transactional
    public Question insert(Question question) {
        return factory.createQuestionRepository().save(question);
    }

    @Transactional
    public Question update(Question question) {
        return factory.createQuestionRepository().save(question);
    }

    @Transactional
    public void remove(int id) {
        Question question = factory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
        factory.createQuestionRepository().remove(question);
    }


}
