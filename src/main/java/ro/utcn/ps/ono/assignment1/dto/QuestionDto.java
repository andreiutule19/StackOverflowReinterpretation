package ro.utcn.ps.ono.assignment1.dto;

import lombok.Data;
import lombok.ToString;
import org.springframework.util.CollectionUtils;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;

import java.util.*;
import java.util.stream.Collectors;

@Data
@ToString
public class QuestionDto {
    private Integer questionId;
    private String title;
    private String body;
    private Date dateTime;
    private List<Boolean> votes ;
    private Integer totalVotes;
    private Integer userId;
    private Set<String> tags;
    private List<String> answers;


    public static QuestionDto questionDtoFromQuestion(Question question) {
        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionId(question.getQuestionId());
        questionDto.setTitle(question.getTitle());
        questionDto.setBody(question.getBody());
        questionDto.setDateTime(question.getDate());
        questionDto.setTotalVotes(question.getTotalVotes());
        questionDto.setUserId(question.getUserId());

        if (!CollectionUtils.isEmpty(question.getTags())) {
            questionDto.setTags(question.getTags().stream()
                    .map(Tag::getName)
                    .collect(Collectors.toSet()));
        } else {
            questionDto.setTags(new TreeSet<>());
        }

        if (!CollectionUtils.isEmpty(question.getAnswers())) {
            questionDto.setAnswers(question.getAnswers().stream()
                    .map(Answer::getBodyAnswer)
                    .collect(Collectors.toList()));
        } else {
            questionDto.setAnswers(new ArrayList<>());
        }

        if (!CollectionUtils.isEmpty(question.getVotes())) {
            questionDto.setVotes(question.getVotes().stream()
                    .map(VoteQuestion::getLiked)
                    .collect(Collectors.toList()));
        } else {
            questionDto.setAnswers(new LinkedList<>());
        }

        return questionDto;

    }




}
