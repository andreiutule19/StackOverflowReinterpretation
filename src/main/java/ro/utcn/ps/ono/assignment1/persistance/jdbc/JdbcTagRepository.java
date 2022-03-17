package ro.utcn.ps.ono.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.persistance.api.TagRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
public class JdbcTagRepository implements TagRepository {
    private final JdbcTemplate template;

    @Override
    public Tag save(Tag tag) {
        if (tag.getId() != null) {
            update(tag);
        } else {
            Integer id = insert(tag);
            tag.setId(id);
        }

        return tag;
    }



    @Override
    public Optional<Tag> findById(int id) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE tag_id = ?",
                ((resultSet, i) -> new Tag(resultSet.getInt("tag_id"),
                        resultSet.getString("name"))),
                id);

        if (tags.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(tags.get(0));
        }
    }

    @Override
    public Optional<Tag> findByName(String name) {
        List<Tag> tags = template.query("SELECT * FROM tag WHERE name = ?",
                ((resultSet, i) -> new Tag(resultSet.getInt("tag_id"),
                        resultSet.getString("name"))),
                name);

        if (tags.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(tags.get(0));
        }
    }

    @Override
    public List<Integer> findByIdQT(int id) {
        List<Integer> tags = template.query("SELECT question_question_id FROM question_tags WHERE tags_tag_id = ?",
                ((resultSet, i) -> new Integer(resultSet.getInt("question_question_id"))),
                id);
        return tags;
    }


    private int insert(Tag tag) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("tag");
        insert.usingGeneratedKeyColumns("tag_id");
        Map<String, Object> data = new HashMap<>();
        data.put("name", tag.getName());
        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(Tag tag) {
        template.update("UPDATE tag SET name = ? WHERE tag_id = ?",
                tag.getName(),
                tag.getId());

    }

}
