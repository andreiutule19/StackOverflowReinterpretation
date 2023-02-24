package ro.utcn.ps.ono.assignment1.service;


import org.junit.jupiter.api.Test;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;
import ro.utcn.ps.ono.assignment1.persistance.memory.InMemoryRepositoryFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTests {
    private RepositoryFactory createMockedFactory() {
        RepositoryFactory factory = new InMemoryRepositoryFactory();
        factory.createUserRepository().save(new User(1,"username1", "password1"));
        factory.createUserRepository().save(new User( 2,"username2", "password2"));
        return factory;
    }
    @Test
    public void findUserById(){
        RepositoryFactory factory= createMockedFactory();
        User user1=new User(3,"username","password");
        factory.createUserRepository().save(user1);
        User foundUser=factory.createUserRepository().findUserByUserId(3).get();
        assertEquals(foundUser, user1);
    }
    @Test
    public void findUserByUsername(){
        RepositoryFactory factory= createMockedFactory();
        User user1=new User(3,"username","password");
        factory.createUserRepository().save(user1);
        User foundUser= factory.createUserRepository().findUserByUsername("username").get();

        assertEquals(foundUser, user1);
    }
}