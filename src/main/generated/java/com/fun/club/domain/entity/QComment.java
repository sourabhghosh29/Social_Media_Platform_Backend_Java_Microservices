package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QComment is a Querydsl query type for Comment
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QComment extends EntityPathBase<Comment> {

    private static final long serialVersionUID = -593677758L;

    public static final QComment comment1 = new QComment("comment1");

    public final StringPath comment = createString("comment");

    public final DateTimePath<java.util.Date> commentTime = createDateTime("commentTime", java.util.Date.class);

    public final StringPath employeeId = createString("employeeId");

    public final NumberPath<Long> feedId = createNumber("feedId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QComment(String variable) {
        super(Comment.class, forVariable(variable));
    }

    public QComment(Path<? extends Comment> path) {
        super(path.getType(), path.getMetadata());
    }

    public QComment(PathMetadata metadata) {
        super(Comment.class, metadata);
    }

}

