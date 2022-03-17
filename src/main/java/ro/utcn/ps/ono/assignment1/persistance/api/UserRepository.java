package ro.utcn.ps.ono.assignment1.persistance.api;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ro.utcn.ps.ono.assignment1.entity.User;


import java.util.List;
import java.util.Optional;

public interface UserRepository{

    Optional<User> findUserByUsername(String username);
    public List<User> list();
    Optional<User> findUserByUser_id(Integer id);
    User save(User user);

}