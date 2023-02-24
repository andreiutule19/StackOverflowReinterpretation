package ro.utcn.ps.ono.assignment1.entity;
import lombok.*;

import javax.persistence.*;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name ="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<Answer> answerList;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<Question> questionList;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<VoteQuestion> voteQuestion;

    @OneToMany
    @JoinColumn(name="user_id")
    private List<VoteAnswer> voteAnswer;



    public User( String username, String password){
        this.username= username;
        this.password= password;
    }

    public User(int user_id, String password, String username) {
        this.username= username;
        this.password= password;
        this.userId=user_id;
    }
}