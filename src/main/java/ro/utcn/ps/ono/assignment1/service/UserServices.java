package ro.utcn.ps.ono.assignment1.service;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;


import javax.transaction.Transactional;

@Getter
@Service
public class UserServices {
    private final RepositoryFactory factory;
    private boolean userLog = false;
    private String currentUser;
    public UserServices(RepositoryFactory factory) {
        this.factory = factory;
    }


    @Transactional
    public User findUserByUsername(String username) {
        return factory.createUserRepository().findUserByUsername(username).orElseThrow(QuestionNotFoundException::new);
    }
    @Transactional
    public User findUserByUser_id(Integer id) {
        return factory.createUserRepository().findUserByUser_id(id).orElseThrow(QuestionNotFoundException::new);
    }
    @Transactional
    public void login(String username, String password){
        Object user2 =factory.createUserRepository().findUserByUsername(username).orElseThrow(QuestionNotFoundException::new);
        System.out.println("AICIAAA  "  + user2);
        User user =factory.createUserRepository().findUserByUsername(username).orElseThrow(QuestionNotFoundException::new);

        if(user==null){
            throw new RuntimeException("User does not exist.");
        }else{
            System.out.println(user.getPassword());
            if(user.getPassword().equals(password)){
                System.out.println("Login succesfuly!");
                userLog = true;
                currentUser= username;
            }else{
                System.out.println("Parola gresita!");
            }
        }

    }
    @Transactional
    public void logout(){
        userLog=false;
    }


}
