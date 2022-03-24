package ro.utcn.ps.ono.assignment1.entity;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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
    private Integer author;
    private Date date;

    @Column(columnDefinition ="int default 0")
    private Integer upVote=new Integer(0);
    @Column(columnDefinition ="int default 0")
    private Integer downVote=new Integer(0);
    private Integer totalVotes=upVote.intValue()-downVote.intValue();

   @ManyToMany
   @JoinTable(name = "question_tags")
   @ToString.Exclude
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

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private List<Answer> answers;


    public String toString2(){
        return "Question = : " + this.getQuestionId()+ " Title = "+this.getTitle()+" Body= "+ this.getBody() +" Tag-uri = "+this.getTags();
    }
}
