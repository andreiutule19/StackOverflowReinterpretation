package ro.utcn.ps.ono.assignment1.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Getter
@Setter
@EqualsAndHashCode
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="answers")
public class Answer {
    @Id
    @Column(name ="answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer answerId;
    private String bodyAnswer;
    private Date dateTime;
    @Column(name ="question_id")
    private Integer questionId;
    @Column(name ="user_id")
    private Integer userId;


    @OneToMany(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="answer_id")
    List<VoteAnswer> votes;




    @Column(columnDefinition ="int default 0")
    private Integer totalVotes= 0;



    public Answer(String bodyAnswer) {
        this.bodyAnswer = bodyAnswer;
    }

    public Answer(int question_id, String body_answer, java.sql.Date date, int my_question, int user_id, int total_votes) {
    }
}