package ro.utcn.ps.ono.assignment1.entity;
import lombok.*;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @Column(name ="userId")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userId;
    private String username;
    private String password;


    public User( String username, String password){
        this.username= username;
        this.password= password;
    }

    @OneToMany
    private List<Answer> answerList;

    @OneToMany
    @JoinColumn
    private List<Question> questionList;

    public User(int user_id, String password, String username) {
        this.username= username;
        this.password= password;
        this.userId=user_id;
    }
}