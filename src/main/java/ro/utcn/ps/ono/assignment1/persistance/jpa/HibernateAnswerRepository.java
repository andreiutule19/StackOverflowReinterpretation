package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateAnswerRepository implements AnswerRepository {
    private final EntityManager entityManager;
    @Override
    public Answer save(Answer answer) {
        if (answer.getAnswerId() != null) {
            // update
            entityManager.merge(answer);
        } else {
            // insert
            entityManager.persist(answer);
        }

        return answer;
    }

    @Override
    public Optional<Answer> findById(int id) {
        return Optional.ofNullable(entityManager.find(Answer.class, id));
    }

    @Override
    public void remove(Answer answer) {
        entityManager.remove(answer);
    }



    @Override
    public List<Answer> findAll() {
        String queryString = "SELECT a FROM Answer a " +
                "order by a.totalVotes ";

        return entityManager.createQuery(queryString, Answer.class).getResultList();
    }

    @Override
    public Answer findByAnswerIdAndMyQuestion(Integer answerId, Integer myQuestion) {
        String queryString = "SELECT a FROM Answer a " +
                "WHERE a.answerId = :answerId AND a.myQuestion = :myQuestion";

        return entityManager.createQuery(queryString,Answer.class)
                .setParameter("answerId", answerId)
                .setParameter("myQuestion",myQuestion).getResultList().get(0);
    }

}
