package ro.utcn.ps.ono.assignment1.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.dto.VoteAnswerDto;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.service.VoteAnswerService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;


@RestController
@CrossOrigin
@RequestMapping(value = "/voteAnswer")
@RequiredArgsConstructor
public class VoteAnswerController {
    private final VoteAnswerService voteAnswerService;

    @GetMapping()
    public List<VoteAnswerDto> findAllVoteAnswer() {
        return voteAnswerService.findAll();
    }

    @GetMapping("/{answerId}")
    public List<VoteAnswerDto> findVoteAnswer(@PathVariable("answerId") Integer answerId) {
        return voteAnswerService.findByAnswerId(answerId);
    }
    @GetMapping("/{answerId}/{userId}")
    public VoteAnswerDto findVoteAnswerByAnswerIdAndUserId(@PathVariable("answerId") Integer answerId, @PathVariable("userId") Integer userId) {
        List<VoteAnswerDto> voteAnswerDos=voteAnswerService.findByAnswerId(answerId);
        return voteAnswerDos.stream().filter(voteAnswerDto ->Objects.equals(voteAnswerDto.getUserId(), userId)).findFirst().orElse(new VoteAnswerDto());
    }

    @PostMapping("/create")
    public VoteAnswerDto insert(@RequestBody VoteAnswerDto voteAnswerDto){
        return voteAnswerService.insert(voteAnswerDto);
    }

    @PutMapping()
    public VoteAnswerDto update(@RequestBody VoteAnswerDto voteAnswerDto) {
        return voteAnswerService.insert(voteAnswerDto);
    }

    @DeleteMapping(value="/{id}")
    public void remove(@PathVariable("id") Integer id){
        voteAnswerService.remove(id);
    }
}
