package ro.utcn.ps.ono.assignment1.persistance.memory;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.persistance.api.TagRepository;

import java.util.*;


public class InMemoryTagRepository implements TagRepository {
    private volatile int currentId = 1;
    private final Map<Integer, Tag> data = new HashMap<>();
    @Override
    public Tag save(Tag tag) {
        if (tag.getId() != null) {
            data.put(tag.getId(), tag);
        } else {
            tag.setId(currentId++);
            data.put(tag.getId(), tag);
        }

        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
       return Optional.ofNullable(data.get(id));
    }

    @Override
    public Optional<Tag> findByName(String  name) {
       return Optional.ofNullable(data.get(name));
    }

    @Override
    public List<Integer> findByIdQT(int id) {
        return new ArrayList<>();
    }

}
