package ro.utcn.ps.ono.assignment1.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.service.QuestionService;
import ro.utcn.ps.ono.assignment1.service.TagService;
import ro.utcn.ps.ono.assignment1.service.UserServices;
import java.util.*;

@Component
@RequiredArgsConstructor
// Command line runners are executed by Spring after the initialization of the app has been done
// https://www.baeldung.com/spring-boot-console-app
public class ConsoleController implements CommandLineRunner {
    private final Scanner scanner = new Scanner(System.in);
    private final QuestionService questionService;
    private final UserServices userService;
    private final TagService tagService;

    @Override
    public void run(String... args) {
        print("Welcome to my awesome reinterpretation of StackOverflow.");
        boolean done = false;
        while (!done) {
            print("Enter a command: ");
            String command = scanner.next().trim();
            try {
                done = handleCommand(command);
            } catch (QuestionNotFoundException questionNotFoundException) {
                print("Not found!");
            }
        }
    }

    private boolean handleCommand(String command) {
        switch (command) {
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
                handleFiltter();
                return false;
            case "exit":
                return true;
            default:
                print("Unknown command. Try again.");
                return false;
        }
    }

    private void handleList() {
        questionService.findAll().forEach(question -> print(question.toString()));
    }

    private void handleSearch(){
        print("Search:");
        String title = scanner.next().trim();
        Question question = questionService.findByTitle(title);
        System.out.println(question.toString());
    }

    private void handleFiltter(){
        print("Filtter:");
        String tagS = scanner.next().trim();
        Tag tag= tagService.findByName(tagS);
        List<Integer> integers= tagService.findByIdQT(tag.getId());
        for(Integer integer1: integers){
            Question question = questionService.findById(integer1);
            System.out.println(question.toString());
        }
    }
    private void handleInsert() {
        if(userService.isUserLog()) {
            List<Tag> tags= new ArrayList<>();
            print("Title:");
            String title = scanner.next().trim();
            print("Body:");
            String body = scanner.next().trim();
           while(true){
            print("Tag:");
                String tag = scanner.next().trim();
                if(tag.equals("stop"))
                    break;
                Optional<Tag> tag1= Optional.ofNullable(tagService.findByName(tag));
               if(tag1.isEmpty()){
                    Tag newTag= new Tag();
                    newTag.setName(tag);
                    tagService.insert(newTag);
                    tags.add(newTag);
                }else{
                    tags.add(tag1.get());
                }
            }
            Question questionI= new Question(title, body);
            questionI.setDate(new Date());
            System.out.println(userService.findUserByUsername(userService.getCurrentUser()).getUser_id());
            questionI.setAuthor(userService.findUserByUsername(userService.getCurrentUser()).getUser_id());
            questionI.setTags(tags);
            System.out.println(questionI.toString());
            questionService.insert(questionI);
        }else{
            print(" Trebuie sa fii autentificat!");
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
        print("Te-ai deconectat!");
    }

    private void handleRemove() {
        if(userService.isUserLog()) {
            print("Question ID:");
            int id = scanner.nextInt();
            questionService.remove(id);
        } else{
        print(" Trebuie sa fii autentificat!");
        }
    }

    private void print(String value) {
        System.out.println(value);
    }
}
