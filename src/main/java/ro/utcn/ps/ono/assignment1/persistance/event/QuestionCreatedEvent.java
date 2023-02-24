package ro.utcn.ps.ono.assignment1.persistance.event;
import lombok.Data;
import ro.utcn.ps.ono.assignment1.dto.QuestionDto;


@Data
public class QuestionCreatedEvent extends BaseEvent {
    private final QuestionDto question;

    public QuestionCreatedEvent(QuestionDto question) {
        super(EventType.QUESTION_CREATED);
        this.question=question;
    }
}
