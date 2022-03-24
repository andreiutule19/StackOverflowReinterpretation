package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.entity.VoteQuestion;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteQuestionRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
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
    public Optional<VoteQuestion> findByVoteQuestionId(Integer userId) {
        return Optional.ofNullable(entityManager.find(VoteQuestion.class, userId));
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
