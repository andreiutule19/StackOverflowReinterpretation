package ro.utcn.ps.ono.assignment1.persistance.memory;

import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;


import java.util.*;

public class InMemoryUserRepository implements UserRepository {
    private volatile int currentId = 1;
    private final Map<Integer, User> data = new HashMap<>();
    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(data.get(username));
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(data.values());
    }

    @Override
    public Optional<User> findUserByUserId(Integer id) {
        return Optional.ofNullable(data.get(id));
    }

    @Override
    public synchronized User save(User user) {
        if(user.getUserId() != null) {
            data.put(user.getUserId(), user);
        } else {
            user.setUserId(currentId++);
            data.put(user.getUserId(), user);
        }
        return user;
    }

    @Override
    public void remove(User user) {

    }


}
