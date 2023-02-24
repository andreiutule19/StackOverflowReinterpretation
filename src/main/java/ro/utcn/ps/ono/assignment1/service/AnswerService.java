package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.dto.AnswerDto;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class AnswerService {
    private final RepositoryFactory factory;
    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public AnswerDto findById(Integer answerId) {
        return AnswerDto.answerDtoFromQuestion(factory.createAnswerRepository().
                findById(answerId).orElseThrow(QuestionNotFoundException::new));
    }


    private AnswerDto getAnswerDto(AnswerDto answerDto) {
        Answer answer = new Answer();
        answer.setAnswerId(answerDto.getAnswerId());
        answer.setBodyAnswer(answerDto.getBodyAnswer());
        answer.setDateTime(answerDto.getDateTime());
        answer.setUserId(answerDto.getUserId());
        answer.setVotes(new LinkedList<>());
        answer.setTotalVotes(answerDto.getTotalVotes());
        answer.setQuestionId(answerDto.getQuestionId());
        AnswerDto output = AnswerDto.answerDtoFromQuestion(factory.createAnswerRepository().save(answer));
       // eventPublisher.publishEvent(new AnswerCreatedEvent(output));
        return output;
    }

    @Transactional
    public AnswerDto insert(AnswerDto answerDto) {
        return getAnswerDto(answerDto);
    }

    @Transactional
    public AnswerDto update(AnswerDto answerDto) {
        return getAnswerDto(answerDto);
    }

    @Transactional
    public List<AnswerDto> findAll() {
        return factory.createAnswerRepository().findAll().stream().map(AnswerDto::answerDtoFromQuestion).collect(Collectors.toList());
    }


    @Transactional
    public void remove(int id) {
        Answer answer= factory.createAnswerRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
        factory.createAnswerRepository().remove(answer);
    }
    @Transactional
    public List<AnswerDto> findByQuestionId(Integer questionId){
        return factory.createAnswerRepository().findByQuestionId(questionId).stream().map(AnswerDto::answerDtoFromQuestion).collect(Collectors.toList());
    }

}
