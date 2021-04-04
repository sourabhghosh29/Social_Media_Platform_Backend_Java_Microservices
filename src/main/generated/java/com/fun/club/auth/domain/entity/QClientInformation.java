package com.fun.club.auth.domain.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QClientInformation is a Querydsl query type for ClientInformation
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QClientInformation extends EntityPathBase<ClientInformation> {

    private static final long serialVersionUID = 925857654L;

    public static final QClientInformation clientInformation = new QClientInformation("clientInformation");

    public final NumberPath<Integer> accessTokenValidity = createNumber("accessTokenValidity", Integer.class);

    public final StringPath clientId = createString("clientId");

    public final StringPath clientScope = createString("clientScope");

    public final StringPath clientSecret = createString("clientSecret");

    public final StringPath grantType = createString("grantType");

    public final NumberPath<Integer> refreshTokenValidity = createNumber("refreshTokenValidity", Integer.class);

    public QClientInformation(String variable) {
        super(ClientInformation.class, forVariable(variable));
    }

    public QClientInformation(Path<? extends ClientInformation> path) {
        super(path.getType(), path.getMetadata());
    }

    public QClientInformation(PathMetadata metadata) {
        super(ClientInformation.class, metadata);
    }

}

