package ro.utcn.ps.ono.assignment1.controller;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ro.utcn.ps.ono.assignment1.dto.TagDto;
import ro.utcn.ps.ono.assignment1.dto.UserDto;
import ro.utcn.ps.ono.assignment1.service.TagService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "/tags")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

//    @GetMapping()
//    public List<TagDto> findUsers() {
//        return tagService.findAll();
//    }

    @GetMapping(value = "/{id}")
    public TagDto findTagById(@PathVariable("id") Integer id){
        return tagService.findById(id);
    }

    @GetMapping(value = "/name/{name}")
    public TagDto findTagByName(@PathVariable("name") String username){
        return TagDto.tagDtoFromTag(tagService.findByName(username));
    }

    @PostMapping()
    public TagDto insert(@RequestBody TagDto userDto){
        return tagService.insert(userDto);
    }

    @PutMapping()
    public TagDto update(@RequestBody TagDto userDto) {
        return tagService.insert(userDto);
    }

//    @DeleteMapping(value="/{id}")
//    public void remove(@PathVariable("id") Integer id){
//        userService.remove(id);
//    }
}
