package ro.utcn.ps.ono.assignment1.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Question {
    @Id
    @Column(name ="question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer questionId;
    private String title;
    private String body;
    private int author;
    private Date date;

   @ManyToMany(fetch = FetchType.EAGER)
   @JoinTable(name = "question_tags")
   private List<Tag> tags;

    public Question(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public Question(Integer integer,String title, String body, Integer author,Date date) {
        this.title = title;
        this.body = body;
        this.date =date;
        this.author=author;
        this.questionId=integer;
    }
    @OneToMany
    @JoinTable
    private List<Answer> answers;
}
