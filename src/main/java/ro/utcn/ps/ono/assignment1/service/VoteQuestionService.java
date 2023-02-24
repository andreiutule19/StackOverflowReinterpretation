package ro.utcn.ps.ono.assignment1.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.utcn.ps.ono.assignment1.dto.VoteQuestionDto;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Service
@RequiredArgsConstructor
public class VoteQuestionService {
    private final RepositoryFactory factory;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public VoteQuestionDto insert(VoteQuestionDto voteQuestion) {
        return getVoteQuestionDto(voteQuestion);
    }

    @Transactional
    public List<VoteQuestionDto> findAll() {
        return factory.createVoteQuestionRepository().findAll().stream().map(VoteQuestionDto::voteQuestionDtoFromVoteQuestion).
                collect(Collectors.toList());
    }


    private VoteQuestionDto getVoteQuestionDto(VoteQuestionDto voteQuestionDto) {
        VoteQuestion voteQuestion = new VoteQuestion();
        voteQuestion.setUserId(voteQuestionDto.getUserId());
        voteQuestion.setQuestionId(voteQuestionDto.getQuestionId());
        voteQuestion.setVoteQuestionId(voteQuestionDto.getVoteQuestionId());
        voteQuestion.setLiked(voteQuestionDto.getLiked());
        System.out.println(voteQuestion.getQuestionId());
        VoteQuestionDto output = VoteQuestionDto.voteQuestionDtoFromVoteQuestion(factory.createVoteQuestionRepository().save(voteQuestion));
        return output;

    }

    @Transactional
    public List<VoteQuestionDto> findByQuestionId(Integer questionId) {
        return factory.createVoteQuestionRepository().findByQuestionId(questionId).stream().map(VoteQuestionDto::voteQuestionDtoFromVoteQuestion).collect(Collectors.toList());
    }


    @Transactional
    public void remove(int voteQuestionId) {
        VoteQuestion voteQuestion = factory.createVoteQuestionRepository().findByVoteQuestionId(voteQuestionId).orElseThrow(QuestionNotFoundException::new);
        factory.createVoteQuestionRepository().remove(voteQuestion);
    }
    @Transactional
    public VoteQuestionDto findByQuestionIdAndUserId(Integer userId, Integer questionId) {
        return VoteQuestionDto.voteQuestionDtoFromVoteQuestion(factory.createVoteQuestionRepository().findByQuestionIdAndUserId(userId,questionId).orElse(null));
    }
}
