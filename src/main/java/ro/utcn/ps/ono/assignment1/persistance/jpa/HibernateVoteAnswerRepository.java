package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.Answer;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.entity.VoteAnswer;
import ro.utcn.ps.ono.assignment1.persistance.api.VoteAnswerRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateVoteAnswerRepository implements VoteAnswerRepository {
    private final EntityManager entityManager;
    @Override
    public List<VoteAnswer> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<VoteAnswer> query = builder.createQuery(VoteAnswer.class);
        query.select(query.from(VoteAnswer.class));

        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public VoteAnswer save(VoteAnswer voteAnswer) {
        if(voteAnswer.getVoteAnswerId() != null) {
            // update
            entityManager.merge(voteAnswer);
        } else {
            // insert
            entityManager.persist(voteAnswer);
        }

        return voteAnswer;
    }

    @Override
    public void remove(VoteAnswer voteAnswer) {
        entityManager.remove(voteAnswer);
    }

    @Override
    public Optional<VoteAnswer> findByVoteAnswerId(Integer userId) {

        return Optional.ofNullable(entityManager.find(VoteAnswer.class, userId));
    }

    @Override
    public Optional<VoteAnswer> findByAnswerIdAndUserId(Integer userId, Integer answerId) {
        String queryString = "SELECT v FROM VoteAnswer v " +
                "WHERE v.answerId = :answerId AND v.userId = :userId";

        return entityManager.createQuery(queryString, VoteAnswer.class)
                .setParameter("answerId", answerId)
                .setParameter("userId", userId).getResultList().stream().findFirst();
    }
}
