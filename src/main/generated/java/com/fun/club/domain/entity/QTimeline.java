package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QTimeline is a Querydsl query type for Timeline
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QTimeline extends EntityPathBase<Timeline> {

    private static final long serialVersionUID = 1596591294L;

    public static final QTimeline timeline = new QTimeline("timeline");

    public final NumberPath<Long> commentCount = createNumber("commentCount", Long.class);

    public final NumberPath<Long> dislikeCount = createNumber("dislikeCount", Long.class);

    public final ArrayPath<byte[], Byte> document = createArray("document", byte[].class);

    public final StringPath employeeId = createString("employeeId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> likeCount = createNumber("likeCount", Long.class);

    public final StringPath suggestion = createString("suggestion");

    public final DateTimePath<java.util.Date> suggestionTime = createDateTime("suggestionTime", java.util.Date.class);

    public QTimeline(String variable) {
        super(Timeline.class, forVariable(variable));
    }

    public QTimeline(Path<? extends Timeline> path) {
        super(path.getType(), path.getMetadata());
    }

    public QTimeline(PathMetadata metadata) {
        super(Timeline.class, metadata);
    }

}

