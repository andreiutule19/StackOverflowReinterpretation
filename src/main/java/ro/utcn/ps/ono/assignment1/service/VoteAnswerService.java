package ro.utcn.ps.ono.assignment1.service;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import ro.utcn.ps.ono.assignment1.dto.VoteAnswerDto;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;


import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;


@Getter
@Service
@RequiredArgsConstructor
public class VoteAnswerService {
    private final RepositoryFactory factory;
    private final ApplicationEventPublisher eventPublisher;
    @Transactional
    public VoteAnswerDto insert(VoteAnswerDto voteQuestion) {
        return getVoteAnswerDto(voteQuestion);
    }

    @Transactional
    public List<VoteAnswerDto> findAll() {
        return factory.createVoteAnswerRepository().
                findAll().stream().map(VoteAnswerDto::voteAnswerDtoFromVoteAnswer).collect(Collectors.toList());
    }


    private VoteAnswerDto getVoteAnswerDto(VoteAnswerDto voteAnswerDto) {
        VoteAnswer voteAnswer = new VoteAnswer();
        voteAnswer.setUserId(voteAnswerDto.getUserId());
        voteAnswer.setVoteAnswerId(voteAnswerDto.getVoteAnswerId());
        voteAnswer.setAnswerId(voteAnswerDto.getAnswerId());
        voteAnswer.setLiked(voteAnswerDto.getLiked());
        VoteAnswerDto output = VoteAnswerDto.voteAnswerDtoFromVoteAnswer(factory.createVoteAnswerRepository().save(voteAnswer));
       // eventPublisher.publishEvent(new VoteAnswerCreatedEvent(output));
        return output;

    }


    @Transactional
    public VoteAnswerDto findByVoteAnswerId(Integer voteAnswerId) {
        return VoteAnswerDto.voteAnswerDtoFromVoteAnswer(factory.createVoteAnswerRepository().findByVoteAnswerId(voteAnswerId).orElseThrow(QuestionNotFoundException::new));
    }
    @Transactional
    public List<VoteAnswerDto> findByAnswerId(Integer answerId) {
        return factory.createVoteAnswerRepository().findByAnswerId(answerId).stream().map(VoteAnswerDto::voteAnswerDtoFromVoteAnswer).collect(Collectors.toList());
    }
    @Transactional
    public void remove(int id) {
        VoteAnswer voteQuestion = factory.createVoteAnswerRepository().findByVoteAnswerId(id).orElseThrow(QuestionNotFoundException::new);
        factory.createVoteAnswerRepository().remove(voteQuestion);
    }
    @Transactional
    public VoteAnswerDto findByAnswerIdAndUserId(Integer userId, Integer answerId) {
        return VoteAnswerDto.voteAnswerDtoFromVoteAnswer(factory.createVoteAnswerRepository().findByAnswerIdAndUserId(userId,answerId).orElse(null));
    }
}
