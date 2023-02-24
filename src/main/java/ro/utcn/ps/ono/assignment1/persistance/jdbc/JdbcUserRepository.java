package ro.utcn.ps.ono.assignment1.persistance.jdbc;

import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
@RequiredArgsConstructor
public class JdbcUserRepository implements UserRepository {
    private final JdbcTemplate template;

    private int insert(User user) {
        // we use the SimpleJdbcInsert to easily retrieve the generated ID
        SimpleJdbcInsert insert = new SimpleJdbcInsert(template);
        insert.setTableName("users");
        insert.usingGeneratedKeyColumns("user_id");

        // String for the column's name
        // Object for the column's inserted value
        Map<String, Object> data = new HashMap<>();
        data.put("username", user.getUsername());
        data.put("password", user.getPassword());

        return insert.executeAndReturnKey(data).intValue();
    }

    private void update(User user) {
        template.update("UPDATE users SET username = ?, password = ? WHERE user_id = ?",
                user.getUsername(),
                user.getPassword(),
                user.getUserId());
    }


    @Override
    public Optional<User> findUserByUsername(String username) {
        List<User> users= template.query("SELECT * FROM users WHERE username = ?",
                ((resultSet, i) -> new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")

                )),username);
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users.get(0));
        }
    }
    @Override
    public List<User> findAll() {
        return template.query("SELECT * FROM users",
                (resultSet, i) -> new User(resultSet.getInt("user_id"),
                        resultSet.getString("username"),
                        resultSet.getString("password")

                ));
    }


    @Override
    public Optional<User> findUserByUserId(Integer id) {
        List<User> users= template.query("SELECT * FROM users WHERE user_id = ?",
                ((resultSet, i) -> new User(
                        resultSet.getInt("user_id"),
                        resultSet.getString("password"),
                        resultSet.getString("username")
                )),id);
        if (users.isEmpty()) {
            return Optional.empty();
        } else {
            return Optional.of(users.get(0));
        }
    }

    @Override
    public User save(User user) {
        return null;
    }



    @Override
    public void remove(User user) {

    }


}
