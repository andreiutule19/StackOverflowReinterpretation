package ro.utcn.ps.ono.assignment1.entity;

import lombok.*;
import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@ToString
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
    @Column(name="user_id")
    private Integer userId;
    private Date date;

    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="question_id")
    List<VoteQuestion> votes;

    @Column(columnDefinition ="int default 0")
    private Integer totalVotes;

   @ManyToMany(cascade = {CascadeType.PERSIST})
   @JoinTable(name = "question_tags",joinColumns=@JoinColumn(name="question_id"),
           inverseJoinColumns =@JoinColumn(name="tag_id")
   )
   @ToString.Exclude
   private List<Tag> tags;


   @OneToMany(fetch = FetchType.EAGER,cascade = {CascadeType.PERSIST})
   @JoinColumn(name = "question_id")
   private List<Answer> answers=new ArrayList<>();


    public Question(String title, String body) {
        this.title = title;
        this.body = body;
    }
    public Question(Integer integer,String title, String body, Integer userId,Date date) {
        this.title = title;
        this.body = body;
        this.date =date;
        this.userId=userId;
        this.questionId=integer;
    }


    public String toString2(){
        return "Question = : " + this.getQuestionId()+ " Title = "+this.getTitle()+" Body= "+ this.getBody() +" Tag-uri = "+this.getTags();
    }
}
