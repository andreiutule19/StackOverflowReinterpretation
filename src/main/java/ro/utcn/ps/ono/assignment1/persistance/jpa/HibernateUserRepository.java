package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {
    private final EntityManager entityManager;

    @Override
    public User save(User user) {
        if (user.getUser_id() != null) {
            // update
            entityManager.merge(user);
        } else {
            // insert
            entityManager.persist(user);
        }

        return user;
    }

    @Override
    public List<User> list() {
        // the criteria builder is used to create a type-safe query; an alternative would have been
        // to write a JPQL query instead ("SELECT s FROM Student s") or to use named queries
        // https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.select(query.from(User.class));

        return entityManager.createQuery(query).getResultList();
    }



    @Override
    public Optional<User> findUserByUsername(String username) {
        return Optional.ofNullable(entityManager.find(User.class, username));
    }



    @Override
    public Optional<User> findUserByUser_id(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }







}