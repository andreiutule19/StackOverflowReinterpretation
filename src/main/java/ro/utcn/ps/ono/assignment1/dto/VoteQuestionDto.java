package ro.utcn.ps.ono.assignment1.dto;

import lombok.Data;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;

@Data
public class VoteQuestionDto {
    private Integer voteQuestionId;
    private Integer questionId;
    private Integer userId;
    private Boolean liked;

    public static VoteQuestionDto voteQuestionDtoFromVoteQuestion(VoteQuestion voteQuestion) {
        VoteQuestionDto voteQuestionDto = new VoteQuestionDto();
        voteQuestionDto.setVoteQuestionId(voteQuestion.getVoteQuestionId());
        voteQuestionDto.setQuestionId(voteQuestion.getQuestionId());
        voteQuestionDto.setUserId(voteQuestion.getUserId());
        voteQuestionDto.setLiked(voteQuestion.getLiked());
        System.out.println(voteQuestionDto.getQuestionId());
        return voteQuestionDto;
    }
}
