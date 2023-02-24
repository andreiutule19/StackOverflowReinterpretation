package ro.utcn.ps.ono.assignment1.dto;

import lombok.Data;
import org.springframework.util.CollectionUtils;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Data
public class AnswerDto {
    private Integer answerId;
    private String bodyAnswer;
    private Date dateTime;
    private Integer questionId;
    private Integer userId;
    private List<Boolean> votes;
    private Integer totalVotes;

    public static AnswerDto answerDtoFromQuestion(Answer answer) {
        AnswerDto answerDto = new AnswerDto();
        answerDto.setAnswerId(answer.getAnswerId());
        answerDto.setBodyAnswer(answer.getBodyAnswer());
        answerDto.setDateTime(answer.getDateTime());
        answerDto.setTotalVotes(answer.getTotalVotes());
        answerDto.setUserId(answer.getUserId());
        answerDto.setQuestionId(answer.getQuestionId());
        if (!CollectionUtils.isEmpty(answer.getVotes())) {
            answerDto.setVotes(answer.getVotes().stream()
                    .map(VoteAnswer::getLiked)
                    .collect(Collectors.toList()));
        } else {
            answerDto.setVotes(new LinkedList<>());
        }
        return answerDto;

    }



}
