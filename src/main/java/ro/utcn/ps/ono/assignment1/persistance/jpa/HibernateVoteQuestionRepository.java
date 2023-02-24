package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteQuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateVoteQuestionRepository implements VoteQuestionRepository {
    private final EntityManager entityManager;

    @Override
    public List<VoteQuestion> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<VoteQuestion> query = builder.createQuery(VoteQuestion.class);
        query.select(query.from(VoteQuestion.class));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public VoteQuestion save(VoteQuestion voteQuestion) {
        if(voteQuestion.getVoteQuestionId() != null) {
            // update
            entityManager.merge(voteQuestion);
        } else {
            // insert
            entityManager.persist(voteQuestion);
        }

        return voteQuestion;
    }

    @Override
    public void remove(VoteQuestion voteQuestion) {
        entityManager.remove(voteQuestion);
    }

    @Override
    public Optional<VoteQuestion> findByVoteQuestionId(Integer voteQuestionId) {
        return Optional.ofNullable(entityManager.find(VoteQuestion.class, voteQuestionId));
    }

    @Override
    public List<VoteQuestion> findByQuestionId(Integer questionId) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<VoteQuestion> cr = cb.createQuery(VoteQuestion.class);
        Root<VoteQuestion> root = cr.from(VoteQuestion.class);
        cr.select(root).where(cb.equal(root.get("questionId"), questionId));
        Query query = entityManager.createQuery(cr);
        List<VoteQuestion> resultList = query.getResultList();
        return  resultList;
    }

    @Override
    public Optional<VoteQuestion> findByQuestionIdAndUserId(Integer userId, Integer questionId) {
        String queryString = "SELECT v FROM VoteQuestion v " +
                "WHERE v.questionId = :questionId AND v.userId = :userId";

        return entityManager.createQuery(queryString, VoteQuestion.class)
                .setParameter("questionId", questionId)
                .setParameter("userId", userId).getResultList().stream().findFirst();

    }
}
