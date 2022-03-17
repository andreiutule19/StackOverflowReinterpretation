package ro.utcn.ps.ono.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;

import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.persistance.api.QuestionRepository;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
public class JdbcQuestionRepository implements QuestionRepository {

    /*
        template.query  - when we want to read from the database
        template.update - when we want to modify the database (eg: delete, insert, update)
        The Jdbc template is a helper class for doing JDBC operations (usually "templates" simplify common tasks)
        see https://spring.io/guides/gs/relational-data-access/
    */
    private final JdbcTemplate template;
    @Override
    public Question save(Question question) {
        if (question.getQuestionId() != null) {
            update(question);
        } else {
            Integer id = insert(question);
            question.setQuestionId(id);
        }
        org.springframework.jdbc.core.simple.SimpleJdbcInsert insertQ= new SimpleJdbcInsert(template);
        insertQ.setTableName("question_tags");
        Map<String, Object> questionTagData = new HashMap<>();
            for(Tag tag: question.getTags()){
                questionTagData.put("question_question_id", question.getQuestionId());
                questionTagData.put("tags_tag_id", tag.getId());
                insertQ.execute(questionTagData);
            }

        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        List<Question> questions = template.query("SELECT * FROM question WHERE question_id = ?",
                ((resultSet, i) -> new Question(resultSet.getInt("question_id"),
                                        resultSet.getString("title"),
                                        resultSet.getString("body"),
                        resultSet.getInt("author"),
                        resultSet.getDate("date"))),
                id);

        if (questions.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(questions.get(0));
        }

    }

    @Override
    public void remove(Question question) {
        template.update("DELETE FROM question WHERE question_id = ?", question.getQuestionId());
    }

    @Override
    public Optional<Question> findByTitle(String title) {
        List<Question> questions = template.query("SELECT * FROM question WHERE title = ?",
                ((resultSet, i) -> new Question(resultSet.getInt("question_id"),
                        resultSet.getString("title"),
                        resultSet.getString("body"),
                        resultSet.getInt("author"),
                        resultSet.getDate("date"))),
                title);

        if (questions.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(questions.get(0));
        }
    }

    @Override
    public List<Question> findAll() {
        return template.query("SELECT * FROM question  ORDER BY date ",
                (resultSet, i) -> new Question(resultSet.getInt("question_id"),
                                        resultSet.getString("title"),
                                        resultSet.getString("body"),
                                        resultSet.getInt("author"),
                        resultSet.getDate("date")
                                     ));
    }

    private int insert(Question question) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("question");
        insert.usingGeneratedKeyColumns("question_id");
        Map<String, Object> data = new HashMap<>();
        data.put("title", question.getTitle());
        data.put("body", question.getBody());
        data.put("author", question.getAuthor());
        data.put("date", question.getDate());
        return insert.executeAndReturnKey(data).intValue();
    }


    private void update(Question question) {
        template.update("UPDATE question SET title = ?, body = ?, date = ? WHERE question_id = ?",
                question.getTitle(),
                question.getBody(),
                question.getQuestionId());


    }
}
