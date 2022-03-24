package ro.utcn.ps.ono.assignment1.persistance.api;
import org.springframework.data.jdbc.repository.query.Query;
import ro.utcn.ps.ono.assignment1.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository{

    Optional<User> findUserByUsername(String username);
    List<User> findAll();
    Optional<User> findUserByUserId(Integer id);
    User save(User user);
    void remove(User user);
}