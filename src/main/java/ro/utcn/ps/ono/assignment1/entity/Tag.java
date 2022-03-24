package ro.utcn.ps.ono.assignment1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Tag {
    @Id
    @Column(name ="tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;




    public Tag(String name){
        this.name=name;
    }

    public Tag(int tag_id, String name) {
        this.name=name;
        this.id=tag_id;
    }
}
