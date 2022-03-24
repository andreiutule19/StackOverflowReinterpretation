package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.RequiredArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.persistance.api.QuestionRepository;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
public class HibernateQuestionRepository implements QuestionRepository {
    private final EntityManager entityManager;

    @Override
    public Question save(Question question) {
        if(question.getQuestionId() != null) {
            // update
            entityManager.merge(question);
        } else {
            // insert
            entityManager.persist(question);
        }

        return question;
    }

    @Override
    public Optional<Question> findById(int id) {
        return Optional.ofNullable(entityManager.find(Question.class, id));
    }

    @Override
    public void remove(Question question) {
        entityManager.remove(question);
    }

    @Override
    public Optional<Question> findByTitle(String title) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<Question> cr = cb.createQuery(Question.class);
        Root<Question> root = cr.from(Question.class);
        cr.select(root).where(cb.equal(root.get("title"), title));
        Query query = entityManager.createQuery(cr);
        query.setMaxResults(1);
        List<Question> resultList = query.getResultList();

        return  Optional.of(resultList.get(0));
    }

    @Override
    public List<Question> findAll() {
        // the criteria builder is used to create a type-safe query; an alternative would have been
        // to write a JPQL query instead ("SELECT s FROM Student s") or to use named queries
        // https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
        String queryString = "SELECT q FROM Question q " +
                "order by q.date,q.totalVotes ";

        return entityManager.createQuery(queryString, Question.class).getResultList();
    }
}
