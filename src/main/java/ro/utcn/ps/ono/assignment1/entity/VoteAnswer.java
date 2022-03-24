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
    private Integer answerId;
    private Integer userId;
}
