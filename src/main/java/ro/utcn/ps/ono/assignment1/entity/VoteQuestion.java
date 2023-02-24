package ro.utcn.ps.ono.assignment1.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "vote_questions")
public class VoteQuestion {
    @Id
    @Column(name ="vote_question_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteQuestionId;
    @Column(name="question_id")
    private Integer questionId;
    @Column(name="user_id")
    private Integer userId;
    private Boolean liked;
}

