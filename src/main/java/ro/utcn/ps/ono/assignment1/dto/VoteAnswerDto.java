package ro.utcn.ps.ono.assignment1.dto;

import lombok.Data;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;

@Data
public class VoteAnswerDto {
    private Integer voteAnswerId;
    private Integer answerId;
    private Integer userId;
    private Boolean liked;


    public static VoteAnswerDto voteAnswerDtoFromVoteAnswer(VoteAnswer voteAnswer) {
        VoteAnswerDto voteAnswerDto = new VoteAnswerDto();
        voteAnswerDto.setVoteAnswerId(voteAnswer.getVoteAnswerId());
        voteAnswerDto.setAnswerId(voteAnswer.getAnswerId());
        voteAnswerDto.setUserId(voteAnswer.getUserId());
        voteAnswerDto.setLiked(voteAnswer.getLiked());
        return voteAnswerDto;
    }

}
