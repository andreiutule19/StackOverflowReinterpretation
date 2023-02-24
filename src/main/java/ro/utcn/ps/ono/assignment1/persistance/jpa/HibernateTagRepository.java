package ro.utcn.ps.ono.assignment1.persistance.jpa;

import lombok.AllArgsConstructor;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.persistance.api.TagRepository;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class HibernateTagRepository implements TagRepository {
    private final EntityManager entityManager;
    @Override
    public Tag save(Tag tag) {
        if (tag.getId() != null) {
            // update
            entityManager.merge(tag);
        } else {
            // insert
            entityManager.persist(tag);
        }

        return tag;
    }

    @Override
    public Optional<Tag> findById(int id) {
        return Optional.ofNullable(entityManager.find(Tag.class, id));
    }

    @Override
    public Optional<Tag> findByName(String name) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> cr = cb.createQuery(Tag.class);
        Root<Tag> root = cr.from(Tag.class);
        cr.select(root).where(cb.equal(root.get("name"), name));
        Query query = entityManager.createQuery(cr);
        List<Tag> resultList = query.getResultList();
        query.setMaxResults(1);
        if(resultList.isEmpty()){
            return  Optional.empty();
        }
        return  Optional.of(resultList.get(0));
    }

    @Override
    public List findTagByQuestion_question_id(int id) {
//        String query ="SELECT q.questionId FROM Question q INNER JOIN Tag t ON q.questionId = t.questionId  WHERE t.id LIKE : id";
//
//        return entityManager.createQuery(query).setParameter("id", id).getResultList();

        return null;
    }


    @Override
    public List<Tag> findAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Tag> query = builder.createQuery(Tag.class);
        query.select(query.from(Tag.class));
        return entityManager.createQuery(query).getResultList();
    }

    @Override
    public void remove(Tag tag) {

    }


}
