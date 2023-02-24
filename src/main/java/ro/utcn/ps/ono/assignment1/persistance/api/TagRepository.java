package ro.utcn.ps.ono.assignment1.persistance.api;

import org.springframework.data.jdbc.repository.query.Query;

import ro.utcn.ps.ono.assignment1.entity.Tag;


import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag);
    Optional<Tag> findById(int id);
    Optional<Tag> findByName(String name);
    List<Integer> findTagByQuestion_question_id(int question_question_id);

    List<Tag> findAll();
    void remove(Tag tag);
}
