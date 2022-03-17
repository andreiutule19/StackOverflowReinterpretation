package ro.utcn.ps.ono.assignment1.entity;
import lombok.*;
import javax.persistence.*;
import java.sql.Date;


@Getter
@Setter
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

    private Integer questionId;
    private Integer userId;


    public Answer(String bodyAnswer) {
        this.bodyAnswer = bodyAnswer;
    }
}