package ro.utcn.ps.ono.assignment1.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class UserDto {
    private Integer userId;
    private String username;
    private String password;
    private List<String> answerList;
    private List<String> questionList;


    public static UserDto userDtoFromUser(User user) {
        UserDto userDto = new UserDto();
        userDto.setUserId(user.getUserId());
        userDto.setUsername(user.getUsername());
        userDto.setPassword(user.getPassword());

        if (!CollectionUtils.isEmpty(user.getAnswerList())) {
            userDto.setAnswerList(user.getAnswerList().stream()
                    .map(Answer::getBodyAnswer)
                    .collect(Collectors.toList()));
        } else {
            userDto.setAnswerList(new ArrayList<>());
        }


        if (!CollectionUtils.isEmpty(user.getQuestionList())) {
            userDto.setQuestionList(user.getQuestionList().stream()
                    .map(Question::getBody)
                    .collect(Collectors.toList()));
        } else {
            userDto.setQuestionList(new ArrayList<>());
        }



        return userDto;

    }
}




