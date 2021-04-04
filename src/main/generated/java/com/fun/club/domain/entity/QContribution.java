package com.fun.club.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QContribution is a Querydsl query type for Contribution
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QContribution extends EntityPathBase<Contribution> {

    private static final long serialVersionUID = 1752847885L;

    public static final QContribution contribution = new QContribution("contribution");

    public final StringPath comment = createString("comment");

    public final NumberPath<Long> contributionAmount = createNumber("contributionAmount", Long.class);

    public final DateTimePath<java.util.Date> contributionDate = createDateTime("contributionDate", java.util.Date.class);

    public final StringPath employeeId = createString("employeeId");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public QContribution(String variable) {
        super(Contribution.class, forVariable(variable));
    }

    public QContribution(Path<? extends Contribution> path) {
        super(path.getType(), path.getMetadata());
    }

    public QContribution(PathMetadata metadata) {
        super(Contribution.class, metadata);
    }

}

