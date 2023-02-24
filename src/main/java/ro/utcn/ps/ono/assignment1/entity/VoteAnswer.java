package ro.utcn.ps.ono.assignment1.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
@AllArgsConstructor
@Entity
@Table(name = "vote_answers")
public class VoteAnswer {
    @Id
    @Column(name ="vote_answer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer voteAnswerId;
    @Column(name="answer_id")
    private Integer answerId;
    @Column(name="user_id")
    private Integer userId;
    private Boolean liked;




}
