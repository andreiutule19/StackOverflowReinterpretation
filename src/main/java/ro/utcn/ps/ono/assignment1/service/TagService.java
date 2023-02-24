package ro.utcn.ps.ono.assignment1.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.utcn.ps.ono.assignment1.dto.TagDto;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.exception.QuestionNotFoundException;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;



import java.util.List;

@Service
@RequiredArgsConstructor
public class TagService {
    private final RepositoryFactory factory;
    private final ApplicationEventPublisher eventPublisher;
    @Transactional
    public TagDto findById(Integer id) {
        return TagDto.tagDtoFromTag(factory.createTagRepository().findById(id).orElseThrow(QuestionNotFoundException::new));
    }

    @Transactional
    public List<Integer> findByIdQT(Integer id) {
        return factory.createTagRepository().findTagByQuestion_question_id(id);
    }

    private TagDto getTagDto(TagDto tagDto) {
        Tag tag = new Tag();
        tag.setId(tagDto.getId());
        tag.setName(tagDto.getName());
        TagDto output = TagDto.tagDtoFromTag(factory.createTagRepository().save(tag));
       // eventPublisher.publishEvent(new TagCreatedEvent(output));
        return output;
    }
    @Transactional
    public Tag findByName(String name) {
        return factory.createTagRepository().findByName(name).orElse(null);
    }

    @Transactional
    public TagDto insert(TagDto tagDto) {
        return getTagDto(tagDto);
    }

    @Transactional
    public TagDto update(TagDto tagDto) {
        return getTagDto(tagDto);
    }

}
