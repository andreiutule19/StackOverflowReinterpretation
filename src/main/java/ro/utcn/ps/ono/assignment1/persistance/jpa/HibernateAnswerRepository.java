package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.persistance.api.AnswerRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
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
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Answer> cr = cb.createQuery(Answer.class);
        cr.select(cr.from(Answer.class));
        return entityManager.createQuery(cr).getResultList();
    }

    @Override
    public List<Answer> findByQuestionId( Integer questionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Answer> cr = cb.createQuery(Answer.class);
        Root<Answer> root = cr.from(Answer.class);
        cr.select(root).where(cb.equal(root.get("questionId"), questionId));
        Query query = entityManager.createQuery(cr);
        List<Answer> resultList = query.getResultList();
        return  resultList;
    }

}
