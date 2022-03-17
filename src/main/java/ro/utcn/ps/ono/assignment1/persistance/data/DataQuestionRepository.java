package ro.utcn.ps.ono.assignment1.persistance.data;
import org.springframework.data.repository.Repository;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.persistance.api.QuestionRepository;


// Spring Data JPA automatically implements this interface (because it extends the Repository interface)
// and generates the queries based on the method names
// check out https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.core-concepts
public interface DataQuestionRepository extends QuestionRepository, Repository<Question, Integer> {

    void delete(Question question);

    // needed due to different naming (Spring Data JPA uses delete, we defined a remove method instead).
    @Override
    default void remove(Question question) {
        delete(question);
    }
}
