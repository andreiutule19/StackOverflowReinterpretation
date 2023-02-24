package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.dto.QuestionDto;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;
import ro.utcn.ps.ono.assignment1.persistance.event.QuestionCreatedEvent;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

// The @Service is a specialized @Component (https://www.baeldung.com/spring-component-repository-service)
@Service
@RequiredArgsConstructor
public class QuestionService {
    private final RepositoryFactory factory;
    private final ApplicationEventPublisher eventPublisher;
    /*
        Transactional methods ensure that the code contained inside is run in a transaction,
        which is committed when the methods returns and is rolled-back when an exception is thrown
        see http://www.codingpedia.org/jhadesdev/how-does-spring-transactional-really-work/
    */
    @Transactional
    public QuestionDto findById(Integer id) {
        return QuestionDto.questionDtoFromQuestion(factory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new));
    }


    @Transactional
    public QuestionDto findByTitle(String title) {
        return QuestionDto.questionDtoFromQuestion(factory.createQuestionRepository().findByTitle(title).orElseThrow(QuestionNotFoundException::new));
    }

    @Transactional
    public List<QuestionDto> findAll() {
        return factory.createQuestionRepository().findAll().stream().map(QuestionDto::questionDtoFromQuestion).collect(Collectors.toList());
    }

    @Transactional
    public QuestionDto insert(QuestionDto questionDto) {
        return getQuestionDto(questionDto);
    }


    @Transactional
    public QuestionDto update(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
        question.setTitle(questionDto.getTitle());
        question.setBody(questionDto.getBody());
        question.setDate(questionDto.getDateTime());
        question.setUserId(questionDto.getUserId());
        question.setTotalVotes(questionDto.getTotalVotes());
        QuestionDto output = QuestionDto.questionDtoFromQuestion(factory.createQuestionRepository().save(question));
        return output;
    }

    private QuestionDto getQuestionDto(QuestionDto questionDto) {
        Question question = new Question();
        question.setQuestionId(questionDto.getQuestionId());
        question.setTitle(questionDto.getTitle());
        question.setBody(questionDto.getBody());
        question.setDate(questionDto.getDateTime());
        question.setUserId(questionDto.getUserId());
        question.setTotalVotes(0);
        question.setVotes(new LinkedList<>());
        question.setAnswers(new ArrayList<>());
        List<Tag> tags=new ArrayList<>();
        System.out.println(questionDto.getTags());
        for(String s : questionDto.getTags()){
            Tag tagy =new Tag();
            tagy.setName(s);
            tags.add(tagy);
        }
        question.setTags(tags);
        QuestionDto output = QuestionDto.questionDtoFromQuestion(factory.createQuestionRepository().save(question));
        eventPublisher.publishEvent(new QuestionCreatedEvent(output));
        return output;
    }

    @Transactional
    public void remove(int id) {
        Question question = factory.createQuestionRepository().findById(id).orElseThrow(QuestionNotFoundException::new);
        factory.createQuestionRepository().remove(question);
    }


}
