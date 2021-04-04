package com.fun.club.auth.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUserDetails is a Querydsl query type for UserDetails
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUserDetails extends EntityPathBase<UserDetails> {

    private static final long serialVersionUID = -1399057716L;

    public static final QUserDetails userDetails = new QUserDetails("userDetails");

    public final BooleanPath isDefaultPasswordReset = createBoolean("isDefaultPasswordReset");

    public final StringPath password = createString("password");

    public final StringPath role = createString("role");

    public final StringPath username = createString("username");

    public QUserDetails(String variable) {
        super(UserDetails.class, forVariable(variable));
    }

    public QUserDetails(Path<? extends UserDetails> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUserDetails(PathMetadata metadata) {
        super(UserDetails.class, metadata);
    }

}

