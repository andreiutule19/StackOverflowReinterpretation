package ro.utcn.ps.ono.assignment1.service;
import org.junit.jupiter.api.Test;
import ro.utcn.ps.ono.assignment1.entity.Tag;
import ro.utcn.ps.ono.assignment1.entity.User;
import ro.utcn.ps.ono.assignment1.persistance.api.RepositoryFactory;
import ro.utcn.ps.ono.assignment1.persistance.memory.InMemoryRepositoryFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;
public class TagServiceTests {

    private RepositoryFactory createMockedFactory() {
    RepositoryFactory factory = new InMemoryRepositoryFactory();
    factory.createTagRepository().save(new Tag(1,"C++"));
    factory.createTagRepository().save(new Tag( 2,"Java"));
    return factory;
}
    @Test
    public void findTagById(){
        RepositoryFactory factory= createMockedFactory();
        Tag tag1=new Tag(3,"Haskell");
        factory.createTagRepository().save(tag1);
        Tag foundTag=factory.createTagRepository().findById(3).get();
        assertEquals(foundTag, tag1);
    }


}
