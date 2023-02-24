package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.dto.AnswerDto;
import ro.utcn.ps.ono.assignment1.dto.QuestionDto;
import ro.utcn.ps.ono.assignment1.dto.TagDto;
import ro.utcn.ps.ono.assignment1.entity.*;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.service.*;

import java.util.*;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
// Command line runners are executed by Spring after the initialization of the app has been done
// https://www.baeldung.com/spring-boot-console-app
public class ConsoleController //implements CommandLineRunner
{
    private final Scanner scanner = new Scanner(System.in);
    private final QuestionService questionService;
    private final UserService userService;
    private final TagService tagService;
    private final AnswerService answerService;
    private final VoteAnswerService voteAnswerService;
    private final VoteQuestionService voteQuestionService;

//    @Override
//    public void run(String... args) {
//        print("Welcome to my awesome reinterpretation of StackOverflow.");
//        boolean done = false;
//        while (!done) {
//            print("Enter a command: ");
//            String command = scanner.next().trim();
//            try {
//                done = handleCommand(command);
//            } catch (QuestionNotFoundException questionNotFoundException) {
//                print("Not found!");
//            }
//        }
//    }

    private boolean handleCommand(String command) {
        switch (command) {
            case "vote":
                //handleVote();
                return false;
            case "addUser":
                handleInsertUser();
                return false;
            case "edit":
                handleEditAnswer();;
                return false;
            case "list":
                handleList();
                return false;
            case "insert":
                handleInsert();
                return false;
            case "login":
                handleLogin();
                return false;
            case "logout":
                handleLogout();
                return false;
            case "remove":
                handleRemove();
                return false;
            case "search":
                handleSearch();
                return false;
            case "filter":
                handleFilter();
                return false;
            case "exit":
                return true;
            case "comment":
                handleAddAnswer();
                return false;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }


    private void handleList() {
        questionService.findAll().forEach(question -> print(question.toString()));
    }
    private void handleAnswers() {
        answerService.findAll().forEach(question -> print(question.toString()));
    }

//    private void handleVote(){
//        if(userService.isUserLog()) {
//            print("Choose what do u want to like :");
//            String choice = scanner.next().trim();
//            if(choice.equals("question")) {
//                print("Enter the question title :");
//                String title = scanner.next().trim();
//                QuestionDto question = questionService.findByTitle(title);
//                System.out.println(question.getId());
//                if(voteQuestionService.findByQuestionIdAndUserId(userService.getAuthorId(), question.getId()) != null){
//                    print("U already voted this question");
//                    handleVote();
//                }
//                if(question.getAuthor().equals(userService.getAuthorId())){
//                    print("U cant vote u re own question !!");
//                    handleVote();
//                }
//                print("Enter ur vote type:");
//                String vote = scanner.next().trim();
//                VoteQuestion voteQuestion=new VoteQuestion();
//                if (vote.equals("like")) {
//
//                    question.setUpVote(question.getUpVote() + 1);
//                    question.setTotalVotes(question.getUpVote()-question.getDownVote());
//                    questionService.insert(question);
//                    voteQuestion.setQuestionId(question.getQuestionId());
//                    voteQuestion.setUserId(userService.getAuthorId());
//                    voteQuestionService.insert(voteQuestion);
//                    System.out.println(voteQuestion);
//
//                } else if (vote.equals("dislike")) {
//
//                    question.setDownVote(question.getDownVote() + 1);
//                    question.setTotalVotes(question.getUpVote()-question.getDownVote());
//                    questionService.insert(question);
//                    voteQuestion.setVoteQuestionId(question.getQuestionId());
//                    voteQuestion.setUserId(userService.getAuthorId());
//                    voteQuestionService.insert(voteQuestion);
//                    System.out.println(voteQuestion);
//
//                } else {
//                    print("Try again !");
//                    handleVote();
//                }
//            }
//
//            else if(choice.equals("answer")){
//                print("Enter the ID of answer :");
//                Integer id= Integer.parseInt(scanner.next().trim());
//                AnswerDto answer =answerService.findById(id);
//                if(voteAnswerService.findByAnswerIdAndUserId(userService.getAuthorId(),answer.getAnswerId()) != null){
//                    print("U already voted this answer");
//                    handleVote();
//                }
//                if(answer.getUserId().equals(userService.getAuthorId())){
//                    print("U cant vote u re own question !!");
//                    handleVote();
//                }
//                print("Enter ur vote type:");
//                String vote = scanner.next().trim();
//                VoteAnswer voteAnswer=new VoteAnswer();
//                if (vote.equals("like")) {
//                    answer.setUpVote(answer.getUpVote() + 1);
//                    answer.setTotalVotes(answer.getUpVote()- answer.getDownVote());
//                    answerService.insert(answer);
//                    voteAnswer.setVoteAnswerId(answer.getAnswerId());
//                    voteAnswer.setUserId(userService.getAuthorId());
//                    voteAnswerService.insert(voteAnswer);
//                    System.out.println(voteAnswer);
//                } else if (vote.equals("dislike")) {
//
//                    answer.setDownVote(answer.getDownVote() + 1);
//                    answer.setTotalVotes(answer.getUpVote()- answer.getDownVote());
//                    answerService.insert(answer);
//                    voteAnswer.setAnswerId(answer.getAnswerId());
//                    voteAnswer.setUserId(userService.getAuthorId());
//                    voteAnswerService.insert(voteAnswer);
//                    System.out.println(voteAnswer);
//                } else {
//                    print("Try again !");
//                    handleVote();
//                }
//
//            }
//            else{
//                print("Try again !");
//                handleVote();
//            }
//
//        }else{
//            print("Wrong command");
//            handleVote();
//        }
//    }
    private void handleEditAnswer(){
//        if(userService.isUserLog()) {
//            print("Enter ur command:");
//            String command = scanner.next().trim();
//            if(command.equals("delete")){
//                print("Delete comment of question with title :");
//                String title = scanner.next().trim();
//                Question question=questionService.findByTitle(title);
//                print("And ID of comment :");
//                String id = scanner.next().trim();
//                Integer id2=Integer.parseInt(id);
//                System.out.println(question.getQuestionId());
//                Answer answer = answerService.findByIdAndQuestion(id2,question.getQuestionId());
//                if(answer.getUserId().equals(userService.getAuthorId()) || answer.getUserId().equals(question.getAuthor())  ) {
//                    answerService.remove(answer.getAnswerId());
//                }else{
//                    print("U re not the autor or question author");
//                    handleEditAnswer();
//                }
//            }
//            else if(command.equals("edit")){
//                print("Edit comment of question with title :");
//                String title = scanner.next().trim();
//                Question question=questionService.findByTitle(title);
//                print("And ID of comment :");
//                String id = scanner.next().trim();
//                Answer answer = answerService.findByIdAndQuestion(Integer.parseInt(id),question.getQuestionId());
//                if(answer.getUserId().equals(userService.getAuthorId()) || answer.getUserId().equals(question.getAuthor())  ) {
//                    print("Edit text:");
//                    String text = scanner.next().trim();
//                    answer.setBodyAnswer(text);
//                    answerService.insert(answer);
//                }else{
//                    print("U re not the autor or question author");
//                    handleEditAnswer();
//                }
//
//            }else{
//                print("Wrong command");
//                handleEditAnswer();
//            }
//    }else{
//        print(" Must be authenticated !");
//    }

    }

    private void handleSearch(){
//        print("Title of question :");
//        String title = scanner.next().trim();
//        Question question = questionService.findByTitle(title);
//        System.out.println(question);
    }

    private void handleFilter(){
//        print("Filter:");
//        String tagS = scanner.next().trim();
//        Tag tag= tagService.findByName(tagS);
//        if(tag == null){
//            print("Not found this tag !");
//            handleFilter();
//        }
//        List<Integer> integers= tagService.findByIdQT(tag.getId());
//        for(Integer integer1: integers){
//            Question question = questionService.findById(integer1);
//            System.out.println(question.toString2());
//        }
    }
    public void handleAddAnswer(){
        if(userService.isUserLog()) {
            List<Answer> answers= new ArrayList<>();
            print("Title of question:");
            String title = scanner.next().trim();
            QuestionDto question =questionService.findByTitle(title);
            while(true){
                System.out.println("Add an comment:");
                String bodyAnswer= scanner.next().trim();
                System.out.println(bodyAnswer);
                if(bodyAnswer.equals("break")){
                    break;
                }
                Answer answer=new Answer();
                answer.setBodyAnswer(bodyAnswer);
                answer.setDateTime(new Date());
                answer.setQuestionId(question.getQuestionId());
                answer.setUserId(userService.getAuthorId());
                answers.add(answer);
                answerService.insert(AnswerDto.answerDtoFromQuestion(answer));
            }
            question.setAnswers(answers.stream().map(Answer::getBodyAnswer).collect(Collectors.toList()));
            System.out.println(question);
        }
        else{
            print(" Must be authenticated !");
        }

    }
    private void handleInsertUser(){
//        print("Username:");
//        String username = scanner.next().trim();
//        print("Password:");
//        String password = scanner.next().trim();
//        User user = new User(username,password);
//        userService.insert(user);
    }

    private void handleInsert() {
        if(userService.isUserLog()) {
            List<TagDto> tags= new ArrayList<>();
            print("Title:");
            String title = scanner.next().trim();
            print("Body:");
            String body = scanner.next().trim();
            QuestionDto questionI= QuestionDto.questionDtoFromQuestion(new Question(title, body));
            questionService.insert(questionI);
            while(true){
                print("Tag:");
                String tag = scanner.next().trim();
                if(tag.equals("break"))
                    break;
                Optional<TagDto> tag1= Optional.ofNullable(TagDto.tagDtoFromTag(tagService.findByName(tag)));
               if(tag1.isEmpty()){
                    Tag newTag= new Tag();
                    newTag.setName(tag);
                    tagService.insert(TagDto.tagDtoFromTag(newTag));
                    tags.add(TagDto.tagDtoFromTag(newTag));
                }else{
                    tags.add(tag1.get());
                }
            }
            questionI.setDateTime(new Date());
            questionI.setUserId(userService.getAuthorId());
            System.out.println(userService.findUserByUsername(userService.getCurrentUser()).getUserId());
            questionI.setUserId(userService.findUserByUsername(userService.getCurrentUser()).getUserId());
            questionI.setTags(tags.stream().map(TagDto::getName).collect(Collectors.toSet()));
            System.out.println(questionI);
            questionService.insert(questionI);
        }else{
            print(" Must be authenticated !");
        }
    }
    private void handleLogin(){
        print("Username:");
        String username = scanner.next().trim();
        System.out.println(username);
        print("Password:");
        String password= scanner.next().trim();
        print(password);
        userService.login(username,password);
    }
    private void handleLogout(){
        userService.logout();
        print("You are disconnected!");
    }

    private void handleRemove() {
        if(userService.isUserLog()) {
            print("Question ID:");
            int id = scanner.nextInt();
            questionService.remove(id);
        } else{
        print(" Must be authenticated !");
        }
    }

    private void print(String value) {
        System.out.println(value);
    }
}
