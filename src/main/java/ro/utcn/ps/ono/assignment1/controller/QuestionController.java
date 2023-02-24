package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.dto.QuestionDto;
import ro.utcn.ps.ono.assignment1.persistance.event.BaseEvent;
import ro.utcn.ps.ono.assignment1.service.QuestionService;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/questions")
@RequiredArgsConstructor
@Slf4j
public class QuestionController {
    private final QuestionService questionService;
    private final SimpMessagingTemplate messagingTemplate;
    @GetMapping()
    public List<QuestionDto> findQuestions() {
        return questionService.findAll();
    }

    @GetMapping(value = "/{id}")
    public QuestionDto findQuestionById(@PathVariable("id") Integer id){
        return questionService.findById(id);
    }

    @GetMapping(value = "/title/{title}")
    public QuestionDto findQuestionByTitle(@PathVariable("title") String title){
        return questionService.findByTitle(title);
    }

    @PostMapping()
    public QuestionDto insert(@RequestBody QuestionDto question){
        return questionService.insert(question);
    }

    @PutMapping(value ="/update")
    public QuestionDto update(@RequestBody QuestionDto question) {
        return questionService.update(question);
    }

    @DeleteMapping(value="/{id}")
    public void remove(@PathVariable("id") Integer id){
        questionService.remove(id);
    }


    @EventListener(BaseEvent.class)
    public void handleEvent(BaseEvent event) {
        log.info("Got an event: {}.", event);
        messagingTemplate.convertAndSend("/topic/events", event);
    }
}

