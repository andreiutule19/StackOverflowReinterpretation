package ro.utcn.ps.ono.assignment1.persistance.data;

import org.springframework.data.repository.Repository;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;

public interface DataUserRepository extends UserRepository, Repository<User, Integer> {
    void delete(User user);

    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
    @Override
    default void remove(User user) {
        delete(user);
    }


}
