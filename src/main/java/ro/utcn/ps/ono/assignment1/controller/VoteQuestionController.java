package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.dto.VoteAnswerDto;
import ro.utcn.ps.ono.assignment1.dto.VoteQuestionDto;
import ro.utcn.ps.ono.assignment1.service.VoteQuestionService;
import java.util.List;
import java.util.Objects;


@RestController
@CrossOrigin
@RequestMapping(value = "/voteQuestion")
@RequiredArgsConstructor
public class VoteQuestionController {
    private final VoteQuestionService voteQuestionService;

    @GetMapping()
    public List<VoteQuestionDto> findVoteQuestions() {
        return voteQuestionService.findAll();
    }

    @GetMapping(value="/{questionId}")
    public List<VoteQuestionDto> findByQuestionId(@PathVariable("questionId") Integer questionId){
        return voteQuestionService.findByQuestionId(questionId);
    }
    @GetMapping(value="/{questionId}/{userId}")
    public VoteQuestionDto findByQuestionIdAndUserId(@PathVariable("questionId") Integer questionId,@PathVariable("userId") Integer userId){
        List<VoteQuestionDto> voteQuestions = voteQuestionService.findByQuestionId(questionId);
        return voteQuestions.stream().filter(voteQuestionDto -> Objects.equals(voteQuestionDto.getUserId(), userId)).findFirst().orElse(new VoteQuestionDto());
    }

    @PostMapping(value="/create")
    public VoteQuestionDto insert(@RequestBody VoteQuestionDto voteQuestionDto){
        return voteQuestionService.insert(voteQuestionDto);
    }

    @PutMapping(value="/edit")
    public VoteQuestionDto update(@RequestBody VoteQuestionDto voteQuestionDto) {
        return voteQuestionService.insert(voteQuestionDto);
    }

    @DeleteMapping(value="/{voteQuestionId}")
    public void remove(@PathVariable("voteQuestionId") Integer voteQuestionId){
        voteQuestionService.remove(voteQuestionId);
    }
}

