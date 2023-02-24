package ro.utcn.ps.ono.assignment1.persistance.event;

import lombok.Data;

@Data
public class BaseEvent {
    private final EventType type;
}
