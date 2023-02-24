package ro.utcn.ps.ono.assignment1.dto;

import lombok.Data;
import ro.utcn.ps.ono.assignment1.entity.Tag;


@Data
public class TagDto {
    private Integer id;
    private String name;

    public static TagDto tagDtoFromTag(Tag tag){
        TagDto tagDto=new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }
}
