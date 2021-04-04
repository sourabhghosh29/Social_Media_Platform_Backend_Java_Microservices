package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QFeedResponse is a Querydsl query type for FeedResponse
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QFeedResponse extends EntityPathBase<FeedResponse> {

    private static final long serialVersionUID = 549425660L;

    public static final QFeedResponse feedResponse = new QFeedResponse("feedResponse");

    public final BooleanPath dislikeFeed = createBoolean("dislikeFeed");

    public final StringPath employeeId = createString("employeeId");

    public final NumberPath<Long> feedId = createNumber("feedId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath likeFeed = createBoolean("likeFeed");

    public QFeedResponse(String variable) {
        super(FeedResponse.class, forVariable(variable));
    }

    public QFeedResponse(Path<? extends FeedResponse> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFeedResponse(PathMetadata metadata) {
        super(FeedResponse.class, metadata);
    }

}

