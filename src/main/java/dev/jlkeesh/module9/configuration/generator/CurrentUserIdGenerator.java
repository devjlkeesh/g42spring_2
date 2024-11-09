package dev.jlkeesh.module9.configuration.generator;

import dev.jlkeesh.module9.configuration.annotation.CurrentUserId;
import dev.jlkeesh.module9.configuration.security.UserSession;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.generator.BeforeExecutionGenerator;
import org.hibernate.generator.EventType;

import java.util.EnumSet;

public class CurrentUserIdGenerator implements BeforeExecutionGenerator {
    private final EnumSet<EventType> eventTypes;

    public CurrentUserIdGenerator(CurrentUserId annotation) {
        EventType[] events = annotation.event();
        if (events.length == 2)
            eventTypes = EnumSet.of(events[0], events[1]);
        else
            eventTypes = EnumSet.of(events[0]);
    }

    @Override
    public EnumSet<EventType> getEventTypes() {
        return eventTypes;
    }

    @Override
    public Object generate(SharedSessionContractImplementor session, Object owner, Object currentValue, EventType eventType) {
        return UserSession.requireUserId();
    }
}