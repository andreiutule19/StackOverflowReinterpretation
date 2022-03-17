package ro.utcn.ps.ono.assignment1.persistance.api;

import ro.utcn.ps.ono.assignment1.entity.Tag;


import java.util.List;
import java.util.Optional;

public interface TagRepository {
    Tag save(Tag tag);
    Optional<Tag> findById(int id);
    Optional<Tag> findByName(String name);
    List<Integer> findByIdQT(int id);
}
