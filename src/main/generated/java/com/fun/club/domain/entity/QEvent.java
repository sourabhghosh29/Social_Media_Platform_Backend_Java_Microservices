package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QEvent is a Querydsl query type for Event
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QEvent extends EntityPathBase<Event> {

    private static final long serialVersionUID = -512535747L;

    public static final QEvent event = new QEvent("event");

    public final StringPath description = createString("description");

    public final StringPath employeeId = createString("employeeId");

    public final DateTimePath<java.util.Date> eventDate = createDateTime("eventDate", java.util.Date.class);

    public final ArrayPath<byte[], Byte> eventDocument = createArray("eventDocument", byte[].class);

    public final StringPath eventName = createString("eventName");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> organizerId = createNumber("organizerId", Long.class);

    public QEvent(String variable) {
        super(Event.class, forVariable(variable));
    }

    public QEvent(Path<? extends Event> path) {
        super(path.getType(), path.getMetadata());
    }

    public QEvent(PathMetadata metadata) {
        super(Event.class, metadata);
    }

}

