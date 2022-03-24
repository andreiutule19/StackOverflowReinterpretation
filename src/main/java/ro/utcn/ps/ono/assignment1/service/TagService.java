package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;


import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
   private final RepositoryFactory factory;

    @Transactional
    public Tag findById(Integer id) {
        return factory.createTagRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
    }

    @Transactional
    public List<Integer> findByIdQT(Integer id) {
        return factory.createTagRepository().findTagByQuestion_question_id(id);
    }

    @Transactional
    public Tag findByName(String name) {
        return factory.createTagRepository().findByName(name).orElse(null);
    }

    @Transactional
    public Tag insert(Tag tag) {
        return factory.createTagRepository().save(tag);
    }

    @Transactional
    public Tag update(Tag tag) {
        return factory.createTagRepository().save(tag);
    }

}
