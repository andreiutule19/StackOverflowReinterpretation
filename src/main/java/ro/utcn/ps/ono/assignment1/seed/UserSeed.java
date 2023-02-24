package ro.utcn.ps.ono.assignment1.seed;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;


@Component
@RequiredArgsConstructor
// The Order ensures that this CommandLineRunner is ran first (before the ConsoleController if you implement that one too)
@Order(Ordered.HIGHEST_PRECEDENCE)
public class UserSeed implements CommandLineRunner {

    private final RepositoryFactory factory;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        UserRepository repository = factory.createUserRepository();
        if (repository.findAll().isEmpty()) {
                User user = new User("Andrei",passwordEncoder.encode("Steau") );
                repository.save(user);

        }
    }
}

