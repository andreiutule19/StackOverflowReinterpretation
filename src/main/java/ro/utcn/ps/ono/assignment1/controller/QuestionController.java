package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.service.QuestionService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping()
    public List<Question> findQuestions() {
        return questionService.findAll();
    }

    @GetMapping(value = "/{id}")
    public Question findQuestionById(@PathVariable("id") Integer id){
        return questionService.findById(id);
    }

    @GetMapping(value = "/title/{title}")
    public Question findQuestionByTitle(@PathVariable("title") String title){
        return questionService.findByTitle(title);
    }

    @PostMapping()
    public Question insert(@RequestBody Question question){
        return questionService.insert(question);
    }

    @PutMapping()
    public Question update(@RequestBody Question question) {
        return questionService.update(question);
    }

    @DeleteMapping(value="/{id}")
    public void remove(@PathVariable("id") Integer id){
        questionService.remove(id);
    }
}
