package ro.utcn.ps.ono.assignment1.persistance.api;

/*
    this is a functional interface at the moment
    functional interfaces were introduced from java 8 and only have one method
    see https://www.geeksforgeeks.org/functional-interfaces-java/?ref=gcse
*/
//@FunctionalInterface
public interface RepositoryFactory {

    QuestionRepository createQuestionRepository();
    UserRepository createUserRepository();
    TagRepository createTagRepository();
    AnswerRepository createAnswerRepository();
}
