package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.dto.AnswerDto;
import ro.utcn.ps.ono.assignment1.service.AnswerService;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@CrossOrigin
@RequestMapping(value = "")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping(value = "/answers")
    public List<AnswerDto> findAll() {
        return answerService.findAll();
    }

    @GetMapping(value = "/answers/{answerId}")
    public AnswerDto findAnswerById(@PathVariable("answerId") Integer answerId){
        return answerService.findById(answerId);
    }
    @GetMapping(value = "/answers/find/{questionId}")
    public List<AnswerDto> findAnswerByQuestionId(@PathVariable("questionId") Integer questionId){
        return answerService.findByQuestionId(questionId);
    }

    @GetMapping(value = "/answers/{questionId}/{answerId}")
    public AnswerDto findAnswerByAnswerIdAndQuestionId(@PathVariable("questionId") Integer questionId, @PathVariable("answerId") Integer answerId){

        List<AnswerDto> answersDto=answerService.findByQuestionId(questionId);
        return answersDto.stream().filter(answerDto -> Objects.equals(answerDto.getAnswerId(), answerId)).collect(Collectors.toList()).get(0);
    }

    @PostMapping(value ="/answers")
    public AnswerDto insert(@RequestBody AnswerDto answer){
        return answerService.insert(answer);
    }

    @PutMapping("/answers/edit")
    public AnswerDto update(@RequestBody AnswerDto answer) {
        return answerService.update(answer);
    }

    @DeleteMapping(value="/answers/remove/{answerId}")
    public void remove(@PathVariable("answerId") Integer answerId){
        answerService.remove(answerId);
    }
}
