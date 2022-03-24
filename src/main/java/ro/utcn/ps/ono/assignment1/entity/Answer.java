package ro.utcn.ps.ono.assignment1.entity;
import lombok.*;
import javax.persistence.*;
import java.util.Date;


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
    private Integer myQuestion;
    private Integer userId;

    @Column(columnDefinition ="int default 0")
    private Integer upVote = 0;
    @Column(columnDefinition ="int default 0")
    private Integer downVote = 0;

    @Column(columnDefinition ="int default 0")
    private Integer totalVotes= 0;



    public Answer(String bodyAnswer) {
        this.bodyAnswer = bodyAnswer;
    }
}