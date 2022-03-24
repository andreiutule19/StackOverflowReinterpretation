package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;
import ro.utcn.ps.ono.assignment1.entity.Question;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.UserRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateUserRepository implements UserRepository {
    private final EntityManager entityManager;

    @Override
    public User save(User user) {
        if (user.getUserId() != null) {
            // update
            entityManager.merge(user);
        } else {
            // insert
            entityManager.persist(user);
        }

        return user;
    }


    @Override
    public void remove(User user) {

    }


    @Override
    public List<User> findAll() {
        // the criteria builder is used to create a type-safe query; an alternative would have been
        // to write a JPQL query instead ("SELECT s FROM Student s") or to use named queries
        // https://docs.jboss.org/hibernate/entitymanager/3.5/reference/en/html/querycriteria.html
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> query = builder.createQuery(User.class);
        query.select(query.from(User.class));

        return entityManager.createQuery(query).getResultList();
    }






    @Override
    public Optional<User> findUserByUserId(Integer id) {
        return Optional.ofNullable(entityManager.find(User.class, id));
    }


    @Override
    public Optional<User> findUserByUsername(String username)
    {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();

        CriteriaQuery<User> cr = cb.createQuery(User.class);
        Root<User> root = cr.from(User.class);
        cr.select(root).where(cb.equal(root.get("username"), username));
        Query query = entityManager.createQuery(cr);
        query.setMaxResults(1);
        List<User> resultList = query.getResultList();

        return  Optional.of(resultList.get(0));
    }



}