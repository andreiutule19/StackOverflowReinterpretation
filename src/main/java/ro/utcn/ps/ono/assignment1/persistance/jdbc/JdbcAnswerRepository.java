package ro.utcn.ps.ono.assignment1.persistance.jdbc;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
public class JdbcAnswerRepository implements AnswerRepository {

    /*
       template.query  - when we want to read from the database
       template.update - when we want to modify the database (eg: delete, insert, update)
       The Jdbc template is a helper class for doing JDBC operations (usually "templates" simplify common tasks)
       see https://spring.io/guides/gs/relational-data-access/
   */
    private final JdbcTemplate template;
    @Override
    public Answer save(Answer answer) {
        if (answer.getAnswerId() != null) {
            update(answer);
        } else {
            Integer id = insert(answer);
            answer.setAnswerId(id);
        }

        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        List<Answer> questions = template.query("SELECT * FROM answers WHERE answer_id = ?",
                ((resultSet, i) -> new Answer(
                        resultSet.getInt("question_id"),
                        resultSet.getString("body_answer"),
                        resultSet.getDate("date"),
                        resultSet.getInt("my_question"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("up_vote"),
                        resultSet.getInt("down_vote"),
                        resultSet.getInt("total_votes")

                )),
                id);

        if (questions.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(questions.get(0));
        }

    }



    @Override
    public void remove(Answer answer) {
        template.update("DELETE FROM answers WHERE answer_id = ?", answer.getAnswerId());
    }



    @Override
    public List<Answer> findAll() {
        return template.query("SELECT * FROM answers  ORDER BY date ",
                (resultSet, i) -> new Answer(
                        resultSet.getInt("answer_id"),
                        resultSet.getString("body_answer"),
                        resultSet.getDate("date"),
                        resultSet.getInt("my_question"),
                        resultSet.getInt("user_id"),
                        resultSet.getInt("up_vote"),
                        resultSet.getInt("down_vote"),
                        resultSet.getInt("total_votes")
                ));
    }

    @Override
    public Answer findByAnswerIdAndMyQuestion(Integer answerId, Integer question) {
        return null;
    }


    private int insert(Answer answer) {
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("answers");
        insert.usingGeneratedKeyColumns("answer_id");
        Map<String, Object> data = new HashMap<>();
        data.put("body_answer", answer.getBodyAnswer());
        data.put("date", answer.getDateTime());
        data.put("my_question", answer.getMyQuestion());
        data.put("user_id", answer.getUserId());
        return insert.executeAndReturnKey(data).intValue();
    }


    private void update(Answer answer) {
        template.update("UPDATE answers SET body_answer = ?, date = ? WHERE answer_id = ?",
                answer.getBodyAnswer(),
                answer.getDateTime(),
                answer.getAnswerId()
                );


    }
}
