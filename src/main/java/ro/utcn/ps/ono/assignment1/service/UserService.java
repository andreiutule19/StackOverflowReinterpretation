package ro.utcn.ps.ono.assignment1.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.utcn.ps.ono.assignment1.dto.UserDto;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;



import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor
public class UserService {
    private final RepositoryFactory factory;
    private boolean userLog = false;
    private Integer authorId=null;
    private String currentUser;
    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public UserDto findUserByUsername(String username) {
        return UserDto.userDtoFromUser(factory.createUserRepository().findUserByUsername(username).orElseThrow(QuestionNotFoundException::new));
    }
    @Transactional
    public UserDto findUserByUser_id(Integer id) {
        return UserDto.userDtoFromUser(factory.createUserRepository().findUserByUserId(id).orElseThrow(QuestionNotFoundException::new));
    }
    @Transactional
    public void login(String username, String password){
        Object user2 =factory.createUserRepository().findUserByUsername(username).orElseThrow(QuestionNotFoundException::new);
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
       authorId =user.getUserId();

    }
    private UserDto getUserDto(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.getUserId());
        user.setPassword(userDto.getPassword());
        user.setUsername(userDto.getPassword());
        UserDto output = UserDto.userDtoFromUser(factory.createUserRepository().save(user));
        //eventPublisher.publishEvent(new UserCreatedEvent(output));
        return output;

    }
    @Transactional
    public List<UserDto> findAll(){
        return factory.createUserRepository().findAll().stream().map(UserDto::userDtoFromUser).collect(Collectors.toList());

    }

    @Transactional
    public UserDto insert(UserDto userDto) {
        return getUserDto(userDto);
    }
    @Transactional
    public void logout(){
        userLog=false;
        authorId = null;
    }


}
